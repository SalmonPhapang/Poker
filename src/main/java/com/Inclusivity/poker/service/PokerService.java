package com.Inclusivity.poker.service;

import com.Inclusivity.poker.HandRanking;
import com.Inclusivity.poker.Rank;
import com.Inclusivity.poker.Suite;

import java.util.Comparator;
import java.util.Map;

public class PokerService {

    public HandRanking findFlush(Map<Suite, Long> suitFrequencies){
        long suiteCount = suitFrequencies.values().stream().max(Comparator.comparing(Long::intValue)).get();
        if (suiteCount == 5){
            return HandRanking.FLUSH;
        }
        return HandRanking.NONE;
    }
    public HandRanking findPairs(Map<Rank, Long> rankFrequencies){
        long pair  = rankFrequencies.values().stream().filter(value -> value == 2).count();
        switch ((int) pair){
            case 1:
                return HandRanking.ONE_PAIR;
            case 2:
                return HandRanking.TWO_PAIR;
            case 3:
                return HandRanking.THREE_OF_A_KIND;
        }
        return HandRanking.NONE;
    }
    public HandRanking findOfKinds(Map<Rank, Long> rankFrequencies){
        long kindRanking = rankFrequencies.values().stream().max(Comparator.comparing(Long::intValue)).get();
        long minKindRanking = rankFrequencies.values().stream().min(Comparator.comparing(Long::intValue)).get();
        switch ((int) kindRanking){
            case 3:
                if (minKindRanking > 1){
                    return HandRanking.FULL_HOUSE;
                }else {
                    return HandRanking.THREE_OF_A_KIND;
                }
            case 4:
                return HandRanking.FOUR_OF_A_KIND;
            case 5:
                return HandRanking.FIVE_OF_A_KIND;
        }
        return HandRanking.NONE;
    }
}
