package application.gamebank.tri;

import application.gamebank.games.MyGames;
import application.gamebank.games.Game;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TriParDate implements Tri {
    @Override
    public void tri(MyGames games) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        games.getAllGames().sort((g1, g2) -> {
            LocalDate date1 = LocalDate.parse(g1.getReleaseDate(), formatter);
            LocalDate date2 = LocalDate.parse(g2.getReleaseDate(), formatter);
            return date2.compareTo(date1); // Inverser l'ordre pour que le plus récent soit en premier
        });
    }
}
