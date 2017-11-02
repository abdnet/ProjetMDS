package enterprise;

import java.util.List;

import javax.ejb.Remote;
@Remote
public interface AdministrationAuction {
	
	
	public String creatAuction(Auction a);
	public String startAuction(String c,int a);
	public List<Auction> getAllAuction(int statut);
	public List<Auction> getAllAuction();

	
	
	public String sendBidAution(String c,int a,boolean z);
	public boolean closeAuction(int a);
	
	public boolean deleteAuction(String c, int a);
	public boolean deleteAllAuctions(String admin);
	
	public Auction updateAuction(int a,int st);
	public Auction getAuctionById(int id);
	public List<Auction> getAuctionByClientId(String pseudo);
	
	public String connectToAuction(int auction,String client);
	

}
