
import java.util.Scanner;
import java.util.stream.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Frequency_command {
	
	  static void frequency_code(String str) {
   	  
   	   
	    	int counter[] = new int[256];
	         str = str.replaceAll("\\s","");
	        char symbol='#';
	        String len="";
	        
	         for (int i = 0; i < str.length(); i++) {
	            counter[(int) str.charAt(i)]++;
	            len=len+symbol;
	         }
	        
	         System.out.println("Total= "+str.length());
	      
	         
	        int t;
	        for (int j = 0; j < 256; j++)  {
	            if (counter[j] != 0 && counter[j]!=' ') {
	       
	            	double l=(((double)counter[j]/str.length())*100);
	                   System.out.printf( (char) j + "  " + counter[j]  + " "+ "%.2f",l);
	                   System.out.print("%");
	                   System.out.println("");
	                
	            }
	        }  
	     
	       System.out.println("");
	       
	      
	        for (int j = 0; j < 256; j++)  {
	            if (counter[j] != 0 && counter[j]!=' ') {
	            	 t=(int)(((double)counter[j]/str.length())*100-((double)counter[j]/str.length())*80);
	            	
	         
	                   double q=(((double)counter[j]/str.length())*100);
	                   System.out.printf((char) j + ": ["+ len.substring(0,t) +"   ]"+"%.2f",q);
	                   System.out.print("%");
	                   System.out.println("");
	                 
	               }
	          }  
	       }

          }
