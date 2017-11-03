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
		if(cli!=null && a!=null) {
			if(aa==null&&sb.getClientRights(cli.getId(), "objets")<=5) {
				if(sb.getObjetsById(a.getObjets().getId())!=null && a.getObjets().getClient().getId().equals(cli.getId())) {
					if(this.checkObjetsAuction(a.getClient().getId(), a.getObjets().getId())) {
						em.persist(a);					
						cli.setNb_Objetss_aucttion(cli.getNb_Objetss_aucttion()+1);
						sb.updateClient(cli);
						return "OK";
					}else {
						return "L'objet est en cours de vente ou deja vendu";
					}

				}else {
					return "vous n'êtes pas le proprietaire de l'objet ou l'objet n'existe plus";
				}
			}else {
				return "Limite depassé";
			}
		}else {
			return "( Problem : Auction or User )";
		}
		
		
	}


	@Override
	public String startAuction(String c, int a) {
		
		Client cli =sb.getClientById(c);
		Auction aa = this.getAuctionById(a);
		if(cli!=null && aa!=null) {
			int current_statut = aa.getStatut();
			if(PARAM_AUCTION_STATUT_STARTED!=current_statut) {
				if(cli.getId().equals(aa.getClient().getId())) {
					this.updateAuction(aa.getId(), PARAM_AUCTION_STATUT_STARTED);
					return "Ok";
				}else{
					return "vous n'avez le droit de demarrer cette auction !";
				}
			}else {
				return "L'auction est déja lancée !";
			}
		}else {
			return "Erreur lors de demarage de l'auction";
		}	
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
			
			if( cli!=null && aa!=null) {
					if(aa.getStatut()==PARAM_AUCTION_STATUT_STARTED) {
						if( cli.getStatut()!=PARAM_ADMIN_USER_STATUT || aa.getClient().getId().equals(cli.getId())) {
							if(z) {
								aa.setId_last_bider(cli);
								if(aa.getPrix_final()==0) {
									aa.setPrix_final(aa.getPrix_inc()+aa.getPrix_depart());
								}
							else{
								aa.setPrix_final(aa.getPrix_final()+aa.getPrix_inc());
							}
								aa.setId_last_bider(cli);
								
								Auction cx=em.merge(aa);
								return "Votre bid a été enregistré ,le prix de l'objet devient : "+cx.getPrix_final();
							}else {
								aa.setId_last_bider(cli);
								if(aa.getPrix_final()==0) {
									aa.setPrix_final(aa.getPrix_inc()+aa.getPrix_depart());
								}
							else{
								aa.setPrix_final(aa.getPrix_final()+aa.getPrix_inc());
							}
								aa.setId_last_bider(cli);
								
								Auction cx=em.merge(aa);
								Objets cs= sb.getObjetsById(aa.getObjets().getId());
								cs.setClient(cli);
								sb.updateObjet(cs);
								closeAuction(cx.getId());
								return "Fin de l'auction l'objet a été acheté par "+cli.getNom()+" [ Prix final ]"+cx.getPrix_final()+""
										+ "\n Auction is closed "+closeAuction(cx.getId());
							}
							
						}else {
							return "les admins et propriétaire de l'auction n'ont pas le droit de faire des proposition";
						}
					}else {
						return "l'auction n'a pas encore demarée!";
					}
			}else {
				return "";
			}
		
		
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

	

	
	private boolean checkObjetsAuction(String cli,int objet) {
		Client cl= sb.getClientById(cli);
		Objets obj= sb.getObjetsById(objet);
		if(cl!=null && obj!=null) {
			Query query =em.createQuery(JPQL_ADD_OBJET_TO_AUCTION);
			query.setParameter(1, cl);
			query.setParameter(2, obj);
			return ((Long)query.getSingleResult()==0)?true:false;
		}

		return false;
		
	}
	
	@Override
	public Auction updateAuction(Auction c) {
			return em.merge(c);
	}

	
	
	
	
	

	
}
