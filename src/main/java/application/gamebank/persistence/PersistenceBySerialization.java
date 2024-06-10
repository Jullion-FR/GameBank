package application.gamebank.persistence;

import application.gamebank.games.MyGames;
import application.gamebank.tags.ListeTags;
import application.gamebank.tags.MyTags;

import java.io.*;

public class PersistenceBySerialization implements Persistence {

    private static final String GAMES_FILE = "gamesSave";
    private static final String TAGS_FILE = "tagsSave";
    private MyGames games;
    private MyTags tags;

    public PersistenceBySerialization(MyGames games, MyTags tags) {
        super();
        this.games =games;
        this.tags = tags;
    }

    /** Enregistre les tags et les jeux données */
    @Override
    public void save() {
        saveGames();
        saveTags();
    }

    /** Charge les tags et les jeux (accésible par getGames() et getTags() */
    @Override
    public void load() {
        games = loadGames();
        tags = loadTags();
    }

    @Override
    public MyGames getGames() {
        return games;
    }

    @Override
    public MyTags getTags() {
        return tags;
    }

    /** Sauvegarde les Jeux présent dans la variable games */
    public void saveGames() {
        try {
            save(GAMES_FILE, games);
            System.out.println("Sauvegarde des Jeux éffectuée");
        } catch (IOException e) {
            System.err.println("La sauvegarde des Jeux à échouée");
        }
    }

    /** Savegarde les tags présent dans la variable tags */
    public void saveTags() {
        try {
            save(TAGS_FILE, tags);
            System.out.println("Sauvegarde des tags éffectuée");
        } catch (IOException e) {
            System.err.println("La savegarde des Tags à échouée");
        }
    }

    /** Renvoie un objet MyGames contenant les jeux de l'application */
    public MyGames loadGames() {
        MyGames games = null;
        try {
            games = (MyGames) load(GAMES_FILE);
            System.out.println("Chargement des Jeux éffectuée");
        } catch (IOException e) {
            games = new MyGames();
            System.out.println("Sauvegarde introuvable, création de nouveau conteneur");
        } catch (ClassNotFoundException e) {
            System.err.println("La Classe 'MyGames' n'a pas été trouver");
            System.exit(-1);
        }
        return games;
    }

    /** Renvoie un objet MyTags contenant les tags de l'application */
    public MyTags loadTags() {
        MyTags tags = null;
        try {
            tags = (MyTags) load(TAGS_FILE);
            System.out.println("Chargement des Tags éffectuée");
        } catch (IOException e) {
            tags = new MyTags();
            System.out.println("Sauvegarde introuvable, création de nouveau conteneur");
        } catch (ClassNotFoundException e) {
            System.err.println("La Classe 'MyTags' n'a pas été trouver");
            System.exit(-1);
        }
        return tags;
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