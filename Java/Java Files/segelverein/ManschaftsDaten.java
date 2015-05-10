package segelverein;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import segelverein.DB_Connection;

/**
 * 
 * Wenn man sich die Erklärung von BootDaten durchgelgesen hat ist hier nichts neues mehr.
 * Die Daten die man aus der Datenbank ausliseßt werden in Arraylists gespeichert mit welche man anschließend einen 2D-Array befüllt
 * welchen man später zur erstellung der JTable in View_WMP benötigt.
 * 
 * @author Bernhard Schmid
 *
 */

public class ManschaftsDaten{
	
	public int row;
	public int col;
	static String query = "SELECT * FROM erzielt;";
	public Object[][] manschaften;
	
	public Object[][] getErzieltDaten() throws SQLException, ClassNotFoundException{
		
		ArrayList<Integer> punkteStand  = new ArrayList<Integer>();
		ArrayList<String> mname = new ArrayList<String>();
		ArrayList<String> wname = new ArrayList<String>();
		ArrayList<String> datum = new ArrayList<String>();
		ArrayList<Integer> jahr = new ArrayList<Integer>();
		
		
		DB_Connection user_con = new DB_Connection();
		
		if (user_con.connect()) {
	    	 
	    ResultSet rs = user_con.execQuery(query);
		
	    while(rs.next()){
	    	
	    	mname.add(rs.getString("mname"));
	    	wname.add(rs.getString("wname"));
	    	jahr.add(rs.getInt("wjahr"));
	    	datum.add(rs.getString("wdatum"));
	    	punkteStand.add(rs.getInt("punkte"));
	    	
	    }
	    rs.close();
	    
	    //id.size() gibt die Anzahl an Einträgen in der ArrayList id (also 10000) zurück
	   
	    manschaften = new Object[mname.size()][5];
	    
	    // -> Bei der Vorschleife hat das den Sinn, dass man kein countColumn braucht
	    
	    for(int i = 0; i < mname.size(); i++){
	    	manschaften[i][0] = mname.get(i);
	    	manschaften[i][1] = wname.get(i);
	    	manschaften[i][2] = jahr.get(i);
	    	manschaften[i][3] = datum.get(i);
	    	manschaften[i][4] = punkteStand.get(i)+"";
	    }
	   
		}
		return manschaften;
	}
	public boolean isCellEditable(int row, int col) {
        if (col < 3) { 
            return false;
        } else {
            return true;
        }
    }  
	
}
