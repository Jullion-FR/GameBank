package application.gamebank.tri;

import application.gamebank.games.MyGames;

public class TriParNotes implements Tri {
    @Override
    public void tri(MyGames games) {
        games.getAllGames().sort((g1, g2) -> {
            return Double.compare(g2.getRating(), g1.getRating()); // Sorting in descending order
        });
    }
}
