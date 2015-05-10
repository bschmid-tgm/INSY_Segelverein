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

public class Inserts_Trainer{


		public Inserts_Trainer(int length) throws IOException, SQLException, ClassNotFoundException {
			
	        String insertTrainer = "";
			
	        int[] key = new int[length];
			
			BufferedWriter writer = null;
		    writer = new BufferedWriter( new FileWriter( "trainer.sql"));
			
		    DB_Connection user_con = new DB_Connection();
		    
		    try{
		    Statement st = user_con.creStm();	
		    ResultSet rs = st.executeQuery("SELECT key FROM person");
		    
		    int i = 0;
		    
		    	
		    while(rs.next()) {
	    		
    			key[i] = rs.getInt("key");
    			System.out.println(key[i]);
    			
    		if(i < 10000){
    			
    			i += 1;
    			
    		}else{
    			insertTrainer = "INSERT INTO trainer(key) VALUES('"+key[i]+"');"+"\n";
				writer.write(insertTrainer+"");
				
    			}
		    }
		    	
		    	writer.close();
		    
		    } catch (SQLException ex) {
	        Logger lgr = Logger.getLogger(Main.class.getName());
	        lgr.log(Level.SEVERE, ex.getMessage(), ex);

		    }
	}
}

