package Inserts_Generators;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * 
 * @author Schmidi
 *
 */

public class Inserts_person {

		public Inserts_person(int length) throws IOException {
		      String nachnamen[] = {"Schmid", "Gruber", "Sprung", "Strasser", "Pichler", "Weber", "Wagner", "Fischer", "Schneider", "Hoffmann", "Klein", "Wolf", "Zimmermann", "Koch", "Richter", "Peters", "Möller", "Weiß", "Schubert", "Vogel", "Vogt", "Jäger", "Kraus", "Baumann", "Arnold", "Schulte", "Graf", "Ziegler", "Lennon", "Harrison", "Wood", "Jagger", "Baker", "Cole", "Smith", "Simpson"};
		      String vornamen[] = {"Bernhard", "Lukas", "Dominik", "Mia", "Hanna", "Johannes", "Maximilian", "Moritz", "David", "Jan", "Oskar", "Florian", "Hannes", "KEVIN", "Frieda", "Jana", "Maria", "Isabella", "Felix", "Noah", "Vogt", "WeißNixMehr", "Benjamin", "Ernst", "Edith", "Phillip", "Romy", "Zlatan", "Arnold", "Joseph", "Miriam", "Tim", "Antonio", "Christoph"};
		      String geburtsdatum = "";
		      String nachName = "";
		      String vorName = "";
		      String insertPerson = "";
		      
		      int key = 0;
		      int tag = 0;
		      int monat = 0;
		      int jahr = 0000;
		      int i = 0;
		      
		      Random random = new Random();
		      
		      BufferedWriter writer = null;
		      
		    	  writer = new BufferedWriter( new FileWriter( "person.sql"));
		      
		      for (i=0;i<length;i++) {
		    	for (int y=0;y<10;y++) {  
		    		key = random.nextInt(1000000000);
		    	}
		    	for(int t = 0; t < length; t++){
		    		tag = random.nextInt(31-1) + 1;
		    	}
		    	for(int t = 0; t < length; t++){
		    		monat = random.nextInt(12-1) + 1;
		    	}
		    	for(int t = 0; t < length; t++){
		    		jahr = random.nextInt(2015-1915) + 1915;
		    	}

		        nachName = nachnamen[random.nextInt(28)];
		        vorName = vornamen[random.nextInt(28)];
		        
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
			    	i = i-1;
		        }else{
		        	geburtsdatum = jahr+ "-" + monat + "-" + tag;
		        	insertPerson = "INSERT INTO person(key,name,geburtsdatum) VALUES('"+key+"','"+vorName+" "+nachName+"',DATE '"+ geburtsdatum +"');"+"\n";
		        	writer.write(insertPerson+"");  
		        }
		        
		      }
		      
		      writer.close();
		      
		      
		      
		}
}
