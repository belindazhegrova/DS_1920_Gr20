
import java.awt.Toolkit;
import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Morse_command {
    static	Scanner input = new Scanner(System.in);
	static String morseEncode(char x)  
	 {  
	     switch (x)  
	     { 
	     	 case ' ':
	     		 return "/";
	         case 'a': 
	             return ".-"; 
	         case 'b': 
	             return "-..."; 
	         case 'c': 
	             return "-.-."; 
	         case 'd': 
	             return "-.."; 
	         case 'e': 
	             return "."; 
	         case 'f': 
	             return "..-."; 
	         case 'g': 
	             return "--."; 
	         case 'h': 
	             return "...."; 
	         case 'i': 
	             return ".."; 
	         case 'j': 
	             return ".---"; 
	         case 'k': 
	             return "-.-"; 
	         case 'l': 
	             return ".-.."; 
	         case 'm': 
	             return "--"; 
	         case 'n': 
	             return "-."; 
	         case 'o': 
	             return "---"; 
	         case 'p': 
	             return ".--."; 
	         case 'q': 
	             return "--.-"; 
	         case 'r': 
	             return ".-."; 
	         case 's': 
	             return "..."; 
	         case 't': 
	             return "-"; 
	         case 'u': 
	             return "..-"; 
	         case 'v': 
	             return "...-"; 
	         case 'w': 
	             return ".--"; 
	         case 'x': 
	             return "-..-"; 
	         case 'y': 
	             return "-.--";  
	         case 'z': 
	             return "--.."; 
	     } 
	     return ""; 
	 } 
	
	
	static String Morse_Encode(String s)  
	
     {String t="";
	 	
	     
	     for (int i = 0;i<s.length(); i++) { 
	    	 t=t+ morseEncode(s.charAt(i))+" "; 
	    	 
	    	 
	    }
	     return t;
	 	};
	 	public static void run(String l) throws InterruptedException {
	 	String s=	Morse_Encode(l);
	 	System.out.println(s);
	 	
 	      
 	       for(int p=0;p<s.length();p++)
 		      {
 		     	 if(s.charAt(p) == '.') {
 		     		System.out.print(s.charAt(p));
 		     		 play("..\\src\\beep-02.wav");
 		     		Thread.sleep(250);}
 		     	else if(s.charAt(p) == '-')
 		     	 {
 		     		System.out.print(s.charAt(p));
		     		  play("..\\src\\beep-09.wav");
		     		 Thread.sleep(250);}
 		     else if(s.charAt(p) == ' ')
 				try {System.out.print(s.charAt(p));
 						Thread.sleep(1000);
 					} catch (InterruptedException e) {
 						e.printStackTrace();
 					}
 				else if (s.charAt(p) == '/')
 					try {System.out.print(s.charAt(p));
 						Thread.sleep(1000);
 					} catch (InterruptedException e) {
 						e.printStackTrace();
 					}
 				else 
 		    		 System.out.println("Invalid character");
 		    }
 		 	    
 	     
 	    }
	 	public static void play(String filename)
	 	{
	 	    try
	 	    {
	 	        Clip clip = AudioSystem.getClip();
	 	        clip.open(AudioSystem.getAudioInputStream(new File(filename)));
	 	        clip.start();
	 	    }
	 	    catch (Exception exc)
	 	    {
	 	        exc.printStackTrace(System.out);
	 	    }
	 	}
	          
	         
	 
	
	
	
	
			
		
		 public static String Morse_Decode(String s) {
			 
			
		        String[] convert = s.split(" ");
		        String output="";

		        for (int i = 0; i < convert.length; i++) {
		           
		            switch (convert[i]) {
		            case ".-":
		                output += "a";
		                break;
		            case "-...":
		                output += "b";
		                break;
		            case "-.-.":
		                output += "c";
		                break;
		            case "-..":
		                output += "d";
		                break;
		            case ".":
		                output += "e";
		                break;
		            case "..-.":
		                output += "f";
		                break;
		            case "--.":
		                output += "g";
		                break;
		            case "....":
		                output += "h";
		                break;
		            case "..":
		                output += "i";
		                break;
		            case ".---":
		                output += "j";
		                break;
		            case "-.-":
		                output += "k";
		                break;
		            case ".-..":
		                output += "l";
		                break;
		            case "--":
		                output += "m";
		                break;
		            case "-.":
		                output += "n";
		                break;
		            case "---":
		                output += "o";
		                break;
		            case ".--.":
		                output += "p";
		                break;
		            case "--.-":
		                output += "q";
		                break;
		            case ".-.":
		                output += "r";
		                break;
		            case "...":
		                output += "s";
		                break;
		            case "-":
		                output += "t";
		                break;
		            case "..-":
		                output += "u";
		                break;
		            case "...-":
		                output += "v";
		                break;
		            case ".--":
		                output += "w";
		                break;
		            case "-..-":
		                output += "x";
		                break;
		            case "-.--":
		                output += "y";
		                break;
		            case "--..a":
		                output += "z";
		                break;
		            case "/":
		            	output+=" ";
		            }
		    }
		           
		        return output;
	 }
}
