package application.gamebank.persistence;

import application.gamebank.games.MyGames;

public interface PersistenceModele {

    void save(MyGames model);

    MyGames load();

}
