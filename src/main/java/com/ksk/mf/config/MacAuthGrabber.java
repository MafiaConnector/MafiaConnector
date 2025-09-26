package com.ksk.mf.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

@Deprecated
@SuppressWarnings("unused")
public class MacAuthGrabber extends AuthGrabber {

    //This will never work
    public MacAuthGrabber() throws IOException {
        super(Files.walk(Paths.get(System.getProperty("user.home")))
                .filter(Files::isDirectory)
                .filter(path -> {
                    String name = path.getFileName().toString();
                    return Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$").matcher(name).matches();
                }).filter(path -> Files.exists(path.resolve("mafia_replay.db")))
                .findFirst().get().resolve("config.json").toFile());
    }
}
