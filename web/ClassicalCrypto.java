package CS.web;

import java.util.Map;
import CS.web.singingData;
import CS.Cyphers.Classical_Cipher.ClassicalCiphers;
import CS.Cyphers.Classical_Cipher.ClassicalCiphers.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassicalCrypto {
	ClassicalCiphers classicalCipher = new ClassicalCiphers();
	singingData db = new singingData();
	
	@GetMapping(value = "/Caesar", consumes = "application/json", produces = "application/json")
	public String newCaesar(@RequestBody Map<String, String> payload)
		throws Exception {
		String s_resp = "New empty get encript request for Caesar cypher";
		String hash = db.search(payload.get("username"), payload.get("password"));
		if(payload.get("user_id").equals(hash)) {
			if(payload.get("action").equals("encript")) {
				final int number_substitution = Integer.parseInt(payload.get("substitution"));
				CaesarCipher caesar = classicalCipher.new CaesarCipher(number_substitution);
				String encripted = payload.get("message");
				s_resp = caesar.encryptMessage(encripted);
			}
			System.out.println("Caesar Encription");
		}
		else s_resp = "Error of Invalid token";

		if(payload.get("user_id").equals(hash)) {
			if(payload.get("action").equals("decript")) {
				final int number_substitution = Integer.parseInt(payload.get("substitution"));
				CaesarCipher caesar = classicalCipher.new CaesarCipher(number_substitution);
				String decripted = payload.get("message");
				s_resp = caesar.decryptMessage(decripted);
			}
			System.out.println("Caesar Decription");
		}
		else s_resp = "Error of Invalid token";
		return (s_resp);
	}
	
	@GetMapping(value = "/CaesarPermutation", consumes = "application/json", produces = "application/json")
	public String newCaesarPerm(@RequestBody Map<String, String> payload)
		throws Exception {
		String s_resp = "New empty get encript request for Caesar Perm cypher";
		String hash = db.search(payload.get("username"), payload.get("password"));
		if(payload.get("user_id").equals(hash)) {
			if(payload.get("action").equals("encript")) {
				final int number_substitution = Integer.parseInt(payload.get("substitution"));
				final String permutation = payload.get("permutation");
				CaesarPerCipher caesar = classicalCipher.new CaesarPerCipher(number_substitution, permutation);
				String encripted = payload.get("message");
				s_resp = caesar.encryptMessage(encripted);
			}
			System.out.println("Caesar Perm Encription");
		}
		else s_resp = "Error of Invalid token";

		if(payload.get("user_id").equals(hash)) {
			if(payload.get("action").equals("decript")) {
				final int number_substitution = Integer.parseInt(payload.get("substitution"));
				final String permutation = payload.get("permutation");
				CaesarPerCipher caesar = classicalCipher.new CaesarPerCipher(number_substitution, permutation);
				String decripted = payload.get("message");
				s_resp = caesar.decryptMessage(decripted);
			}
			System.out.println("Caesar Perm Decription");
		}
		else s_resp = "Error of Invalid token";
		return (s_resp);
	}
	
	@GetMapping(value = "/Vigenere", consumes = "application/json", produces = "application/json")
	public String newVigenere(@RequestBody Map<String, String> payload)
		throws Exception {
		String s_resp = "New empty get encript request for Vigenere cypher";
		String hash = db.search(payload.get("username"), payload.get("password"));
		if(payload.get("user_id").equals(hash)) {
			if(payload.get("action").equals("encript")) {
				final String permutation = payload.get("permutation");
				VigenereCipher vigenere = classicalCipher.new VigenereCipher(permutation);
				String encripted = payload.get("message");
				s_resp = vigenere.encryptMessage(encripted);
			}
			System.out.println("Vigenere Encription");
		}
		else s_resp = "Error of Invalid token";
		
		if(payload.get("user_id").equals(hash)) {
			if(payload.get("action").equals("decript")) {
				final String permutation = payload.get("permutation");
				VigenereCipher vigenere = classicalCipher.new VigenereCipher(permutation);
				String decripted = payload.get("message");
				s_resp = vigenere.decryptMessage(decripted);
			}
			System.out.println("Vigenere Decription");
		}
		else s_resp = "Error of Invalid token";
		return (s_resp);
	}
	
	@GetMapping(value = "/Playfair", consumes = "application/json", produces = "application/json")
	public String newPlayfair(@RequestBody Map<String, String> payload)
		throws Exception {
		String s_resp = "New empty get encript request for Playfair cypher";
		String hash = db.search(payload.get("username"), payload.get("password"));
		if(payload.get("user_id").equals(hash)) {
			if(payload.get("action").equals("encript")) {
				final String permutation = payload.get("permutation");
				PlayfairCipher playfair = classicalCipher.new PlayfairCipher(permutation);
				String encripted = payload.get("message");
				s_resp = playfair.encryptMessage(encripted);
			}
			System.out.println("Vigenere Encription");
		}
		else s_resp = "Error of Invalid token";

		if(payload.get("user_id").equals(hash)) {
			if(payload.get("action").equals("decript")) {
				final String permutation = payload.get("permutation");
				PlayfairCipher playfair = classicalCipher.new PlayfairCipher(permutation);
				String decripted = payload.get("message");
				s_resp = playfair.decryptMessage(decripted);
			}
			System.out.println("Vigenere Decription");
		}
		else s_resp = "Error of Invalid token";

		return (s_resp);
	}
}
