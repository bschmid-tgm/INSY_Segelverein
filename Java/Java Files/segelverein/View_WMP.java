package segelverein;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * Wie schon öfters erwähnt die View_WMP erzeugt eine JTable mit den Daten von erzeilt (Manschaftsname, Wettfahtsname, etc...)
 * Die Aufgabe hierbei war das man den Punktestand der einzelnen Manschaften bei einer Wettfahrt bearbeiten können soll.
 * Die Methode hierbei war die gleiche wie das Update in der View() bei updateRow() (<- Die Methode und Probleme sind dort genau beschrieben)
 * 
 * @author Bernhard Schmid
 *
 */

public class View_WMP extends JFrame{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public ManschaftsDaten md;
		public BootDaten bd;
		public String query;
		public ResultSet rs;
		public static DefaultTableModel dtm;
		public View_Login vl;
		
		private String changedRow;
		private String changedRowWettfahrt;
		private int row;
		static int punkteStand = 0;
		
	    private javax.swing.JButton jButton1;
	    private javax.swing.JButton jButton2;
	    private javax.swing.JButton jButton4;
	    private static javax.swing.JTable wmp;
		
	    /**
	     * 
	     * Die View_WMP ist für das anzeigen des Inhaltes der Tabelle erzeilt aus der Datenbank zuständig.
	     * Dessweiteren besitzt sie auch noch die Methode update
	     * 
	     * @param md
	     * @throws SQLException
	     * @throws ClassNotFoundException
	     */
	    
