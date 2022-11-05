# The title of the work

### Course: Cryptography & Security
### Author: Coșeru Cătălin

----

## Theory
I passed all the theory in the course, but a small reminder:

&ensp;&ensp;&ensp; Cryptography consists a part of the science known as Cryptology. The other part is Cryptanalysis. There are a lot of different algorithms/mechanisms used in Cryptography, but in the scope of these laboratory works the students need to get familiar with some examples of each kind.

&ensp;&ensp;&ensp; First, it is important to understand the basics so for the first task students will need to implement a classical and relatively simple cipher. This would be the Caesar cipher which uses substitution to encrypt a message.

&ensp;&ensp;&ensp; In it's simplest form, the cipher has a key which is used to substitute the characters with the next ones, by the order number in a pre-established alphabet. Mathematically it would be expressed as follows:

$em = enc_{k}(x) = x + k (mod \; n),$

$dm = dec_{k}(x) = x + k (mod \; n),$

where:
- em: the encrypted message,
- dm: the decrypted message (i.e. the original one),
- x: input,
- k: key,
- n: size of the alphabet.

&ensp;&ensp;&ensp; Judging by the encryption mechanism one can conclude that this cipher is pretty easy to break. In fact, a brute force attack would have __*O(nm)*__ complexity, where __*n*__ would be the size of the alphabet and __*m*__ the size of the message. This is why there were other variations of this cipher, which are supposed to make the cryptanalysis more complex.



## Objectives:

* Get familiar with the basics of cryptography and classical ciphers.

* Implement 4 types of the classical ciphers:
    - Caesar cipher with one key used for substitution (as explained above),
    - Caesar cipher with one key used for substitution, and a permutation of the alphabet,
    - Vigenere cipher,
    - Playfair cipher.


## Implementation description
### Caesar Cipher
* The Caesar Cipher technique is one of the earliest and simplest methods of encryption technique. It’s simply a type of substitution cipher, i.e., each letter of a given text is replaced by a letter with a fixed number of positions down the alphabet. For example with a shift of 1, A would be replaced by B, B would become C, and so on. The method is apparently named after Julius Caesar, who apparently used it to communicate with his officials.


* **Encrypt**

    In order to encrypt, I allowed the choice of a key in the range 0 .. 26, to then shift the IDs of each character to another. Thus, in order to have both Upper and Lower case characters, it was necessary to do an if and control the letters in the message. Also, due to the use of ASCII codes, it was necessary to decrease the index with the ASCII code of the character a.
```
public String encryptMessage(final String message)
        {
            String encryptedMess = "";
            for(int i=0;i<=message.length()-1;i++){
                if(Character.isLowerCase(message.charAt(i))){
                    encryptedMess = encryptedMess + (char)(((int)message.charAt(i) - (int)'a' + this.substitutionKey) % 26 + (int)'a');
                }else if (Character.isUpperCase(message.charAt(i))) {
                    encryptedMess = encryptedMess + (char)(((int)message.charAt(i) - (int)'A' + this.substitutionKey) % 26 + (int)'A');
                }else {
                    encryptedMess = encryptedMess + message.charAt(i);

                }
            }

            return encryptedMess;
        }
```

* **Decrypt**

    Decryption is like encryption, only that instead of adding the substitution key, it subtracts it.
```
public String decryptMessage(final String encryptedMessage)
        {
            String decryptedMess = "";
            for(int i=0;i<=encryptedMessage.length()-1;i++){
                if(Character.isLowerCase(encryptedMessage.charAt(i))){
                    decryptedMess = decryptedMess + (char)(((int)encryptedMessage.charAt(i) - (int)'a' - this.substitutionKey + 26) % 26 + (int)'a');
                }else if(Character.isUpperCase(encryptedMessage.charAt(i))){
                    decryptedMess = decryptedMess + (char)(((int)encryptedMessage.charAt(i) - (int)'A' - this.substitutionKey + 26) % 26 + (int)'A');
                }else{
                    decryptedMess = decryptedMess + encryptedMessage.charAt(i);
                }
            }

            return decryptedMess;
        }
```

### Caesar Cipher with Permutation
* As in the case of the Caesar cipher, here the substitution takes place with a certain key, after which a word is taken as permutationKey and based on it the alphabet is permuted. Thus, the letters of the word are placed at the beginning of the alphabet, neglecting the letters that are repeated.


* **Initialization**

    When the object is initialized, the alphabet is permuted. For this we include in a set the letters from the permutationKey, then all the letters from the initial alphabet.


* **Encrypt**

    The encryption takes place by replacing the letter id in the message with the new alphabet from the list. List created using set.
