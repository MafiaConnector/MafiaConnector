package com.ksk.mf.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class AuthGrabber {
    final Logger logger = LoggerFactory.getLogger(AuthGrabber.class);
    final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    final File file;

    protected AuthGrabber(File file) {
        this.file = file;
    }


    protected String getFileContent() {
        try {
            try(FileInputStream fis = new FileInputStream(file)) {
                byte[] buf = new byte[fis.available()];
                int len;
                StringBuilder sb = new StringBuilder();
                while ((len = fis.read(buf, 0, buf.length)) != -1) {
                    sb.append(new String(buf, 0, len));
                }
                return sb.toString();
            }
        } catch (IOException e) {
            logger.error("Auth file not found!", e);
            return "null";
        }
    }

    public String getAuthToken() {
        try {
            JsonObject object = gson.fromJson(getFileContent(), JsonObject.class);
            if (object.has(System.getenv("AUTH_FILE_GOOGLE"))) {
                return object.get(System.getenv("AUTH_FILE_GOOGLE")).getAsString();
            }
            throw new Exception("No Auth Token. File doesn't have any accessToken");
        } catch (Exception e) {
            logger.error("Error while getting auth token!", e);
            return "null";
        }
    }
}
