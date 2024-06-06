package application.gamebank.persistence;

import application.gamebank.games.MyGames;

import java.io.*;

public class PersistenceBySerialization implements PersistenceModele {

    private static final String SAVE_FILE = "saveFile";

    @Override
    public void save(MyGames model) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            objectOutputStream.writeObject(model);
            objectOutputStream.flush();
            System.out.println("SAVE OK");
        } catch (IOException e) {
            System.err.println("Saving file error");
        }
    }

    @Override
    public MyGames load() {
        MyGames model = null;

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            model = (MyGames) input.readObject();
            System.out.println("LOAD OK");
        } catch (IOException e) {
            System.err.println("Save file does not exist");
            System.err.println("Creation of empty model");
            model = new MyGames();
        } catch (ClassNotFoundException e) {
            System.err.println("Loading save file error");
        }
        return model;
    }
}