```
public String encryptMessage(final String message)
        {
            String encryptedMess = "";
            for(int i=0;i<=message.length()-1;i++){
                if(Character.isLowerCase(message.charAt(i))){
                    encryptedMess = encryptedMess + language[(int)message.charAt(i) - (int)'a'];
                }else if(Character.isUpperCase(message.charAt(i))) {
                    encryptedMess = encryptedMess + language[(int)message.charAt(i) - (int)'A' + 26];
                }else{
                    encryptedMess = encryptedMess + message.charAt(i);
                }
            }

            System.out.println(language);

            return encryptedMess;
        }
```

* **Decrypt**

The encryption takes place by replacing the letter id in the message with the new alphabet from the list.
```
public String decryptMessage(final String encryptedMessage)
        {
            String decryptedMess = "";
            for(int i=0;i<=encryptedMessage.length()-1;i++){
                int sl = Search_Letter(encryptedMessage.charAt(i));
                if(sl < 26 && sl != -1){
                    decryptedMess = decryptedMess + (char)(sl + (int)'a') ;
                }else if(sl >= 26){
                    decryptedMess = decryptedMess + (char)(sl - 26 + (int)'A');
                }else{
                    decryptedMess = decryptedMess + encryptedMessage.charAt(i);
                }
            }

            return decryptedMess;
        }
```
### Vigenere Cipher
* Because the Vigenere Cipher is also based on the character shift, only that each letter in the message is moved with the id of the letter in the key, it becomes more secure than the Caesar cipher.

* **Encrypt**

  The encryption takes place by adding to the letter in the message, the id of the letter in the key, then the order 'a' or 'A' is subtracted due to the use of the ASCII code, so that at the end we make a mod 26, in order not to exceed the size maximum of the alphabet.
```
public String encryptMessage(final String message)
        {
            String encryptedMess = "";
            for(int i=0;i< message.length();i++){
                if(Character.isLowerCase(message.charAt(i))){
                    int id_letter = 0;
                    if(Character.isUpperCase(Key.charAt(i % Key.length()))){
                        id_letter = ((int)message.charAt(i) - (int)'a' + Key.charAt(i % Key.length()) - (int)'A') % 26 + 'A';
                    }else{
                        id_letter = ((int)message.charAt(i) - (int)'a' + Key.charAt(i % Key.length()) - (int)'a') % 26 + 'A';
                    }
                    encryptedMess = encryptedMess + (char)(id_letter);
                }else if(Character.isUpperCase(message.charAt(i))){
                    int id_letter = 0;
                    if(Character.isUpperCase(Key.charAt(i % Key.length()))){
                        id_letter = ((int)message.charAt(i) - (int)'A' + Key.charAt(i % Key.length()) - (int)'A') % 26 + 'A';
                    }else{
                        id_letter = ((int)message.charAt(i) - (int)'A' + Key.charAt(i % Key.length()) - (int)'a') % 26 + 'A';
                    }
                    encryptedMess = encryptedMess + (char)(id_letter);
                }else{
                    encryptedMess = encryptedMess + message.charAt(i);
                }
            }

            return encryptedMess;
        }
```

* **Decrypt**

    When decrypting, it is taken into account that after encryption all the message is written with UpperCase characters, so it is necessary to write the message in ASCII, so the code of the character 'A' is subtracted from the letter code. Then there is again the difference between what we obtained and the id of the order letter mod key letter (i % length of key). Thus we obtain the value of the initial id, which if we add it to the ASCII code of the letter 'a', we obtain the encrypted message, only as in LowerCase.
```
 public String decryptMessage(final String encryptedMessage)
        {
            String decryptedMess = "";
            for(int i=0;i< encryptedMessage.length();i++){
                if(Character.isUpperCase(encryptedMessage.charAt(i))){
                    int id_letter = 0;
                    if(Character.isUpperCase(Key.charAt(i % Key.length()))){
                        id_letter = ((int)encryptedMessage.charAt(i) - (int)Key.charAt(i % Key.length()) + 26) % 26 + 'a';
                    }else{
                        id_letter = ((int)encryptedMessage.charAt(i) - 'A' - ((int)Key.charAt(i % Key.length()) - 'a') + 26) % 26 + 'a';
                    }
                    decryptedMess = decryptedMess + (char)(id_letter);
                }else{
                    decryptedMess = decryptedMess + encryptedMessage.charAt(i);
                }
            }

            return decryptedMess;
        }
```


### Playfair Cipher
* One of the more complicated numbers to implement, but just as easy to break is Playfair. It is based on the permutation of the alphabet according to a certain key, and according to the 5*5 matrix.

