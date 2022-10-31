# Report on Laboratory number 3

### Course: Cryptography & Security
### Author: Coșeru Cătălin

----

## Some Words , that I would say
So, in general, the RSA algorithm is quite simple, being based on the mathematics 
studied in year 1, more precisely, it is based on the problem of the product between 
two prime numbers (in general, the numbers being quite large, this form of encryption
has a high level of security ).

In general asymmetric cryptography, i.e. encryption with two keys, one of which is 
public, are based on mathematical problems, better said mathematical functions that 
are one-way, they are easy to calculate in one part, but difficult to solve the 
other way around.

## Theory
 **RSA**\
 RSA or Rivest–Shamir–Adleman is an algorithm employed by modern computers to encrypt 
 and decrypt messages. It is an asymmetric cryptographic algorithm. Asymmetric means 
 that there are two different keys. This is also called public-key cryptography 
 because one among the keys are often given to anyone. The other is the private key 
 which is kept private. The algorithm is predicated on the very fact that finding 
 the factors of an outsized number is difficult: when the factors are prime numbers, 
 the matter is named prime factorization. It is also a key pair (public and personal 
 key) generator.

## Objectives:

1. Get familiar with the asymmetric cryptography mechanisms.

2. Implement an example of an asymmetric cipher.

3. As in the previous task, please use a client class or test classes to showcase the execution of your programs.




## Implementation description

####RSA

In general, the idea is simple, we rely on the ASCII code of each character, so we get a message. Or let's start in general with the initialization of an object of the RSA class, it is created through a constructor. The given constructor initializes 2 random prime numbers taken from a predefined list of about 75 prime numbers from 1 to about 500. Also, the calculation of their product and the function fi, which is generally the product of the predecessors of these prime numbers, takes place.


* **Code of Constructor**

```
    public RSA()
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

```
For encryption, the message is divided by the encryption function into letters, each letter having an ASCII code. The codes of these letters are raised to the power e mod n (the product of prime numbers), e at the moment is the public key.

* **Code of Encryption**
```
public String encription(String message){
        String enc_mess = "";
        System.out.println("the value of d = " + d);
        for(int i = 0; i < message.length(); i++) {
            int msg = message.charAt(i);
            c = (Math.pow(msg, this.e)) % n;
            enc_mess = enc_mess + String.valueOf((int)c) + ",";
        }
        System.out.println("Encrypted message is : " + enc_mess);
        return enc_mess;
    }
```

Decryption, in the same way, takes place by dividing the encrypted message into numbers and raising them to the power d (the secret key). Thus we obtain the sequence numbers of the letters in ASCII.

* **Code of Decryption**
```
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
```




## Conclusions / Screenshots / Results
Conclusion, this algorithm was quite easy to implement, the reason being that 
I learned this algorithm before in the special mathematics course. But in general 
many things were read from wiki and geeksforgeeks.com because many things were 
forgotten by me. However, the algorithm works, which makes me extremely happy. 
In general, I put in enough effort to get a good grade :D. What is related to 
structure requirements. All my laboratory is in a separate folder, 
I also included a test app to see the functionality. 
The report is in a separate folder, if I haven't done something, please let me know 
in the future so I can correct it O_O.