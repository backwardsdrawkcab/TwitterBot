package edu.woodson.core;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Argument;
import com.julienvey.trello.domain.Board;

import java.util.Optional;
import java.util.function.Supplier;

public class TrelloStateBuilder implements Supplier<TrelloState> {
    private final Trello trello;
    private String boardID;

    public TrelloStateBuilder(Trello trello) {
        this.trello = trello;
    }

    @Override
    public TrelloState get() {
        Board board = trello.getBoard(getBoardID().orElseThrow());
        board.getLists().add();
        return new TrelloState();
    }

    public void setBoardID(String boardID) {
        this.boardID = boardID;
    }

    public Optional<String> getBoardID(){
        return Optional.ofNullable(boardID);
    }
}
