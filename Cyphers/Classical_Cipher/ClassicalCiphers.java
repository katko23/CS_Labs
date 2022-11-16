package CS.Cyphers.Classical_Cipher;

import java.util.LinkedHashSet;
import java.util.Set;

public class ClassicalCiphers {

    private interface Ciphers{
        public String encryptMessage(final String message);


        public String decryptMessage(final String encryptedMessage);

    }

    public class CaesarCipher implements Ciphers
    {

        private int substitutionKey = 1;


        public CaesarCipher(final int substitutionKey)
        {
            if((2<=substitutionKey) && (substitutionKey<=25)){
                this.substitutionKey = substitutionKey - 1;
            } else {
                System.out.println("Cheie gresita, alegeti din intervalul 2 .. 25 ");
            }

        }

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

    }

    public class CaesarPerCipher implements Ciphers
    {
        // Some state variables if needed.

        private int substitutionKey = 0;
        private String permutationKey = "";
        private char[] language = new char[54];



        public CaesarPerCipher(final int substitutionKey, final String permutationKey)
        {
            if((2<=substitutionKey) && (substitutionKey<=25)){
                this.substitutionKey = substitutionKey - 1;
            } else {
                System.out.println("Cheie gresita, alegeti din intervalul 2 .. 25 ");
            }

            Set<Character> languageS = new LinkedHashSet<Character>();

            String alf = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

            // facem substitutia :
            alf = Make_Substitution();

            //realizam permutarea prin inscrierea intr-un set si inscrierea in array
            for(int i = 0; i < permutationKey.length(); i++){
                languageS.add(permutationKey.charAt(i));
            }

            for(int i = 0; i < alf.length(); i++){
                languageS.add(alf.charAt(i));
            }
            Object[] ltemp = languageS.toArray();

            for(int i=0;i < ltemp.length;i++){
                language[i] = (char) ltemp[i];
            }
        }

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

        private int Search_Letter(char c){
            for (int i = 0; i< language.length; i++){
                if(language[i] == c) return i;
            }
            return -1;
        }

        private String Make_Substitution(){
            String alf = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String alfn = "";

            for(int i=0;i<=alf.length()-1;i++){
                if(Character.isLowerCase(alf.charAt(i))){
                    alfn = alfn + (char)(((int)alf.charAt(i) - (int)'a' + this.substitutionKey) % 26 + (int)'a');
                }else if (Character.isUpperCase(alf.charAt(i))) {
                    alfn = alfn + (char)(((int)alf.charAt(i) - (int)'A' + this.substitutionKey) % 26 + (int)'A');
                }else {
                    alfn = alfn + alf.charAt(i);

                }
            }

            return alfn;
        }
    }

    public class VigenereCipher implements Ciphers
    {
        // Some state variables if needed.

        private String Key;


        public VigenereCipher(final String Key)
        {
        this.Key = Key;
        }

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

    }

    public class PlayfairCipher implements Ciphers
    {
        // Some state variables if needed.

        private String key;
        private char[][] keyMatrix = new char[5][5];
        private char[] language = new char[26];

        public PlayfairCipher(final String key)
        {
            this.key = key;

            Set<Character> languageS = new LinkedHashSet<Character>();

            String alf = "abcdefghijklmnopqrstuvwxyz";

            for(int i = 0; i < key.length(); i++){
                languageS.add(key.charAt(i));
            }

            for(int i = 0; i < alf.length(); i++){
                languageS.add(alf.charAt(i));
            }
            Object[] ltemp = languageS.toArray();

            for(int i=0;i < ltemp.length;i++){
                language[i] = (char) ltemp[i];
            }

            System.out.println(language);
        }

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

        private int[] Pos(char c){
            for(int i=0;i< keyMatrix.length;i++){
                for(int j=0;j< keyMatrix.length;j++){
                    if (keyMatrix[i][j] == c){
                        return new int[] {i,j};
                    }
                }
            }
            return new int[] {-1,-1};
        }

    }

}
