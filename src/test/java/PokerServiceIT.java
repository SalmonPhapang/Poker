import com.Inclusivity.poker.Card;
import com.Inclusivity.poker.HandRanking;
import com.Inclusivity.poker.Rank;
import com.Inclusivity.poker.Suite;
import com.Inclusivity.poker.generators.RankGenerator;
import com.Inclusivity.poker.generators.SuitesGenerator;
import com.Inclusivity.poker.service.PokerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PokerServiceIT {

    private PokerService pokerService;
    private List<Suite> suites;
    private List<Rank> ranks;
    private List<Card> cards;
    private Map<Suite, Long> suitFrequencies;
    private Map<Rank, Long> rankFrequencies;
    @Before
    public void init(){
        pokerService = new PokerService();
        suites = SuitesGenerator.generateSuite();
        ranks = RankGenerator.generateRanks();
        cards = new ArrayList<>();

    }
    private void initialize(String hand){
        String[] cardHands = hand.trim().split(",");
        pokerService.renderDeck(cardHands,ranks,suites,cards);
        suitFrequencies = pokerService.groupBySuite(cards);
        rankFrequencies =  pokerService.groupByRank(cards);
    }
    @Test
    public void findFlushIT(){
        initialize("AS, 10S, 5S, 2S, 3S");
        HandRanking handRanking = pokerService.findFlush(suitFrequencies);
        Assert.assertEquals(handRanking,HandRanking.FLUSH);
    }
    @Test
    public void findTwoPairsIT(){
        initialize("AS, 10C, 10H, 3D, 3S");
        HandRanking handRanking = pokerService.findPairs(rankFrequencies);
        Assert.assertEquals(handRanking,HandRanking.TWO_PAIR);
    }
    @Test
    public void findOnePairsIT(){
        initialize("AS, 10C, 10H, 2D, 3S");
        HandRanking handRanking = pokerService.findPairs(rankFrequencies);
        Assert.assertEquals(handRanking,HandRanking.ONE_PAIR);
    }
    @Test
    public void findThreeOfKindsIT(){
        initialize("10S, 10C, 10H, 4D, 3S");
        HandRanking handRanking = pokerService.findOfKinds(rankFrequencies);
        Assert.assertEquals(handRanking,HandRanking.THREE_OF_A_KIND);
    }
    @Test
    public void findFourOfKindsIT(){
        initialize("6S, 6C, 6H, 6D, 3S");
        HandRanking handRanking = pokerService.findOfKinds(rankFrequencies);
        Assert.assertEquals(handRanking,HandRanking.FOUR_OF_A_KIND);
    }
    @Test
    public void findFiveOfKindsIT(){
        initialize("3S, 3C, 3H, 3D, 3S");
        HandRanking handRanking = pokerService.findOfKinds(rankFrequencies);
        Assert.assertEquals(handRanking,HandRanking.FIVE_OF_A_KIND);
    }
    @Test
    public void findFullHouseIT(){
        initialize("10S, 10C, 10H, 3D, 3S");
        HandRanking handRanking = pokerService.findOfKinds(rankFrequencies);
        Assert.assertEquals(handRanking,HandRanking.FULL_HOUSE);
    }
    @Test
    public void findNoneIT(){
        initialize("AS, 4C, 5H, 1D, 3S");
        HandRanking handRanking = pokerService.findOfKinds(rankFrequencies);
        Assert.assertEquals(handRanking,HandRanking.NONE);
    }
}
