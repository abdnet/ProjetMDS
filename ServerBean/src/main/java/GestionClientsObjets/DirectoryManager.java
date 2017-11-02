package GestionClientsObjets;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import enterprise.AdministrationClient;
import enterprise.Client;
import enterprise.Objets;
import parameters.Constants;


/**
 * The stateless session bean.
 */
@Stateless
public  class DirectoryManager implements AdministrationClient,Constants {

	@PersistenceContext(name = "pu1")
	  private EntityManager em;
	
	@Override
	public String addClient(Client c) {
		
		if(c!=null) {
			if(em.find(Client.class,c.getId())==null) {
			 	em.persist(c);
				 if(em.find(Client.class, c.getId())!=null) {
					 return "Ok";
				 }		 
			}
		}
	em.close();
	return "Non";
}


	@Override
	public boolean removeClient(String cible, String admin) {
			Client c= this.getClientById(cible);
			Client adm=this.getClientById(admin);
			if( c!=null && adm!=null && this.getClientRights(adm.getId(), "statut")==PARAM_ADMIN_USER_STATUT) {
			 	em.remove(c);
			 	
			 	return true;
			}
		
		return false;
	}	

	@Override
	public boolean updatdeClientRights(Client cc, String rights, Client admin,int order) throws SQLException {
			Client c= this.getClientById(cc.getId());
		if( c!=null){
				switch(rights) {
				case "statut" : 
						if(this.getClientRights(admin.getId(), "statut")==PARAM_ADMIN_USER_STATUT) {
							switch(order) {			
								case 1:
									c.setStatut(PARAM_ADMIN_USER_STATUT);
									em.merge(c);
									return true;
												
								case 2:
									c.setStatut(PARAM_SIMPLE_USER_STATUT);
									em.merge(c);
									return true;
								}
					  }else { return false;}
				case "objets" :
						int current_val=this.getClientRights(c.getId(), rights);
						switch(order) {
						case 1: 
								if(current_val<5) {
									Query query = em.createQuery(JPQL_UPDATE_LIMIT_CLIENT);
									query.setParameter(1, current_val++);
									query.setParameter(2, c.getId());
									if(query.getSingleResult()!=null && this.getClientById(c.getId()).getNb_Objetss_aucttion()==current_val+1) {
										return  true;
									}	
								}else {
									return false;
								}
						case 2 : 
								if(current_val>0) {
									Query query= em.createQuery(JPQL_UPDATE_STATUT_CLIENT);
									query.setParameter(1, current_val--);
									query.setParameter(2, c.getId());
									if(query.getSingleResult()!=null && this.getClientById(c.getId()).getNb_Objetss_aucttion()==current_val+1) {
										return  true;
									}
								}else {
									    return false;
								}
						default : 
								return false;
				}
				default :
						return false;
				
			}
			}
		return false;
	}

	@Override
	public int getClientRights(String c, String typeright) {
		int right = 0;
		Client cli=this.getClientById(c);
		if(this.existClient(c)) {
				switch(typeright) {
					case "statut":
						right= cli.getStatut();
						return right;					
					case "objets":
						right= cli.getNb_Objetss_aucttion();
						
						return right;
					default:
							break;
				}
					
		}
		return right;
		
		
	}

	@Override
	public String addObjet(Objets o) {
		Client c =this.getClientById(o.getClient().getId());
		if(o!=null) {
			if(c!=null) {
				em.persist(o);
				if(this.getObjetsById(o.getId())!=null) {
					c.addObjet(o);
					return "Ok";
				}		
			}
		}
		return "Non";
	}

	@Override
	public boolean deleteObjet(Objets o, Client c) {
		boolean result;
		Client cli = this.getClientById(c.getId());
		Objets obj = this.getObjetsById(o.getId());
		if(cli!=null && obj!=null && (this.getClientRights(cli.getId(), "statut")==PARAM_SIMPLE_USER_STATUT || obj.getClient().getId()==cli.getId())) {
				em.remove(obj);
			if(this.getObjetsById(obj.getId())==null) {
			result=true;
			}else { result=false;}
		}else {
			result=false;
		}
		
		return result;
	
	}

	@Override
	public Client getClientById(String id) {
			return  (Client)em.find(Client.class, id);
		
	}

	@Override
	public Collection<Client> getAllClients() {
		Query requete = em.createQuery( JPQL_SELECT_ALL_SAMPLE_CLIENT );
		return (Collection<Client>) requete.getResultList();
		
	}

	@Override
	public String DeleteAllClient(String admin) {
		String result="";
		Collection<Client> list= this.getAllClients();
		for(Client l:list) {
			if(this.removeClient(l.getId(), admin) && em.find(Client.class, l.getId())==null) {
				result="Ok";
			}else {
				result+=" | Non";
			}
		}
		return result;
	}

	@Override
	public Objets getObjetsById(int id) {
		return (Objets)em.find(Objets.class, id);
	}
	
	@Override
	public Collection<Objets> getAllObjets() {
		Query query = em.createQuery(JPQL_SELECT_ALL_OBJETS);
		return (Collection<Objets>) query.getResultList();
	}
	
	@Override
	public String DeleteAllObjets() {
		String result="";
		Collection<Objets> objs = this.getAllObjets();
		for(Objets obj:objs) {
			if(this.deleteObjet(obj, obj.getClient())==true) {
				result=" | Ok";
			}else {
				result+=" | Non";
			}
	}
		return result;
}

	@Override
	public Client getObjet_Client(Objets obj) {
		if(this.getObjetsById(obj.getId())!=null) {
			return obj.getClient();
		}
		return null;
	}

	@Override
	public List<Objets> getObjetsByClient(Client cc) {
		Client c=this.getClientById(cc.getId());
		if(c!=null){
			return c.getMesObjetss();
		}
		return null;
		
	}
	
	@Override
	public String DeleteAllAdmin() {
		return null;
		
	}
	
	private boolean existClient(String id) {
		return (this.getClientById(id)==null)?false:true;
		
	}


	@Override
	public void updateObjet(Objets o) {
			if(this.getObjetsById(o.getId())!=null && o!=null) 
				em.merge(o);
			
				
	}


	@Override
	public void updateClient(Client c) {
			if(this.getClientById(c.getId())!=null && c!=null)
					em.merge(c);
	}


	


	



	

	

	
	

	
	
}
