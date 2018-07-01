package presentation;

import java.util.ArrayList;

import dao.ClientDao;
import domaine.Client;

public class MaBoutique {

	public static void main(String[] args) {

		ArrayList<Client> listeClientsRecup = new ArrayList<Client>();

		ClientDao clientDao = new ClientDao();

		Client clientRecherche = new Client();
		
		Client clientAinserer = new Client("Test", "test", 2);

		// Tous les enregistrements de la table clients

		listeClientsRecup = clientDao.getAll();

		System.out.println("+------------------------+");
		for (Client client : listeClientsRecup) {
			System.out.println("| " + client.getNom() + " " 
					+ client.getPrenom() + "\n+------------------------+");
		}

		// récup d'un client à partir de nom et prénom.

		clientRecherche = clientDao.getClient("RICHY", "Rick");
		System.out.println("Voici les données du client recherché : \n" + clientRecherche.getNom() + " "
				+ clientRecherche.getPrenom() + ", id: " + clientRecherche.getId() + ", ville : "
				+ clientRecherche.getId_ville());

		clientRecherche = clientDao.getClient(7);
		System.out.println("Voici les données du client recherché : \n" + clientRecherche.getNom() + " "
				+ clientRecherche.getPrenom() + ", id: " + clientRecherche.getId() + ", ville : "
				+ clientRecherche.getId_ville());

		// modifier un client :
		clientDao.updateNomClient(9, "TATA");

		// effacer un client :
		clientDao.deleteClient(8);
		
		//insérer un client : 
		clientDao.insertClient(clientAinserer);

	}

}
