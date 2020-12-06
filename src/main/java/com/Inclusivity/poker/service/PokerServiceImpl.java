package com.Inclusivity.poker.service;

import com.Inclusivity.poker.Card;
import com.Inclusivity.poker.HandRanking;
import com.Inclusivity.poker.Rank;
import com.Inclusivity.poker.Suite;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PokerServiceImpl implements PokerService{

    public HandRanking findFlush(Map<Suite, Long> suitFrequencies) {
        Optional<Long> optionalVal = suitFrequencies.values().stream().max(Comparator.comparing(Long::intValue));
        long suiteCount = 0;
        if (optionalVal.isPresent()){
            suiteCount = optionalVal.get();
        }
        if (suiteCount == 5) {
            return HandRanking.FLUSH;
        }
        return HandRanking.NONE;
    }

    public HandRanking findPairs(Map<Rank, Long> rankFrequencies) {
        long pair = rankFrequencies.values().stream().filter(value -> value == 2).count();
        switch ((int) pair) {
            case 1:
                return HandRanking.ONE_PAIR;
            case 2:
                return HandRanking.TWO_PAIR;
            case 3:
                return HandRanking.THREE_OF_A_KIND;
        }
        return HandRanking.NONE;
    }

    public HandRanking findOfKinds(Map<Rank, Long> rankFrequencies) {
        long kindRanking = rankFrequencies.values().stream().max(Comparator.comparing(Long::intValue)).get();
        long minKindRanking = rankFrequencies.values().stream().min(Comparator.comparing(Long::intValue)).get();
        switch ((int) kindRanking) {
            case 3:
                if (minKindRanking > 1) {
                    return HandRanking.FULL_HOUSE;
                } else {
                    return HandRanking.THREE_OF_A_KIND;
                }
            case 4:
                return HandRanking.FOUR_OF_A_KIND;
            case 5:
                return HandRanking.FIVE_OF_A_KIND;
        }
        return HandRanking.NONE;
    }

    public void renderDeck(String[] hands, List<Rank> ranks, List<Suite> suites, List<Card> cards) {
        for (String hand : hands) {
            if (hand.trim().length() == 1) {
                Optional<Rank> optionalRank = ranks.stream().filter(rank -> rank.getSymbol().equalsIgnoreCase(hand.trim())).findFirst();
                Optional<Suite> optionalSuite = suites.stream().filter(suite -> suite.getSymbol().equalsIgnoreCase("HC")).findFirst();
                if (optionalRank.isPresent() && optionalSuite.isPresent()) {
                    cards.add(new Card(optionalSuite.get(), optionalRank.get()));
                }
            } else if (hand.trim().length() == 2) {
                String[] splitHands = hand.trim().split("");
                extractIntoCard(suites, ranks, cards, splitHands[1], splitHands);
            } else if (hand.trim().length() == 3) {
                String lastCharacter = String.valueOf(hand.charAt(hand.length() - 1));
                String[] splitHands = hand.trim().split(lastCharacter);

                extractIntoCard(suites, ranks, cards, lastCharacter, splitHands);
            } else {
                System.out.println("Incorrect hand input,Please enter correct hand");
            }

        }
    }

    private static void extractIntoCard(List<Suite> suites, List<Rank> ranks, List<Card> cards, String lastCharacter, String[] splitHands) {
        Optional<Rank> optionalRank = ranks.stream().filter(rank -> rank.getSymbol().equalsIgnoreCase(splitHands[0].trim())).findFirst();
        Optional<Suite> optionalSuite = suites.stream().filter(suite -> suite.getSymbol().equalsIgnoreCase(lastCharacter.trim())).findFirst();
        if (optionalRank.isPresent() && optionalSuite.isPresent()) {
            cards.add(new Card(optionalSuite.get(), optionalRank.get()));
        }
    }

    public Map<Suite, Long> groupBySuite(List<Card> cards) {
        return cards.stream().collect(
                Collectors.groupingBy(Card::getSuite, Collectors.counting()));
    }

    public Map<Rank, Long> groupByRank(List<Card> cards) {
        return cards.stream().collect(
                Collectors.groupingBy(Card::getRank, Collectors.counting()));
    }
}
