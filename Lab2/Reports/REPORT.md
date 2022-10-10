# Report on Laboratory number 2

### Course: Cryptography & Security
### Author: Coșeru Cătălin

----

## Some Words , that I would say
First of all, I will express that I had quite a bit of time to complete the 
laboratory (the reason being the exaggerated laboratories in the rest of the 
disciplines), and I say openly that I used sources from the Internet that I 
will present. However, I will say openly that I watched the video, 
I read information (including a whole report that guided me) and 
I understood how the BlowFish and RC4 cipher is made. 
After all, I think the most important thing is to understand how it works, 
not to struggle over generating a stream key.

## Theory
 **BlowFish**\
 Blowfish, the first symmetric key block cipher, was designed in 1993 by Bruce Schneier. He designed this algorithm as an alternative to the aging DES cipher; however, it failed due to the small block size of 64, which is considered insecure. He also placed the algorithm for public use, and anyone can use it without his permission.
\
Though Blowfish is much faster than DES, it is less secure. This algorithm was included in many encryption products and cipher programs, but many vulnerabilities started to crop up. Schneier removed these vulnerabilities and made another encryption algorithm—the AES.
\
Without Blowfish, the AES algorithm (used today in most of the applications) would not have been created.
 
 **RC4**\
 RC4 is a symmetric stream cipher and variable key length algorithm. This symmetric key algorithm is used identically for encryption and decryption such that the data stream is simply XORed with the generated key sequence. The algorithm is serial as it requires successive exchanges of state entries based on the key sequence. The algorithm works in two phases:

- KSA (Key Sheduling alg.)
- PRGA (Pseudo Random Generating alg.)

## Objectives:

* Get familiar with the symmetric cryptography, stream and block ciphers.

* Implement an example of a stream cipher. - in my case RC4

* Implement an example of a block cipher. - in my case BlowFish




## Implementation description

####RC4
The idea and the whole realization, including what I did, I took from the given video: https://www.youtube.com/watch?v=wW3WOLX4itc
In fact, it clearly explains how RC4 is made, and yet in general I took the elements from the pseudocode and translated them into Java.
I also used array of bytes, for that it was necessary to transform from string to byte through getBytes, and vice versa through new String(), sometimes there is a bug during generation that makes the transfer operations from string to bytes and back erroneous. 
But the implementation does not suffer from this:


* **Code of KSA**

```
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
```
This piece of code I take from constructor , cause I initialize the object with an given 
key and I made the keysheduling at this initialization.

* **code of PRGA:**\
ohhh I completely forgot, I made 2 decryptors, one that takes string as value, and another for array of Byte.
```
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
```

####BlowFish
Sooo blowfish, I got into the idea and realization of this algorithm, but unfortunately I used a lot of implementations of key generation, of the javax.crypto package... 
Also, the general problem was that after a vast research I realized how good are the implementations of others and, in relation to them, I wrote my own algorithm, based on some implementations from the given sources:

https://study.com/academy/lesson/block-cipher-definition-purpose-examples.html
https://stackoverflow.com/questions/5244950/encryption-with-blowfish-in-java
https://medium.com/@arie.valiant/java-cryptography-blowfish-encryption-decryption-tutorial-1db5f2cc15f1 \
and last but not least, I used a written report for the realization of this algorithm in java and MySQL - https://www.researchgate.net/publication/329701190_Effectuation_of_Blowfish_Algorithm_using_Java_Cryptography.

Now I have a very simple implementation based on 4 functions:
the first initialization of the 128-bit key, the second its transformation into the symmetric key and, of course, encryption and decryption.

* **Code of first step key initialization**

```
        KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(seed);
        kgen.init(128, sr); // 128, 256 and 448 bits may not be available
        SecretKey skey = kgen.generateKey();
        raw = skey.getEncoded();
        return raw;
```

* **Code of second step key transform.**

```
            Random r = new Random();
            int num = r.nextInt(10000);
            String knum = String.valueOf(num);
            byte[] knumb = knum.getBytes();
            skey=getRawKey(knumb);
            skeyString = new String(skey);
            System.out.println("Blowfish Symmetric key = "+skeyString);
```

* **Code of encryption**

```
    private static byte[] encrypt(byte[] raw, byte[] clear) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(clear);
        return encrypted;
    }
```

* **Code of decryption**

```
    private static byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        byte[] decrypted = cipher.doFinal(encrypted);
        return decrypted;
    }
```

## Conclusions / Screenshots / Results
Conclusion, I know that I didn't really work honestly at blowfish, 
but honestly I know how the encryption and decryption of this algorithm takes place, 
this is how the message is divided into 2, then the left part is xored with the key, 
after which the result is passed through the function that it divides the block into 
4 parts, the first is added to the second, an xor is made with the third and the 
result is added to the fourth, after which the result of the function is made xor 
with the block on the right, and a switch is made between the R and L block . 
This is done 10 times, with a total of 16 P-array entries being used 
(at the exit from cycle 10, immediately after it, the last switch between R and L 
is made, and these are xor-sated with the last 2 P-array entries). 
This is how the encryption takes place. 
Decryption takes place in the same way, only the P-entries are reversed.