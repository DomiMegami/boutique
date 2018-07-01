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

	// méthodes

	/**
	 * Méthode pour appeler tous les enregistrements de la table clients.
	 * 
	 * @return une liste de Client
	 */
	public ArrayList<Client> getAll() {
		listeClients.clear();

		try {
			// Etape 1-2 : ouvrir la connection
			cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

			// Etape 3 : Préparer la requête
			st = cn.createStatement();

			// Etape 4 : Execution de la requête
			String requeteSql = "SELECT * FROM clients";
			ResultSet rs = st.executeQuery(requeteSql);

			// Etape 5 (Si récup ok) : parcours du résultat
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
	 * Méthode pour récupérer les données d'un client à partir de son nom et de son
	 * prénom
	 * 
	 * @param nomClient    : nom du client à chercher
	 * @param prenomClient : prénom du client à chercher
	 * @return : le client recherché
	 */
	public Client getClient(String nomClient, String prenomClient) {

		Client client = new Client();
		listeClients.clear();

		try {
			// Etape 1-2 : ouvrir la connection
			cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

			// Etape 3 : Préparer la requête
			st = cn.createStatement();

			// Etape 4 : Execution de la requête
			String requeteSql = "SELECT * FROM clients WHERE nom_clt = '" + nomClient + "' AND prenom_clt = '"
					+ prenomClient + "'";
			// System.out.println(requeteSql);
			ResultSet rs = st.executeQuery(requeteSql);

			// Etape 5 (Si récup ok) : parcours du résultat
			while (rs.next()) {
				int id = rs.getInt("id_clt");
				String nom = rs.getString("nom_clt");
				String prenom = rs.getString("prenom_clt");
				int id_ville = rs.getInt("id_ville");
				clientEnCours = new Client(id, nom, prenom, id_ville);
				listeClients.add(clientEnCours);
			}

			if (listeClients.size() > 1) {
				System.out.println("Attention, plusieurs clients ont le même nom.");
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
	 * Méthode pour récupérer les données d'un client à partir de son id
	 * 
	 * @param idClientCherche : id du client à chercher
	 * @return : le client recherché
	 */
	public Client getClient(int idClientCherche) {

		Client client = new Client();

		try {
			// Etape 1-2 : ouvrir la connection
			cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

			// Etape 3 : Préparer la requête
			st = cn.createStatement();

			// Etape 4 : Execution de la requête
			String requeteSql = "SELECT * FROM clients WHERE id_clt = " + idClientCherche;

			ResultSet rs = st.executeQuery(requeteSql);

			// Etape 5 (Si récup ok) : parcours du résultat
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
	 * Méthode pour modifier le nom d'un client à partir de son id
	 * 
	 * @param idClientAModifier : id du client à modifier
	 * @param nouveauNom        : nouveau nom à inscrire dans la base de données
	 */
	public void updateNomClient(int idClientAModifier, String nouveauNom) {

		try {

			// vérif que l'enregistrement existe en bdd :
			if (this.getClient(idClientAModifier).getId() != 0) {

				// Etape 1-2 : ouvrir la connection
				cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

				// Etape 3 : Préparer la requête
				st = cn.createStatement();
				// Etape 4 : Execution de la requête
				String requeteSql = "UPDATE clients SET nom_clt = '" + nouveauNom + "' WHERE id_clt = "
						+ idClientAModifier;

				st.executeUpdate(requeteSql);

			} else {
				System.out.println("Modification impossible : ce client n'est pas enregistré");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Etape 6 : fermer la connection et le statement.
			new ConnectBoutiqueDao().fermerConnectionEtStatement(cn, st);
		}

	}

	/**
	 * Méthode pour supprimer un client à partir de son id
	 * 
	 * @param idClientAsupprimer : id du client à supprimer
	 */
	public void deleteClient(int idClientAsupprimer) {
		try {
			// vérif que l'enregistrement existe en bdd :
			if (this.getClient(idClientAsupprimer).getId() != 0) {

				// Etape 1-2 : ouvrir la connection
				cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

				// Etape 3 : Préparer la requête
				st = cn.createStatement();
				// Etape 4 : Execution de la requête
				String requeteSql = "DELETE FROM clients WHERE id_clt = " + idClientAsupprimer;

				st.executeUpdate(requeteSql);

			} else {
				System.out.println("Suppression impossible : ce client n'est pas enregistré");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Etape 6 : fermer la connection et le statement.
			new ConnectBoutiqueDao().fermerConnectionEtStatement(cn, st);
		}

	}

	/**
	 * Méthode pour créer un nouvel enregistrement de client dans la bese de données
	 * 
	 * @param clientAinserer : nouveau client
	 */
	public void insertClient(Client clientAinserer) {
		try {
			// Etape 1-2 : ouvrir la connection
			cn = new ConnectBoutiqueDao().ouvrirConnectionBdd();

			// Etape 3 : Préparer la requête
			st = cn.createStatement();
			// Etape 4 : Execution de la requête
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
