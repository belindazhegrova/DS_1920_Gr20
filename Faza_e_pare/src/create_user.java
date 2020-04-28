import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;



public class create_user {

    private KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public create_user(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keylength);
    }

    public void createKeys() {
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
        String p =path.substring(path.indexOf("/") + 1);
          if (f.isFile()) 
        	  System.out.println("Gabim: Celesi "+ "'"+p+"'"+ " ekziston paraprakisht.");
          else {
        	 
        f.getParentFile().mkdirs();
        System.out.println("Eshte krijuar celesi " +"'"+ path + "'");
        FileOutputStream fos = new FileOutputStream(f);
        String encoded = Base64.getEncoder().encodeToString(key);
       
        byte[] b = encoded.getBytes();
        fos.write(b);
        fos.flush();
        fos.close();
        }
    }
    static boolean valid(String name2) {
    	name2 = name2.trim();

	   
	    if(!name2.matches("^[a-zA-Z0-9._%+-]+$")) {
	    	return false;
	    }
	       
   return true;
	
    	
    }
  static void createuser(String name) {
	  create_user gk;
	  if(valid(name)) {
		  String name1="keys/";
		  String user=name1.concat(name.concat(".xml"));
		  String userpub=name1.concat(name.concat(".pub.xml"));

		    try {
		        gk = new create_user(1024);
		        gk.createKeys();
		       
		        gk.writeToFile(user, gk.getPublicKey().getEncoded());
		       
		        gk.writeToFile(userpub, gk.getPrivateKey().getEncoded());
		      
		    } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
		        System.err.println(e.getMessage());
		    } catch (IOException e) {
		        System.err.println(e.getMessage());
		    }
	  }
	  else {
		  System.out.println("Gabim:Emri i celesit duhet te permbaje vetem shkronja, numra dhe underscore");
	  
	  
  }
}
}
