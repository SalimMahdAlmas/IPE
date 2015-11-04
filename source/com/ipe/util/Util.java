package com.ipe.util;

import java.math.BigInteger;

public class Util {

    public static String encodeCodec(String a) {

        a = a.toLowerCase();

        byte[] bytes = a.getBytes();
        String bs = (Util.encodeCodec(bytes));

        String[] abcd = new String[999];
        abcd[0] = "a";
        abcd[1] = "b";
        abcd[2] = "c";
        abcd[3] = "d";
        abcd[4] = "e";
        abcd[5] = "f";
        abcd[6] = "g";
        abcd[7] = "h";
        abcd[8] = "i";
        abcd[9] = "j";
        abcd[10] = "k";
        abcd[11] = "l";
        abcd[12] = "m";
        abcd[13] = "o";
        abcd[14] = "p";
        abcd[15] ="z";
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < bs.split("").length ; i++) {
            if (i < 15)  {

                stringBuilder.append(bs.split("")[i]).append(abcd[i]);
            }
            else {
                break;
            }
        }
        return stringBuilder.toString().toUpperCase();
    }
    private static String encodeCodec(byte[] bytes) {
        int c = (int) (Math.log(bytes.length));
        int i = bytes.length % c;
        byte[] arrayOfByte = new byte[i == 0 ? bytes.length : bytes.length + c - i];
        System.arraycopy(bytes, 0, arrayOfByte, c - i, bytes.length);

        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < arrayOfByte.length; j += c) {
            encode(arrayOfByte, stringBuffer, j, c);
        }
        return stringBuffer.toString();
    }

    private static void encode(byte[] bytes, StringBuffer stringBuffer, int off, int len) {
        if (len == 0) {
            return;
        }
        BigInteger pubKey = new BigInteger("0123456789");
        BigInteger privKey = new BigInteger("9876543210");

        byte[] arrayOfByte = new byte[len];
        System.arraycopy(bytes, off, arrayOfByte, 0, len);
        BigInteger localBigInteger1 = new BigInteger(1, arrayOfByte);
        if (localBigInteger1.compareTo(pubKey) >= 0) {
            throw new IllegalArgumentException("result is too long");
        }
        BigInteger localBigInteger2 = localBigInteger1.modPow(privKey, pubKey);
        stringBuffer.append((localBigInteger2));
    }
}
