package edu.woodson;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import edu.woodson.util.trello.TrelloForTwitter;
import edu.woodson.util.trello.util.MovedCard;
import edu.woodson.util.trello.util.TrelloList;

import java.util.List;
import java.util.Scanner;

public class NewTrelloTest {

    // Using trello account
    // Email: twitterinterfacewithtrello@gmail.com
    // Username: @twitterinterfacewithuser
    // Password: wtwoodsontwitterlab2019twitterinterfacewithtrello


    private static final String API_KEY = "6a888dffccb4c413711d7d617057fa07";
    private static final String TOKEN = "186d9044cd5dfdb60c3b5ab3befb2aaeb2daddccdd05244ce152743701fec680";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Trello trello = new TrelloImpl(API_KEY, TOKEN, new ApacheHttpClient());
        String boardId = "1XDTOfqt";
        Board board = trello.getBoard(boardId);
        List<TList> oldLists = board.fetchLists();

        TrelloList oldTrelloList = TrelloForTwitter.getTrelloList(trello, oldLists);

        sc.next();
        Board newBoard = trello.getBoard(boardId);
        List<TList> newLists = newBoard.fetchLists();

        TrelloList newTrelloList = TrelloForTwitter.getTrelloList(trello, newLists);

        MovedCard moved = TrelloForTwitter.findMovedCard(board, oldTrelloList, newTrelloList);

        assert moved != null;
        System.out.println("You moved " + moved.getName() + " from " + moved.getFrom().getName() + " to " + moved.getTo().getName());

    }

}
