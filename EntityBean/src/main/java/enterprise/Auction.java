package enterprise;	

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Auction
 *
 */
@Entity
@Table(name="auctions")
public class Auction implements Serializable {

	
	  
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column
	private int statut;
	@ManyToOne
	@JoinColumn(name="pseudo")
	private Client client;
	@OneToOne
	@JoinColumn(name="id_Objets")
	private Objets Objets;
	@Column
	private float duree;
	@Column
	private float prix_depart;
	@Column
	private double prix_inc;
	@Column
	private double prix_final;
	@OneToOne
	private static 	Client last_bider; 
	
	
	


	public Auction(int id, Client client, enterprise.Objets objets) {
		this.id = id;
		this.statut = 0;
		this.client = client;
		Objets = objets;
		this.duree = 0;
		this.prix_depart = 0;
		this.prix_inc = 0;
		this.prix_final=0;
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
	public Auction setDuree(float duree) {
		this.duree = duree;
		return this;
	}
	public float getPrix_depart() {
		return prix_depart;
	}
	public Auction setPrix_depart(float prix_depart) {
		this.prix_depart = prix_depart;
		return this;
	}
	public double getLast_bid() {
		return prix_final;
	}
	public Auction setLast_bid(double prix_final) {
		this.prix_final = prix_final;
		return this;
	}
	public Client getId_last_bider() {
		return last_bider;
	}
	public Auction setId_last_bider(Client id_last_bider) {
		this.last_bider = id_last_bider;
		return this;
	}

	public double getPrix_final() {
		return prix_final;
	}

	public Auction setPrix_final(double prix_final) {
		this.prix_final = prix_final;
		return this;
	}

	public static Client getLast_bider() {
		return last_bider;
	}

	public static void setLast_bider(Client last_bider) {
		Auction.last_bider = last_bider;
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
	public double getPrix_inc() {
		return prix_inc;
	}
	public Auction setPrix_inc(double d) {
		this.prix_inc = d;
		return this;
	}
	
	
	@Override
	public String toString() {
		return "Auction [id=" + id + ", statut=" + statut + ", client=" + client + ", Objets=" + Objets + ", duree="
				+ duree + ", prix_depart=" + prix_depart + ", prix_inc=" + prix_inc + ", abonnees="  + "]";
	} 
   
}
