package enterprise;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.ws.rs.DefaultValue;

/**
 * Entity implementation class for Entity: Objet
 *
 */
@Entity
public class Objets implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_objet;
	@Column
	private String description;	
	@Column
	private String nom;
	@Column
	private String categorie;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pseudo")
	private Client client;
	private static final long serialVersionUID = 1L;
	
	
	
	


	public Objets() {
		super();
	}   
	
	

	public Objets(int id_objet, String description, String nom, String categorie, Client client) {
		this.id_objet = id_objet;
		this.description = description;
		this.nom = nom;
		this.categorie = categorie;
		this.client = client;
	}



	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

	public int getId() {
		return this.id_objet;
	}

	public void setId(int id) {
		this.id_objet = id;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public String getCategorie() {
		return this.categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}



	@Override
	public String toString() {
		return "Objets [ " + id_objet + "| " + description + "| " + nom + "| "+ categorie +"| "+client.getId() +"  ]";
		}
   
	
}
