package enterprise;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.Remote;

/**
 * The API of the entity bean.
 */
@Remote
public interface AdministrationClient  {
	
	//Ajouter un Client
	public String addClient( Client c);
	//supprimer un Client
	//mettre à jour un les droits d'un client
	public boolean updatdeClientRights(Client c,String rights,Client admin,int order) throws SQLException;
	//recuperer les droits d'un client
	public int getClientRights(String c,String typeright);
	
	//ajouter un objet 
	public String addObjet(Objets o);
	//supprimer un objet
	public boolean deleteObjet(Objets o,Client c);
	public Client getClientById(String id);
	public Collection<Client> getAllClients();
	public String DeleteAllClient(String admin);
	public Objets getObjetsById(int id);
	public Collection<Objets> getAllObjets();	
	public String DeleteAllObjets();
	public boolean removeClient(String cible,String admin);
	public Client getObjet_Client(Objets obj);
	public List<Objets> getObjetsByClient(Client c);
	public String DeleteAllAdmin();
	public void updateObjet(Objets o);
	public void updateClient(Client c);
	
	
	 
	
	
}
