import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

public class delete_user {

    private KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public delete_user(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keylength);
        
    }

    public void deleteKeys() {
    	
        this.pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
       
        
        
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public void writeToFile(String path, byte[] key) throws IOException {
		File f = new File(path);
		f.getParentFile().mkdirs();

		FileOutputStream fos = new FileOutputStream(f);
        String encoded = Base64.getEncoder().encodeToString(key);
        byte[] b = encoded.getBytes();
		fos.write(key);
		fos.flush();
		fos.close();
    }
    static boolean valid(String name2) {
    	name2 = name2.trim();

	   
	    if(!name2.matches("^[a-zA-Z0-9._%+-]+$")) {
	    	return false;
	    }
	       
   return true;
	
    	
    }
   
  static void deleteuser(String name) {
      delete_user gk;
      if(valid(name)) {
      String name1="keys/";
      String user=name1.concat(name.concat(".xml"));
      String userpub=name1.concat(name.concat(".pub.xml"));
        try {
        	 
        	File f1 = new File(user);
        	File f2 = new File(userpub);
        	if(f1.delete() && f2.delete()) {
        		System.out.println("Eshte larguar celesi privat "+ "'"+ f1 + "'");
        		System.out.println("Eshte larguar publik " + "'" + f2 + "'");
        	}
        	else if(!(f1.delete() && f2.delete())) {
        		System.out.println("Gabim: Celesi " + "'" + name +  "'" + " nuk egziston");
        	}
        	
        	
            gk = new delete_user(1024);
            gk.deleteKeys();
           
        	
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.err.println(e.getMessage());
        }
      }

  }
}

