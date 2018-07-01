package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectBoutiqueDao {
	
	private String url = "jdbc:mysql://localhost/boutique";
	private String login = "root";
	private String passwd = "";
	
	
	public Connection ouvrirConnectionBdd() {
		
		Connection cn = null;
		
		try {
			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");			
			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(this.url, this.login, this.passwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cn;
	}
	
	public void fermerConnectionEtStatement(Connection cn, Statement st) {
		try {
			// Etape 6 : libérer ressources de la mémoire.
			cn.close();
			st.close();
			//System.out.println("connection et statement fermés.");
		} catch (SQLException e) {
			// TODO: gestion erreur
			e.printStackTrace();
		}		
	}

}
