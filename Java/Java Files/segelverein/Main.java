package segelverein;

import java.io.IOException;
import java.sql.SQLException;
/*
import Inserts_Generators.Insert_Boot;
import Inserts_Generators.Inserts_Manschaft;
import Inserts_Generators.Inserts_Regatta;
import Inserts_Generators.Inserts_Segler;
import Inserts_Generators.Inserts_Sportboot;
import Inserts_Generators.Inserts_Tourenboot;
import Inserts_Generators.Inserts_Trainer;
import Inserts_Generators.Inserts_erzielt;
import Inserts_Generators.Inserts_person;
*/
/**
 * Main-Methode
 * 
 * @author Bernhard Schmid
 *
 */

public class Main{

	/**
	 * 
	 * Die Main Methode erzeugt ein Object von der Klasse Controler und startet das Programm
	 * 
	 * Falls noch bzw. neue Inserts benötigt werden können von den Generator Klassen die Inserts erzeugt werden
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws IOException
	 */
	
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException{
       
        //new Inserts_person(20000);
        //new Inserts_Segler(20000);
        //new Inserts_Trainer(20000);
    	//new Insert_Boot(20000);
    	//new Inserts_Tourenboot(10000);
    	//new Inserts_Sportboot(10000);
    	//new Inserts_Manschaft(10000);
    	//new Inserts_Regatta(10000);
    	//new Inserts_Wettkampf(10000);
    	//new Inserts_erzielt(10000);
    	//new Inserts_Bildet(10000);
    	//new Inserts_Zugewiesen(10000);
    	//new Inserts_Nimmt_teil(10000);
    	
    	
    	//System.out.println("Funktioniert :)");
    	
    	
    	//Erzeugen des Controllers
    	new Control();
    	System.out.println("Aktiv");
    	
        }
    }
