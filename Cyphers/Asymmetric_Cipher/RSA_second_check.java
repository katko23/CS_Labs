package CS.Cyphers.Asymmetric_Cipher;
import java.math.BigInteger;

public class RSA_second_check {

    int p, q, n, z, d = 0, e, i;
    BigInteger msgback;
    double c;
    int[] prime_numbers = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
            101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
            211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331,
            337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397};

    public RSA_second_check()
    {
        // The number to be encrypted and decrypted
        int msg = 12;
        // 1st prime number p
        int max = prime_numbers.length;
        int min = 1;
        p = prime_numbers[(int)(Math.random()*(max-min+1)+min)];
        System.out.println("the value of p = " + p);
        // 2nd prime number q
        q = prime_numbers[(int)(Math.random()*(max-min+1)+min)];
        System.out.println("the value of q = " + q);
        n = p * q;
        z = (p - 1) * (q - 1);
        System.out.println("the value of fi(n) = " + z);

        for (e = 2; e < z; e++) {
            // e is for public key
            if (gcd(e, z) == 1) {
                break;
            }
        }

        System.out.println("the value of public key = " + e);
        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * z);

            // d is for private key
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
    }

    static int gcd(int e, int z)
    {
        if (e == 0)
            return z;
        else
            return gcd(z % e, e);
    }

    public String encription(String message){
        String enc_mess = "";
        System.out.println("the value of d = " + d);
        String c_temp = "";
        for(int i = 0; i < message.length(); i++) {
            int msg = message.charAt(i);
            if (0 <= msg && msg < 10){
                c_temp = c_temp + "00" + msg;
            }
            else if (10 <= msg && msg < 100){
                c_temp = c_temp + "0" + msg;
            }
            else {
                c_temp = c_temp + msg;
            }
            if(i%6 == 1 || i == message.length()-1) {
                long enc_c = Long.parseLong(c_temp);
                c = (Math.pow(enc_c, this.e)) % n;
                enc_mess = enc_mess + String.valueOf((int) c) + ",";
                System.out.println(c_temp);
                c_temp = "";
            }
        }
        System.out.println("Encrypted message is : " + enc_mess);
        return enc_mess;
    }

    public static long powerMod(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) != 0)
                result = (result * base) % mod;
            exp >>= 1;
            base = base * base % mod;
        }
        return result < 0 ? result + mod : result;
    }

    public String decription(String encr_mess){
        String[] string_int = encr_mess.split(",");
        int[] arr = new int[string_int.length];
        for (int i = 0; i < string_int.length; i++) {
            arr[i] = Integer.valueOf(string_int[i]);
        }
        // converting int value of n to BigInteger
        BigInteger N = BigInteger.valueOf(n);
        BigInteger C;
        String message = "";
        for (int i = 0; i < arr.length; i++) {
            // converting float value of c to BigInteger
            C = BigInteger.valueOf(arr[i]);

            msgback = (C.pow(d)).mod(N);
            int mess_int = msgback.intValue();
            message = message + String.valueOf((char)mess_int) ;
        }
        System.out.println("Decrypted message is : " + message);
        return message;
    }
}

