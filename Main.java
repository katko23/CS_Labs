package CS;
import CS.Cyphers.Block_Cipher.BlowFish;
import CS.Cyphers.Stream_Cipher.RC4;

public class Main {

    public static void main(String[] args) {
        String keyword = "hello";
        byte[] rc4key = keyword.getBytes(); //convert keyword to byte
        RC4 streamcipher = new RC4(rc4key);

        try {
            System.out.println("Beginning encoding ");
            byte[] encriptedMess = streamcipher.encrypt("C");
//            System.out.println("Encoded");
            System.out.println("Encoded code " + encriptedMess);
            String decriptedMess = streamcipher.decrypt(encriptedMess);
            System.out.println("Decoded mess: \n" + decriptedMess);
        }catch(Exception e){
            System.out.println("Something is wrong with RC4");
            System.out.println(e);
        }

        BlowFish bw = new BlowFish();



    }
//        ClassicalCiphers class_ciphers = new ClassicalCiphers();
//
//	    ClassicalCiphers.CaesarCipher cipherC = class_ciphers.new CaesarCipher(5);
//
//        String enMess = cipherC.encryptMessage("Asta e un messaj direct 23 and XYZ , xyz");
//
//        System.out.println(enMess);
//
//        System.out.println(cipherC.decryptMessage(enMess));
//
//        ClassicalCiphers.CaesarPerCipher cipherCP = class_ciphers.new CaesarPerCipher(5,"Catalinn");
//
//        enMess = cipherCP.encryptMessage("Asta e un messaj mai nou la fel 23 XYZ si xyz");
//
//        System.out.println(enMess);
//
//        System.out.println(cipherCP.decryptMessage(enMess));
//
//        ClassicalCiphers.VigenereCipher cipherV = class_ciphers.new VigenereCipher("Lemon");
//
//        enMess = cipherV.encryptMessage("attackatdawn");
//
//        System.out.println(enMess);
//
//        System.out.println(cipherV.decryptMessage(enMess));
//
//        ClassicalCiphers.PlayfairCipher cipherPl = class_ciphers.new PlayfairCipher("computer");
//
//        enMess = cipherPl.encryptMessage("communication");
//
//        System.out.println(enMess);
//
//        System.out.println(cipherPl.decryptMessage(enMess));

//    }
}
