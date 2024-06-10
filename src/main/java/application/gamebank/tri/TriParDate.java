package application.gamebank.tri;

import application.gamebank.games.MyGames;
import application.gamebank.games.Game;
import java.util.Collections;
import java.util.Comparator;

public class TriParDate implements Tri {
    @Override
    public void tri(MyGames games) {
        games.getAllGames().sort(Comparator.comparing(Game::getReleaseDate));
    }
}
