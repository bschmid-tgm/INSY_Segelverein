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

public class Insert_Boot {

	public Insert_Boot(int length) throws ClassNotFoundException, SQLException, IOException{
		
		String bootsnamen[] = {"Atropos","Bellipotent","Lydia","Pucelle","Sophie","Surprise","Themis","Venus","Endor","Avenger","Dauntless","Surprise","Indefatigable","Providence","Clampherdown","Flower","Ulysses","Viperous","Torrin","King","Fish","ChopChop","Cleopatra","Titanic","Sea Tiger","Bismarck","Hero","Monarch","Novara","Blue Moon","Tegetthoff","Sea Viper","Albatross","Bremse","Cormoran","Magdeburg","Oldenburg","Wien","Deutschland","Dresden","Koenigsberg","Meteor","Vineta"};
		int id = 0;
		int personen = 0;
		double tiefgang = 0;
		
		String insertBoot = "";
		
		Random random = new Random();
		
		BufferedWriter writer = null;
	    writer = new BufferedWriter( new FileWriter( "boot.sql"));
		
	    DB_Connection user_con = new DB_Connection();
	    
	    //if (user_con.connect()) {
	    	
	    int i = 0;
	    
	    String bootsNamen = "";
	    
	    for(i = 0; i < length; i++){
	    	for (int y=0;y<100;y++) {  
	    		id = random.nextInt(1000000000);
	    	}
	    	for(int y=0;y<100;y++){
		    	personen = random.nextInt(25);
	    	}
		    for(int y=0;y<100;y++){ 
			    tiefgang = random.nextInt(200);
			    tiefgang = tiefgang/100;
		    }
	    	
	    	bootsNamen = bootsnamen[random.nextInt(43)];
	    	
	    		System.out.println(id);
	    		insertBoot = "INSERT INTO boot(id,name,personen,tiefgang) VALUES('"+id+"','"+bootsNamen+"','"+personen+"','"+ tiefgang +"');"+"\n";
	    		writer.write(insertBoot+"");
	    }
	  //}
	      writer.close();
	}
	
}
