package enterprise;	

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Auction
 *
 */
@Entity
@Table(name="auctions")
public class Auction implements Serializable {

	

	private static double last_bid=0;
	private static 	String id_last_bider;   
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private int statut;
	@ManyToOne
	@JoinTable(name="mesauctions")
	private Client client;
	@ManyToOne
	@JoinColumn(name="id_Objets")
	private Objets Objets;
	@Column
	private float duree;
	@Column
	private float prix_depart;
	@Column
	private float prix_inc;
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "auction", cascade=CascadeType.ALL)
	private List<Client> mesabonnes = new ArrayList<Client>();
	
	
	
	
	
	public List<Client> getAbonnees() {
		return mesabonnes;
	}
	
	public void setMesabonnes(List<Client> mesabonnes) {
		this.mesabonnes = mesabonnes;
	}

	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Objets getObjets() {
		return Objets;
	}
	public void setObjets(Objets Objets) {
		this.Objets = Objets;
	}
	public float getDuree() {
		return duree;
	}
	public void setDuree(float duree) {
		this.duree = duree;
	}
	public float getPrix_depart() {
		return prix_depart;
	}
	public void setPrix_depart(float prix_depart) {
		this.prix_depart = prix_depart;
	}
	public double getLast_bid() {
		return last_bid;
	}
	public void setLast_bid(double last_bid) {
		this.last_bid = last_bid;
	}
	public String getId_last_bider() {
		return id_last_bider;
	}
	public void setId_last_bider(String id_last_bider) {
		this.id_last_bider = id_last_bider;
	}

	private static final long serialVersionUID = 1L;

	public Auction() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getStatut() {
		return this.statut;
	}

	public void setStatut(int statut) {
		this.statut = statut;
	}
	public float getPrix_inc() {
		return prix_inc;
	}
	public void setPrix_inc(float prix_inc) {
		this.prix_inc = prix_inc;
	}
	
	
	
	@Override
	public String toString() {
		return "Auction [id=" + id + ", statut=" + statut + ", client=" + client + ", Objets=" + Objets + ", duree="
				+ duree + ", prix_depart=" + prix_depart + ", prix_inc=" + prix_inc + ", abonnees=" + mesabonnes + "]";
	} 
   
}
