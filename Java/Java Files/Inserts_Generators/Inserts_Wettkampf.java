package Inserts_Generators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import segelverein.DB_Connection;
/**
 * 
 * 
 * 
 * @author Schmidi
 *
 */

public class Inserts_Wettkampf{
	
	public Inserts_Wettkampf(int length) throws IOException, SQLException, ClassNotFoundException {
	     
		  String wettfahrtName[] = {""};
	      String name = "";
	      String datum = "";
	      String insertRegatta = "";
	      
	      
	      
	      double laenge = 0;
	      int jahr = 0000;
	      int monat = 0;
	      int tag = 0;
	      int i = 0;
	      
	      Random random = new Random();
	      
	      BufferedWriter writer = null;
	      
		    writer = new BufferedWriter( new FileWriter( "wettfahrt.sql"));
			
		    DB_Connection user_con = new DB_Connection();
		    
		    //if (user_con.connect()) {
		    	
		    Statement st = user_con.creStm();	
		    ResultSet rs = st.executeQuery("SELECT * FROM regatta");

		    while(rs.next()){
		    	
		    		laenge = random.nextInt(100);
		    		
		    		if(laenge <= 0){
		    			laenge = random.nextInt(100);
		    		}
		    		
		    	for(int t = 0; t < length; t++){
		    		tag = random.nextInt(31-1) + 1;
		    	}
		    	for(int t = 0; t < length; t++){
		    		monat = random.nextInt(12-1) + 1;
		    	}
		        
		        // Da der Monat Februar nicht mehr als 28 Tage (und das auch nur beim Schaltjahr) besitzt 
		        // Setzte  ich hier ein if() welches eben ueberpruefen soll ob es sich um ein invalides Datum handelt!
		        
		        if(tag >= 29 && monat == 2){
		        	for(int t = 0; t < length; t++){
			    		tag = random.nextInt(31-1) + 1;
			    	}
			    	for(int t = 0; t < length; t++){
			    		monat = random.nextInt(12-1) + 1;
			    	}
			    	if(tag >= 29 && monat == 2){
			    		for(int t = 0; t < length; t++){
				    		tag = random.nextInt(31-1) + 1;
				    	}
			    	}
		        }
		        
		    	name = rs.getString("name");
		    	jahr = rs.getInt("jahr");
		    	
		    	datum = ""+jahr+"-"+monat+"-"+tag;
		    	
		    	System.out.println(datum);
		    	
	        	insertRegatta = "INSERT INTO wettfahrt(name,jahr,datum,laenge) VALUES('"+name+"','"+jahr+"','"+datum+"','"+laenge+"');" + "\n";
	        	writer.write(insertRegatta+"");  
		  }
	      writer.close();
	        
	      //}
	 }		
}
