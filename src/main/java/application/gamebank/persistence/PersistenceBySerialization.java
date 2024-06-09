package application.gamebank.persistence;

import application.gamebank.games.MyGames;
import application.gamebank.tags.ListeTags;

import java.io.*;

public class PersistenceBySerialization implements Persistence {

    private static final String GAMES_FILE = "gamesSave";
    private static final String TAGS_FILE = "tagSave";

    /** Savegarde les Jeux présent dans la variable games */
    @Override
    public void saveGames(MyGames games) {
        try {
            save(GAMES_FILE, games);
        } catch (IOException _) {
            System.err.println("La savegarde des Jeux à échouée");
        }
    }

    /** Savegarde les tags présent dans la variable tags */
    @Override
    public void saveTags(ListeTags tags) {
        try {
            save(TAGS_FILE, tags);
        } catch (IOException _) {
            System.err.println("La savegarde des Tags à échouée");
        }
    }

    /** Renvoie un objet MyGames contenant les jeux de l'application */
    @Override
    public MyGames loadGames() {
        MyGames games = null;
        try {
            games = (MyGames) load(GAMES_FILE);
        } catch (IOException e) {
            games = new MyGames();
        } catch (ClassNotFoundException e) {
            System.err.println("La Classe 'MyGames' n'a pas été trouver");
            System.exit(-1);
        }
        return games;
    }

    /** Renvoie un objet ListTags contenant les tags de l'application */
    @Override
    public ListeTags loadTags() {
        return null; // TODO Chargement des tags à faire
    }

    /** Renvoie le contenu d'un fichier de sauvegarde */
    private Object load(String file_name) throws IOException, ClassNotFoundException {
        return new ObjectInputStream(new FileInputStream(file_name)).readObject();
    }

    /** Enregistre un objet sous forme d'un fichier binaire */
    private void save(String file_name, Object model) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file_name));
        objectOutputStream.writeObject(model);
        objectOutputStream.flush();
    }
}