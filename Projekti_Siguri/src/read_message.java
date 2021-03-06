
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class read_message {
	
	

	 static void read(String str) throws IOException, ParserConfigurationException, SAXException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException
	  { 
	
		 String[] arrOfStr = str.split("\\.");
		
		
		byte[] name = Base64.getDecoder().decode(arrOfStr[0]);
	    String NAME =  new String(name);
	    
        byte[] iv = Base64.getDecoder().decode(arrOfStr[1]);
        byte[] bytes = Base64.getDecoder().decode(arrOfStr[2]);
        byte[] encryptionM = Base64.getDecoder().decode(arrOfStr[3]); 
       

        System.out.println("Marresi:" + NAME);

	    String s="keys/";
	    String a =s.concat(NAME);
	    String b =a.concat(".xml");
        File f = new File(b);
        if (f.exists()) 
    {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
 		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
 		Document fromdoc = dBuilder.parse(f);

 		String modulus = fromdoc.getElementsByTagName("Modulus").item(0).getTextContent();
 		String exponent = fromdoc.getElementsByTagName("D").item(0).getTextContent();
 		
 		byte[] mod = Base64.getDecoder().decode(new String(modulus).getBytes("UTF-8"));
 		byte[] ex = Base64.getDecoder().decode(new String(exponent).getBytes("UTF-8"));
 		
 		BigInteger M = new BigInteger(1,mod);
 		BigInteger E = new BigInteger(1,ex);
 		
 	    RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(M, E);
 	    
 	    Cipher cipher = Cipher.getInstance("RSA");
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(privateKeySpec);
		cipher.init(Cipher.DECRYPT_MODE, key );
		byte[] ciphertext = cipher.doFinal(bytes);
		
 	    byte[] KEY = ciphertext;
 	    
 	    AlgorithmParameterSpec Iv = new IvParameterSpec(iv);
		Key desKey = new SecretKeySpec(KEY, "DES");
		
		Cipher dCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		
		dCipher.init(Cipher.DECRYPT_MODE, desKey, Iv);
				
	
		byte[] plaintext = dCipher.doFinal(encryptionM);
 	    byte[] theMessage = plaintext;
 	    String  MESSAGE = new String(theMessage, StandardCharsets.UTF_8);
 	    System.out.print("Mesazhi: "+MESSAGE);
	 
 	    
    } else {
         System.out.println("Celesi privat '" + NAME + "' nuk ekziston");
        } 
    }
	static void read1(String str) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException, ParserConfigurationException, SAXException {
		if(str.endsWith(".txt")){

			FileReader fr =  new FileReader(("keys/").concat(str)); 
			  int i; 
			  String m="";
				while ((i=fr.read()) != -1) 
				    m=m+(char) i;
				read(m);
				fr.close();
			}  
		else {
			read(str);
		}
			 
	}
	
	static void readmesstoken(String str) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, IOException, ParserConfigurationException, SAXException, SignatureException {
		String[] emessage = str.split("\\.");
		String part1 = emessage[0] + "."+emessage[1] + "."+ emessage[2]+"."+emessage[3];
		read1(part1);
	
		byte[] s =  Base64.getDecoder().decode(emessage[4]);
 		String Sender = new String(s);
 		
		System.out.println("\nDerguesi: "+Sender);
	
		try {sigv(emessage[3],emessage[4], emessage[5]) ;
			System.out.println("Nenshkrimi: Valid");
			
		} catch (Exception e) {
			System.out.println("Nenshkrimi: mungon celesi publik " + "'"+ Sender +"'");
		}
		
	}
static boolean sigv(String token,String name,String mess) throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		
	
		byte[] s = Base64.getDecoder().decode(token);
		byte[] payload = Base64.getDecoder().decode(name);
		String dpayload = new String(payload);
		String name1 = dpayload;
	
		String dp=new String(s) ;
		
		File file = new File("keys/" + name1 + ".pub.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(file);
        doc.getDocumentElement().normalize();
        String modulus = doc.getElementsByTagName("Modulus").item(0).getTextContent();
        String exponent = doc.getElementsByTagName("Exponent").item(0).getTextContent();
        BigInteger mod = new BigInteger(1,Base64.getDecoder().decode(modulus));
        BigInteger exp = new BigInteger(1,Base64.getDecoder().decode(exponent));
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(mod, exp);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);


        Signature Signature1 = Signature.getInstance("SHA256withRSA");
        Signature1.initVerify(publicKey);
        Signature1.update(dp.getBytes("UTF-8"));
    
        byte[] sBytes = Base64.getDecoder().decode(mess);
       
        return Signature1.verify(sBytes);
	}
}
 
  

