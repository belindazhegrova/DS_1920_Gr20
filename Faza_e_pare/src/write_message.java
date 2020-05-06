import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
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
