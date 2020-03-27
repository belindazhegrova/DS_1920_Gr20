
public class Program {
	
	public static void main(String[] args) throws InterruptedException
	{
			try {
				switch (args[0]) {
			
     case "frequency": Frequency_command.frequency_code(args[1]);
	 break;
	 
	 case "morse-code":switch(args[1]) {
					case "encode": if (args[2].equalsIgnoreCase("--audio"))
							Morse_command.run(args[3]);
						else System.out.println(Morse_command.Morse_Encode(args[2]));
					break;
					case "decode": System.out.println(Morse_command.Morse_Decode(args[2]));
					break;
					default:{
				System.out.println("USAGE: java Program morse-code encode|decode <plaintext|ciphertext> or with alias: ds morse-code encode|decode <plaintext|ciphertext>");
                System.exit(1);}
}
	 break;
	 case "four-square":switch(args[1]) {
					case "encrypt":  System.out.println(Four_Square_command.encrypt(args[2], args[3], args[4]));
					break;
					case "decrypt":  System.out.println(Four_Square_command.decrypt(args[2], args[3], args[4]));
					break;
					default:{
			    System.out.println("USAGE: java Program four-square encrypt|decrypt <plaintext|ciphertext> or with alias: ds four-square encrypt|decrypt <plaintext|ciphertext>");
			    System.exit(1);}
	                       }
	 break;
	 
       default:{
          System.out.println("USAGE: choose one of the command frequency|morse-code|four-square");
          System.exit(1);
        }
				}
				
			}
				catch (ArrayIndexOutOfBoundsException e ) { 
					System.out.println ("There was a mistake during your writing of commands");
			                System.exit(1);
					
				}
			}
}