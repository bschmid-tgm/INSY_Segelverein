	package Inserts_Generators;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import segelverein.DB_Connection;
import segelverein.Main;

public class Inserts_erzielt{

		public Inserts_erzielt(int length) throws IOException, SQLException, ClassNotFoundException {
			
	        String insertErzielt = "";

	        String mname = "";
	        String wname = "";
	        String datum = "";
	        
	        int jahr = 0;
	        int punkteStand = 0;
	        
	        int[] id = new int[length+1];
			
			BufferedWriter writer = null;
		    writer = new BufferedWriter( new FileWriter( "erzielt.sql"));
			
		    DB_Connection user_con = new DB_Connection();
		    
		    Random random = new Random();
		    
		    //if (user_con.connect()) {
		    	
		    try{
		    Statement st = user_con.creStm();	
		    ResultSet rs = st.executeQuery("SELECT * FROM wettfahrt");
		    Statement st2 = user_con.creStm();
		    ResultSet rs2 = st2.executeQuery("SELECT name FROM mannschaft");
		    
		    int i = 0;
		    
	
		    while(rs.next() && rs2.next()){

			        mname = rs2.getString("name");
			        wname = rs.getString("name");
			        datum = rs.getString("datum");
			        jahr = rs.getInt("jahr");
			        
			        for (int y=0;y<10;y++) {  
			    		punkteStand = random.nextInt(100);

			    	}
			        
			        System.out.println(punkteStand);	
			        		

				if(i < length){
			        i += 1;
				} 
					//System.out.println(i);
					insertErzielt = "INSERT INTO erzielt(mname,wname,wjahr,wdatum,punkte) VALUES('"+mname+"','"+wname+"','"+jahr+"','"+datum+"','"+punkteStand+"');"+"\n";
					writer.write(insertErzielt+"");
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
