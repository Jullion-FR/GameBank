
package application.gamebank.tags;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;


public class ListeTags {

    private List<Tag> liste = new ArrayList();


    public ListeTags(List<Tag> liste) {
        this.liste = liste;
    }

    public List<Tag> getListe() {
        return liste;
    }

    public void setListe(List<Tag> liste) {
        this.liste = liste;
    }
    public void triAlpha(){
        liste.sort(Comparator.naturalOrder());
    }
    public void addTag(Tag tag) {
        this.liste.add(tag);
    }


}
