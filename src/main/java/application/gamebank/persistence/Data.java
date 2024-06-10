package application.gamebank.persistence;

import application.gamebank.games.MyGames;
import application.gamebank.tags.MyTags;

import java.io.Serializable;

public class Data implements Serializable {

    private MyGames games;
    private MyTags tags;

    public Data(MyGames games, MyTags tags) {
        super();
        this.games = games;
        this.tags = tags;
    }

    public void setGames(MyGames games) {
        this.games = games;
    }

    public MyGames getGames() {
        return games;
    }

    public void setTags(MyTags tags) {
        this.tags = tags;
    }

    public MyTags getTags() {
        return tags;
    }
}
