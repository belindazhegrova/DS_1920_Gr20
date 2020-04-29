
import java.io.*;

import java.io.PrintWriter;


public class import_key {

	  static void privatekey(String name,String path) throws IOException
	  { String s="keys/";
	  String a =s.concat(name);
	  String b =a.concat(".xml");
	  String c= a.concat(".pub.xml");
      String content= importfilepri(path);
  	if(check(content)) {
  String [] split = content.split("<P>");
  String public_part=split[0].concat("\n</RSAKeyValue>");
	 try(PrintWriter writer = new PrintWriter(c)){
	           writer.write(public_part);
	           System.out.println(public_part);
	      }
	}
      
      File f = new File(b);
     
      if (f.isFile()) 
    	  System.out.println("Gabim: Celesi "+ "'"+name+"'"+ " ekziston paraprakisht.");
      else {
	  BufferedWriter writer = new BufferedWriter(new FileWriter(b));
	    writer.write(content);
	    writer.close();
      }
      
	  }
	
	  static boolean check(String sentence) {
		String search = "</DP>";
		  if ( sentence.toUpperCase().indexOf(search.toUpperCase()) != -1 )
			  return true;
		  else
			  return false;
	  }
	  
	  static void publickey(String name, String path) throws IOException
	  { String s="keys/";
	  String a =s.concat(name);
	  String b =a.concat(".pub.xml");
      String content= importfilepub(path);
      File f = new File(b);
      if (f.isFile()) 
    	  System.out.println("Gabim: Celesi "+ "'"+name+"'"+ " ekziston paraprakisht.");
      else {
	  BufferedWriter writer = new BufferedWriter(new FileWriter(b));
	    writer.write(content);
	    writer.close();
	    System.out.println();
      }
	  }
	  static String importfilepub( String n) throws IOException {
		  String t= ("keys/").concat(n);
		  String m="";
		  if(n.endsWith(".xml")==false)
	    	  System.out.println("Gabim: Fajlli i dhene nuk eshte celes valid.");
		  else {
			
		  FileReader fr =  new FileReader(t); 
		  int i; 
		 
		  try {
			while ((i=fr.read()) != -1) 
			    m=m+(char) i;
		
		  } catch (IOException e) {
				
				e.printStackTrace();
			}
		  }return m;
			
		     
		  }
	  static String importfilepri( String n) throws IOException {
		  String t= ("keys/").concat(n);
		  String m="";
		  
		  if(n.endsWith(".xml")==false)
	    	  System.out.println("Gabim: Fajlli i dhene nuk eshte celes valid.");
		  else {
			 
		  FileReader fr =  new FileReader(t); 
		  int i; 
		 
		  try {
			while ((i=fr.read()) != -1) 
			    m=m+(char) i;
		  
		  }
		  catch (IOException e) {
			
				e.printStackTrace();
			} 
		  
		     
		  }
		  return m;
}
	  static void get(String path, String name) throws IOException {
		  String r= (("keys/").concat(path)).concat(".xml");
		String t= url.callURL(name);
		
		 BufferedWriter writer = new BufferedWriter(new FileWriter(r));
		    writer.write(t);
		    writer.close();
		    System.out.println("Celesi publik u ruajt ne fajllin "+ "'" +r +"'");
		
		   
	  }
}