package mg.spring_mvc.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import mg.spring_mvc.dao.HibernateDao;

/**
 *
 * @author 26132
 */
@Entity
public class Scene {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    int id;
    String nom;
    int idfilm;
    int idauteur;
    int etat;

    @Override
    public String toString() {
        return "Scene{" + "id=" + id + ", nom=" + nom + ", idfilm=" + idfilm + ", etat=" + etat + '}';
    }

    public Scene() {
    }

    public Scene(int id, String nom, int idfilm, int idauteur, int etat) {
        this.id = id;
        this.nom = nom;
        this.idfilm = idfilm;
        this.setEtat(etat);
    }

    public Scene(String nom, int idplateau, int idfilm, int etat) {
        this.nom = nom;
        this.idfilm = idfilm;
        this.setEtat(etat);
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        try {
            this.etat = etat;
        } catch (Exception e) {
            this.etat = 0;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdfilm() {
        return idfilm;
    }

    public void setIdfilm(int idfilm) {
        this.idfilm = idfilm;
    }

    public int getIdauteur() {
        return idauteur;
    }

    public void setIdauteur(int idauteur) {
        this.idauteur = idauteur;
    }

//    public List<Scene> selectAll(HibernateDao dao) {
//        List<Scene> list = new ArrayList<>();
//        List<Object> obj = dao.select(Scene.class);
//        for (int i = 0; i < obj.size(); i++) {
//            list.add((Scene) obj.get(i));
//            System.out.println("scene : " + list.get(i).nom);
//        }
//        return list;
//    }

}
