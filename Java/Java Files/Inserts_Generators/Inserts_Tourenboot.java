package Inserts_Generators;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import segelverein.DB_Connection;
import segelverein.Main;


/**
 * 
 * 
 * @author Schmidi
 *
 */

public class Inserts_Tourenboot{

		public Inserts_Tourenboot(int length) throws IOException, SQLException, ClassNotFoundException {
			
	        String insertTourenboot = "";
	        int[] id = new int[length];
	        String[] bootsklasse = {"Platu 25","H-Boot","Drachen","Cadet","Vela Latina","20-m-Rennjolle","Internationales 14-Fuﬂ-Dinghy","Contender","IMS Rennyacht","Katameran","Jolle","International 806","D‰nische Nationale Klasse","Dynamic","Flash","Knarr-Boot","Open 60 Klasse"};
	        String bootsKlasse = "s";
	        
	        Random random = new Random();
			
			BufferedWriter writer = null;
		    writer = new BufferedWriter( new FileWriter( "tourenboot.sql"));
			
		    DB_Connection user_con = new DB_Connection();
		    
		    //if (user_con.connect()) {
		    
		    try{
		    	
		    	Statement st = user_con.creStm();	
		    	ResultSet rs = st.executeQuery("SELECT key FROM trainer");
		    
		    int i = 0;
		    	
		    while(rs.next()) {
	    	
		    if(i < 5000){
    			id[i] = rs.getInt("id");
    			System.out.println(id[i]);
    			
    			bootsKlasse = bootsklasse[random.nextInt(17)];
    			insertTourenboot = "INSERT INTO tourenboot(id,bootsklasse) VALUES('"+id[i]+"','"+bootsKlasse+"');"+"\n";
				writer.write(insertTourenboot+"");
				
				i += 1;
		    	}
		    }
		    
		    	writer.close();
		    	
		    } catch (SQLException ex) {
	        Logger lgr = Logger.getLogger(Main.class.getName());
	        lgr.log(Level.SEVERE, ex.getMessage(), ex);

		    }
		//}
	}
}
