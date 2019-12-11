package com.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GenerateBCryptPasswordUtil {

    private static final Logger logger = LoggerFactory.getLogger(GenerateBCryptPasswordUtil.class);

    public static String generatedBCryptPassword(String realPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        return bCryptPasswordEncoder.encode(realPassword);
    }

    public static boolean matchBCryptPassword(String realPassword, String hashedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(realPassword, hashedPassword);
    }

    private static final String AHEAD_BCRYPT="{bcrypt}";

    public static void main(String[] args) {
        String encodedPassword = generateJdbcBCryptPassword("onlineTest01");
        System.out.println(encodedPassword);
//
//        for (int i = 0; i < 10; i++) {
//            String encodedPassword = generateJdbcBCryptPassword("onlineTest01");
//            System.out.println(encodedPassword);
//        }
//
//        String replace = encodedPassword.replace("{bcrypt}", "");
//        boolean decodePassword = matchBCryptPassword("1234abcd", replace);
//        System.out.println(decodePassword);

//        "{bcrypt}$2a$12$IMKnOMpZuk.gSKGxf5HDS.raMISKRFIdpjzYVh1Cs6MpdA9aRthSC" korn0001
//        "{bcrypt}$2a$12$m8icyjIVoBWaudUqvBksYeBLG0c1mpbZwon04T9E2PXDssg.M3thW" jirap001
//        boolean result= matchBCryptPassword("jirap001", "$2a$12$m8icyjIVoBWaudUqvBksYeBLG0c1mpbZwon04T9E2PXDssg.M3thW");
//        System.out.println(result);
    }

    public static String generateJdbcBCryptPassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
        return String.format("%1$s%2$s", AHEAD_BCRYPT, bCryptPasswordEncoder.encode(password));
    }
}
