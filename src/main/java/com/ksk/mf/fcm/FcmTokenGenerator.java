package com.ksk.mf.fcm;

import checkin_proto.AndroidCheckin;
import checkin_proto.Checkin;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.security.*;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.ECPoint;
import java.util.Base64;
import java.util.UUID;

public class FcmTokenGenerator {

    public String getFCMToken() {
        try {
            Checkin.AndroidCheckinRequest requestProto = getAndroidCheckinRequest();
            byte[] checkinResponse = getResponseFromCheckin(requestProto);
            Checkin.AndroidCheckinResponse response = Checkin.AndroidCheckinResponse.parseFrom(checkinResponse);
            return getFCMToken(getResponseFromC2DM(response.getAndroidId(), response.getSecurityToken()), getTokenFromInstall());
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | IOException e) {
            throw new FcmTokenGenerationException("FCM token 생성 중 오류", e);
        }
    }

    private Checkin.AndroidCheckinRequest getAndroidCheckinRequest() {
        return Checkin.AndroidCheckinRequest.newBuilder()
                .setDigest("")
                .setCheckin(
                        AndroidCheckin.AndroidCheckinProto.newBuilder()
                                .setLastCheckinMsec(0)
                                .setCellOperator("")
                                .setSimOperator("")
                                .setRoaming("")
                                .setUserNumber(0)
                                .setType(AndroidCheckin.DeviceType.DEVICE_CHROME_BROWSER)
                                .setChromeBuild(
                                        AndroidCheckin.ChromeBuildProto.newBuilder()
                                                .setPlatform(AndroidCheckin.ChromeBuildProto.Platform.PLATFORM_MAC)
                                                .setChromeVersion("94.0.4606.51")
                                                .setChannel(AndroidCheckin.ChromeBuildProto.Channel.CHANNEL_STABLE)))
                .setDesiredBuild("")
                .setLocale("")
                .setLoggingId(0)
                .setMarketCheckin("")
                .setTimeZone("Europe/Prague")
                .setVersion(3)
                .setFragment(0)
                .setUserName("")
                .setUserSerialNumber(0).build();
    }

    private byte[] getResponseFromCheckin(Checkin.AndroidCheckinRequest request) throws IOException {
        URL obj = new URL("https://android.clients.google.com/checkin");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-protobuf");
        conn.setDoOutput(true);
        conn.getOutputStream().write(request.toByteArray());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (InputStream in = conn.getInputStream()) {
            byte[] array = new byte[1024];
            int len;
            while ((len = in.read(array)) != -1) {
                out.write(array, 0, len);
            }
        }
        return out.toByteArray();
    }

