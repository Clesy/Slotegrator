package utils;

public final class NumberUtils {
    public static int parseInt(String values) {
        try {
            return Integer.parseInt(values);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("String cant be parse to Integer");
        }
    }
}
