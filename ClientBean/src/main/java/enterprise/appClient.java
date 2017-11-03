
package enterprise;

import java.util.Collection;
import java.util.List;

import javax.naming.InitialContext;




public class appClient {

	public static void main(String args[]) {
		AdministrationClient sb =null;
		AdministrationAuction sb2=null;
		String sim="Simple";
		String adm="Admin";
	        try {
			InitialContext ic = new InitialContext();
			

			sb = (AdministrationClient) ic.lookup("enterprise.AdministrationClient");
			sb2 = (AdministrationAuction) ic.lookup("enterprise.AdministrationAuction");

			System.out.println("  \n\n\n\n ............Creation des utilisateurs ...................");
			Client cl1,cl2,cl3,cl4,cl5;
			//Admin
			cl1= new Client("abdel", "iidouhammou", "abderrahman", 95800, "idouhammou.a@gmail.com", 1);
			//Simple user
			cl2= new Client("Dup1","Arthur","Martin",75009,"arthur@gmail.com",2);
			cl3= new Client("Dup2","Dupont","Lionel",75019,"dupont@gmail.com",2);
			cl4= new Client("Dup3","Axel","Martin",78109,"axel@gmail.com",2);
			cl5= new Client("Dup4","Mimi","Bouch",51009,"mimi@gmail.com",2);
			
			System.out.println("Client "+cl1);
			System.out.println("Client "+cl2);
			System.out.println("Client "+cl3);
			System.out.println("Client "+cl4);
			System.out.println("Client "+cl5);
			
			System.out.println("\n\n\n\n .............Persistence des utilisateurs ...................");
			
			System.out.println("Add client .... "+cl1.getNom()+" ...."+sb.addClient(cl1));
			System.out.println("Add client .... "+cl2.getNom()+" ...."+sb.addClient(cl2));
			System.out.println("Add client .... "+cl3.getNom()+" ...."+sb.addClient(cl3));
			System.out.println("Add client .... "+cl4.getNom()+" ...."+sb.addClient(cl4));
			System.out.println("Add client .... "+cl5.getNom()+" ...."+sb.addClient(cl5));
			System.out.println(" \n\n Nombre de client .................."+sb.getAllClients().size());
			System.out.println("\n\n\n\n .............Creation des Objets ...................");

			Objets obj1,obj2,obj3,obj4,obj5;
			
			obj1 = new Objets(1, "ordinateur portable", "ordinateur 1", "technologie",sb.getClientById(cl2.getId()));
			obj2 = new Objets(2, "ordinateur portable", "ordinateur 2", "technologie",sb.getClientById(cl2.getId()));
			obj3 = new Objets(3, "ordinateur portable", "ordinateur 3", "technologie",sb.getClientById(cl3.getId()));
			obj4 = new Objets(4, "ordinateur portable", "ordinateur 4", "technologie",sb.getClientById(cl4.getId()));
			obj5 = new Objets(5, "ordinateur portable", "ordinateur 5", "technologie",sb.getClientById(cl4.getId()));
			
			System.out.println("Objet "+obj1);
			System.out.println("Objet "+obj2);
			System.out.println("Objet "+obj3);
			System.out.println("Objet "+obj4);
			System.out.println("Objet "+obj5);
			

			System.out.println("\n\n\n\n .............Persistence des Objets ...................");
			
			System.out.println("Add Objet .... "+obj1.getNom()+" ...."+sb.addObjet(obj1));
			System.out.println("Add Objet .... "+obj2.getNom()+" ...."+sb.addObjet(obj2));
			System.out.println("Add Objet .... "+obj3.getNom()+" ...."+sb.addObjet(obj3));
			System.out.println("Add Objet .... "+obj4.getNom()+" ...."+sb.addObjet(obj4));
			System.out.println("Add Objet .... "+obj5.getNom()+" ...."+sb.addObjet(obj5));
			System.out.println("Add Objet .... "+obj5.getNom()+" ...."+sb.addObjet(obj5));
			System.out.println(" \n\n Nombre d'objets .................."+sb.getAllObjets().size());
			
			
			System.out.println("\n\n\n\n .............Test Client_Objets ...................");
			Client test_cl=sb.getClientById(cl2.getId());
			System.out.println(" \n\n\tLes informations sur le client "+test_cl.getNom());
			System.out.println(test_cl);
			System.out.println("Les objets de "+test_cl.getNom()+"\n\n");
			for(Objets obj:test_cl.getMesObjetss()) {
				System.out.println(obj +"\n\n");
			}
			
			
			System.out.println("\n\n\n\n .............Creation des auctions ...................");
			
			Auction ac1,ac2,ac3,ac4,ac5,ac6,ac7,ac8;
			ac1=new Auction(1,sb.getClientById("Dup1"), sb.getObjetsById(1));
			ac2=new Auction(2,sb.getClientById(cl2.getId()), sb.getObjetsById(obj2.getId()));
			ac3=new Auction(3,sb.getClientById(cl3.getId()), sb.getObjetsById(obj2.getId()));
			ac4=new Auction(4,sb.getClientById(cl3.getId()), sb.getObjetsById(obj3.getId()));
			ac5=new Auction(5,sb.getClientById(cl5.getId()), sb.getObjetsById(obj5.getId()));
			ac6=new Auction(6,sb.getClientById(cl5.getId()), sb.getObjetsById(obj1.getId()));
			ac7=new Auction(7,sb.getClientById(cl4.getId()), sb.getObjetsById(obj1.getId()));
			ac8=new Auction(8,sb.getClientById(cl2.getId()), sb.getObjetsById(obj1.getId()));
			
			System.out.println("Auction "+ac1);
			System.out.println("Auction "+ac2);
			System.out.println("Auction "+ac3);
			System.out.println("Auction "+ac4);
			System.out.println("Auction "+ac5);
			System.out.println("Auction "+ac6);
			System.out.println("Auction "+ac7);
			System.out.println("Auction "+ac8);
			
			System.out.println("\n\n\n\n .............Persistence des auctions ...................");
			System.out.println("Persist auction "+ac1.getId()+"..................."+sb2.creatAuction(ac1));
			System.out.println("Persist auction "+ac2.getId()+"..................."+sb2.creatAuction(ac2));
			System.out.println("Persist auction "+ac3.getId()+"..................."+sb2.creatAuction(ac3));
			System.out.println("Persist auction "+ac4.getId()+"..................."+sb2.creatAuction(ac4));
			System.out.println("Persist auction "+ac5.getId()+"..................."+sb2.creatAuction(ac5));
			System.out.println("Persist auction "+ac6.getId()+"..................."+sb2.creatAuction(ac6));
			System.out.println("Persist auction "+ac7.getId()+"..................."+sb2.creatAuction(ac7));
			System.out.println("Persist auction "+ac8.getId()+"..................."+sb2.creatAuction(ac8));
			
			//Afichage des objets
			
			System.out.println("......................Simple Client.........................................");
			
			System.out.println("*******************************************************************");
			System.out.println("|              |                     |                |            |");
			System.out.println("|     ID       |     Nom             |  Nombre auction|    Statut  |");
			System.out.println("|              |                     |                |            |");
			System.out.println("********************************************************************");
			Collection<Client> allclients =sb.getAllClients();
			for(Client client:allclients) {
			System.out.println("|              |                     |                |            |");

				System.out.println("|"+client.getId()+"          |"+client.getNom()+"              |        "+client.getNb_Objetss_aucttion()+"       |    "+client.getStatut()+""
						+ "      |");
			System.out.println("|              |                     |                |            |");
			System.out.println("--------------------------------------------------------------------");

			}
			
			System.out.println("\n\n......................Simple Client.........................................");
			
			System.out.println("*****************************************************************************************");
			System.out.println("|              |                     |                                |                  |");
			System.out.println("|     ID       |     Nom             |  Description                   |    propriétaire  |");
			System.out.println("|              |                     |                                |                  |");
			System.out.println("******************************************************************************************");
			Collection<Objets> allobjets =sb.getAllObjets();
			for(Objets objet:allobjets) {
			System.out.println("|              |                     |                                |                  |");
			System.out.println("|    "+objet.getId()+"         |  "+objet.getNom()+"|    "+objet.getDescription()+"|"+objet.getClient().getNom()+"|");
			System.out.println("|              |                     |                                |                  |");
			System.out.println("------------------------------------------------------------------------------------------");

			}
			
			System.out.println("\n\n......................Auction avant le demarrage.........................................");
			
			System.out.println("*****************************************************************************************");
			System.out.println("|              |                     |                                |                  |");
			System.out.println("|     ID       |     Objet           |  Statut                        |    propriétaire  |");
			System.out.println("|              |                     |(1:start,0:not-start,2:close)  |                  |");
			System.out.println("******************************************************************************************");
			List<Auction> allauctions = sb2.getAllAuction();
			for(Auction auction:allauctions) {
			System.out.println("|              |                     |                                |                  |");
			System.out.println("|      "+auction.getId()+"       |     "+auction.getObjets().getNom()+"   |                   "+auction.getStatut()+"            |  "+auction.getClient().getNom()+" |");
			System.out.println("|              |                     |                                |                  |");
			System.out.println("------------------------------------------------------------------------------------------");
			}
			
			
			System.out.println("\n\n\n\n ................Lancer les auctions..........................");
			ac1 = sb2.getAuctionById(ac1.getId());ac1.setDuree(20).setPrix_depart(24).setPrix_inc(10);sb2.updateAuction(ac1);
			ac2 = sb2.getAuctionById(ac2.getId());ac2.setDuree(44).setPrix_depart(21).setPrix_inc(10);sb2.updateAuction(ac2);			
			ac4 = sb2.getAuctionById(ac4.getId());ac4.setDuree(42).setPrix_depart(23).setPrix_inc(10);sb2.updateAuction(ac4);

			System.out.println("Start auction  "+ac1.getId()+"..................."+sb2.startAuction(cl2.getId(),ac1.getId()));
			System.out.println("Start auction  "+ac2.getId()+"..................."+sb2.startAuction(cl2.getId(),ac2.getId()));
			System.out.println("Start auction  "+ac3.getId()+"..................."+sb2.startAuction(cl3.getId(),ac3.getId()));
			System.out.println("Start auction  "+ac4.getId()+"..................."+sb2.startAuction(cl3.getId(),ac4.getId()));
			System.out.println("Start auction  "+ac5.getId()+"..................."+sb2.startAuction(cl5.getId(),ac5.getId()));
			System.out.println("Start auction  "+ac6.getId()+"..................."+sb2.startAuction(cl5.getId(),ac6.getId()));
			System.out.println("Start auction  "+ac7.getId()+"..................."+sb2.startAuction(cl4.getId(),ac7.getId()));
			System.out.println("Start auction  "+ac8.getId()+"..................."+sb2.startAuction(cl2.getId(),ac8.getId()));

			System.out.println("\n\n......................Auction aprés le demarrage.........................................");

			System.out.println("***************************************************************************************************************************");
			System.out.println("|              |                     |                                |                  |         |          |           |");
			System.out.println("|     ID       |     Objet           |  Statut                        |    propriétaire  |  Bid    | Prix de  |   Durée   |");
			System.out.println("|              |                     |(1:start,0:not-start,2:close)   |                  |         | départ   |           |");
			System.out.println("***************************************************************************************************************************");
			List<Auction> allauctionss = sb2.getAllAuction();
			for(Auction auction:allauctionss) {
			System.out.println("|              |                     |                                |                  |         |          |           |");
			System.out.println("|      "+auction.getId()+"       |     "+auction.getObjets().getNom()+"   |                   "+auction.getStatut()+"            |  "+auction.getClient().getNom()+"|   "+auction.getPrix_inc()+"   |  "+auction.getPrix_depart()+"    |    "+auction.getDuree()+"   |");
			System.out.println("|              |                     |                                |                  |         |          |           |");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.println("\n\n\n ...........Inscription aux auctions.......................................");
			System.out.println("Le client "+cl1.getNom()+" a fait un bid sur l'auction "+ac1.getId()+" ==> "+sb2.sendBidAution(cl1.getId(), ac1.getId(), true));
			
			System.out.println("Le client "+cl2.getNom()+" a fait un bid sur l'auction "+ac1.getId()+" ==> "+sb2.sendBidAution(cl2.getId(), ac1.getId(), true));
			System.out.println("\n\n......................Auction aprés deux bids.........................................");

			System.out.println("***************************************************************************************************************************");
			System.out.println("|              |                     |                                |                  |         |          |           |");
			System.out.println("|     ID       |     Objet           |  Statut                        |    propriétaire  |  Bid    | Prix     |   Durée   |");
			System.out.println("|              |                     |(1:start,0:not-start,2:close)   |                  |         | final    |           |");
			System.out.println("***************************************************************************************************************************");
			List<Auction> bis = sb2.getAllAuction();
			for(Auction auction:bis) {
			System.out.println("|              |                     |                                |                  |         |          |           |");
			System.out.println("|      "+auction.getId()+"       |     "+auction.getObjets().getNom()+"   |                   "+auction.getStatut()+"            |  "+auction.getClient().getNom()+"|   "+auction.getPrix_inc()+"   |  "+auction.getPrix_final()+"    |    "+auction.getDuree()+"   |");
			System.out.println("|              |                     |                                |                  |         |          |           |");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
			}
			System.out.println("Le client "+cl1.getNom()+" a fait un bid sur l'auction "+ac1.getId()+" ==> "+sb2.sendBidAution(cl1.getId(), ac1.getId(), true));
			System.out.println("Le client "+cl5.getNom()+" a fait un bid sur l'auction "+ac1.getId()+" ==> "+sb2.sendBidAution(cl5.getId(), ac1.getId(), true));
			System.out.println("Le client "+cl1.getNom()+" a fait un bid sur l'auction "+ac2.getId()+" ==> "+sb2.sendBidAution(cl1.getId(), ac2.getId(), true));
			System.out.println("Le client "+cl4.getNom()+" a fait un bid sur l'auction "+ac3.getId()+" ==> "+sb2.sendBidAution(cl1.getId(), ac3.getId(), true));
			//en utilisant les threads on peut simuler le temps de l'auction
			System.out.println("Le client "+cl1.getNom()+" a fait un bid sur l'auction "+ac1.getId()+" ==> "+sb2.sendBidAution(cl1.getId(), ac1.getId(), false));
			System.out.println("Le client "+cl3.getNom()+" a fait un bid sur l'auction "+ac2.getId()+" ==> "+sb2.sendBidAution(cl3.getId(), ac2.getId(), false));
			System.out.println("\n\n......................A la fin d'une auction.........................................");

			System.out.println("***************************************************************************************************************************");
			System.out.println("|              |                     |                                |                  |         |          |           |");
			System.out.println("|     ID       |     Objet           |  Statut                        |    propriétaire  |  Bid    |Prix final|   Durée   |");
			System.out.println("|              |                     |(1:start,0:not-start,2:close)   |                  |         |          |           |");
			System.out.println("***************************************************************************************************************************");
			List<Auction> end = sb2.getAllAuction();
			for(Auction auction:end) {
			System.out.println("|              |                     |                                |                  |         |          |           |");
			System.out.println("|      "+auction.getId()+"       |     "+auction.getObjets().getNom()+"   |                   "+auction.getStatut()+"            |  "+auction.getClient().getNom()+"           |   "+auction.getPrix_inc()+"   |  "+auction.getPrix_final()+"    |    "+auction.getDuree()+"   |");
			System.out.println("|              |                     |                                |                  |         |          |           |");
			System.out.println("---------------------------------------------------------------------------------------------------------------------------");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
