package com.ksk.mf;

import com.ksk.mf.config.WinAuthGrabber;
import com.ksk.mf.fcm.FcmTokenGenerator;

public class AuthUtil {
    public static final AuthUtil instance = new AuthUtil();

    private final String deviceKey = System.getenv("DEVICE_KEY") == null ? "" : System.getenv("DEVICE_KEY");
    private final String authToken = System.getenv("ACCESS_TOKEN") == null ? new WinAuthGrabber().getAuthToken() : System.getenv("ACCESS_TOKEN");
    private final FcmTokenGenerator fcmToken = new FcmTokenGenerator();

    private AuthUtil() {
    }

    public String getDeviceKey() {
        return deviceKey;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getFcmToken() {
        return fcmToken.getFCMToken();
    }
}
