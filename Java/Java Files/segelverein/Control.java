package segelverein;

// import java.sql.SQLException;

/**
 * Der Controler erzeugt eine neue View und �bergibt ihr die ben�tigten Parameter von BootDaten & manschaftsDaten
 * 
 * @author Bernhard Schmid
 *
 */

public class Control {

	View_Login vl;
	
	public Control() throws ClassNotFoundException {

		vl = new View_Login();
	}
}
