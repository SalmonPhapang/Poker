package com.Inclusivity.poker;

public class Card  implements Comparable{
    private Suite suite;
    private Rank rank;

    public Card(Suite suite, Rank rank) {
        this.suite = suite;
        this.rank = rank;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public int compareTo(Object o) {
        return ((Card)o).rank.getSymbol().compareToIgnoreCase(this.rank.getSymbol());
    }

}
