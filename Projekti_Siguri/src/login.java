import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class login {

	static void login(String name) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
		 java.io.Console console = System.console();
	      String pass1 = console.readLine("Jepni fjalekalimin: ");
	      
              dbconnect connect = new dbconnect();
	      String pass2= connect.login(name);
	  
	 if( hs_password.checkPassword(pass1,pass2)) {
	     System.out.println("Token: "+"true");

	     }
	}

	static String token() {
		return null;
	}
}
