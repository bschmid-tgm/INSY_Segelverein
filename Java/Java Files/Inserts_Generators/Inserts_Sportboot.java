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

public class Inserts_Sportboot{

		public Inserts_Sportboot(int length) throws IOException, SQLException, ClassNotFoundException {
				
		        String insertSportboot = "";
				
		        int[] id = new int[length];
		        double segelflaeche = 0;
		        double squarefeetQuadratmeter = 10.7639104;
				
				BufferedWriter writer = null;
			    writer = new BufferedWriter( new FileWriter( "sportboot.sql"));
				
			    DB_Connection user_con = new DB_Connection();
			    
			    Random random = new Random();
			    
			    //if (user_con.connect()) {
			    	
			    try{
			    Statement st = user_con.creStm();	
			    ResultSet rs = st.executeQuery("SELECT id FROM boot");
			    
			    int i = 0;
			    	
			    while(rs.next()) {
		    		
	    			id[i] = rs.getInt("id");
	    			System.out.println(id[i]);
	    			
	    			for (int y=0;y<10;y++) {  
			    		segelflaeche = random.nextInt(100)/squarefeetQuadratmeter;
			    		if(segelflaeche == 0.0 || segelflaeche < 2){
			    			segelflaeche = random.nextInt(100)/squarefeetQuadratmeter;
			    		}
			    	}
	    			
	    			if(i < 5000){
	    				
	    					i += 1;
	    			
	    			}else{	    					
	    					insertSportboot = "INSERT INTO sportboot(id,segelflaeche) VALUES('"+id[i]+"','"+segelflaeche+"');"+"\n";
	    					writer.write(insertSportboot+"");
	    					
	    			}
			    }
			    	
			    	writer.close();
			    
			    } 
			    
			    catch (SQLException ex) {
			    	Logger lgr = Logger.getLogger(Main.class.getName());
			    	lgr.log(Level.SEVERE, ex.getMessage(), ex);
			}
		//}
	}
}
