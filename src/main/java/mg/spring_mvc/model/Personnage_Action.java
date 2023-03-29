/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.spring_mvc.model;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 26132
 */
@Entity
public class Personnage_Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    int id;
    int idperso;
    int idaction;
    int idscene;
    String  valeur;
    Time debut;
    Time fin;
    

    public Personnage_Action() {
    }

    public Personnage_Action(int idperso, int idaction, int idscene, String valeur, Time debut, Time fin) {
        this.idperso = idperso;
        this.idaction = idaction;
        this.idscene = idscene;
        this.valeur = valeur;
        this.debut = debut;
        this.fin = fin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdperso() {
        return idperso;
    }

    public void setIdperso(int idperso) {
        this.idperso = idperso;
    }

    public int getIdaction() {
        return idaction;
    }

    public void setIdaction(int idaction) {
        this.idaction = idaction;
    }

    public int getIdscene() {
        return idscene;
    }

    public void setIdscene(int idscene) {
        this.idscene = idscene;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Time getDebut() {
        return debut;
    }

    public void setDebut(Time debut) {
        this.debut = debut;
    }

    public Time getFin() {
        return fin;
    }

    public void setFin(Time fin) {
        this.fin = fin;
    }

    
    
}
