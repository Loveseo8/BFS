package com.company;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            String text = "IT-Samsung School";
            byte[] md = messageDigest.digest(text.getBytes());
            BigInteger no = new BigInteger(1, md);
            String str = no.toString(16);
            while (str.length() < 32) {
                str = "0" + str;
            }

            System.out.print(str);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
