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
	/**
	 * Ajoute un Client dans la base de donées
	 * elle prend en parametre le client à ajouter et renvoie une chaine de caractere 
	 * spécifiant len fait que le client ai été ajouté ou non
	 * @param c
	 * @return
	 */
	public String addClient( Client c);
	
	//mettre à jour un les droits d'un client
	/**
	*Permet de mettre à jour les droits du client.
	* prend en parametre le client, ses nouveaux droits, l'aministrateur, et un entier
	* Nous renvoie un booleen disant si le client a été ajouté ou pas.
	 * 
	 * @param c
	 * @param rights
	 * @param admin
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	public boolean updatdeClientRights(Client c,String rights,Client admin,int order) throws SQLException;
	//recuperer les droits d'un client
	/**
	 * Permet elle de récuperer les droits clients. 
	 * prend en parametre un client et le type de ses droit
	 * et nous renvoie ses droits
	 * @param c
	 * @param typeright
	 * @return
	 */
	public int getClientRights(String c,String typeright);
	
	//ajouter un objet 
	/**
	 * Permet d'ajouter un objet dans la base de données.
	 * renvoie ok si l'objet a été bien ajouté sinon Non
	 * @param o
	 * @return
	 */
	public String addObjet(Objets o);
	//supprimer un objet
	/**
	* Supprime un objet de la base de données persistante
	 * Prend en parametre l'objet et le client qui souhaite supprimer l'objet
	 * @param o
	 * @param c
	 * @return
	 */
	public boolean deleteObjet(Objets o,Client c);
	/**
	 * Recherche un client à partir de son identifiant.
	 * Renvoie un client et prend en parametre un id
	 * @param id
	 * @return
	 */
	public Client getClientById(String id);
	/**
	 * Recupere tous les clients dans la base de données à partir d'une requete.
	 * Nous renvoie une collection de client.
	 * @return
	 */
	public Collection<Client> getAllClients();
	/**
	 * Supprime tous les clients de la base de données. Seul l'admin peu effectuer cette operation
	 * prend en parametre l'admin puis nous renvoie une chaine de caractere 
	 * disant si le client a été supprimé ou pas 
	 * @param id
	 * @return
	 */
	public String DeleteAllClient(String admin);
	/**
	 * Permet de recuperer un objet issu de la base de données persistante à partir de son id.
	 *Cette methode nous renvoie, un objet et prend en parametre un id objet 
	 * @return
	 */
	public Objets getObjetsById(int id);
	/**
	 * 	 *Permet de recuperer tous les  objets issus de la base de données persistante.
	 *Cette methode nous renvoie, un une collection d'objet
	 * @return
	 */
	public Collection<Objets> getAllObjets();	
	/**
	 *  Supprime tous les objets de la base de données. 
	 * Renvoie Ok si fait sinon Non
	 * @return
	 */
	public String DeleteAllObjets();
	/**
	 * 
	 * @param cible
	 * @param admin
	 * @return
	 */
	public boolean removeClient(String cible,String admin);
	/**
	 * Cette methode permet elle de supprimer un client cible, c'est à dire un client donné.
	 * Elle prend en parametre une chaine de caractere et le pseudo de l'admin
	 * Elle verifie au prealable si le client existe bel et bien et l'admin aussi avant d'executer la 
	 * requete de suppression.
	 * Renvoie vrai si le clien a ete supprimé sinon faux
	 * @param obj
	 * @return
	 */
	public Client getObjet_Client(Objets obj);
	/**
	 * Nous permet de recuperer tous les ojets liés à un client donné.
	 * prend en parametre un client et retourne la liste de ses objets 
	 * @param c
	 * @return
	 */
	public List<Objets> getObjetsByClient(Client c);
	/**
	 * 	 * Supprimme tous les administrateurs
	 * @return
	 */

	public String DeleteAllAdmin();
	/**
	 * 
	 * @param o
	 */
	public void updateObjet(Objets o);
	/**
	 * Permet de mettre à jour un client donné.
	 * Prend en parametre le client dont on veut faire la mise à jour.
	 * @param c
	 */
	public void updateClient(Client c);
	
	
	 
	
	
}
