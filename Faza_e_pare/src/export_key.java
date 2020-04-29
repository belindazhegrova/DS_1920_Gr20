
import java.io.*; 
public class export_key
{ 
  static String privatekey(String name) throws FileNotFoundException
  { String s="keys/";
  String a =s.concat(name);
  String b =a.concat(".xml");

  FileReader fr =  new FileReader(b); 
  int i; 
  String m="";
  try {
	while ((i=fr.read()) != -1) 
	    m=m+(char) i;
	
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} return m;
  } 
  static void prints(String name) throws FileNotFoundException
  {String s = privatekey(name);
	 System.out.println(s); 
  }
 
  
  static String publickey(String name) throws FileNotFoundException
  { String s="keys/";
  String a =s.concat(name);
  String b =a.concat(".pub.xml");

  FileReader fr =  new FileReader(b); 
  int i; 
  String m="";
  try {
	while ((i=fr.read()) != -1) 
	    m=m+(char) i;
}  
  catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
  return m;
  }
  static void printss(String name) throws FileNotFoundException
  {
	  String s = publickey(name);
	 System.out.println(s); 
  }
  static void exportfilepub( String n) throws IOException {
	 String [] t=n.split(" ");
	 String s= "keys/";
	 String thepath=s.concat(t[1]);
	 String content=publickey(t[0]);
	     System.out.println("Celesi public u ruajt ne fajllin " +"'"+ thepath + "'");
	 if(n.endsWith(".xml")==false)
		 printss(t[0]);
	 else {
	  BufferedWriter writer = new BufferedWriter(new FileWriter(thepath));
	    writer.write(content);
	    writer.close();}
     
  }
  static void exportfilepri(String n) throws IOException {
	  String [] t=n.split(" ");
	  String s= "keys/";
		 String thepath=s.concat(t[1]);
		 String content=privatekey(t[0]);
	    System.out.println("Celesi privat u ruajt ne fajllin " +"'"+ thepath + "'");
		 if(n.endsWith(".xml")==false)
			 prints(t[0]);
		 else {
		  BufferedWriter writer = new BufferedWriter(new FileWriter(thepath));
		    writer.write(content);
		    writer.close();
		    }
	     
  }

} 
