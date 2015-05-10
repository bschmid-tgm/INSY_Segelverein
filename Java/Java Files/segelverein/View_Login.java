package segelverein;

import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author Bernahrd Schmid
 */

public class View_Login extends javax.swing.JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public BootDaten bd;
	public ManschaftsDaten md;
	public String db_conn;
	DB_Connection dbc;
	
	private String dburl;
	private String dbuser;
	private String dbpw;

	private javax.swing.JTextField db_name;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JTextField password;
	private javax.swing.JTextField server_url;
	private javax.swing.JTextField username;
	private javax.swing.JCheckBox jCheckBox1;
	protected String un;
	protected String pw;
	protected String dbn;
	protected String dbu;

	public View_Login() {
		initComponents();
	}	
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		username = new javax.swing.JTextField();
		password = new javax.swing.JTextField();
		db_name = new javax.swing.JTextField();
		server_url = (new javax.swing.JTextField());
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jCheckBox1 = new javax.swing.JCheckBox();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Wilkommen auf der db der Segelvereine");

		jLabel2.setText("Autor: Bernhard Schmid");

		jLabel3.setText("Version: 1.0");

		jLabel4.setText("Loggen Sie sich bitte ein um auf die Datenbank zugreifen zu Können:");

		jLabel5.setText("Username:");

		jLabel6.setText("Passwort:");

		jLabel7.setText("Datenbankname:");

		jLabel8.setText("Server-Url:");


		jButton1.setText("Login");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				dbu =  server_url.getText(); 
				dbn =  db_name.getText(); 
				un =  username.getText(); 
				pw =  password.getText();
				
				setUrl();
				setUser();
				setPassw();
				
				System.out.println("1: " + getUrl() + getUser() + getPassw());
				
				dbc = new DB_Connection();
				dbc.setDaten(getUrl(),getUser(),getPassw());
				
				bd = new BootDaten();
				md = new ManschaftsDaten();
				
				try {
					new View(bd,md);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "Bitte ueberpruefen Sie ihre Anmeldedaten und versuchen Sie es erneut!", "InfoBox: " + "Anemldung fehlgeschlagen", JOptionPane.INFORMATION_MESSAGE);
				}


			}
		});

		jButton2.setText("Bsp. Conn");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed1(evt);
			}
		});

		jButton3.setText("Abbrechen");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			}
		});

		jCheckBox1.setText("Bestätigen der allgemeinen Segelverein_DB Nutzungsbedingungen");

		jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

			}
		});
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
														.addComponent(jLabel7)
														.addGap(18, 18, 18)
														.addComponent(db_name))
														.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jLabel5)
																		.addComponent(jLabel6))
																		.addGap(18, 18, 18)
																		.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(username)
																				.addComponent(password)))
																				.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
																						.addComponent(jLabel8)
																						.addGap(18, 18, 18)
																						.addComponent(server_url, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))))
																						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jLabel1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(jLabel4)
						.addGap(18, 18, 18)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel5)
								.addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel6)
										.addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel7)
												.addComponent(db_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGap(18, 18, 18)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel8)
														.addComponent(server_url, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18, 18))
				);

		this.setVisible(true);

		pack();
	}                      

	private void jButton2ActionPerformed1(java.awt.event.ActionEvent evt) {                                           

		username.setText("postgres");
		password.setText("DzDh741852963!");
		db_name.setText("segelverein");
		server_url.setText("localhost:5432");

	}
	
	public void setUrl(){
		this.dburl = "jdbc:postgresql://"+server_url.getText()+"/"+ db_name.getText();
	}
	public void setUser(){
		this.dbuser = username.getText();
	}
	public void setPassw(){
		this.dbpw = password.getText();
	}

	public String getUrl(){
		return this.dburl;
	}
	public String getUser(){
		return this.dbuser;
	}
	public String getPassw(){
		return this.dbpw;
	}
	/*
	public String getDaten(){
		System.out.println("jdbc:postgresql://"+server_url.getText()+"/"+ db_name.getText()+ ", "+username.getText()+", "+password.getText());
		return "jdbc:postgresql://"+server_url.getText()+"/"+ db_name.getText()+ "?user="+username.getText()+"&password="+password.getText();
	}
	*/
}
