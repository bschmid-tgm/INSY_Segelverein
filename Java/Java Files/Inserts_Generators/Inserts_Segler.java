package Inserts_Generators;

import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
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

public class Inserts_Segler{

	public Inserts_Segler(int length) throws IOException, SQLException, ClassNotFoundException {
        
        String insertSegler = "";
        int[] key = new int[length];
		
		BufferedWriter writer = null;
	    writer = new BufferedWriter( new FileWriter( "segler.sql"));
		
	    DB_Connection user_con = new DB_Connection();
	    
	    try{
	    Statement st = user_con.creStm();	
	    ResultSet rs = st.executeQuery("SELECT key FROM person");
	    
	    int i = 0;
	    
	    for(i = 0; i < length; i++){
	    	while(rs.next()) {
	    		
	    			key[i] = rs.getInt(1);
	    			System.out.println(key[i]);
	    			
	    			if(i < 10000){	
	    				insertSegler = "INSERT INTO segler(key) VALUES('"+key[i]+"');"+"\n";
	    				writer.write(insertSegler+"");
	    				i += 1;
	    			}else{
	    				
	    			}
	    		
	    	}
	    }
	    	
	      writer.close();
	    	
	    } catch (SQLException ex) {
        Logger lgr = Logger.getLogger(Main.class.getName());
        lgr.log(Level.SEVERE, ex.getMessage(), ex);

	    }
	    
	       
   
	}
	
}
