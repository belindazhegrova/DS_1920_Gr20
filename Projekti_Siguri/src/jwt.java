
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class jwt {

	
	
	
	static String payload(String name) {
		
		    
		    long duration = System.currentTimeMillis() + (1200000);
	        Date resultdate = new Date(duration);
	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        String s = timestamp.toString();
	        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
	        String strDate = dateFormat.format(duration);
	        
	     
            String payload = "{'user':'" + name + "'," +
           	            "'Skadimi':'"+ strDate +"'}";
	        
	        return payload;    
	}
	
	
}
