package domaine;

public class Client {

	// attributs
	private int id;
	private String nom;
	private String prenom;
	private int id_ville;

	// getters et setters
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @return the id_ville
	 */
	public int getId_ville() {
		return id_ville;
	}

	/**
	 * @param id_ville the id_ville to set
	 */
	public void setId_ville(int id_ville) {
		this.id_ville = id_ville;
	}

	// constructeurs
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(int id, String nom, String prenom, int id_ville) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.id_ville = id_ville;
	}

	public Client(String nom, String prenom, int id_ville) {
		super();
		this.id = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.id_ville = id_ville;
	}

}
