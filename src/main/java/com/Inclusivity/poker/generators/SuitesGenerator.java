package com.Inclusivity.poker.generators;

import com.Inclusivity.poker.Rank;
import com.Inclusivity.poker.Suite;

import java.util.ArrayList;
import java.util.List;

public class SuitesGenerator {

    public static List<Suite> generateSuite(){
        List<Suite> suites = new ArrayList<>();
        suites.add(new Suite("Spades","S"));
        suites.add(new Suite("Diamonds","D"));
        suites.add(new Suite("Hearts","H"));
        suites.add(new Suite("Clubs","C"));
        suites.add(new Suite("High Card","HC"));
        return  suites;
    }
}
