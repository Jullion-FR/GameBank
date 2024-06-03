
package application.gamebank.tags;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;


public class listeTags  {

    private List<tag> liste = new ArrayList();


    public listeTags(List<tag> liste) {
        this.liste = liste;
    }

    public List<tag> getListe() {
        return liste;
    }

    public void setListe(List<tag> liste) {
        this.liste = liste;
    }
    public void triAlpha(){
        liste.sort(Comparator.naturalOrder());
    }
    public void addListe(tag tag) {
        this.liste.add(tag);
    }

}
