import java.io.File;

public class delete_user {

    static boolean valid(String name2) {
    	name2 = name2.trim();

	    if(!name2.matches("^[a-zA-Z0-9._%+-]+$")) {
	    	return false;
	    	
	    } return true;
    }
    
   static void delete1(String name)throws Exception {

        if(valid(name)) {
        	
        String name1="keys/";
		  String user=name1.concat(name.concat(".xml"));
		  String userpub=name1.concat(name.concat(".pub.xml"));
	        	 
	        	File f1 = new File(user);
	        	File f2 = new File(userpub);
	        	
	        	if(f1.exists()&&f2.exists()) {
	        		f1.delete();
	        		f2.delete();
	        		System.out.println("Eshte larguar celesi privat "+ "'"+ f1 + "'");
	        		System.out.println("Eshte larguar celesi publik " + "'" + f2 + "'");
	        	}
	        	else if(f1.exists()==true && f2.exists()==false)
	        	{
	        		f1.delete();
	        		System.out.println("Eshte larguar celesi privat "+ "'"+ f1 + "'");
	        		
	        	}
	        	else if (f1.exists()==false && f2.exists()==true) {
					f2.delete();
					System.out.println("Eshte larguar celesi publik " + "'" + f2 + "'");
					
				}
	        	else if(!(f1.delete() && f2.delete())) {
	        		System.out.println("Gabim: Celesi " + "'" + name +  "'" + " nuk ekziston");
	        	}
	        }
        else {
        	System.out.println("Gabim: Celesi " + "'" + name +  "'" + " nuk ekziston");
		}
        }  
    }

