import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Base64;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import static java.nio.charset.StandardCharsets.UTF_8;
public class RsaSignature {


static String rsa(String name) throws NoSuchAlgorithmException, InvalidKeySpecException, SAXException, IOException, ParserConfigurationException, InvalidKeyException, SignatureException {
	 
	  File file = new File("keys/" + name + ".xml");

      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc = docBuilder.parse(file);
      doc.getDocumentElement().normalize();
      String modulus = doc.getElementsByTagName("Modulus").item(0).getTextContent();
      String exponent = doc.getElementsByTagName("D").item(0).getTextContent();
      BigInteger mod = new BigInteger(1,Base64.getDecoder().decode(modulus));
      BigInteger exp= new BigInteger(1,Base64.getDecoder().decode(exponent));
      RSAPrivateKeySpec keySpec = new RSAPrivateKeySpec(mod, exp);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
      

      Signature signature1 = Signature.getInstance("SHA256withRSA");
      signature1.initSign(privateKey);
      
      
      signature1.update((jwt.payload(name)).getBytes("UTF8"));
      byte[] signature = signature1.sign();
      String ssignature = Base64.getEncoder().encodeToString(signature);
      
      
      return ssignature;
}

}
