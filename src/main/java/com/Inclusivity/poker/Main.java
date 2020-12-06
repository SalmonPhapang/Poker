package com.Inclusivity.poker;

import com.Inclusivity.poker.generators.RankGenerator;
import com.Inclusivity.poker.generators.SuitesGenerator;
import com.Inclusivity.poker.service.PokerService;
import com.Inclusivity.poker.service.PokerServiceImpl;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    private static PokerService pokerService = new PokerServiceImpl();

    public static void main(String[] args) {
        List<Suite> suites = SuitesGenerator.generateSuite();
        List<Rank> ranks = RankGenerator.generateRanks();
        List<Card> cards = new ArrayList<>();
        HandRanking handRanking;
        Scanner in = new Scanner(System.in);
        String handInput = in.nextLine();
        String[] hands = handInput.trim().split(",");

        pokerService.renderDeck(hands, ranks, suites, cards);

        // find frequencies of each rank to determine pairs
        Map<Rank, Long> rankFrequencies = pokerService.groupByRank(cards);
        // find frequencies of each suite to determine pairs
        Map<Suite, Long> suitFrequencies = pokerService.groupBySuite(cards);

        handRanking = pokerService.findFlush(suitFrequencies);

        if (handRanking.equals(HandRanking.NONE))
            handRanking = pokerService.findOfKinds(rankFrequencies);

        if (handRanking.equals(HandRanking.NONE))
            handRanking = pokerService.findPairs(rankFrequencies);

        System.out.println(handRanking);
    }

}
