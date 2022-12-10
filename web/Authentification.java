package CS.web;

import java.util.Map;
import CS.web.singingData;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.JSONWrappedObject;

@RestController
public class Authentification {
	singingData db = new singingData();
	
	@PostMapping(value = "/signin", consumes = "application/json", produces = "application/json")
	public String signIn(@RequestBody Map<String, String> payload) 
		    throws Exception {
		System.out.println(payload);
		String token = db.signingDataIn(payload.get("username"), payload.get("password"));
		return ("{'user_id':/'"+token+"/'}");
	}
	
	@PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
	public String signUp(@RequestBody Map<String, String> payload)
	throws Exception{
		System.out.println("User wanna Sign Up");
		System.out.println(payload);
		String token = db.signingDataUp(payload.get("username"), payload.get("password") );
		return ("{ 'user_id':'"+token+"}");
	}
	
	@PostMapping(value = "/signout", consumes = "application/json", produces = "application/json")
	public String signOut(@RequestBody Map<String, String> payload) 
		    throws Exception {
		
		return ("{'user_id':'None'}");
	}
	
}
