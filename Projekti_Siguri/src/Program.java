

public class Program {
	public static void main(String[] args) throws Exception {
		try {
			  

			 dbconnect connect = new dbconnect();
			switch (args[0]) {
		
                        case "create-user":try {
                        	if(args[2].equals("read -s"))
                            	create_user.create2(args[1]);
							
						} catch (Exception e) {
							create_user.create1(args[1]);
						}
                         break;
                        case "delete-user": delete_user.delete1(args[1]);
                       connect.delete(args[1]);
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
                                       
                        case "write-message":
							try {
								if (args[3].equals("--sender")) {
									write_message.write3(args[1], args[2], args[4]);}
								else {
									write_message.write1(args[1], args[2], args[3]);}
							
							}
							catch (Exception e) {
								System.out.println(write_message.write(args[1],args[2]));
							}
							
						
                        break;
                        case "read-message":
                        	
                        		int count = 0;

                        	    for(int i=0; i < args[1].length(); i++)
                        	    {    if(args[1].charAt(i) == '.')
                        	            count++;
                        	    }
								if (count==5) {
									read_message.readmesstoken(args[1]);}
								else {
									read_message.read1(args[1]);
							
							}
						
                        	
                        	
                        break;
                        case "login":try {
                        	if(args[2].equals("read -s"))
                            	login.login1(args[1]);
							
						} catch (Exception e) {
							login.login(args[1]);
						}
                         break;
                        case "status":Status.verify(args[1]);
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
