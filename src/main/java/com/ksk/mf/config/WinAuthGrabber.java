package com.ksk.mf.config;

import java.io.File;

public class WinAuthGrabber extends AuthGrabber {

    public WinAuthGrabber() {
        super(new File(System.getenv("APPDATA") + System.getenv("MAFIA_CONFIG_FILE")));
    }
}
