package parameters;

public interface Constants {
	 

    public static final String JPQL_SELECT_CLIENT_PAR_PSEUDO = "SELECT u FROM Client u WHERE u.pseudo=?1";
    public static final String JPQL_SELECT_OBJET_PAR_2Para = "SELECT u FROM Objet u WHERE u.description=?1 and u.nom=?2";
    public static final String JPQL_SELECT_ALL_SAMPLE_CLIENT="SELECT u FROM Client u WHERE u.statut=2";
    public static final String JPQL_SELECT_ALL_OBJETS="SELECT obj FROM Objets obj";
    public static final String JPQL_UPDATE_STATUT_CLIENT= "UPDATE Client c SET c.statut=?1 WHERE c.pseudo=?1";
    public static final String JPQL_UPDATE_LIMIT_CLIENT= "UPDATE Client c SET c.nb_Objetss_aucttion=?1 WHERE c.pseudo=?2";

    public static final int PARAM_ADMIN_USER_STATUT=1;
    public static final int PARAM_SIMPLE_USER_STATUT=2;

    public static final String JPQL_START_CLOSE_AUCTION = "UPDATE Auction a SET a.statut=?1 where a.id=?2";
    public static final String JPQL_SELECT_ALL_AUCTION_BY_STATUT="SELECT c FROM Auction WHERE c.satatut=?1";
    public static final String JPQL_SELECT_ALL_AUCTION="SELECT c FROM Auction c";

    public static final String JPQL_SELECT_ALL_AUCTION_BY_CLIENT_ID="SELECT a FROM Auction a WHERE a.client=?1";
    public static final String JPQL_COUNT_AUCTION_CREATED_BY_CLIENT="select count(a) from Auction a where a.client=?1 and a.statut=?2";
    public static final String JPQL_ADD_OBJET_TO_AUCTION="select count(aa) from Auction aa where aa.client=?1 and aa.Objets=?2";
    public static final int PARAM_AUCTION_STATUT_STARTED=1;
    public static final int PARAM_AUCTION_STATUT_NOT_STARTED=0;
    public static final int PARAM_AUCTION_STATUT_CLOSED=2;
    public static final int PARAM_LIMIT_AUCTION=5;
    


}
