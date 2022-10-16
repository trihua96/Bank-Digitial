package vn.funix.fx16573.java.asm02.models;

import java.util.Random;

public class GeneratorRandom {
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
    private static final String alphaUpperCase = alpha.toUpperCase();
    private static final String digits = "0123456789";
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;

    private static Random randGenerator = new Random();

    /**
     * Random string: a-zA-Z0-9
     */
    public static String randomAlphaNumeric(int numberOfCharater) {
        // Xây dựng chuỗi
        StringBuilder str = new StringBuilder();
        // Tạo vòng lặp với kích thước chuỗi
        for (int i = 0; i < numberOfCharater; i++) {
            // Lấy số ngẫu nhiên của chuỗi a-zA-Z0-9
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char character = ALPHA_NUMERIC.charAt(number);
            str.append(character);
        }

        return str.toString();
    }

    public static int randomNumber(int min, int max) {
        return randGenerator.nextInt((max - min) + 1) + min;
    }
}
