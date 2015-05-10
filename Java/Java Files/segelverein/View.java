package segelverein;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * Die Main View ist nicht nur Hauptanzeige sonder auch das Gehirn aller hier stattfindenenden Operationen
 * Die View generiert eine Anzeige mit einer JTable (welche die BootDaten enthält) sowie sechs Buttons mit den aufgaben
 * den User einen neuen Eintrag in die DB zu erstellen ermöglichen, einen vorhandenen Eintrag bearbeiten zu können, ihm die möglichkeit geben 
 * einen oder meherere Einträge aus JTable und DB zu löschen sowie die Umleitung auf die View_WMP welche es dem User erlaubt den Punktestand 
 * der einzelnen Mannschaften für die unterschiedlichen Wettfahrten zu bearbeiten.
 * 
 * Update:
 * 
 * Es ist jetzt auch moeglich eine Sortierung (Aufsteigend oder Absteigend) zuwaelen, sowie eine Hilfe anzuforden falls man sich bei der
 * Bediehnung der Programmes schwer tut.
 * 
 * @author Bernhard Schmid
 *
 */

@SuppressWarnings("serial")
public class View extends JFrame{

	public static BootDaten bd;
	public static ManschaftsDaten md;
	public String query;
	public ResultSet rs;
	public static DefaultTableModel dtm;
	static int changedId;
	private int row;
	static private String name;
	static private int personen;
	static private double tiefgang;
	static private int id;
	static int dialogButton = JOptionPane.YES_NO_OPTION;
	static String[] spaltennamen = {"id","name","personen anz.","tiefgang"};
	static Savepoint savepoint;
	
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    static javax.swing.JTable boote;
    
    static int dialogResult;

	public View(BootDaten bd,  ManschaftsDaten md) throws SQLException, ClassNotFoundException{

		View.bd = bd;
		this.md = md;
		
		/*
		 * Definieren der Layouts & Container
		 */
		
		this.setLayout(new GridLayout(2,1));
		
		Container buttons = new Container();
		
		buttons.setLayout(new GridLayout(1,6));
		
		Container r = new Container();
		
		r.setLayout(new FlowLayout());
		
		//Namen für Buttons und JTable Spalten erstellen
		String[] buttonname = {"Add","Update","Delete","Zu den Wettfahrten"};
		
		Object[][] bootDaten = bd.getbootDaten();

		
		dtm = new DefaultTableModel(bootDaten,View.spaltennamen);
		
		//JTable und TableModel erzeugen sowie größe definieren
		boote = new JTable(dtm){
			public boolean isCellEditable(int rowIndex, int colIndex) {
			      return false; //Disallow the editing of any cell
			 }
		};
		
		
		//boote.setAutoCreateRowSorter(true);
		boote.setPreferredScrollableViewportSize(new Dimension(800,400));
		boote.setFillsViewportHeight(true);
		
		//Neuen RowSorter erstellen welcher die Spalten der Tabelle entweder ASC oder DESC sortierbar macht
		//View.boote.setAutoCreateRowSorter(true);
		
		View.boote.getTableHeader().setReorderingAllowed(false);
		
		/*
		 * Erzeugen aller benötigter Buttons
		 */
		jButton1 = new JButton();
		jButton2 = new JButton();
		jButton3 = new JButton();
		jButton4 = new JButton();
		jButton5 = new JButton();
		jButton6 = new JButton();
		
		/*
		 * Die Buttons in den Container Buttons einfügen
		 */
		buttons.add(jButton1);
		buttons.add(jButton2);
		buttons.add(jButton3);
		buttons.add(jButton4);
		buttons.add(jButton5);
		buttons.add(jButton6);
		
		//Name von Button definieren
        jButton1.setText(buttonname[0]);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton1ActionPerformed(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        //Name von Button definieren
        jButton2.setText(buttonname[1]);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton2ActionPerformed(evt);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        //Name von Button definieren
        jButton3.setText(buttonname[2]);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton3ActionPerformed(evt);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        //Name von Button definieren
        jButton4.setText(buttonname[3]);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton4ActionPerformed(evt);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        jButton5.setText("Hilfe?");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
		
        jButton6.setText("Sortierung");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        
        /**
         * Die methode valueChanged bekommt die id der ausgewählten Zeile und speichert sie in die Variable changedId
         * welche später noch von großer Bedeutung ist.
         */
        View.boote.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
          public void valueChanged(ListSelectionEvent event)
          {
            if (View.boote.getSelectedRow() > -1)
            {
              View.this.row = View.boote.getSelectedRow();
              View.changedId = ((int)View.boote.getValueAt(View.this.row, 0));
              System.out.println(row);
              
            }
          }
        });
        
