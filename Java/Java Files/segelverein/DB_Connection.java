package segelverein;

import segelverein.View_Login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Die DB_Connection ist relativ Standartmäßig gehlaten.
 * Dem Constructor werden alle Werete übergeben die die connect Methode benötigt
 * In der Connect Methode word dann die Datenbankverbindung con definiert und getestet ob alle Parameter nicht NULL sind
 * Ausserdem wird AUTOCOMMIT ausgeschaltet und eine direkte bearbeitung mittels CRUD Befehlen auf die DB erlaubt
 * 
 * Weiters gibt es noch die Methoden execQuery, creStm und pstm
 * 		   execQuery verlangt eine Query inform eines Strings und erzeugt eine CreateStatement und führt dieses mit executeQuery auch aus
 * 	       creStm erzeugt ein neues Statement
 * 		   pstm verlangt ebenfalls eine Query inform eines Strings und erzeugt ein neues PremapredStatement mit der DB connection
 * 
 * @author Bernhard Schmid
 * 
 */

public class DB_Connection {

	//private View_Login vl;
	static Connection con;
	private Statement st;
	static private String url;
	static private String user;
	static private String pw;
	private PreparedStatement pst;
	
	/*
	public DB_Connection( String url, String db_name, String user, String pw ) {
		
		this.url = url;
		this.db_name = db_name;
		this.user = user;
		this.pw = pw;
		
	}
	*/

	public DB_Connection() {
		
		
		
	}
	
	public void setDaten(String url1, String user1, String pw1){
		this.url = url1;
		this.user = user1;
		this.pw = pw1;
		
		
		System.out.println("2: " + url+user+pw);
		
	}

	public boolean connect() throws SQLException, ClassNotFoundException {
		/*
		if (url.isEmpty() || db_name.isEmpty() || user.isEmpty() || pw.isEmpty()) {
			throw new SQLException("Einer oder Mehr der Drei geforderten Parameter ist ungültig");
		}
		*/
		
		Class.forName("org.postgresql.Driver");
		
		System.out.println("3: " + this.url+", "+this.user + ", " +this.pw);
		
		this.con = DriverManager.getConnection(this.url,this.user,this.pw);

		this.con.setAutoCommit(false);
		return true;
		
	}
	
	public ResultSet execQuery(String query) throws SQLException {
		ResultSet ret = null;
		
		if(con != null)
			ret = this.con.createStatement().executeQuery(query);
		
		return ret;
	}
	
	public Statement creStm() throws SQLException {
		return this.st = con.createStatement();
	}
	
	public static PreparedStatement pstm(String query) throws SQLException {
		
		PreparedStatement pst = null;
		
		if(con != null)
			pst = con.prepareStatement(query);
		
		System.out.println(pst);
		return pst;
	}
	
}