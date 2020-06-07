
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Base64;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


 

public class login {

	static void login(String name) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, SAXException, IOException, ParserConfigurationException {
		 java.io.Console console = System.console();
	      String pass1 = console.readLine("Jepni fjalekalimin: ");
	     
	  
	      
	      dbconnect connect = new dbconnect();
	      String pass2= connect.login(name);
	  String token = token(name);
	 if( hs_password.checkPassword(pass1,pass2)) {
	     System.out.println("Token: "+ token);
	     }
	 FileWriter file = new FileWriter(("tokens/").concat(name.concat(".txt")));
	   file.write(token);
		    file.close();
	}
	static void login1(String name) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, SAXException, IOException, ParserConfigurationException {
		 java.io.Console console = System.console();
		   String pass =new  String(console.readPassword("Jepni fjalekalimin: "));
	     
	  
	      
	      dbconnect connect = new dbconnect();
	      String pass2= connect.login(name);
	  
	 if( hs_password.checkPassword(pass,pass2)) {
	     System.out.println("Token: "+token(name));
	     }
	}
	
	
	static String token(String name) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, SAXException, IOException, ParserConfigurationException {
		
		String payload = Base64.getEncoder().encodeToString(jwt.payload(name).getBytes());
		String token= payload+"."+RsaSignature.rsa(name);
		
		
		return token;
	}
}
