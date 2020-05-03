import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.lang.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Random;

public class write_message {
	
	 static void write_message1(String name, String str, String path) throws IOException, ParserConfigurationException, SAXException, InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException
	  { 
		 byte arr[] = name.getBytes("UTF8");
		 String NAME = Base64.getEncoder().encodeToString(arr);
		 
	  String s="keys/";
	  String message=" ";
	  String a =s.concat(name);
	  String b =a.concat(".pub.xml");
     File f = new File(b);
     if (f.exists()) 
     {
    	 SecureRandom srandom = new SecureRandom();
 	  byte[] iv = new byte[8];
 	srandom.nextBytes(iv);
 	 String IV = Base64.getEncoder().encodeToString(iv);
 	 
 	 
 	 File file = new File(b);
 	 
 	 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
     DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
     Document doc = docBuilder.parse(file);
     doc.getDocumentElement().normalize();
 	
 	// keys me 8 bytes
 	Random randomkey = new Random();
 	 byte[] keys = new byte[8];
 	randomkey.nextBytes(keys);
 	 KeySpec myKeySpec = new DESKeySpec(keys);
     SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
     SecretKey celesi = secretKeyFactory.generateSecret(myKeySpec); 
     
     String moduliS = ((org.w3c.dom.Document) doc).getElementsByTagName("Modulus").item(0).getTextContent();
     String exponentAsString = ((org.w3c.dom.Document) doc).getElementsByTagName("Exponent").item(0).getTextContent();

     
     
    BigInteger modulus = new BigInteger(1,Base64.getDecoder().decode(moduliS));
    BigInteger exponent = new BigInteger(1,Base64.getDecoder().decode(exponentAsString));
 

 	
    
      
     
	

	
	

    //formimi i RSA publci key nga file ne xml
    RSAPublicKeySpec keySpec = new RSAPublicKeySpec(modulus, exponent);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PublicKey pubKey = keyFactory.generatePublic(keySpec);

    //enkriptimi i rsa key me key
    Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
    cipher.init(Cipher.ENCRYPT_MODE, pubKey);
    byte[] encryptedKe = cipher.doFinal(celesi.getEncoded());
    String encryptedKey = Base64.getEncoder().encodeToString(encryptedKe);

    //enkriptimi i mesazhit me key
    Cipher encryptedcipher = Cipher.getInstance("DES");
    encryptedcipher.init(Cipher.ENCRYPT_MODE, celesi);
    byte[] utf8 = message.getBytes("UTF8");
    byte[] enc = encryptedcipher.doFinal(utf8);
    enc = Base64.getEncoder().encode(enc);
    String encryptedWord = Base64.getEncoder().encodeToString(enc);




    System.out.println(NAME + "." + IV + "." + encryptedKey + "." + encryptedWord);
 
   String message1= (((NAME.concat(".")).concat(IV).concat(".")).concat(encryptedKey.concat("."))).concat(encryptedWord);
   String path1 = "keys/";
   FileWriter file1 = new FileWriter(path1.concat(path));
   file1.write(message1);
	    file1.close();
	    System.out.println("Celesi publik u ruajt ne fajllin "+ "'" +path +"'");
} else {
    System.out.println("Qelesi publik '" + name + "' nuk ekziston");
}
     
     }
     }
	 