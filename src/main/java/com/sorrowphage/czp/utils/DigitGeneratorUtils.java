package com.sorrowphage.czp.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author: SorrowPhage
 * @date: 2023/10/27
 */
public class DigitGeneratorUtils {

    private static final String FIELD = "0123456789";
    private static final Random RANDOM = new SecureRandom();
    private static final String SYMBOL= "sp";

    public static String generateVerCode() {
        char[] verCode = new char[6];
        for (int i = 0; i < verCode.length; i++) {
            verCode[i] = FIELD.charAt(RANDOM.nextInt(FIELD.length()));
        }
        return String.valueOf(verCode);
    }

    public static String generateAccount() {
        char[] verCode = new char[7];
        for (int i = 0; i < verCode.length; i++) {
            verCode[i] = FIELD.charAt(RANDOM.nextInt(FIELD.length()));
        }
        return SYMBOL+String.valueOf(verCode);
    }





}
