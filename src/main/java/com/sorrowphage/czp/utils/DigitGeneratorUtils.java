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

    public static String generateAccountAllNum() {
        StringBuilder account = new StringBuilder();
        Random random = new Random();

        // 生成8位随机数字组合的账号，第一个数字不能为0
        account.append(1 + random.nextInt(9)); // 第一个数字为1-9之间的随机数
        for (int i = 1; i < 8; i++) {
            account.append(random.nextInt(10)); // 生成0-9之间的随机数
        }

        return account.toString();
    }




}
