package com.aligntech.inventory.controllers;

import org.springframework.security.crypto.codec.Base64;

import java.nio.charset.StandardCharsets;

/**
 * Created by mozg on 15.03.2018.
 * inventory
 */
public class AuthUtils {

    private static final String LOGIN = "admin";
    private static final String PASSWORD = "qwerty";
    static final String AUTH_HEADER = "Authorization";
    private static final String AUTH_TYPE = "Basic";

    static String createHttpAuthenticationHeaderValue() {
        String auth = LOGIN + ":" + PASSWORD;
        byte[] encodedAuth = Base64.encode(auth.getBytes(StandardCharsets.UTF_8));
        return AUTH_TYPE + " " + new String(encodedAuth);
    }

}
