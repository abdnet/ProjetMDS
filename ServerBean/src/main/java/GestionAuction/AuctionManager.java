package GestionAuction;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import enterprise.AdministrationAuction;
import enterprise.AdministrationClient;
import enterprise.Auction;
import enterprise.Client;
import enterprise.Objets;
import parameters.Constants;


@Stateless
public class AuctionManager implements AdministrationAuction,Constants {

	@PersistenceContext(name = "pu1")
	  private EntityManager em;
	
	@EJB
	private AdministrationClient sb;

	@Override
	public String creatAuction(Auction a) {
		Auction aa = this.getAuctionById(a.getId());
		Client cli = sb.getClientById(a.getClient().getId());
		if(cli!=null && checkCountAuction(cli.getId())) {	
				if(aa==null && a!=null) {
				if(this.checkObjetsAuction(a.getClient().getId(), a.getObjets().getId())) {
					if(sb.getObjetsById(a.getObjets().getId())!=null && sb.getClientById(a.getClient().getId())!=null) {
						em.persist(a);					
						cli.getMesauction().add(a);
						sb.updateClient(cli);
						return "OK";
					}
					
				}
					return "L'objet est en cours de vente ou déja vendu";
				}else{
						return "Erreur lors de la creation de l'auction"; 
				}
		}
		return "vous êtes limité à 5 ventes en même temps !!";
	}


	@Override
	public String startAuction(String c, int a) {
		
		Client cli =sb.getClientById(c);
		Auction aa = this.getAuctionById(a);
		if( cli!=null && aa!=null) {
			int current_statut= aa.getStatut();
			Objets obj =sb.getObjetsById(aa.getObjets().getId());
			if(current_statut==PARAM_AUCTION_STATUT_NOT_STARTED && this.checkCountAuction(c) && obj!=null) {
			if(cli.getId().equals(aa.getClient().getId())) {
				if(this.updateAuction(aa.getId(), PARAM_AUCTION_STATUT_STARTED)!=null) {
					return "OK";
				}else {
					return "Non1";
				}
			}else {
				return "Non2"+cli.getId()+"  "+aa.getClient().getId()+" Auction  "+aa;
			}
		}
			else {
				return "5 auctions en attente"; 
			}
		}
		
		return"non";
	}

	@Override
	public List<Auction> getAllAuction(int statut) {
		ArrayList<Auction> auctios=new ArrayList<Auction>();
		Query query = em.createQuery(JPQL_SELECT_ALL_AUCTION_BY_STATUT);
		query.setParameter(1, statut);
		return (ArrayList<Auction>)query.getResultList();
		
	}
	
	@Override
	public List<Auction> getAllAuction() {

		Query query = em.createQuery(JPQL_SELECT_ALL_AUCTION);
		return (List<Auction>)query.getResultList();
		
	}
	
	@Override
	public String sendBidAution(String c, int a,boolean z) {
		Client cli=sb.getClientById(c);
		Auction aa = this.getAuctionById(a);
		if(cli==null ||!aa.getAbonnees().contains(cli)) {
			return "Vous n'etes pas abonné à l'auction";
		}
		if(aa==null || aa.getStatut()!=PARAM_AUCTION_STATUT_STARTED) {
			return "L'auction n'a pas demarée .. veuillez patienter svp";
		}
		if(z) {
		aa.setId_last_bider(c);
		aa.setPrix_depart(aa.getPrix_depart()+aa.getPrix_inc());
		em.merge(aa);
		}else {
			Objets o= sb.getObjetsById(aa.getObjets().getId());
			o.setClient(sb.getClientById(aa.getId_last_bider()));
			em.merge(o);
			this.closeAuction(a);
			return "Fin de l'auction";
		}
		
		
		return "Bid enregistré";
		
	}


	@Override
	public boolean closeAuction(int a) {
		return (this.updateAuction(a, PARAM_AUCTION_STATUT_CLOSED).getStatut()==PARAM_AUCTION_STATUT_CLOSED)?true:false;
		
	}

	@Override
	public boolean deleteAuction(String c, int a) {
		Auction aa = this.getAuctionById(a);
		Client cli=sb.getClientById(c);
		if(aa!=null && cli!=null && (aa.getClient().getId().equals(cli.getId()) || cli.getStatut()==PARAM_ADMIN_USER_STATUT) ) {
				em.remove(aa);
				return (this.getAuctionById(a)==null)?true:false;
		}
		return false;
	}

	@Override
	public boolean deleteAllAuctions(String admin) {
		List<Auction> auctions=this.getAllAuction();
		for(Auction auction:auctions) {
			this.deleteAuction(admin,auction.getId());
		}
		
		return (this.getAllAuction()==null)?true:false;
	}

	@Override
	public Auction updateAuction(int a,int statut) {
		Auction aa = this.getAuctionById(a);	
		aa.setStatut(statut);
		return em.merge(aa);
			}
	@Override
	public Auction getAuctionById(int id) {
		return em.find(Auction.class, id);
	}

	@Override
	public List<Auction> getAuctionByClientId(String pseudo) {
		Query query =em.createQuery(JPQL_SELECT_ALL_AUCTION_BY_CLIENT_ID);
		query.setParameter(1, pseudo);
		return (ArrayList<Auction>)query.getResultList();
	}
	@Override
	public String connectToAuction(int auction, String client) {
		Auction a = this.getAuctionById(auction);
		//em.refresh(a);
		Client cli= sb.getClientById(client);
		if(!a.getAbonnees().contains(cli)) {			
		if(cli!=null && a!=null  && !cli.getId().equals(a.getClient().getId()) && cli.getStatut()!=PARAM_ADMIN_USER_STATUT&&a.getStatut()==PARAM_AUCTION_STATUT_STARTED) {
					cli.setAuction(a);
					sb.updateClient(cli);
					a.getAbonnees().add(cli);
					a.setMesabonnes(a.getAbonnees());
					em.merge(a);
					Auction c =getAuctionById(a.getId());
					return "Accordé"+c.getAbonnees().contains(cli);	}
		}else{
			return "Vous êtes déja abonné";
		}
		
		return "Refusé";
	} 
	


	private boolean checkCountAuction(String cli) {
		Client cl= sb.getClientById(cli);
		if( cl!=null) {
			
			List<Auction> mesauctions = cl.getMesauction();
			/*Query query = em.createQuery(JPQL_COUNT_AUCTION_CREATED_BY_CLIENT);
			query.setParameter(1, cl);
			query.setParameter(2, PARAM_AUCTION_STATUT_STARTED);
			List<Long> count = query.getResultList();*/
			return (mesauctions.size()<PARAM_LIMIT_AUCTION)?true:false;
		}
		return false;
	}
	
	private boolean checkObjetsAuction(String cli,int objet) {
		Client cl= sb.getClientById(cli);
		Objets obj= sb.getObjetsById(objet);
		if(cl!=null && obj!=null) {
			Query query =em.createQuery(JPQL_ADD_OBJET_TO_AUCTION);
			query.setParameter(1, cl);
			query.setParameter(2, obj);
			List<Long> count = query.getResultList();
			return (count.get(0)==0)?true:false;
		}

		return false;
		
	}
	
	private  boolean containsClient(List<Client> list, Client id) {
	    for (Client object : list) {
	        if (object.getId() == id.getId()) {
	            return true;
	        }
	    }
	    return false;
	}
	

	
}