        View.boote.getSelectionModel().addListSelectionListener(new ListSelectionListener()
        {
          public void valueChanged(ListSelectionEvent event)
          {
				
				if (View.boote.getSelectedRow() > -1)
	            {
					View.id = (int)boote.getValueAt(boote.getSelectedRow(), 0);
	   	 			View.name = boote.getValueAt(boote.getSelectedRow(), 1).toString();
	   	 			View.personen = (int) boote.getValueAt(boote.getSelectedRow(), 2);
	   	 			View.tiefgang = (double) boote.getValueAt(boote.getSelectedRow(), 3);
	   	 			
	   	 			System.out.println(View.id);
	            }
				
			}	
        });
        
        
        // Adden der Container zum Panel/Frame
		JScrollPane  sp = new JScrollPane(boote);
		add(sp);
		add(buttons);
		//Sichtbarkeit und größe setzten
		this.setVisible(true);
		this.setSize(1280,700);
		
	}

	protected void jButton6ActionPerformed(ActionEvent evt) {
		
		 String[] options = new String[] {"ASC", "DESC", "Abbrechen"};
		    int response = JOptionPane.showOptionDialog(null, "In der Suchfunktion ist es moeglich die Daten absteigend oder Aufstagend (mit dem Namen) zu sortieren___ASC = Aufsteigend___DESC = Absteigend", "Suchfunktion",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);
		
		    if(response == 0){    
		    	BootDaten.query = "SELECT * FROM boot ORDER BY name ASC";
		    	
		    	reload();
		   		
	   			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   			dispose();
		    	
		    }
		    if(response == 1){    
		    	BootDaten.query = "SELECT * FROM boot ORDER BY name DESC";
		    	
		    	reload();
		    	
	   			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   			dispose();
		    }

		    if(response == 4){    
		    	
		    }

	}

	protected void jButton5ActionPerformed(ActionEvent evt) {

		JOptionPane.showMessageDialog(null, "Bitte Achten Sie auf folgendes:", "InfoBox: " + "Hilfe?", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Um Werte einer Zeile zu aktuallisieren klicken Sie auf die zu atuallisierende Zeile und anschließend auf den Button Update", "InfoBox: " + "Hilfe?", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Um eine Zeile zu löschen klicken sie auf eine beliebige Zeile und dann auf den delete Button", "InfoBox: " + "Hilfe?", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Um einen neuen Eintrag zu erstellen klicken Sie auf den Button Add", "InfoBox: " + "Hilfe?", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.showMessageDialog(null, "Bitte vermeiden Sie es, beim Update, mehr als eine Zeile gleichzeitig aus zuwählen.", "InfoBox: " + "Hilfe?", JOptionPane.INFORMATION_MESSAGE);
		
	}

	/**
	 * 
	 * Erzeugen einer neuen View (View_AddBoot)
	 * View_AddBoot öffnet ein neues Fenster wo man einen neuen Eintrag in der Tabelle Boot erstellen kann
	 * 
	 * @param evt
	 * @throws SQLException
	 */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws SQLException { 
        	
    	new View_AddBoot();
    	
    }                                        

    /**
     * Aufrufen der updateRow() Methode wenn Button update (alias jButton2) gedrückt wird
     * 
     * @param evt
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
     private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) throws ClassNotFoundException, SQLException {                                         
          
          		try {
          			updateRow();
          			View.boote.repaint();
     			} catch (SQLException e) {
     				//e.printStackTrace();
     				DB_Connection.con.rollback(savepoint);
     			}
      	
              
    }                                        

     /**
      * Button drei ist jener der sich um das löschen der Daten aus der JTable als auch aus der Datenbank kümmert.
      * Mittels getSelectedRows() bekommtman die aktuell ausgewählten Zeilen
      * In der for-Schleife werden die Daten aus der ersten Spalte der Zeile in eine int variable id gespeichert (welche später wichtig ist, denn row[i] kann man nicht in eine sql query einfügen)
      * In der for-Schleife wird die Methode removeRecord welche wie unten genauer beschrieben, die ausgewählten Daten von der row, aus der Datenbank löscht
      * 
      * Der Teil der mich wirklich sehr lange gequählt hat war, dass sich mit löschen der Daten aus der Datenbank, auch die Einträge
      * in der JTable entfernen
      * 
      * Durch durchlesen der Java-Api habe ich schlussendlich doch herausgefunden das die Tables mittels der fireTableCell... (in diesem Fall Update oder Delete)
      * Methode "aktualisiert" also gelöscht werden
      * 
      * Mit der einzigen if() in dieser Methode wird überprüft ob mindestens eine Zeile (row) ausgewählt ist wenn ja wird diese gelöscht...
      * 
      * Stand: 17-03-2015
      * 
      * @param evt
      * @throws ClassNotFoundException
      */
     
     private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) throws ClassNotFoundException {                                         
    	 
    	/* 
    	
         */		
         		try {
         			removeRow(id);
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			
     		//}
     			
     		//}
     	}
     
     /**
      * 
      * Button Vier (zu den Wettfahrten) erzeugt eine neue View_WMP und refresht die JTable Boote
      * In der View_WMP kann man den Punktestand der einzelnen Wettfahrt für jede Manschaft bearbeiten
      * 
      * @param evt
      * @throws SQLException 
      * @throws ClassNotFoundException 
      * @throws InterruptedException 
      */
     
     private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) throws ClassNotFoundException, SQLException {                                         

    	 new View_WMP(md);
    	 
    	 int[] rows = boote.getSelectedRows();  	
       	
       		for(int i = 0;i < rows.length;i++){
       		
       			if (rows[i] > -1) {
    	 
       				dtm.fireTableCellUpdated(rows[i],i);
       			}
       		}

     }
     
         public void run() {
     		//this.boote.getValueAt(this.boote.getSelectedRow(), 0);
             try {
					try {
						new View(bd,md).setVisible(true);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             
         }
         
       /**
        * 
        *   Die Methode removeRow wird aufgerufen wenn der DELETE Button gedrückt wurde.
        *   removeRow() ist für die Entfernung der zu löschenden Daten (row) aus der Datenbank zuständig
        *   Die Methode erstellt eine Datenbankverbindung sowie eine (bzw, mehrere) Querys, welche ins preparedStatement kommen und ausgeführt werden
        *   
        *   Warum drei Querys?
        *   
        *   Hätte man nur eine Query und zwar deleteRowBoot würde man einen Primary key löschen obwohl eine andere Tabelle eine Referenz auf den key besitzt (macht wenig sinn...)
        *   Also erstelle ich ebenfalls DELETE querys für sport & tourenboot denn eines dieser Boote enthält den key mit der Referenz auf boot
        *   Nachdem ich nicht auslese welche der beiden Tabellen den Key mit der Referenc enthält füge ich eine Where Klausel an und sage wenn du genau den gesuchten key
        *   enthälts .... lösche den Eintrag. Falls er nicht enthalten ist macht die query überhaupt nichts (logisch...). Also hat diese Methode nur den nachteil das sie nur mit der Tabelle boot funktioniert (ist also nicht generisch)
        *   
        *   Stand: 17-03-2015
        *   
        * @param row
        * @throws SQLException
        * @throws ClassNotFoundException
        */
         
       public static void removeRow(int row) throws SQLException, ClassNotFoundException {

        	 	DB_Connection user_con = new DB_Connection();
    		    
    		    if (user_con.connect()) {
    		    
    		    try{
    		    	
    		    	int[] rows = boote.getSelectedRows();  	
			    	
			    	for(int i = 0;i < rows.length;i++){
			    		
			    		if (rows[i] > -1) {
    		    	
			    			String deleteRowBoot = "BEGIN; DELETE FROM boot where id=?; ";
			    			String deleteRowSportboot = "DELETE FROM sportboot where id=?; ";
			    			String deleteRowTourenboot = "DELETE FROM tourenboot where id=?; Commit;";
			    			
			    			int dialogResult = JOptionPane.showConfirmDialog (null, "Wollen Sie den Eintrag wirklich loeschen?","Warning", dialogButton);
			    			
			    			if(dialogResult == JOptionPane.YES_OPTION){
    		    	
			    				PreparedStatement pst = DB_Connection.pstm(deleteRowBoot + deleteRowSportboot + deleteRowTourenboot);
    		    		
			    				pst.setInt(1, changedId);
			    				pst.setInt(2, changedId);
			    				pst.setInt(3, changedId);
			    				pst.executeUpdate();
    		    	
			    				JOptionPane.showMessageDialog(null, "Eintrag erfolgreich geloescht!", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
			    						((DefaultTableModel)boote.getModel()).removeRow(rows[i]);
			    						dtm.fireTableCellUpdated(rows[i],i);	
			    						View.boote.repaint();
			    			}else{
			    				JOptionPane.showMessageDialog(null, "Bitte immer EINEN Eintrag auswaehlen", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
			    			}
			    		}
    		    	}
    		    	if(dialogResult == JOptionPane.NO_OPTION){        		 
    		    		JOptionPane.showMessageDialog(null, "Loeschen abgebrochen!", "InfoBox: " + "abgebraochen", JOptionPane.INFORMATION_MESSAGE);
    		    	}
    		   
    		    }catch(SQLException e){
    		    	JOptionPane.showMessageDialog(null, "Beim Loeschvorgang ist ein Fehler passiert. Bitte ernuet Versuchen oder Programm neustarten!", "InfoBox: " + "Warning", JOptionPane.INFORMATION_MESSAGE);
    		    }
    		    
    		    
    		}
    		  
      }
       

       public void updateRow() throws SQLException, ClassNotFoundException {
   	 	
    	   new UpdateBoot();
    	   
    	   UpdateBoot.textf_id.setText(""+View.id);
    	   UpdateBoot.textf_name.setText(""+View.name);
    	   UpdateBoot.textf_pers.setText(""+View.personen);
    	   UpdateBoot.textf_tiefg.setText(""+View.tiefgang);
    	   View.boote.repaint();
      }
       
       /**
        * 
        * Die Methode updateRowValues loest das Problem "Wie kann ich den Inhalt einer Row bei einem Update aktualisieren" etwas unkonventionell.
        * Als erstes wird die zuerst ausgewahelte Zeile geloescht und dann wird eine neue Zeile erstellt mit den aktualisierten Daten.
        * @throws SQLException 
        * 
        */
       
       public static void updateRowValues() throws SQLException{
    	   
    	   savepoint = DB_Connection.con.setSavepoint();
    	   			
							int[] rows = boote.getSelectedRows();  	
					    	
					    	for(int i = 0;i < rows.length;i++){
					    		
					    		if (rows[i] == 0 || rows[i] == 1) {
					    			
					    			dtm.removeRow(rows[i]);
					    			dtm.fireTableCellUpdated(rows[i],i);
					    	}
					Object[] updateData = {UpdateBoot.update_id,UpdateBoot.update_name,UpdateBoot.update_pers,UpdateBoot.update_tiefgang};
       
					DefaultTableModel model = (DefaultTableModel) View.boote.getModel();
					model.addRow(updateData);
					View.boote.repaint();
			}		
       }
       
       public static void reload(){
    	   
    	    bd = new BootDaten();
   			md = new ManschaftsDaten();
   		
   				try {
   					new View(bd,md);
   				} catch (ClassNotFoundException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				} catch (SQLException e) {
   					JOptionPane.showMessageDialog(null, "Bitte ueberpruefen Sie ihre Anmeldedaten und versuchen Sie es erneut!", "InfoBox: " + "Anemldung fehlgeschlagen", JOptionPane.INFORMATION_MESSAGE);
   					e.printStackTrace();
   				}
       }
       
       public static void reloadWettfahrt(){
    	   
  			md = new ManschaftsDaten();
  		
  				try {
  					new View_WMP(md);
  				} catch (ClassNotFoundException e) {
  					// TODO Auto-generated catch block
  					e.printStackTrace();
  				} catch (SQLException e) {
  					JOptionPane.showMessageDialog(null, "Bitte ueberpruefen Sie ihre Verbindung zur Datenbank und versuchen Sie es erneut!", "InfoBox: " + "Anemldung fehlgeschlagen", JOptionPane.INFORMATION_MESSAGE);
  					e.printStackTrace();
  				}
      }
           
}
