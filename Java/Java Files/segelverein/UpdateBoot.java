package segelverein;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;  
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

public class UpdateBoot extends JFrame{

	/**
	 * 
	 * Die Klasse UpdateBoot ist fuer das aktuallisieren der Eintraege sowohl in der Datenbank als auch in der JTable verantwortlich.
	 * 
	 * (Naehere beschreibung der einzelnen Methoden  jeweils direkt bei den Methoden selbst !!)
	 * 
	 * @author Bernhard Schmid
	 */
	
	private static final long serialVersionUID = 1L;
	private javax.swing.JButton abr;
	@SuppressWarnings("unused")
	private BootDaten bd;
	private javax.swing.JButton add;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	static javax.swing.JTextField textf_id;
	static javax.swing.JTextField textf_name;
	static javax.swing.JTextField textf_pers;
	static javax.swing.JTextField textf_tiefg;
	private Object updateValues[];
	
	static boolean pruef;
	static int update_id;
	static String update_name;
	static int update_pers;
	static double update_tiefgang;
	static Object[] update;
	static int dialogButton = JOptionPane.YES_NO_OPTION;
	
	public UpdateBoot(){
		
		/*
		 * Benötigten Textfelder, Lables und Buttons definieren
		 */
        textf_id = new javax.swing.JTextField();
        textf_name = new javax.swing.JTextField();
        textf_pers = new javax.swing.JTextField();
        textf_tiefg = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        abr = new javax.swing.JButton();

        jLabel1.setText("ID:");

        jLabel2.setText("Name:");

        jLabel3.setText("Anz. Personen:");

        jLabel4.setText("Tiefgang:");

        add.setText("Update");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

					try {
						addActionPerformed(evt);
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, "Es gibt einen Fehler bei ihrer Dateneingabe. Fuellen Sie die Felder nach diesem Schema aus:", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
   	 					JOptionPane.showMessageDialog(null, "id = Ganze Zahl, z.B.: 1234", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
   	 					JOptionPane.showMessageDialog(null, "name = Buchstaben & Leerzeichen (max 75), z.B.: abcd", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
   	 					JOptionPane.showMessageDialog(null, "persoenen = Ganze Zahl, z.B.: 1234", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
   	 					JOptionPane.showMessageDialog(null, "tiefgang = Ganze oder Dezimalzahl, z.B.: 12.34", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
	    	 					e.printStackTrace();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Es gibt einen Fehler bei ihrer Dateneingabe. Fuellen Sie die Felder nach diesem Schema aus:", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
   	 					JOptionPane.showMessageDialog(null, "id = Ganze Zahl, z.B.: 1234", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
   	 					JOptionPane.showMessageDialog(null, "name = Buchstaben & Leerzeichen (max 75), z.B.: abcd", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
   	 					JOptionPane.showMessageDialog(null, "persoenen = Ganze Zahl, z.B.: 1234", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
   	 					JOptionPane.showMessageDialog(null, "tiefgang = Ganze oder Dezimalzahl, z.B.: 12.34", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
	    	 			e.printStackTrace();
					}
            }
        });
        
        //Button Name setzten
        abr.setText("Abbrechen");
        //ActionListener für Button erzeugen
        abr.addActionListener(new java.awt.event.ActionListener() {
        	//Methode um das Aktuelle Fenster zu schließen
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        /*
         * Hier beginnt der Autogenerierte Code von Netbeans
         */
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textf_pers, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textf_id, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textf_name, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textf_tiefg)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(abr, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textf_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textf_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textf_pers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(textf_tiefg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        
        /*
         * Hier Endet er wieder
         */
        
        this.setVisible(true);
		this.setSize(1280,700);

        pack();
    }                      

	 /**
     * 
     * updateRow ist eine Methode an der ich lange feilen musste (zumindest länger als bei remove & addRow)
     * Diese Methode ist wie der Name schon sagt dafür zuständig eine bereits vorhandene eintrag in der JTable sowie in der Datenbank zu
     * verändern. Das Essenzielle hierbei sind vier dinge: 
     * 
     * 1.) Die "changedId" welche zuvor in der Tabelle stand auszulesen und mit denen in der Datenbank zu vergleichen.
     * 	  Die schwiereigkeit war das die Methode updateRow erst dann ausgeführt wurde wenn die spalte bereits verändert war und deshalb 
     * 	  konnte der Eintrag in der DB auch nicht bearbeitet werden da eben dieser Eintrag nirgendst in der Datenbank vorhanden ist.
     * 	  Die Lösung war dann allerdings relativ simpel. In der Java Api stand das man nicht nur auf buttons sonder auch auf eine JTable 
     * 	  einen eigenen ActionListener (der heißt hier allerdings ListActionListener oder einem TabelActionListener) geben kann. Dieser reagiert dann sofort wenn die 
     * 	  Zeile oder Spalte in der Tabelle angecklickt wird und kann daher auch die benötigten Daten aus der JTable (im meinem Fall id) auslesen
     * 	  bevor die Daten aktive verändert worden sind.
     * 
     * 2.) Nachdem sich mehrere User gleichzeitig auf die Datenbank verbinden können sollen, muss es eine möglichkeit geben die UPDATE Vorgänge
     *     vor einem möglichen LOST UPDATE zu schützen. Postgres biete dafür einige möglichkeiten z.B. mit den setzen einer Isolationstufe
     *     In dem man eine Isolationstufe setzt (also in meinem Fall SERIALIZABLE, welche nicht die "beste" Loesung ist aber dennoch den zweck erfuellt) 
     *     reserviert man sich quasi die Rechte für die benutzung des Datensatzes für die UPDATES. Wärend der Updates kann also keine Andere Transaktion
     *     ein Update auf diesen Datensatz ausführen  weil er gesperrt ist. Nachdem Transaktion1 also fertig ist UND Commitet hat, kann Transaktion 2 ihr Update durchführen.
     *     
     * 3.) Die Daten die man in der Tabelle Boot bearbeitet sind nicht nur in boot enthalten sonder auch in einer der beiden Tabellen sportboot oder tourenboot
     * 	   bearbeitet man also einen Eintrag in boot so muss man auch die Daten in einer der beiden anderen Tabellen mitverändern da sonst
     *     bei einer der beiden Tabellen ein FK keine Referenz mehr auf den alten PK hat denn der wurde ja verändert. Es gäbe zur Lösung des verfahrens 
     *     mehrere Lösungansätze: UPDATE & DELETE ON CASCADE , Constraints,....
     *     Ich schreibe einfach drei UPDATE querys welche jedes mal den FK der betroffenen Tabelle mit aktualisiert.
     *     Die Query bei der der gesuchte FK nicht vorkommt wird einfach ignoriert weil diese kein Ergebnis liefert.
     *     
     * 4.) Die letzte Frage ist wie aktuallisiert man die JTable und updatet die Werte in der bearbeiteten Zeile.
     * 	   Meine Loesung kam mit der Methode updateRowValues(). Diese loest das update / aktuallisier Problem wahrscheinlich auf die unkonventionellste Art.
     * 	   Als erstes wird die zuerst ausgewahelte Zeile geloescht und dann wird eine neue Zeile erstellt mit den aktualisierten Daten.
     * 	   Im Endeffeckt eine Kombination aus den Methoden deleteRow & addRow....
     * 	   Die Alternative (welche ebenfalls funktioniert hat) ist die gesamten Daten aus der Datenbank neu in die JTable zu laden. Bei 10.000 oder mehr Datensaetzen
     * 	   ist das allerdings VIEL zu performencelastig und keine echte Loesung.
     * 
     * 5.) Eine Rollback Funtkion ist geplant, diese wird entweder gleich hier umgesetzt oder in View
     * 
     * @param evt
     * @throws SQLException
     * @throws ClassNotFoundException
     */
	
    private void addActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, ClassNotFoundException {                                    
    	
   	 	DB_Connection user_con = new DB_Connection();

   	 	if (user_con.connect())
   	 	{
   	 		/*
   	 		 * Variablen aus der JTable auslesen und in neue Varibalen speichern
   	 		 * --> Diese werden später dem PreparedStatement übergeben
   	 		 */

   	 		try{
   	 			  	 	
   	 				try{
   	 					UpdateBoot.update_id = Integer.parseInt(UpdateBoot.textf_id.getText());
	    	 			UpdateBoot.update_name = UpdateBoot.textf_name.getText();
	    	 			UpdateBoot.update_pers = Integer.parseInt(UpdateBoot.textf_pers.getText());
	    	 			UpdateBoot.update_tiefgang = Double.parseDouble(UpdateBoot.textf_tiefg.getText());  
	    	 			
	    	 			//Query für das PreparedStatement erzeugen  	    	 			
	    	   	 		if (Integer.class.isInstance(UpdateBoot.update_id)) {
	    	   	 				
		    	 			int dialogResult = JOptionPane.showConfirmDialog (null, "Aenderungen speichern?","Warning", dialogButton);
							
							if(dialogResult == JOptionPane.YES_OPTION){
								
								String updateRowBoot =  "BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE; " +
		 								   				"UPDATE sportboot SET id = ? WHERE id = " + View.changedId + ";" +
		 								   				"UPDATE tourenboot SET id = ? WHERE id = " + View.changedId + ";" +
		 								   				"UPDATE boot SET id = ?, name = ?, personen = ?, tiefgang = ? WHERE id = " + View.changedId + ";"+
		 								   				"COMMIT;";
	   
								PreparedStatement pst = user_con.pstm(updateRowBoot);

								// Parameter dem PreparedStatement übergeben

								pst.setInt(1, UpdateBoot.update_id);
								pst.setInt(2, UpdateBoot.update_id);
								pst.setInt(3, UpdateBoot.update_id);
		 			
								pst.setString(4, UpdateBoot.update_name);
								pst.setInt(5, UpdateBoot.update_pers);
								pst.setDouble(6, UpdateBoot.update_tiefgang);
		 			
								//View.boote.getValueAt(update_personen, update_personen);
	   
								System.out.println(UpdateBoot.update_id);
	   
								//update ausführen
								pst.executeUpdate();
								
								JOptionPane.showMessageDialog(null, "Erfolgreich gespeichert!");
								
								View.updateRowValues();	
								
								}
							
								if(dialogResult == JOptionPane.NO_OPTION){
									JOptionPane.showMessageDialog(null, "Aenderungen verworfen!");
								}
	    	   	 			}
	    	 			
   	 				}catch(NumberFormatException ex){
	 					JOptionPane.showMessageDialog(null, "Es gibt einen Fehler bei ihrer Dateneingabe. Fuellen Sie die Felder nach diesem Schema aus:", "InfoBox: " + "Warning", JOptionPane.INFORMATION_MESSAGE);
	 					JOptionPane.showMessageDialog(null, "id = Ganze Zahl, z.B.: 1234", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
	 					JOptionPane.showMessageDialog(null, "name = Buchstaben & Leerzeichen (max 75), z.B.: abcd", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
	 					JOptionPane.showMessageDialog(null, "persoenen = Ganze Zahl, z.B.: 1234", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
	 					JOptionPane.showMessageDialog(null, "tiefgang = Ganze oder Dezimalzahl, z.B.: 12.34", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
	 				}

   	 				} catch (SQLException e) {
   	 					JOptionPane.showMessageDialog(null, "Es gibt einen Fehler bei ihrer Dateneingabe. Vergewissern sie sich das Ihre Eingaben korrekt sind.", "InfoBox: " + "Warning", JOptionPane.INFORMATION_MESSAGE);
   	 					JOptionPane.showMessageDialog(null, "Womöglich existiert ihr Eintrag bereits", "InfoBox: " + "Warning", JOptionPane.INFORMATION_MESSAGE);
   	 					//e.printStackTrace();
   	 				}
	   	    	 		//Tabelle aktualisieren      	 		  	 	
	    	 		}	
			}		
   	}	
	
