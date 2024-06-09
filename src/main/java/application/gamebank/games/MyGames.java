package application.gamebank.games;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyGames implements Serializable {

    private List<Game> allGames;

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

    public void triAlphabetique() {
        Collections.sort(allGames);
    }

    @Override
    public String toString() {
        String result = "";
        for (Game game : allGames) {
            result += game.toString() +" ; ";
        }
        return result;
    }
}