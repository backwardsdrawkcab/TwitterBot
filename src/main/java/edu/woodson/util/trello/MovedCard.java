package edu.woodson.util.trello;

import com.julienvey.trello.domain.TList;

public class MovedCard {

    private String name;
    private TList from;
    private TList to;

    public MovedCard(String name, TList from, TList to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public TList getFrom() {
        return from;
    }

    public TList getTo() {
        return to;
    }
}
