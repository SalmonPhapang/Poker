package com.Inclusivity.poker.generators;

import com.Inclusivity.poker.Rank;

import java.util.ArrayList;
import java.util.List;

public class RankGenerator {

    public static List<Rank>  generateRanks(){
        List<Rank> ranks = new ArrayList<>();
       ranks.add(new Rank("Ace","A"));
        ranks.add(new Rank("Two","2"));
        ranks.add(new Rank("Three","3"));
        ranks.add(new Rank("Four","4"));
        ranks.add(new Rank("Five","5"));
        ranks.add(new Rank("Six","6"));
        ranks.add(new Rank("Seven","7"));
        ranks.add(new Rank("Eight","8"));
        ranks.add(new Rank("Nine","9"));
        ranks.add(new Rank("Ten","10"));
        ranks.add(new Rank("Jack","J"));
        ranks.add(new Rank("Queen","Q"));
        ranks.add(new Rank("King","K"));

        return  ranks;

    }
}
