
import java.sql.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class dbconnect {

	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	
	public dbconnect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 String driver = "com.mysql.jdbc.Driver";
			    String url    = "jdbc:mysql://localhost:3307/sig2020?autoReconnect=true&useSSL=false";
			    String username = "root";
			    String password = "";            // Change it to your Password
			    System.setProperty(driver,"");
     
			    con= DriverManager.getConnection(url,username,password);
			   
			System.out.println("Connection succeded!");
			
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
	}
	
	//inserting data
public void insert(String kname,String pass) {
		try {
			
			 st = (Statement) con.createStatement();
			 String query1 = "INSERT INTO users(kname,name,password) " + "VALUES ("+"'"+kname+"',"+" '"+kname+"',"+" '"+pass+"')";
		     st.executeUpdate(query1);
		     
			      
		} catch (Exception e) {
			System.out.println("Error:" + e);
		}
	}
	
	//deleting data
	public void  delete(String name) throws SQLException {
		try {
		st = (Statement) con.createStatement();
		String query = "delete from users where kname = '" + name + "'";
		st.executeUpdate(query);
		}
		catch (Exception e) {
			System.out.println("Error:" + e);		
			}
	}
	//user login
	public String  login(String name) throws SQLException {
		
		st = (Statement) con.createStatement();
		String query = "select * from users where kname = '" + name + "'";
		 rs = st.executeQuery(query);
		 String pass2="";
	      // iterate through the java resultset
	      while (rs.next())
	      {
	         pass2 = rs.getString("password");
	
	      }
		
		
	      return pass2;
	      
	}
	
}
