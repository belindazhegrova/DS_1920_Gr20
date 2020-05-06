
public class Program {
	public static void main(String[] args) throws Exception {
		try {
			switch (args[0]) {
		
                        case "create-user": create_user.create1(args[1]);
                         break;
                        case "delete-user": delete_user.delete1(args[1]);
                        break;
                        case "export-key": 
                        	switch(args[1]) {
                        case "public" :
                        	try {
                        	String n= (args[2].concat(" ")).concat(args[3]);
                        	
                        	export_key.exportfilepub(n);
                        	}
                        	catch (ArrayIndexOutOfBoundsException e)
                        	{
                        		export_key.printss(args[2]);
							}
                        	
                        break;
                        case "private":
                        	try {
                            	String r= (args[2].concat(" ")).concat(args[3]);
                            	export_key.exportfilepri(r);
                            	}
                            	catch (ArrayIndexOutOfBoundsException e)
                        	   {
                            		export_key.prints(args[2]);
    						   }
                        break;

                         default:
                          {
                        	 System.out.println("Gabim1");
                           System.exit(1);
                          }
			                                   }
                        
                        break;
                        case "import-key":
                        	if(args[2].startsWith("http"))
                        	{
                        	import_key.get(args[1],args[2]);
                        	}
                        else if ((args[2].endsWith(".xml")) && !(args[2].endsWith(".pub.xml")))
                        {
							import_key.privatekey(args[1], args[2]);
						}
                        else 
                        	import_key.publickey(args[1], args[2]);
                        	
                        break;
                                       
                        case "write-message":try{write_message.write1(args[1], args[2], args[3]);}
                        catch (Exception e) {
							write_message.write2(args[1],args[2]);
						}
                        break;
                        case "read-message": read_message.read1(args[1]);
                        break;
                        default:
                        {
                        	System.out.println("Ka ndodhur nje problem");
                        	System.exit(1);
                        }
			}
			}
			catch (ArrayIndexOutOfBoundsException e ) { 
				
		               System.out.println("Gabim2");
				System.exit(1);
			          }
	                         }
          }
