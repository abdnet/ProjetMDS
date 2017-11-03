package enterprise;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Client
 *
 */
@Entity
public class Client implements Serializable {
	
	@Id
	private String pseudo;
	@Column
	private String nom;
	@Column
	private String prenom;
	@Column
	private int code_postal;
	@Column(unique=true)
	private String mail;
	
    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
	private List<Objets> mesObjetss=new ArrayList<Objets>();
	
	@Column
	private int statut;
	@Column
	private int nb_Objetss_aucttion;
	
	
	private static final long serialVersionUID = 1L;

	
	
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(String pseudo, String nom, String prenom, int code_postal, String mail,int statut) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.code_postal = code_postal;
		this.mail = mail;
		this.statut = statut;
		this.nb_Objetss_aucttion=0;
	}
	public List<Objets> getMesObjetss() {
		return mesObjetss;
	}
	public void setMesObjetss(ArrayList<Objets> mesObjetss) {
		this.mesObjetss = mesObjetss;
	}
	public void setId(String id) {
		this.pseudo = id;
	}
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}   
	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}   
	public int getCode_postal() {
		return this.code_postal;
	}

	public void setCode_postal(int i) {
		this.code_postal = i;
	}   
	public String getMail() {
		return this.mail;
	}
	public String getId() {
		return pseudo;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public int getStatut() {
		return statut;
	}
	public void setStatut(int statut) {
		this.statut = statut;
		
	}
	public int getNb_Objetss_aucttion() {
		return nb_Objetss_aucttion;
	}
	public void setNb_Objetss_aucttion(int nb_Objetss_aucttion) {
		this.nb_Objetss_aucttion = nb_Objetss_aucttion;
	}
	
	public void addObjet(Objets obj) {
		if(!this.getMesObjetss().contains(obj)) {
			this.getMesObjetss().add(obj);
			if(obj.getClient() !=null) {
				obj.getClient().getMesObjetss().remove(obj);
			}
			obj.setClient(this);
			
		}
	}
	@Override
	public String toString() {
		return "Client_info [ \t" + pseudo + "|\t" + nom + "|\t" + prenom + "|\t" + code_postal
				+ "|\t" + mail + "|\t" + statut + "|\t"
				+ nb_Objetss_aucttion + "\t ]";
	}
	
	

	
}