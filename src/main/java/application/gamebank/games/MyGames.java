package application.gamebank.games;

import application.gamebank.tags.MyTags;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyGames implements Serializable {

    private final List<Game> allGames;

    public MyGames() {
        super();
        allGames = new ArrayList<>();
    }

    public void addGame(Game newGame) {
        allGames.add(newGame);
    }

    public List<Game> getAllGames() {
        return allGames;
    }

    public void removeGame(Game game) {
        allGames.remove(game);
    }

}