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

public class Inserts_Manschaft{

	public Inserts_Manschaft(int length) throws IOException, SQLException, ClassNotFoundException {
		
        String insertManschaft = "";
        String[] stadtNamen = {"Wien","Podsdorf","Wiener Neustadt","St. Pölten","Abfaltersbach","Kainach","Johnsdorf-Brunn","Johnsbach","Jenbach","Ischgl","Ilz","Horn","Hollabrunn","Hofkirchen im Traunkreis","Höchst","Hochburg-Ach","Hirschegg","Hirschbach","Hippach","Herzogenburg","Helpfau-Uttendorf","Haag","Götzendorf an der Leitha","Göllersdorf","Gmünd","Gleisdorf","Gerasdorf","Garsten","Gaishorn am See","Gablitz","Fürstenfeld","Friesach","Freistadt","Flachau","Fischbach","Fischamend","Fieberbrunn","Fallbach","Faistenau","Eisenstadt","Eggendorf","Edlitz","Ebreichsdorf","Ebensee","Ebbs","Dornbirn","Dienersdorf","Deutsch Kaltenbrunn","Desselbrunn","Bruck an der Leitha","Bregenz","Braunau","Bludesch","Bischofshofen","Biedermannsdorf","Biberbach","Bairisch Kölldorf","Bad Zell","Bad Sauerbrunn","Bad Mitterndorf","Bad Hall","Bad Gastein","Bad Aussee","Attersee","Artstetten","Amstetten","Altlichtenwarth","Altenberg","Aistersheim","Ahorn","Afritz am See","Abtenau","Absdorf","Klagenfurt","Klosterneuburg","Korneuburg","Maria Enzersdorf","Mattersburg","Melk","Micheldorf","Moosbach","Moosbrunn","Mörtschach","Naas","Nappersdorf","Neudorf bei Passail","Neulengbach","Neusiedl","Nöchling","Oberwart","Oed","Pack","Paternion","Paldau","Pasching","Pernitz","Pians","Piberbach","Bremen","Hamburg","Hannover","Dortmund","Wolfsberg","Wolfsburg","Bayern","Pottendorf","Puch bei Weiz","Rutzenham","Rudersdorf","Rohrau","Rohrbach","Röhrenbach","Rietz","Rettenegg","Reichenau an der Rax","Reichendorf","Raab","Rastenfeld","Rankweil","Ranggen","Radfeld","St. Florian","St. Aegidi","Spittal","Sommerein","Seewalchen am Attersee","Seefeld","Sebersdorf","Schwanberg","Schoppernau","Schnepfau","Schleedorf ","Schärding ","Schandorf","Sarleinsbach","Salzburg"};
        String[] segelvereinArt = {"SV","SSV","LÜV","VTS","SBV","KS","BSC","AYC","BYC","JKWB","KCH","LSV","NYC","OSV","SCB","WVW","PSV"};
        String[] aklasse = {"U-16","U-18","U-21","Ersatzmanschaft","Kampfmanschaft","Senioren"};
        
        String sn = "";
        String sva = "";
        String ak = "";
        
        int jahr = 0;
        
        int[] id = new int[length+1];
		
		BufferedWriter writer = null;
	    writer = new BufferedWriter( new FileWriter( "manschaft.sql"));
		
	    DB_Connection user_con = new DB_Connection();
	    
	    Random random = new Random();
	    
	    //if (user_con.connect()) {
	    	
	    try{
	    Statement st = user_con.creStm();	
	    ResultSet rs = st.executeQuery("SELECT key FROM trainer");
	    
	    int i = 0;
	    
	    
	    while(rs.next()&& i < length){
    		
			id[i] = rs.getInt("key");
			System.out.println(id[i]);
			
		    	for(int t = 0; t < length; t++){
		    		jahr = random.nextInt(2015-1888) + 1888;
		    	}
		    	
		    	

		        sn = stadtNamen[random.nextInt(70)];
		        sva = segelvereinArt[random.nextInt(17)];
		        ak = aklasse[random.nextInt(5)];
		        int key = id[i];
			
			if(i < length){
		        i += 1;
			} 
				//System.out.println(i);
				insertManschaft = "INSERT INTO manschaft(name,aklasse,key) VALUES('"+i+"_Segelverein " + sva +" - " +sn+"_"+jahr+"','"+ak+"','"+key+"');"+"\n";
				writer.write(insertManschaft+"");
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
