package CS;
import CS.Asymmetric_Cipher.RSA;

public class TestLab3 {

    public static void main (String[] args){
        RSA new_cript = new RSA();
        String enc = new_cript.encription("message is Encripted and Decripted");
        String mess = new_cript.decription(enc);
    }
}
