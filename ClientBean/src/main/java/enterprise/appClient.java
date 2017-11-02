
package enterprise;

import javax.naming.InitialContext;




public class appClient {

	public static void main(String args[]) {
		Client c,c2,c3,c4;
		Objets o,o1;
		AdministrationClient sb =null,sb3=null;
		AdministrationAuction sb2=null;
	        try {
			InitialContext ic = new InitialContext();

			sb = (AdministrationClient) ic.lookup("enterprise.AdministrationClient");
			sb3 = (AdministrationClient) ic.lookup("enterprise.AdministrationClient");

			sb2=(AdministrationAuction) ic.lookup("enterprise.AdministrationAuction");
			System.out.println("               LE Client                ");
			c=new Client();
			c.setMail("idouh@il.com");
			c.setId("abel");
			c.setCode_postal(243);
			c.setNb_Objetss_aucttion(2);
			c.setNom("putain cmarche a moitié");
			c.setPrenom("ssd");
			c.setStatut(2);
			
			c4=new Client();
			c4.setMail("idouh@l.com");
			c4.setId("mimi");
			c4.setCode_postal(243);
			c4.setNb_Objetss_aucttion(2);
			c4.setNom("Dupont");
			c4.setPrenom("ssd");
			c4.setStatut(2);
			
			c2=new Client();
			c2.setMail("idoh@ail.com");
			c2.setId("yassine");
			c2.setCode_postal(3243);
			c2.setNb_Objetss_aucttion(3);
			c2.setNom("idouhammou");
			c2.setPrenom("sdsd");
			c2.setStatut(2);
			
			c3=new Client();
			c3.setMail("idoh@il.com");
			c3.setId("admin");
			c3.setCode_postal(3243);
			c3.setNb_Objetss_aucttion(0);
			c3.setNom("idouammou");
			c3.setPrenom("sdsd");
			c3.setStatut(1);
			System.out.println("[Info] Creation d'un admin .."+sb.addClient(c3));
			//les objets
			o=new Objets();
			o.setNom("ordi");
			o.setDescription("mon ordi");
			o.setCategorie("tech");
			o.setClient(c2);
			o.setId(1);
			
			o1=new Objets();
			o1.setNom("ordi");
			o1.setDescription("mon ordi");
			o1.setCategorie("tech");
			o1.setClient(c2);
			o1.setId(2);
			
			System.out.println("[Info] Creation d'un user 2 .."+sb.addClient(c2));
			System.out.println("[Info] Creation d'un user 4 .."+sb.addClient(c4));

			System.out.println("[Info] Creation d'un user .."+sb.addClient(c));
			System.out.println("add objet ..............."+sb.addObjet(o));
			System.out.println("add objet ..............."+sb.addObjet(o1));

			System.out.println("Nb d'objet .................................."+sb.getAllObjets().size());
			System.out.println("Les objets .................................."+sb.getObjetsByClient(c2).size());
			System.out.println("Nombre de client ............................  "+sb.getAllClients().size());

			System.out.println("To String "+sb.getClientById("abel"));
			System.out.println("les droites de "+c.getId()+ " --- "+sb.getClientRights(c.getId(), "statut") +" ---- " +sb.getClientRights(c.getId(), "objets"));
			System.out.println("Client ...... right "+sb.getClientRights(c.getId(), "objets")+" "
									+ "une autre valeur (desicn) "+sb.updatdeClientRights(c, "statut",c3, 2)+""
											+ " "+sb.getClientRights(c.getId(), "statut"));
			
			System.out.println(" autre modif "+sb.updatdeClientRights(c, "statut", c3, 2) +" mon statut "+sb.getClientRights(c.getId(), "statut")+"pour les objet j'ai "+sb.getClientRights(c.getId(), "objets"));
			
			
			
			
			Client cli = sb.getClientById("yassine");
			Client cl = sb.getClientById("abel");
			System.out.println("le client yassine ...............;;;"+cli);
			System.out.println(" le client est "+cli.getNom() +" nombre d'objet "+sb.getObjetsByClient(cli).size());
			
			//Auction
			//
			Objets oo=sb.getObjetsById(1);
			Objets ooo=sb.getObjetsById(1);

			Auction a,a1,a2,a3,a4;
			a= new Auction();
			a.setId(1);
			a.setClient(cli);
			a.setObjets(oo);
			a.setPrix_depart(33);
			a.setDuree(44);
			
			a1=new Auction();
			a1.setId(2);
			a1.setClient(cli);
			a1.setObjets(ooo);
			a1.setPrix_depart(33);
			a1.setDuree(34);
		//	a1.setStatut(2);
			
			System.out.println("Creationde la premiere auction ........."+sb2.creatAuction(a));
			System.out.println("Creationde la deuxiéme auction ........."+sb2.creatAuction(a1));
			
			System.out.println("Start auction 1..................... "+sb2.startAuction(cli.getId(), a.getId()));
			System.out.println("Start auction 2..................... "+sb2.startAuction(cli.getId(), a1.getId()));
			
			System.out.println("demande de "+cli.getId()+" reponse "+sb2.connectToAuction(a.getId(), cli.getId()));
			System.out.println("demande de "+cl.getId()+" reponse "+sb2.connectToAuction(a.getId(), cl.getId()));
			System.out.println("demande 2 de "+cl.getId()+" reponse "+sb2.connectToAuction(a.getId(), cl.getId()));
			System.out.println("demande 3 de "+cl.getId()+" reponse "+sb2.connectToAuction(a.getId(), c4.getId()));

			System.out.println("nombre d'abonnes .................."+sb2.getAuctionById(a.getId()).getAbonnees().size());
			
			System.out.println("First bid ........ "+cl.getNom() +" a proposé un bid --> "+sb2.sendBidAution(cl.getId(), 1, true));
			
			//System.out.println("Delete all auction "+sb2.deleteAllAuctions(c3.getId()));
			
			//Client b = sb.getClientById("yassine");
			//System.out.println("mes objet depuis la bdd "+b.getMesObjetss().size());
			
			//System.out.println(" Smaple client to admin "+sb.updatdeClientRights(c2, "statut", c3, 2)+"   " +sb.getClientRights(c.getId(), "statut"));
			//System.out.println("delete insert ................. EJB ................");
			//System.out.println("Delete  objets ......................................"+sb.DeleteAllObjets());
			//System.out.println("Nb d'objet ..........................................."+sb.getAllObjets().size());
			//System.out.println("Delete users ..........................................."+sb.DeleteAllClient(c3.getId()));
			//System.out.println("Nombre de client ......................................  "+sb.getAllClients().size());
			
			System.out.println("  client de sb3"+sb3.getClientById("yassine").getNom());
			
			 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
