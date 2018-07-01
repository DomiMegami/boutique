package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import domaine.Client;

public class ClientDao {

	private Connection cn = null;
	private Statement st = null;
	private ArrayList<Client> listeClients = new ArrayList<Client>();
	private Client clientEnCours = null;

	// m�thodes

	/**
	 * M�thode pour appeler tous les enregistrements de la table clients.
	 * 
	 * @return une liste de Client
	 */
	public ArrayList<Client> getAll() {
		listeClients.clear();

		try {
			// Etape 1-2 : ouvrir la connection
			cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

			// Etape 3 : Pr�parer la requ�te
			st = cn.createStatement();

			// Etape 4 : Execution de la requ�te
			String requeteSql = "SELECT * FROM clients";
			ResultSet rs = st.executeQuery(requeteSql);

			// Etape 5 (Si r�cup ok) : parcours du r�sultat
			while (rs.next()) {
				int id = rs.getInt("id_clt");
				String nom = rs.getString("nom_clt");
				String prenom = rs.getString("prenom_clt");
				int id_ville = rs.getInt("id_ville");
				clientEnCours = new Client(id, nom, prenom, id_ville);
				listeClients.add(clientEnCours);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Etape 6 : fermer la connection et le statement.
			new ConnectBoutiqueDao().fermerConnectionEtStatement(cn, st);

		}

		return listeClients;

	}

	/**
	 * M�thode pour r�cup�rer les donn�es d'un client � partir de son nom et de son
	 * pr�nom
	 * 
	 * @param nomClient    : nom du client � chercher
	 * @param prenomClient : pr�nom du client � chercher
	 * @return : le client recherch�
	 */
	public Client getClient(String nomClient, String prenomClient) {

		Client client = new Client();
		listeClients.clear();

		try {
			// Etape 1-2 : ouvrir la connection
			cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

			// Etape 3 : Pr�parer la requ�te
			st = cn.createStatement();

			// Etape 4 : Execution de la requ�te
			String requeteSql = "SELECT * FROM clients WHERE nom_clt = '" + nomClient + "' AND prenom_clt = '"
					+ prenomClient + "'";
			// System.out.println(requeteSql);
			ResultSet rs = st.executeQuery(requeteSql);

			// Etape 5 (Si r�cup ok) : parcours du r�sultat
			while (rs.next()) {
				int id = rs.getInt("id_clt");
				String nom = rs.getString("nom_clt");
				String prenom = rs.getString("prenom_clt");
				int id_ville = rs.getInt("id_ville");
				clientEnCours = new Client(id, nom, prenom, id_ville);
				listeClients.add(clientEnCours);
			}

			if (listeClients.size() > 1) {
				System.out.println("Attention, plusieurs clients ont le m�me nom.");
			}

			client = listeClients.get(0);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Etape 6 : fermer la connection et le statement.
			new ConnectBoutiqueDao().fermerConnectionEtStatement(cn, st);
		}

		return client;
	}

	/**
	 * M�thode pour r�cup�rer les donn�es d'un client � partir de son id
	 * 
	 * @param idClientCherche : id du client � chercher
	 * @return : le client recherch�
	 */
	public Client getClient(int idClientCherche) {

		Client client = new Client();

		try {
			// Etape 1-2 : ouvrir la connection
			cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

			// Etape 3 : Pr�parer la requ�te
			st = cn.createStatement();

			// Etape 4 : Execution de la requ�te
			String requeteSql = "SELECT * FROM clients WHERE id_clt = " + idClientCherche;

			ResultSet rs = st.executeQuery(requeteSql);

			// Etape 5 (Si r�cup ok) : parcours du r�sultat
			while (rs.next()) {
				int id = rs.getInt("id_clt");
				String nom = rs.getString("nom_clt");
				String prenom = rs.getString("prenom_clt");
				int id_ville = rs.getInt("id_ville");
				client = new Client(id, nom, prenom, id_ville);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Etape 6 : fermer la connection et le statement.
			new ConnectBoutiqueDao().fermerConnectionEtStatement(cn, st);
		}

		return client;
	}

	/**
	 * M�thode pour modifier le nom d'un client � partir de son id
	 * 
	 * @param idClientAModifier : id du client � modifier
	 * @param nouveauNom        : nouveau nom � inscrire dans la base de donn�es
	 */
	public void updateNomClient(int idClientAModifier, String nouveauNom) {

		try {

			// v�rif que l'enregistrement existe en bdd :
			if (this.getClient(idClientAModifier).getId() != 0) {

				// Etape 1-2 : ouvrir la connection
				cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

				// Etape 3 : Pr�parer la requ�te
				st = cn.createStatement();
				// Etape 4 : Execution de la requ�te
				String requeteSql = "UPDATE clients SET nom_clt = '" + nouveauNom + "' WHERE id_clt = "
						+ idClientAModifier;

				st.executeUpdate(requeteSql);

			} else {
				System.out.println("Modification impossible : ce client n'est pas enregistr�");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Etape 6 : fermer la connection et le statement.
			new ConnectBoutiqueDao().fermerConnectionEtStatement(cn, st);
		}

	}

	/**
	 * M�thode pour supprimer un client � partir de son id
	 * 
	 * @param idClientAsupprimer : id du client � supprimer
	 */
	public void deleteClient(int idClientAsupprimer) {
		try {
			// v�rif que l'enregistrement existe en bdd :
			if (this.getClient(idClientAsupprimer).getId() != 0) {

				// Etape 1-2 : ouvrir la connection
				cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

				// Etape 3 : Pr�parer la requ�te
				st = cn.createStatement();
				// Etape 4 : Execution de la requ�te
				String requeteSql = "DELETE FROM clients WHERE id_clt = " + idClientAsupprimer;

				st.executeUpdate(requeteSql);

			} else {
				System.out.println("Suppression impossible : ce client n'est pas enregistr�");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Etape 6 : fermer la connection et le statement.
			new ConnectBoutiqueDao().fermerConnectionEtStatement(cn, st);
		}

	}

	/**
	 * M�thode pour cr�er un nouvel enregistrement de client dans la bese de donn�es
	 * 
	 * @param clientAinserer : nouveau client
	 */
	public void insertClient(Client clientAinserer) {
		try {
			// Etape 1-2 : ouvrir la connection
			cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

			// Etape 3 : Pr�parer la requ�te
			st = cn.createStatement();
			// Etape 4 : Execution de la requ�te
			String requeteSql = "INSERT INTO clients(nom_clt, prenom_clt, id_ville) VALUES ('" + clientAinserer.getNom()
					+ "','" + clientAinserer.getPrenom() + "'," + clientAinserer.getId_ville() + ")";
			// System.out.println(requeteSql);
			st.executeUpdate(requeteSql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Etape 6 : fermer la connection et le statement.
			new ConnectBoutiqueDao().fermerConnectionEtStatement(cn, st);
		}

	}

}
