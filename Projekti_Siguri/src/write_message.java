
import java.io.File;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
public class write_message{
		
public static String write(String name, String message) throws ParserConfigurationException, SAXException, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException {
		

		SecureRandom sr = new SecureRandom();
		byte[] IV = new byte[8];
		sr.nextBytes(IV);
	
		IvParameterSpec iv = new IvParameterSpec(IV);
		SecretKey key = KeyGenerator.getInstance("DES").generateKey();

		 String s="keys/";
		 String a =s.concat(name);
		 String b =a.concat(".pub.xml");
	     File f = new File(b);
		
		if(!f.exists()) {
			System.out.println("Gabim: Celesi publik "+name+" nuk ekziston.");
			System.exit(1);
		}
		AlgorithmParameterSpec iv2= iv;
		Base64.Encoder encoder = Base64.getEncoder(); 
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv2);
		byte[] mes = message.getBytes("UTF8");
		byte[] ciphertext = cipher.doFinal(mes);
		String EncryptedMessage = encoder.encodeToString(ciphertext);
		
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document document = dBuilder.parse(f);
		
		String modulus = document.getElementsByTagName("Modulus").item(0).getTextContent();
		String exponent = document.getElementsByTagName("Exponent").item(0).getTextContent();
		
		byte[] mod = Base64.getDecoder().decode(new String(modulus).getBytes("UTF-8"));
		byte[] ex = Base64.getDecoder().decode(new String(exponent).getBytes("UTF-8"));
		
		BigInteger M = new BigInteger(1,mod);
		BigInteger E = new BigInteger(1,ex);
		
		
		RSAPublicKeySpec pKeySpec =new RSAPublicKeySpec(M, E);
		
		Cipher cipher1 = Cipher.getInstance("RSA");
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		RSAPublicKey key1 = (RSAPublicKey) keyFactory.generatePublic(pKeySpec);
		cipher1.init(Cipher.ENCRYPT_MODE, key1);
		byte[] mes1 = key.getEncoded();
		cipher1.update(mes1);
		byte[] emessage = cipher1.doFinal();
		
		String EncryptedKey = encoder.encodeToString(emessage);
		String NAME = encoder.encodeToString(name.getBytes("UTF8"));
		String Iv = encoder.encodeToString(IV);
		  
		String message1= (((NAME.concat(".")).concat(Iv).concat(".")).concat(EncryptedKey.concat("."))).concat(EncryptedMessage);
	
		return message1;
	}

    static void write1(String name, String message, String path) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException, ParserConfigurationException, SAXException {
    	if(path.endsWith(".txt"))
		{
			
			 FileWriter file = new FileWriter(("keys/").concat(path));
			   file.write(write(name, message));
				    file.close();
				    System.out.println("Celesi publik u ruajt ne fajllin "+ "'" +path +"'");
		}else {
		
			System.out.println("Fajlli" +"'" +path +"'"+ "nuk eshte valid." );
		}
    }
   
    static void write3(String name, String message,String token) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeySpecException, ParserConfigurationException, SAXException, IOException, SignatureException {
       String[] token1= token.split("\\.");
      
      
       
       
       byte[] payload = Base64.getDecoder().decode(token1[0]);
		String data = new String(payload);
		String[] name1 = data.split("'");
		String name2 = name1[3];
		Base64.Encoder encoder = Base64.getEncoder();
		String Sender = encoder.encodeToString(name2.getBytes("UTF8"));
      
        String part1 = write(name,message);
    	String[] part= part1.split("\\.");
        String base64mess= part[3];
    	byte[] mess = Base64.getDecoder().decode(base64mess);
        String messi = new String(mess);
        String signmess =  rsa(name2, messi);
      
        
        
        
        
        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date resultdate = new Date(currentTimeMillis);
        String string = sdf.format(resultdate);

        String part4 = part1.concat("."+Sender+"." + signmess);
        int compareTO = string.compareTo(name1[7]);
        if (compareTO < 0) {
        	System.out.println( part4);
        	}
        else {
        	System.out.println( part1);
 		}
           
}

    static String rsa(String name, String message1) throws NoSuchAlgorithmException, InvalidKeySpecException, SAXException, IOException, ParserConfigurationException, InvalidKeyException, SignatureException {
      	 
    	  File file = new File("keys/" + name + ".xml");

          DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
          DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
          Document doc = docBuilder.parse(file);
          doc.getDocumentElement().normalize();
          String moduliS = doc.getElementsByTagName("Modulus").item(0).getTextContent();
          String D = doc.getElementsByTagName("D").item(0).getTextContent();
          BigInteger modulus = new BigInteger(1,Base64.getDecoder().decode(moduliS));
          BigInteger d = new BigInteger(1,Base64.getDecoder().decode(D));


          RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(modulus, d);
          KeyFactory keyFactory = KeyFactory.getInstance("RSA");
          PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
          Signature signature1 = Signature.getInstance("SHA256withRSA");
          signature1.initSign(privateKey);   
          signature1.update((message1).getBytes("UTF-8"));
          byte[] signature = signature1.sign();
          String ssignature = Base64.getEncoder().encodeToString(signature);
          
          
          return ssignature;
          }  


}
