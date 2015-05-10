package segelverein;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;  
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Bei dieser View habe ich erstmals NET-Beans verwendet um eine GUI zu generieren (desshalb sieht diese hier auch ander als die vorherigen aus)
 * View_AddBoot hat wie schon in der View erklärt die Funktion neue einträge in die Tabelle Boot in der DB zu ermöglichen
 * Hierfür werden einfach 4 Textfelder benötigt in denen man die gewünschten werte eingibt (also id, name, etc....). Die Werte die in den 
 * 4 Textfeldern stehen holt man sich mittels Textfeldname.getText() und speichert diese Werte dann mittels PreparedStatement in die Datenbank
 * Es gibt noch zwei Buttons. Der Add Button führt die zuvor erwähnten Funktion aus .
 * Der Abr (Abbrechen) Button Beendet das bearbeiten und schließt das Fenster (mittels dispose())
 * 
 * @author Bernhard Schmid
 *
 */

public class View_AddBoot extends JFrame{

	private static final long serialVersionUID = 1593106592317978715L;
	private javax.swing.JButton abr;
	private javax.swing.JButton add;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JTextField textf_id;
	private javax.swing.JTextField textf_name;
	private javax.swing.JTextField textf_pers;
	private javax.swing.JTextField textf_tiefg;
	public View_AddBoot(){
		
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

        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					addActionPerformed(evt);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Es gibt einen Fehler bei ihrer Dateneingabe. Vergewissern sie sich das Ihre Eingaben korrekt sind.", "InfoBox: " + "Warning", JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(null, "Womöglich existiert ein andere Eintrag mit der selben ID bereits", "InfoBox: " + "Warning", JOptionPane.INFORMATION_MESSAGE);
					//e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
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

	/*
	 * Die ActionPerfomed Methode des add-Buttons bekommt ihre werte über die Textfelder mit Textfeld.getText()
	 * Und speichert die bekommenen Werte in Variablen. Danach wird eine Query Definiert für das PreparedStatement
	 * Dem PreparedStatement werden die Variablen-Werte übergeben und es wird Ausgeführt
	 * 
	 * Falls der User nicht mit der Datenbank verbunden ist soll ihm eine Felermeldung asugegeben werden
	 * 
	 */
    private void addActionPerformed(java.awt.event.ActionEvent evt) throws SQLException, ClassNotFoundException {                                    
    	
    	DB_Connection user_con = new DB_Connection();

    		try{
    	
    			int id = Integer.parseInt(this.textf_id.getText());
    			String name = this.textf_name.getText();
    			int personen = Integer.parseInt(this.textf_pers.getText());
    			double tiefgang = Double.parseDouble(this.textf_tiefg.getText());
    			
    			System.out.println(id + name + personen + tiefgang);
        		if (user_con.connect())
        		{
        			String query = "BEGIN;INSERT INTO boot(id,name,personen,tiefgang) VALUES(?,?,?,?);Commit;";
              



        			System.out.println(query);
              
        			PreparedStatement pst = user_con.pstm(query);
              
        			pst.setInt(1, id);
        			pst.setString(2, name);
        			pst.setInt(3, personen);
        			pst.setDouble(4, tiefgang);
              
        			pst.executeUpdate();
              
        			Object[] updateData = {id,name,personen,tiefgang};
              
        			DefaultTableModel model = (DefaultTableModel) View.boote.getModel();
        			model.addRow(updateData);
              
        			JOptionPane.showMessageDialog(null, "Eintrag erfolgreich erstellt. (Dieser befindet sich ganz unten in der Tabelle)", "InfoBox: " + "Warning", JOptionPane.INFORMATION_MESSAGE);
        			
        		}
        		else
        		{
        			System.out.print("Keine Verbindung zur DB möglich!!");
        		}
    		
    		}catch(NumberFormatException ex){
				JOptionPane.showMessageDialog(null, "Es gibt einen Fehler bei ihrer Dateneingabe. Fuellen Sie die Felder nach diesem Schema aus:", "InfoBox: " + "Warning", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "id = Ganze Zahl, z.B.: 1234", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "name = Buchstaben & Leerzeichen (max 75), z.B.: abcd", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "persoenen = Ganze Zahl, z.B.: 1234", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "tiefgang = Ganze oder Dezimalzahl, z.B.: 12.34", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
    		}	
    		
      }
    	
 }    
	
