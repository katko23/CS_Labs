package CS.Cyphers.Stream_Cipher;

import java.io.UnsupportedEncodingException;

public class RC4 {
    private final byte[] S = new byte[256];
    private final byte[] T = new byte[256];
    private final int keylen;

    // constructor al clasei cu o cheie care o dam noi
    public RC4(final byte[] key) {
        if (key.length < 1 || key.length > 256) {
            throw new IllegalArgumentException(
                    "key must be between 1 and 256 bytes");
        } else {
            keylen = key.length;

            //Initialize vectors and make Key-SHeduling Algorithm KSA
            for (int i = 0; i < 256; i++) {
                S[i] = (byte) i;
                T[i] = key[i % keylen];
            }
            int j = 0;
            byte tmp;
            for (int i = 0; i < 256; i++) {
                j = (j + S[i] + T[i]) & 0xFF;
                tmp = S[j];
                S[j] = S[i];
                S[i] = tmp;
            }
        }
    }

    public byte[] encrypt(final String plaintext) throws UnsupportedEncodingException {
        final byte[] byteArrray = plaintext.getBytes("ASCII");
        System.out.println(byteArrray);
        String s = new String(byteArrray, "ASCII");
        System.out.println(s);
        final byte[] ciphertext = new byte[byteArrray.length];
        int i = 0, j = 0, k, t;
        byte tmp;
        //Generate the bitestream by pseudo-random generation Algorithm PRGA and encrypt with
        for (int counter = 0; counter < byteArrray.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            tmp = S[j];
            S[j] = S[i];
            S[i] = tmp;
            t = (S[i] + S[j]) & 0xFF;
            k = S[t];
            ciphertext[counter] = (byte) (byteArrray[counter] ^ k);
        }
        return ciphertext;
    }

    public byte[] encrypt_bytes(final byte[] byteArray) throws UnsupportedEncodingException {
//        final byte[] byteArray = plaintext.getBytes("ASCII");
        System.out.println(byteArray);
        String s = new String(byteArray, "ASCII");
        System.out.println(s);
        final byte[] ciphertext = new byte[byteArray.length];
        int i = 0, j = 0, k, t;
        byte tmp;
        //Generate the bitestream by pseudo-random generation Algorithm PRGA and encrypt with
        for (int counter = 0; counter < byteArray.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            tmp = S[j];
            S[j] = S[i];
            S[i] = tmp;
            t = (S[i] + S[j]) & 0xFF;
            k = S[t];
            ciphertext[counter] = (byte) (byteArray[counter] ^ k);
        }
        return ciphertext;
    }

//    public static String convertStringToBinary(String input) {
//
//        StringBuilder result = new StringBuilder();
//        char[] chars = input.toCharArray();
//        for (char aChar : chars) {
//            result.append(
//                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
//                            .replaceAll(" ", "0")                         // zero pads
//            );
//        }
//        return result.toString();
//
//    }
//
//    public static String convertBinaryToString(String input) {
//        String raw = Arrays.stream(input.split(" "))
//                .map(binary -> Integer.parseInt(binary, 2))
//                .map(Character::toString)
//                .collect(Collectors.joining()); // cut the space
//
//        return raw;
//    }

    public String decrypt(final byte[] ciphertext) throws UnsupportedEncodingException {
        String s = new String(ciphertext, "ASCII");
        System.out.println("String of encripted mess is : " + s + " " + s.getBytes("ASCII")) ;
        final byte[] decriptedBytes = encrypt_bytes(ciphertext);
        final String decriptedMess = new String(decriptedBytes, "ASCII");
        return decriptedMess;
    }
}