마피아42 클라이언트 접속 시스템 입니다.
=============

현재 개발중에 있으며   
사용방법은 이 문서를 참조 하시면 됩니다.   
이 프로젝트는 오로지 연구 목적이며, 실제 사용을 원할 경우 프로그램 실행시 필요한 값을 직접 구해서 .env에 넣으셔야 합니다.

MafiaChannel
```java
MafiaChannel channel = MafiaChannel.getChannelFromChannelId(20);
MafiaChannel channel = MafiaChannel.CHANNEL_RANK;
MafiaChannel channel = MafiaChannel.getChannelFromChannelName("랭크 채널");
```
위 코드 모두 동일한 결과를 얻습니다   

ChannelProvider   
마피아42 channel.php 에 접근해서 실제 주소, 포트, 현재 인원(미구현) 등을 알 수 있습니다.
```java
ChannelProvider provider = new ChannelProvider();
ChannelData channel = provider.getChannelDataFromChannel(MafiaChannel.CHANNEL_RANK);
```

FcmTokenGenerator   

Firebase Cloud Message에 필요한 토큰을 자동으로 받아와줍니다.
```java
String fcmToken = new FcmTokenGenerator().getFCMToken();
```
이 코드는 마피아42 로그인에서만 유효하며 어떠한 Firebase Message 도 받지 못합니다.  

Connector   
마피아42 에 접속하기 위한 Netty System 입니다.   
MafiaChannel 인자 / 전통적인 주소,포트 방식 둘다 사용 가능합니다.
```java
Connector connector = new Connector(MafiaChannel.CHANNEL_RANK);
Connector connector = new Connector("localhost", 8080);
```

Packet Handling
==

RequestPacket#sendPacket(ChannelHandlerContext)를 반드시 이용하셔야 하며

혹여나 ctx.writeAndFlush 를 사용 해야 하는 경우
ByteBuf 를 받아서 new BinaryWebSocketFrame 으로 감싸야 합니다.

AuthUtil
=
마피아42 접속을 위한 accessToken, deviceKey, fcmToken 을 관리합니다.

fcmToken은 FcmTokenGenerator를 이용해서 쓰면 되고,

accessToken 은 mafia42.com 사이트 로그인 시에 GET mafia42.com/api/google-login 에 사용되는 accessToken을 그대로 가져오면 됩니다.

파일 읽는 형식 대신 수동 기입을 원하시면 Main class 에서 setAuthToken 메소드를 사용하시면 됩니다.

마지막으로 deviceKey는 현재 자체 생산이 불가능 하므로, 1회 정도는 HTTP Toolkit 같이 네트워크를 분석 할 수 있는 툴로 직접 파싱 해오셔야 합니다.

Main class에 있는 setDeviceKey#Ljava/lang/String;V 을 이용해서 넣어주시면됩니다


