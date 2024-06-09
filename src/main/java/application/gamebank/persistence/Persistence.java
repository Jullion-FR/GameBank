package application.gamebank.persistence;

import application.gamebank.games.MyGames;
import application.gamebank.tags.ListeTags;

import java.io.IOException;

public interface Persistence {

    void saveGames(MyGames games);

    void saveTags(ListeTags tags);

    MyGames loadGames();

    ListeTags loadTags() throws IOException, ClassNotFoundException;

}
