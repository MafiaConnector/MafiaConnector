package com.ksk.mf.gui;

import com.ksk.mf.game.info.MafiaLoginInfo;

public interface ConnectorCallback {
    void onConnected();
    void onDisconnected();
    void onError(String error);
    void onLoginInfoReceived(MafiaLoginInfo loginInfo);
    void onMessageReceived(String message);
}