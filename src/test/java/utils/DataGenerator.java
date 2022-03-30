package utils;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public final class DataGenerator {
    private DataGenerator() {
    }

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrsqtuvwxyz";
    private static final String NUMBER = "0123456789";

    public static String generateString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(CHARS.charAt(randomInteger(CHARS.length())));
        }
        return stringBuilder.toString().toLowerCase(Locale.ROOT);
    }

    public static String generateId(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(NUMBER.charAt(randomInteger(NUMBER.length())));
        }
        return stringBuilder.toString();
    }

    public static int randomInteger(int fromInclusive, int toExclusive) {
        return ThreadLocalRandom.current().nextInt(fromInclusive, toExclusive);
    }

    public static int randomInteger() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int randomInteger(int toExclusive) {
        return randomInteger(0, toExclusive);
    }

    public static String email(String prefix, int length) {
        return prefix + "-" + generateString(length) + "@example.com";
    }

    public static String email(int length) {
        return email("user", length);
    }

    public static String userNameOrSurname(String prefix, int length) {
        return prefix + generateString(length);
    }

    public static String userNameOrSurname(int length) {
        return userNameOrSurname("user", length);
    }
}