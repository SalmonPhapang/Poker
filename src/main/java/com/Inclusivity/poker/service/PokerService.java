package com.Inclusivity.poker.service;

import com.Inclusivity.poker.Card;
import com.Inclusivity.poker.HandRanking;
import com.Inclusivity.poker.Rank;
import com.Inclusivity.poker.Suite;

import java.util.List;
import java.util.Map;

public interface PokerService {
    HandRanking findFlush(Map<Suite, Long> suitFrequencies);

    HandRanking findPairs(Map<Rank, Long> rankFrequencies);

    HandRanking findOfKinds(Map<Rank, Long> rankFrequencies);

    void renderDeck(String[] hands, List<Rank> ranks, List<Suite> suites, List<Card> cards);

    Map<Suite, Long> groupBySuite(List<Card> cards);

    Map<Rank, Long> groupByRank(List<Card> cards);
}
