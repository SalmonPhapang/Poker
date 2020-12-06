package com.Inclusivity.poker.generators;

import com.Inclusivity.poker.Rank;

import java.util.ArrayList;
import java.util.List;

public class RankGenerator {

    public static List<Rank>  generateRanks(){
        List<Rank> ranks = new ArrayList<>();
       ranks.add(new Rank("Ace","A",0));
        ranks.add(new Rank("Two","2",0));
        ranks.add(new Rank("Three","3",0));
        ranks.add(new Rank("Four","4",0));
        ranks.add(new Rank("Five","5",0));
        ranks.add(new Rank("Six","6",0));
        ranks.add(new Rank("Seven","7",0));
        ranks.add(new Rank("Eight","8",0));
        ranks.add(new Rank("Nine","9",0));
        ranks.add(new Rank("Ten","10",0));
        ranks.add(new Rank("Jack","J",1));
        ranks.add(new Rank("King","K",2));
        ranks.add(new Rank("Queen","Q",3));

        return  ranks;

    }
}
