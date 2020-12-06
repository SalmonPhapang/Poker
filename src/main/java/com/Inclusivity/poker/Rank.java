package com.Inclusivity.poker;

public class Rank {
    private String name;
    private String symbol;
    private int priority;

    public Rank(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        this.priority = 0;
    }
    public Rank(String name, String symbol,int priority) {
        this.name = name;
        this.symbol = symbol;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rank)) return false;

        Rank rank = (Rank) o;

        return symbol != null ? symbol.equals(rank.symbol) : rank.symbol == null;
    }

    @Override
    public int hashCode() {
        return symbol != null ? symbol.hashCode() : 0;
    }
}
