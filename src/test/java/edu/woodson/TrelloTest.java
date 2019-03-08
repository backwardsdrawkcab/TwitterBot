package edu.woodson;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;

import java.util.List;
import java.util.Scanner;

public class TrelloTest {

    // Using trello account
    // Email: twitterinterfacewithtrello@gmail.com
    // Username: @twitterinterfacewithuser
    // Password: wtwoodsontwitterlab2019twitterinterfacewithtrello


    private static final String API_KEY = "6a888dffccb4c413711d7d617057fa07";
    private static final String TOKEN = "186d9044cd5dfdb60c3b5ab3befb2aaeb2daddccdd05244ce152743701fec680";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Trello trello = new TrelloImpl(API_KEY, TOKEN, new ApacheHttpClient());
        String boardId = "dqJhYMUB";
        Board board = trello.getBoard(boardId);

//        for (Card c: board.fetchCards()) {
//            System.out.println(c.getName());
//        }


        List<TList> currentLists = board.fetchLists();
        TList list = currentLists.get(0);
        List<Card> cards = trello.getListCards(list.getId());
        for (Card c:cards) {
            System.out.println(c.getName()); //YESSSS!!!!!!
        }

//        for (int i = 0; i < list.getCards().size(); i++) {
//
//        }

//        for (TList tl:currentLists) {
//            for (Card c:tl.getCards()) {
//                System.out.println(tl.toString());
//            }
//        }

//        String nothing = sc.next();
//        List<TList> newLists = board.fetchLists();
//
//        System.out.println(findChangedCard(currentLists, newLists));
    }

    public static Card findChangedCard(List<TList> currentLists, List<TList> newLists) {
        List<Card> current = null;
        List<Card> newList = null;

        int i = 0;

        for (; i < newLists.size(); i++) {
            if (!currentLists.get(i).getCards().equals(newLists.get(i).getCards())) {
                current = currentLists.get(i).getCards();
                newList = newLists.get(i).getCards();
                continue;
            }
        }

            if (i == newLists.size())
                return null;

                for (int j = 0; j < current.size(); j++) {
                    if (!current.get(i).equals(newList.get(i))) {
                        return newList.get(i);
                    }
                }

        return null;
    }
}
