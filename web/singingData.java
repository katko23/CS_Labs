package CS.web;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;  


public class singingData {
	public String search(String username, String pass) {
	try{  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://127.0.0.1:3306/pbl" , "root","Sininkii2305200");// root is username and password  
		Statement stmt=con.createStatement();  
		ResultSet rs=stmt.executeQuery("Select *\r\n"
				+ "from pbl.users\r\n"
				+ "where users.UserName =" +"\""+ username +"\"");  
		while(rs.next()) { 
			String currusername = rs.getString(2);
			String currpass = rs.getString(3);
			String currtok = rs.getString(4);
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4));  
		return currtok;
		}
		con.close();  
		}catch(Exception e){ System.out.println(e);}  
	return "Error";
	}
	
	private void WriteDB(String username, String pass, String hash) {
		try{  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://127.0.0.1:3306/pbl" , "root","Sininkii2305200");// root is username and password  
			Statement stmt=con.createStatement();  
			stmt.executeUpdate("INSERT INTO `pbl`.`users` (`UserName`, `Password`, `HashId`) VALUES "
					+ "('"+ username + "', '" + pass +"', '"+hash+"');\r\n");  
			  
			con.close();  
			}catch(Exception e){ System.out.println(e);} 
	}
	
	String signingDataUp(String username, String pass) throws NoSuchAlgorithmException {
		String originalString = username+pass;
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] encodedhash = digest.digest(
				originalString.getBytes(StandardCharsets.UTF_8));
		String s_encodedhash = Base64.getEncoder().encodeToString(encodedhash);
		System.out.println(s_encodedhash);
		WriteDB(username , pass, s_encodedhash);
		
		return s_encodedhash;
	}
	
	String signingDataIn(String userName, String pass) {
		String sdata = search(userName, pass);
		System.out.println(sdata);
		if(sdata != "Error") return sdata;
		return "Error";
	}
	
}
