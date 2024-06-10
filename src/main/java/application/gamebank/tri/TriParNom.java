package application.gamebank.tri;

import application.gamebank.games.MyGames;
import java.util.Collections;

public class TriParNom implements Tri {
    @Override
    public void tri(MyGames games) {
        Collections.sort(games.getAllGames());
    }
}
