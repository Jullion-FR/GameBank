package application.gamebank.persistence;

import application.gamebank.games.MyGames;
import application.gamebank.tags.MyTags;

import java.io.IOException;

public interface Persistence {

    void save();

    void load();

    MyTags getTags();

    MyGames getGames();

}
