package edu.woodson.lab;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

import java.util.List;

public class TrelloTest {

    // Using trello account
    // Email: twitterinterfacewithtrello@gmail.com
    // Username: @twitterinterfacewithuser
    // Password: wtwoodsontwitterlab2019twitterinterfacewithtrello


    private static final String API_KEY = "6a888dffccb4c413711d7d617057fa07";
    private static final String TOKEN = "186d9044cd5dfdb60c3b5ab3befb2aaeb2daddccdd05244ce152743701fec680";

    public static void main(String[] args) {
        Trello trello = new TrelloImpl(API_KEY, TOKEN, new ApacheHttpClient());
        Board board = trello.getBoard("dqJhYMUB");
        List<TList> lists = board.fetchLists();
        for (TList tl:lists) {
            System.out.println(tl.getName());
        }
    }
}
