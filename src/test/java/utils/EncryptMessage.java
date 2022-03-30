package utils;

import java.util.Base64;

public final class EncryptMessage {
    private EncryptMessage() {}

    public static String encode(String encodedString) {
        return Base64.getEncoder().encodeToString(encodedString.getBytes());
    }
}