		public View_WMP(ManschaftsDaten md) throws SQLException, ClassNotFoundException{

			this.md = md;
			this.setLayout(new GridLayout(2,1));
			
			Container buttons = new Container();
			
			buttons.setLayout(new GridLayout(1,3));
			
			Container r = new Container();
			
			r.setLayout(new FlowLayout());
			
			String[] spaltennamen = {"Manschaft","Wettfahrt","Jahr","Datum","Punkte"};
			String[] buttonname = {"Punktestand bearbeiten","Sortierung","Beenden"};
			
			Object[][] manschaftsDaten = md.getErzieltDaten();
			
			dtm = new DefaultTableModel(manschaftsDaten,spaltennamen);
			
			wmp = new JTable(dtm){
				public boolean isCellEditable(int rowIndex, int colIndex) {
				      return false; //Disallow the editing of any cell
				 }
			};
			
			wmp.setPreferredScrollableViewportSize(new Dimension(800,400));
			wmp.setFillsViewportHeight(true);
			wmp.getTableHeader().setReorderingAllowed(false);
			
			//this.wmp.setAutoCreateRowSorter(true);
			
			jButton1 = new JButton();
			jButton2 = new JButton();
			jButton4 = new JButton();
	        
			buttons.add(jButton1);
			buttons.add(jButton2);
			buttons.add(jButton4);
			
	        jButton1.setText(buttonname[0]);
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                try {
						jButton1ActionPerformed(evt);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
	                
	            }
	        });
	        
	        jButton2.setText(buttonname[1]);
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	                jButton2ActionPerformed(evt);
	                
	                
	            }
	        });
	        
	        jButton4.setText(buttonname[2]);
	        jButton4.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {

						jButton4ActionPerformed(evt);
						
						dispose();
	            }
	        });
	        
	        wmp.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            @Override
	            public void valueChanged(ListSelectionEvent event) {
	                if (wmp.getSelectedRow() > -1) {

	                   row = wmp.getSelectedRow();
	                   changedRow = (String) wmp.getValueAt(row, 0);
	                   changedRowWettfahrt = (String) wmp.getValueAt(row, 1);
	                   System.out.print(changedRowWettfahrt);
	                	
	                }
	            }
	        });
			
			
			JScrollPane  sp = new JScrollPane(wmp);
			add(sp);
			add(buttons);
			this.setVisible(true);
			this.setSize(1280,700);
		}

	    protected void jButton2ActionPerformed(ActionEvent evt) {
			
	    	String[] options = new String[] {"ASC", "DESC", "Abbrechen"};
		    int response = JOptionPane.showOptionDialog(null, "In der Suchfunktion ist es moeglich die Daten absteigend oder Aufstagend (nach aktuellem Punktestand) zu sortieren___ASC = Aufsteigend___DESC = Absteigend", "Suchfunktion",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);
		
		    if(response == 0){    
		    	ManschaftsDaten.query = "SELECT * FROM erzielt ORDER BY punkte ASC";
		    	
	         	View.reloadWettfahrt();
		   		
	   			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   			dispose();
		    	
		    }
		    if(response == 1){    
		    	ManschaftsDaten.query = "SELECT * FROM erzielt ORDER BY punkte DESC";
		    	
	         	View.reloadWettfahrt();
		    	
	   			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   			dispose();
		    }

		    if(response == 4){    
		    	
		    }
			
		}

		private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
	    	
	    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        dispose();
	    	
	    }                                        

                                      

	     /**
	      * 
	      * 
	      * 
	      * @param evt
	      * @throws SQLException 
	      * @throws ClassNotFoundException 
	      */
	     
	     private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws ClassNotFoundException, SQLException {                                         
	    	 
	    	 updateRow(row);
	    	 
	    }
	     
	         public void run() {
	             try {
						try {
							new View_WMP(md).setVisible(true);
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	             
	             wmp.repaint();
	         } 
	       
	         /**
	          * Detailierte beschreibung in der Main View
	          * 
	          * Kurzbeschreibung: Die Methode updateRow bekommt die Aktuell ausgewählte row als Parameter übergeben und soll bei der asugewählten Zeile
	          * 				  den Punktestand verändern. Der schon fast einzige Unterschied hierbei ist das hier zwei Werte überprüft werden und zwar
	          * 				  einaml der mannschaftsname und einemal der Wettfahrtsname um nicht der gleichen mannschaft bei der Punktestandbearbeitung
	          * 				  bei jedem der Wettkämpfe die gleiche Punkteanzahl zu geben.
	          * 
	          * 
	          * @param row
	          * @throws SQLException
	          * @throws ClassNotFoundException
	          */
	         
	         public void updateRow(int row) throws SQLException, ClassNotFoundException {

	         	DB_Connection user_con = new DB_Connection();
	        	 	
	         	if (user_con.connect()) {
	         	
	         		/*
	         	String mname = "";
	     	 	String wname = "";
	     	 	int wjahr = 0;
	     	 	String wdatum = "";
	     	 	*/
	     	 	
	     	 	/*
	     	 	mname = (String) this.wmp.getValueAt(this.wmp.getSelectedRow(), 0);
	     	 	wname = (String) this.wmp.getValueAt(this.wmp.getSelectedRow(), 1);
	     	 	wjahr = (Integer) this.wmp.getValueAt(this.wmp.getSelectedRow(), 2);
	     	 	wdatum = (String) this.wmp.getValueAt(this.wmp.getSelectedRow(), 3);
	     	 	*/
	     	 	
	         	try{
	     	 	
	         		String[] options = new String[] {"ASC", "DESC", "Abbrechen"};
	     	 	
	         		punkteStand = Integer.parseInt(JOptionPane.showInputDialog(wmp, "Bitte tragen Sie hier den neuen Punktestand ein"));
	     	 	
	         		System.out.println(punkteStand+", "+changedRow+", "+changedRowWettfahrt);
	     	    		
	     	    	try{
	     		    	
	     		    	
	     		    	String updateRowBoot = "BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE; UPDATE erzielt SET punkte = ? WHERE mname = '"+changedRow+"' and wname = '"+changedRowWettfahrt+"'; COMMIT;";
	     		    	
	     		    	PreparedStatement pst = user_con.pstm(updateRowBoot);
	     		    	
	     		    	pst.setInt(1,punkteStand);
	     		    	
	     		    	System.out.println(updateRowBoot);

	     		    	pst.executeUpdate();
	     		    	
	     		    }catch(SQLException e){
	     		    	e.printStackTrace();
	     		    }
	     		    
	       			dtm.fireTableCellUpdated(wmp.getSelectedRow(),0);
	       			
	     	 	}catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Es gibt einen Fehler bei ihrer Dateneingabe. Fuellen Sie die Felder nach diesem Schema aus:", "InfoBox: " + "Warning", JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(null, "punktestand = Ganze Zahl, z.B.: 12345", "InfoBox: " + "geloescht", JOptionPane.INFORMATION_MESSAGE);
	    		}
	         	
	         	dispose();
	         	View.reloadWettfahrt();
	         	
	         }
	     }
	}