* **Encrypt**

    Encryption takes place by generating digraphs, as a rule, no digraph with 2 identical letters, and all digraphs consisting of 2 letters. Thus we get some gaps, which are filled with letters that are not often used in the language, in the code I wrote I use the letter x.
```
 public String encryptMessage(final String message)
        {
            String encryptedMess = "";
            String[] digraph = new String[message.length()];
            String temp = "";
            int index = 0;
            for(int i = 0; i < message.length() ; i++)
            {
                if ( i == message.length()-1 && temp.length() == 0){
                    temp = temp + "x";
                } else {
                    temp = temp + message.charAt(i);
                }
                if(i != message.length()-1) {
                    if (message.charAt(i) == message.charAt(i + 1)) {
                        temp = temp + "x";
                    }
                }

                if(temp.length() == 2){
                    digraph[index] = temp;
                    index = index + 1;
                    temp = "";
                    System.out.println(digraph[index-1]);
                }
            }

            for(int i = 0;i < language.length-1; i++){
                if(language[i] != 'j'){
                    int row = i / 5;
                    int col = i % 5;
                    if ( row < 0 ) row = 0;
                    if ( col < 0 ) col = 0;

                    keyMatrix[row][col] = language[i];
                }

            }

            for (String d: digraph ) {
                if(d != null) {
                    if (d.charAt(0) == 'j') d.replace('j', 'i');
                    if (d.charAt(1) == 'j') d.replace('j', 'i');
                    int[] pos1 = Pos(d.charAt(0));
                    int[] pos2 = Pos(d.charAt(1));

                    char[] chars = d.toCharArray();

                    if (pos1[0] == pos2[0]) {
                        chars[0] = keyMatrix[pos1[0]][(pos1[1] + 1) % 5];
                        chars[1] = keyMatrix[pos2[0]][(pos2[1] + 1) % 5];
                    } else if (pos1[1] == pos2[1]) {
                        chars[0] = keyMatrix[(pos1[0] + 1) % 5][pos1[1]];
                        chars[1] = keyMatrix[(pos2[0] + 1) % 5][pos2[1]];
                    } else {
                        chars[0] = keyMatrix[pos1[0]][pos2[1]];
                        chars[1] = keyMatrix[pos2[0]][pos1[1]];
                    }
                    d = String.valueOf(chars);
                    encryptedMess = encryptedMess + d;
                }
            }

            return encryptedMess;
        }
```

* **Decrypt**

  Decryption takes place using the same method as encryption, only that now the operations are reversed, it is checked if the letters of the digraph are on the same row, the column id is decreased by 1 (instead of increasing), if they are on the same column, the row index decreases by 1, and if both are different, the horizontal inversion of the column takes place.
```
 public String decryptMessage(final String encryptedMessage)
        {
            String decryptedMess = "";
            String[] digraph = new String[encryptedMessage.length()];
            String temp = "";
            int index = 0;
            for(int i = 0; i < encryptedMessage.length() ; i = i + 2)
            {
                temp = temp + encryptedMessage.charAt(i) + encryptedMessage.charAt(i+1);

                if(temp.length() == 2){
                    digraph[index] = temp;
                    index = index + 1;
                    temp = "";
                    //System.out.println(digraph[index-1]);
                }
            }

            for (String d: digraph ) {
                if(d != null) {
                    int[] pos1 = Pos(d.charAt(0));
                    int[] pos2 = Pos(d.charAt(1));

                    char[] chars = d.toCharArray();

                    if (pos1[0] == pos2[0]) {
                        chars[0] = keyMatrix[pos1[0]][(pos1[1] - 1 +5) % 5];
                        chars[1] = keyMatrix[pos2[0]][(pos2[1] - 1 +5) % 5];
                    } else if (pos1[1] == pos2[1]) {
                        chars[0] = keyMatrix[(pos1[0] - 1 +5) % 5][pos1[1]];
                        chars[1] = keyMatrix[(pos2[0] - 1 +5) % 5][pos2[1]];
                    } else {
                        chars[0] = keyMatrix[pos1[0]][pos2[1]];
                        chars[1] = keyMatrix[pos2[0]][pos1[1]];
                    }
                    d = String.valueOf(chars);
                    decryptedMess = decryptedMess + d;
                }
            }
            return decryptedMess;
        }
```

## Conclusions / Screenshots / Results
In the end, I can say that all the things related take place in the main function, actually representing a set of instructions, which must reproduce the realization of this laboratory. Besides that, I conclude that classical numbers and their implementation have a good impact on the understanding of cryptography.

![img.png](../Images_Screens/img.png)