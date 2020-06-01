import org.mindrot.jbcrypt.BCrypt;

public class  hs_password{
	

	


	public static  String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(15);
		String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return hashed_password;
	}

	
	public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
	}
	
	

}



/*import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class hs_password {
	
	
	
	
	public String hs_password(String password) throws NoSuchAlgorithmException, InvalidKeySpecException{
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		
		
		
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
	    SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
	    byte[] hash = f.generateSecret(spec).getEncoded();
	    
	    return String.valueOf(hash);

		
	}
	
	

}
*/