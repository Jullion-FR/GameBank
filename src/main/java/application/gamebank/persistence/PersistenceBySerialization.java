package application.gamebank.persistence;

import application.gamebank.games.MyGames;
import application.gamebank.tags.MyTags;

import java.io.*;

public class PersistenceBySerialization implements Persistence {

    private static final String FILE_GAMES = "saveGames";
    private static final String FILE_TAGS = "saveTags";


    private MyGames games;
    private MyTags tags;

    public PersistenceBySerialization(MyGames games, MyTags tags) {
        super();
        this.games = games;
        this.tags = tags;
    }

    @Override
    public void save() {
        saveGames();
        saveTags();
    }

    private void saveGames() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_GAMES))) {
            objectOutputStream.writeObject(games); //<- erreur
            objectOutputStream.flush();
            System.out.println("SAVE OK for games");
        } catch (IOException e) {
            System.err.println("Saving file error for games");
            e.printStackTrace();
        }
    }

    private void saveTags() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_TAGS))) {
            objectOutputStream.writeObject(tags);
            objectOutputStream.flush();
            System.out.println("SAVE OK for tags");
        } catch (IOException e) {
            System.err.println("Saving file error for tags");
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        games = loadGames();
        tags = loadTags();
    }

    private MyTags loadTags() {
        MyTags tags = null;

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_TAGS))) {
            tags = (MyTags) input.readObject();
            System.out.println("LOAD OK for tags");
        } catch (IOException e) {
            System.err.println("Save file does not exist for tags");
            System.err.println("Creation of empty model for tags");
            tags = new MyTags();
        } catch (ClassNotFoundException e) {
            System.err.println("Loading save file error for tags");
            System.exit(-1);
        }

        return tags;
    }

    private MyGames loadGames() {
        MyGames games = null;

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(FILE_GAMES))) {
            games = (MyGames) input.readObject();
            System.out.println("LOAD OK for games");
        } catch (IOException e) {
            System.err.println("Save file does not exist for games");
            System.err.println("Creation of empty model for games");
            games = new MyGames();
        } catch (ClassNotFoundException e) {
            System.err.println("Loading save file error for games");
            e.printStackTrace();
            //System.exit(-1);
        }
        return games;
    }

    @Override
    public MyGames getGames() {
        return games;
    }

    @Override
    public MyTags getTags() {
        return tags;
    }
}