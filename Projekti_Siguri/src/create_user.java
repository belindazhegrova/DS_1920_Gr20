
import java.io.File;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class create_user {

    static final String KEY_ALGORITHM = "RSA";
    static final int KEY_LENGTH = 1024;

    static final String NL = System.getProperty("line.separator");
    static boolean valid(String name2) {
    	name2 = name2.trim();

	   
	    if(!name2.matches("^[a-zA-Z0-9._%+-]+$")) {
	    	return false;
	    }
	       
   return true;
	
    	
    }

    

   static void create1(String name)throws Exception {
        KeyPair keyPair = createKeyPair(KEY_LENGTH);
        
        if(valid(name)) {
        	
        String name1="keys/";
		  String user=name1.concat(name.concat(".xml"));
		  String userpub=name1.concat(name.concat(".pub.xml"));
		   File f1 = new File(user);
                   File f2 = new File(userpub);
	    	
	    	 String p =name.substring(name.indexOf("/") + 1);
			  if (f1.exists()&&f2.exists()) {
	       	  System.out.println("Gabim: Celesi "+ "'"+p+"'"+ " ekziston paraprakisht.");
			  }
	         else  {
	       System.out.println("Eshte krijuar celesi privat " +"'"+ f1 + "'");
	       System.out.println("Eshte krijuar celesi publik " +"'"+ f2 + "'");

	       

	         }
		
    
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        String privateKeyAsXml = getPrivateKeyAsXml(privateKey);
       
        writeFile(privateKeyAsXml,user);
        String publicKeyAsXml = getPublicKeyAsXml(publicKey);
    
        writeFile(publicKeyAsXml, userpub);}
        else {
        	 System.out.println("Gabim:Emri i celesit duhet te permbaje vetem shkronja, numra dhe underscore");
		}
       
   }

    static KeyPair createKeyPair(int keyLength) throws NoSuchAlgorithmException {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keygen.initialize(keyLength, new SecureRandom());
        KeyPair keyPair = keygen.generateKeyPair();
        return keyPair;
    }

    static String getPrivateKeyAsEncoded(PrivateKey privateKey){
        byte[] privateKeyEncodedBytes = privateKey.getEncoded();
        return getBase64(privateKeyEncodedBytes);
    }

    static String getPublicKeyAsEncoded(PublicKey publicKey){
        byte[] publicKeyEncoded = publicKey.getEncoded();
        return getBase64(publicKeyEncoded);
    }

    static String getPrivateKeyAsXml(PrivateKey privateKey) throws Exception{
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        RSAPrivateCrtKeySpec spec = keyFactory.getKeySpec(privateKey, RSAPrivateCrtKeySpec.class);
        StringBuilder sb = new StringBuilder();
         sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
        sb.append("<RSAKeyValue>" + NL);
        sb.append(getElement("Modulus", spec.getModulus()));
        sb.append(getElement("Exponent", spec.getPublicExponent()));
        sb.append(getElement("P", spec.getPrimeP()));
        sb.append(getElement("Q", spec.getPrimeQ()));
        sb.append(getElement("DP", spec.getPrimeExponentP()));
        sb.append(getElement("DQ", spec.getPrimeExponentQ()));
        sb.append(getElement("InverseQ", spec.getCrtCoefficient()));
        sb.append(getElement("D", spec.getPrivateExponent()));
        sb.append("</RSAKeyValue>");

        return sb.toString();
    }

    static String getPublicKeyAsXml(PublicKey publicKey) throws Exception{
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        RSAPublicKeySpec spec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
        StringBuilder sb = new StringBuilder();

        sb.append("<RSAKeyValue>" + NL);
        sb.append(getElement("Modulus", spec.getModulus()));
        sb.append(getElement("Exponent", spec.getPublicExponent()));
        sb.append("</RSAKeyValue>");

        return sb.toString();
    }

    static String getElement(String name, BigInteger bigInt) throws Exception {
        byte[] bytesFromBigInt = getBytesFromBigInt(bigInt);
        String elementContent = getBase64(bytesFromBigInt);
        return String.format("  <%s>%s</%s>%s", name, elementContent, name, NL);
    }

    static byte[] getBytesFromBigInt(BigInteger bigInt){
        byte[] bytes = bigInt.toByteArray();
        int length = bytes.length;

        if(length % 2 != 0 && bytes[0] == 0) {
            bytes = Arrays.copyOfRange(bytes, 1, length);
        }

        return bytes;
    }

    static String getBase64(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    static void writeFile(String text, String filename) throws Exception{
    	
        try(PrintWriter writer = new PrintWriter(filename)){
            writer.write(text);
        }
    }

    static void print(String line){
        System.out.println(line);
    }
}
