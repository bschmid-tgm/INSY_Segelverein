package segelverein;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import segelverein.DB_Connection;
//import segelverein.View_Login;

/**
 * 
 * Die Klasse BootDaten ließt alle Daten der Tabelle Boot aus der Datenbank aus und Speichert diese in ArrayListen
 * Dann wird ein 2D-Array erstellt welchen man für die JTable benötigt
 * In diesen 2D-Array kommen einerseits die Daten aus den ArrayLists welche man einfach mittels For-Schleife
 * aus den ArrayLists in den 2d-Array einließt. Und das zweite wäre die Definition der größe des Arrays.
 * 
 * Hinweis: Nicht wie bei normalen Arrays kann man die Länge einer ArrayList nicht mit .length bestimmen sonder muss hierfür
 * 	 		.size() verwenden
 * 
 * @author Bernhard Schmid
 *
 */

public class BootDaten{
	
	public int row;
	public int col;
	public Object[][] boot;
	static String query = "SELECT * FROM boot";
	//private View_Login vl;
	
	public Object[][] getbootDaten() throws SQLException, ClassNotFoundException{
		
		//Arraylists definiren
		ArrayList<Integer> id = new ArrayList<Integer>();
		ArrayList<String> name = new ArrayList<String>();
		ArrayList<Integer> presonen = new ArrayList<Integer>();
		ArrayList<Double> tiefgang = new ArrayList<Double>();
		
		DB_Connection user_con = new DB_Connection();
		
		//Überpruefen ob der Benutzer richtig mit der DB verbunden ist
		
		//System.out.println(vl.getUrl());
		
		if(user_con.connect()) {
			
		//Erstellung eines neuen ResultSets
	    ResultSet rs = user_con.execQuery(query);
	    
	   //Auslesen der Daten aus der Datenbank
	    while(rs.next()){
	    	id.add(rs.getInt(1));
	    	name.add(rs.getString(2));
	    	presonen.add(rs.getInt(3));
	    	tiefgang.add(rs.getDouble(4));
	    }
	    rs.close();
		
	    
	    //id.size() gibt die Anzahl an Einträgen in der ArrayList id (also 10000) zurück
	   
	    boot = new Object[id.size()][4];
	    
	    // -> Bei der Vorschleife hat das den Sinn, dass man kein countColumn braucht
	    // -> keine unnötige Belastung der DB
	    
	    //2D-Array boot befüllen
	    for(int i = 0; i < id.size(); i++){
	    	boot[i][0] = id.get(i);
	    	boot[i][1] = name.get(i);
	    	boot[i][2] = presonen.get(i);
	    	boot[i][3] = tiefgang.get(i);
	    }
	   
		}
		//den 2D-Array zurückgeben
		return boot;
	}
	public boolean isCellEditable(int row, int col) {
        if (col < 3) { 
            return false;
        } else {
            return true;
        }
    }  
	
}
