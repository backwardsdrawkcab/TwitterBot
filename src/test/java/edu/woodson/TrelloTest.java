package edu.woodson;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import edu.woodson.util.trello.MovedCard;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

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
        String boardId = "1XDTOfqt";
        Board board = trello.getBoard(boardId);
        List<TList> oldLists = board.fetchLists();
        List<Card> oldCards = getCards(trello, oldLists.get(1));
        for (Card c : oldCards) {
            System.out.println(c.getName());
        }
        List<List<Card>> oldTrelloList = getTrelloList(trello, oldLists);
        // input list of cards to findMovedCards instead of fetch cards from board

        sc.next();
        Board newBoard = trello.getBoard(boardId);
        List<TList> newLists = newBoard.fetchLists();
        List<Card> newCards = getCards(trello, newLists.get(1));
        for (Card c : newCards) {
            System.out.println(c.getName());
        }
        List<List<Card>> newTrelloList = getTrelloList(trello, newLists);


        System.out.println(findMovedCard(oldTrelloList, newTrelloList).getName());

    }

    public static List<Card> getCards(Trello trello, TList list) {
        return trello.getListCards(list.getId());
    }

    private static List<List<Card>> getTrelloList(Trello trello, List<TList> lists) {
        List<List<Card>> result = new LinkedList<>();

        for (TList tl : lists) {
            result.add(getCards(trello, tl));
        }

        return result;
    }

    public static MovedCard findMovedCard(List<List<Card>> oldBoard, List<List<Card>> newBoard) {
        for (int i = 0; i < oldBoard.size(); i++) {
            List<Card> oldCards = oldBoard.get(i);
            List<Card> newCards = newBoard.get(i);
            if (oldCards.size() != newCards.size()) {
                return new MovedCard(findMovedCardWithinLists(oldCards, newCards), findMovedFrom(oldBoard, newBoard), findMovedTo(oldBoard, newBoard));
            }
        }
        return null;
    }

    private static TList findMovedTo(List<List<Card>> oldBoard, List<List<Card>> newBoard) {
        return null;
    }

    private static TList findMovedFrom(List<List<Card>> oldBoard, List<List<Card>> newBoard) {
        return null;
    }

    private static String findMovedCardWithinLists(List<Card> oldCards, List<Card> newCards) {
        List<String> movedTo;
        List<String> movedFrom;

        if (newCards.size() < oldCards.size()) {
            movedFrom = new LinkedList<>();
            for (Card c : newCards) {
                movedFrom.add(c.getName());
            }
            movedTo = new LinkedList<>();
            for (Card c : oldCards) {
                movedTo.add(c.getName());
            }
        } else {
            movedFrom = new LinkedList<>();
            for (Card c : oldCards) {
                movedFrom.add(c.getName());
            }
            movedTo = new LinkedList<>();
            for (Card c : newCards) {
                movedTo.add(c.getName());
            }
        }

        return new LinkedList<>(CollectionUtils.subtract(movedTo, movedFrom)).get(0);

    }

    private static Set<Card> symmetricDifference(Set<Card> a, Set<Card> b) {
        Set<Card> result = new HashSet<>(a);
        for (Card element : b) {
            // .add() returns false if element already exists
            if (!result.add(element)) {
                result.remove(element);
            }
        }
        return result;
    }
}
