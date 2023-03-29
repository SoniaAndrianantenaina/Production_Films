
package mg.spring_mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Personnage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String nom;
    String email;

    public Personnage() {
    }

    public Personnage(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    

    public Personnage(int id, String nom,String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
