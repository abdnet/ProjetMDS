package enterprise;

import java.util.List;

import javax.ejb.Remote;
@Remote
public interface AdministrationAuction {
	
	/**
	 * Methode permettant de créer une enchere : 
	 * Elle prend en parametre une enchere a, recupere l'id de l'enchere puis du 
	 * client souhaintant créer cette enchère et verifie si l'utilisateur a le droit 
	 * de créer l'enchère ou pas. La methode renvoie OK si c'est bon et un 
	 * message d'erreur dans le cas contraire. 
	 * @param a
	 * @return
	 */
	public String creatAuction(Auction a);
	/**
	 * Cette methode permet de commencer une enchere et prend en parametre un entier a, et 
	 * un string. 
	 * @param c
	 * @param a
	 * @return
	 */
	public String startAuction(String c,int a);
	
	/**
	 * On obtient à partir de cette methode une collection representant une 
	 * liste de toutes les enchères en fonction de leurs statut.
	 * Elle prend en parametre un statut
	 * @param statut
	 * @return
	 */
	public List<Auction> getAllAuction(int statut);
	/**
	 * Cette methode nous renvoie juste la liste de toutes les enchères 
	 * et ceci peu importe leur statut
	 * @return
	 */
	public List<Auction> getAllAuction();

	
	/**
	 * Methode prenant en parametre une chaine de caracteres, un entier et un booleen
	 *, celle ci nous permet d'envoyer une offre selon un scenario donné:
	 *On recupere l'id du client, l'id de l'enchere
	 *puis on verifie si le clien est déja abonnné ou pas à l'enchere.
	 *Si oui, on envoie un message comme quoi il est deja abonné, sinon 
	 *on passe à la verification du statut et on demarre l'enchère tout en verifiant que 
	 *celle au meme id n'existait pas déja.
	 * @param c
	 * @param a
	 * @param z
	 * @return
	 */
	public String sendBidAution(String c,int a,boolean z);
	/**
	 * Celle ci permetb de fermer une enchere tout en prenant le soin de vérifier que les droits 
	 * utilisateurs on été bien mis à jour
	 * @param a
	 * @return
	 */
	public boolean closeAuction(int a);
	/**
	 * permet de supprimer une enchere, et ce de la souce de données 
	 * @param c
	 * @param a
	 * @return
	 */
	
	public boolean deleteAuction(String c, int a);
	/**
	 * Fait une suppression totale des enchere de la base de données tout en veillant bien à ce que ce 
	 * soit juste l'utilisateur admin qui puisse faire cette opération.
	 * @param admin
	 * @return
	 */
	public boolean deleteAllAuctions(String admin);

	/**
	 * Cette methode consiste au fait  de mettre à jour une enchere, c'est à dire 
	 *par son id et son statut. On a en retour une enchere à jour
	 * @param a
	 * @param st
	 * @return
	 */
	public Auction updateAuction(int a,int st);
	/**
	 * Permet de recuperer une enchere à partir de son id persistant dans la base de 
	 * données.
	 * @param id
	 * @return
	 */
	public Auction getAuctionById(int id);
	/**
	 * 
	 * Cette methode permet de récuperer toutes les encheres faites par un client donné(id).
	 * Elle renvoie une liste d'encheres et prend en parametre le pseudo du client(vendeur ou acheteur)
	 * @param pseudo
	 * @return
	 */
	public List<Auction> getAuctionByClientId(String pseudo);
	
	public Auction updateAuction(Auction a);

	

}