    private String getResponseFromC2DM(long androidId, long securityToken) throws IOException {
        String aidLogin = "AidLogin " + androidId + ':' + securityToken;
        URL obj = new URL("https://android.clients.google.com/c2dm/register3");
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", aidLogin);
        conn.setDoOutput(true);
        String sb = URLEncoder.encode("app", "UTF-8") + '=' + URLEncoder.encode("org.chromium.linux", "UTF-8") +
                '&' +
                URLEncoder.encode("X-subtype", "UTF-8") + '=' +
                URLEncoder.encode("wp:receiver.push.com#", "UTF-8") +
                URLEncoder.encode(UUID.randomUUID().toString(), "UTF-8") +
                '&' +
                URLEncoder.encode("device", "UTF-8") + '=' + URLEncoder.encode(String.valueOf(androidId), "UTF-8") +
                '&' +
                URLEncoder.encode("sender", "UTF-8") + '=' +
                URLEncoder.encode(System.getenv("SENDER"), "UTF-8");
        conn.getOutputStream().write(sb.getBytes());
        String response;
        try (InputStream in = conn.getInputStream()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] array = new byte[1024];
            int len;
            while ((len = in.read(array)) != -1) {
                out.write(array, 0, len);
            }
            response = out.toString();
        }
        return response.split("=")[1];
    }

    private String getTokenFromInstall() throws IOException {
        URL obj = new URL(System.getenv("FIREBASE_INSTALL_URL"));
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("x-firebase-client", System.getenv("FIRE_BASE_CLIENT"));
        conn.setRequestProperty("x-goog-api-key", System.getenv("GOOGLE_API_KEY"));
        conn.setRequestProperty("User-Agent", "undici");
        conn.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
        conn.setDoOutput(true);
        JsonObject object = new JsonObject();
        object.addProperty("appId", System.getenv("FIRE_BASE_APP_ID"));
        object.addProperty("authVersion", "FIS_v2");
        object.addProperty("fid", new RandomFidGenerator().createRandomFid());
        object.addProperty("sdkVersion", "w:0.6.6");
        conn.getOutputStream().write(object.toString().getBytes());
        String response;
        try (InputStream in = conn.getInputStream()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] array = new byte[1024];
            int len;
            while ((len = in.read(array)) != -1) {
                out.write(array, 0, len);
            }
            response = out.toString();
        }
        JsonObject element = JsonParser.parseString(response).getAsJsonObject();
        return element.get("authToken").getAsJsonObject().get("token").getAsString();
    }

    private String getFCMToken(String registerToken, String installToken) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException, IOException {
        KeyPair pair = generateKeyPair();
        String publicKey = encodePublicKey((ECPublicKey) pair.getPublic());
        String authSecret = generateAuthSecret();
        URL obj = new URL(System.getenv("FIREBASE_REGISTER_URL"));
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("x-goog-api-key", System.getenv("GOOGLE_API_KEY"));
        conn.setRequestProperty("x-goog-firebase-installations-auth", installToken);
        conn.setRequestProperty("User-Agent", "undici");
        conn.setRequestProperty("Content-Type", "text/plain;charset=UTF-8");
        JsonObject object = new JsonObject();
        JsonObject web = new JsonObject();
        web.addProperty("auth", authSecret);
        web.addProperty("p256dh", publicKey);
        web.addProperty("endpoint", "https://fcm.googleapis.com/fcm/send/" + registerToken);
        object.add("web", web);
        conn.setDoOutput(true);
        conn.getOutputStream().write(object.toString().getBytes());
        String response;
        try (InputStream in = conn.getInputStream()) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] array = new byte[1024];
            int len;
            while ((len = in.read(array)) != -1) {
                out.write(array, 0, len);
            }
            response = out.toString();
        }
        JsonObject element = JsonParser.parseString(response).getAsJsonObject();
        return element.get("token").getAsString();
    }

    private KeyPair generateKeyPair() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");
        keyPairGenerator.initialize(ecSpec, new SecureRandom());
        return keyPairGenerator.generateKeyPair();
    }

    private String generateAuthSecret() {
        // 1. 16바이트 배열을 생성합니다.
        byte[] authSecretBytes = new byte[16];

        // 2. 암호학적으로 안전한 난수 생성기로 배열을 채웁니다.
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(authSecretBytes);

        // 3. 일반적으로 auth_secret은 Base64로 인코딩하여 저장하고 전송합니다.
        return Base64.getUrlEncoder().withoutPadding().encodeToString(authSecretBytes);
    }

    /**
     * ECPublicKey를 65바이트 비압축 형식으로 변환하고 URL-safe Base64로 인코딩합니다.
     *
     * @param publicKey 변환할 ECPublicKey 객체
     * @return p256dh 형식의 문자열
     */
    private String encodePublicKey(ECPublicKey publicKey) {
        // --- Step 1: 공개키에서 X, Y 좌표 추출 ---
        ECPoint point = publicKey.getW();
        BigInteger x = point.getAffineX();
        BigInteger y = point.getAffineY();

        // --- Step 2: X, Y 좌표를 각각 32바이트 배열로 변환 ---
        byte[] xBytes = to32Bytes(x);
        byte[] yBytes = to32Bytes(y);

        // --- Step 3: 65바이트 비압축 형식 배열 생성 ---
        // [접두사 0x04] + [X 좌표 (32바이트)] + [Y 좌표 (32바이트)]
        ByteBuffer uncompressedKey = ByteBuffer.allocate(65);
        uncompressedKey.put((byte) 0x04); // 비압축 형식 접두사
        uncompressedKey.put(xBytes);
        uncompressedKey.put(yBytes);

        // --- Step 4: URL-safe Base64로 인코딩하여 반환 ---
        return Base64.getUrlEncoder().withoutPadding().encodeToString(uncompressedKey.array());
    }

    /**
     * BigInteger를 32바이트 길이의 배열로 변환합니다.
     * BigInteger.toByteArray()는 가변 길이이므로 길이를 맞춰주는 작업이 필요합니다.
     */
    private byte[] to32Bytes(BigInteger number) {
        byte[] source = number.toByteArray();
        byte[] target = new byte[32];

        // source 배열이 target보다 짧거나 길 수 있음 (부호 비트 때문)
        int bytesToCopy = Math.min(source.length, target.length);
        int sourceOffset = Math.max(0, source.length - bytesToCopy);
        int targetOffset = Math.max(0, target.length - bytesToCopy);

        System.arraycopy(source, sourceOffset, target, targetOffset, bytesToCopy);
        return target;
    }
}
