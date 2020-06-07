import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import static java.nio.charset.StandardCharsets.UTF_8;
public class Status {
	
	
	static void verify(String token) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, ParserConfigurationException, SAXException, IOException {
		
		
		
		String[] tokparts= token.split("\\.");
		
		byte[] payload = Base64.getDecoder().decode(tokparts[0]);
		String data = new String(payload);
		String[] name = data.split("'");    
	
		if (sigv(token)) {
			System.out.println("Name: "+name[3]);
			
			validd(name[7]);
			
			
		}	
	}
	
	
	static boolean sigv(String token) throws ParserConfigurationException, SAXException, IOException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
		
		String[] tokparts= token.split("\\.");
		byte[] payload = Base64.getDecoder().decode(tokparts[0]);
		String dpayload = new String(payload);
		String[] name = dpayload.split("'");
		String name1 = name[3];
		
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
        Signature1.update((dpayload).getBytes("UTF8"));
    
        byte[] sBytes = Base64.getDecoder().decode(tokparts[1]);
       
        return Signature1.verify(sBytes);
	}
	
	static void validd(String exp) {
		 long currentTimeMillis = System.currentTimeMillis();
         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
         Date currentDate = new Date(currentTimeMillis);
         String Date = sdf.format(currentDate);

         int cDateToInt = Date.compareTo(exp);
         if (cDateToInt < 0) {
             System.out.println("Valid: Po");
             System.out.println("Skadimi: " + exp);}
         else {
        	 System.out.println("Valid: Jo");
		}
	}
	

}
