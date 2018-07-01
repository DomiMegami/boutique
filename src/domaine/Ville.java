package domaine;

public class Ville {
	
	//attributs
	private int id;
	private String nom;
	private int codePostal;
	
	//geters et setters
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
	 * @return the codePostal
	 */
	public int getCodePostal() {
		return codePostal;
	}
	/**
	 * @param codePostal the codePostal to set
	 */
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	
	//constructeurs
	public Ville() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ville(int id, String nom, int codePostal) {
		super();
		this.id = id;
		this.nom = nom;
		this.codePostal = codePostal;
	}
	
	
	

}
