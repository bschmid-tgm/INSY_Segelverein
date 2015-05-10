package Inserts_Generators;

	import java.io.BufferedWriter;

	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.Random;

public class Inserts_Regatta {

			public Inserts_Regatta(int length) throws IOException {
			      String ortschaftName[] = {"Lumpi", "RAW", "Spitzkopf", "Schnell", "Groß", "Wagner", "Fischer", "Bauer", "Schnellboot","Yacht","Dinghy","Stink & Sink","Simpson","Yombolatschoku","Siren","Chuppy","Senioren"};
			      String land[] = {"Österreich","Deutschland","Ungarn","Italien","Norwegen","Schweden","Finnland","Dänemark","Portugal","Schweiz","Großbritanien","Irland","Frankreich","Malta","Belgien","Spanien","Polen","Niederland","Australien","Türkei","Griechenland","Kroatien","Makedonien","Tschechien","Island","USA","Kanada","Brasilien","Argentinien"};
			      String datum = "";
			      String on = "";
			      String ld = "";
			      String insertRegatta = "";
			      
			      int anzahl = 0;
			      int jahr = 0000;
			      int i = 0;
			      
			      Random random = new Random();
			      
			      BufferedWriter writer = null;
			      
			    	  writer = new BufferedWriter( new FileWriter( "regatta.sql"));
			      
			      for (i=0;i<length;i++) {
			    	for (int y=0;y<10;y++) {  
			    		anzahl = random.nextInt(999999999);
			    	}
			    	for(int t = 0; t < length; t++){
			    		jahr = random.nextInt(2015-1915) + 1915;
			    	}

			        on = ortschaftName[random.nextInt(17)];
			        ld = land[random.nextInt(21)];
			        // Da der Monat Februar nicht mehr als 28 Tage (und das auch nur beim Schaltjahr) besitzt 
			        // Setzte  ich hier ein if() welches eben ueberpruefen soll ob es sich um ein invalides Datum handelt!
			        
			        	insertRegatta = "INSERT INTO regatta(name,jahr,land) VALUES('"+on+"- Segelmeisterschaft_"+anzahl+"','"+ jahr +"','"+ld+"');"+"\n";
			        	writer.write(insertRegatta+"");  
			        }
			      
			      writer.close();
			        
			      }
	}


