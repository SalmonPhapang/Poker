package com.Inclusivity.poker;

import com.Inclusivity.poker.generators.RankGenerator;
import com.Inclusivity.poker.generators.SuitesGenerator;
import com.Inclusivity.poker.service.PokerService;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    private static PokerService pokerService = new PokerService();

    public static void main(String[] args) {
        List<Suite> suites = SuitesGenerator.generateSuite();
        List<Rank> ranks = RankGenerator.generateRanks();
        List<Card> cards = new ArrayList<>();
        HandRanking handRanking;
        Scanner in = new Scanner(System.in);
        String handInput = in.nextLine();
       String[] hands = handInput.trim().split(",");
        for (String hand : hands) {
            if (hand.trim().length() == 1){
                Optional<Rank> optionalRank = ranks.stream().filter(rank -> rank.getSymbol().equalsIgnoreCase(hand.trim())).findFirst();
                Optional<Suite> optionalSuite = suites.stream().filter(suite -> suite.getSymbol().equalsIgnoreCase("HC")).findFirst();
                if (optionalRank.isPresent() && optionalSuite.isPresent()) {
                    cards.add(new Card(optionalSuite.get(), optionalRank.get()));
                }
            }
            else if (hand.trim().length() == 2){
                String[] splitHands = hand.trim().split("");
                extractIntoCard(suites, ranks, cards, splitHands[1], splitHands);
            }else if (hand.trim().length() == 3){
               String lastCharacter = String.valueOf(hand.charAt(hand.length()-1));
                String[] splitHands = hand.trim().split(lastCharacter);

                extractIntoCard(suites, ranks, cards, lastCharacter, splitHands);
            }else {
                System.out.println("Incorrect hand input,Please enter correct hand");
            }

        }

        Collections.sort(cards);
        // find frequencies of each rank to determine pairs
        Map<Rank, Long> rankFrequencies = cards.stream().collect(
                Collectors.groupingBy(Card::getRank, Collectors.counting()));

        // find frequencies of each suite to determine pairs
        Map<Suite, Long> suitFrequencies = cards.stream().collect(
                Collectors.groupingBy(Card::getSuite, Collectors.counting()));

            handRanking = pokerService.findFlush(suitFrequencies);

        if (handRanking.equals(HandRanking.NONE))
            handRanking = pokerService.findOfKinds(rankFrequencies);

        if (handRanking.equals(HandRanking.NONE))
            handRanking = pokerService.findPairs(rankFrequencies);

        System.out.println(handRanking);
    }


    private static void extractIntoCard(List<Suite> suites, List<Rank> ranks, List<Card> cards, String lastCharacter, String[] splitHands) {
        Optional<Rank> optionalRank = ranks.stream().filter(rank -> rank.getSymbol().equalsIgnoreCase(splitHands[0].trim())).findFirst();
        Optional<Suite> optionalSuite = suites.stream().filter(suite -> suite.getSymbol().equalsIgnoreCase(lastCharacter.trim())).findFirst();
        if (optionalRank.isPresent() && optionalSuite.isPresent()) {
            cards.add(new Card(optionalSuite.get(), optionalRank.get()));
        }
    }
}
