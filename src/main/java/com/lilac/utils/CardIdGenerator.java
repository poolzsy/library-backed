package com.lilac.utils;

import java.security.SecureRandom;
import java.util.Random;

public final class CardIdGenerator {
    private static final String PREFIX = "lilac"; // 卡号前缀，可以自定义
    private static final int RANDOM_NUMBER_LENGTH = 8; // 随机部分的长度
    private static final Random RANDOM = new SecureRandom(); // 使用更安全的随机数生成器

    // 私有化构造器，防止实例化
    private CardIdGenerator() {
    }

    /**
     * 生成一个唯一的会员卡号
     * 格式: LILAC + 2位年份 + 8位随机数 (e.g., LILAC2412345678)
     *
     * @return 会员卡号字符串
     */
    public static String generate() {
        // 1. 获取当前年份的后两位
        String year = String.valueOf(java.time.Year.now().getValue()).substring(2);
        // 2. 生成指定长度的随机数字字符串
        StringBuilder randomNumber = new StringBuilder(RANDOM_NUMBER_LENGTH);
        for (int i = 0; i < RANDOM_NUMBER_LENGTH; i++) {
            randomNumber.append(RANDOM.nextInt(10));
        }
        return PREFIX + year + randomNumber.toString();
    }
}