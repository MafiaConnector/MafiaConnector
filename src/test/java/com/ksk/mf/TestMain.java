package com.ksk.mf;

import com.ksk.mf.config.AuthGrabber;
import com.ksk.mf.config.WinAuthGrabber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestMain {
    @DisplayName("Google Auth Grab")
    @Test
    void test1() {
        AuthGrabber grab = new WinAuthGrabber();
        assert grab.getAuthToken() != null;
        String authToken = grab.getAuthToken();
        assert !authToken.isEmpty();
    }
}
