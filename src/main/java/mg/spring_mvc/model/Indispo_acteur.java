/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.spring_mvc.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 26132
 */
@Entity
public class Indispo_acteur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "idperso")
    int idacteur;
    @Column(name = "dateindispodebut")
    Timestamp datedebut;
    @Column(name = "dateindispofin")
    Timestamp datefin;
    String observation;

    public Indispo_acteur() {
    }

    public Indispo_acteur(int id, int idacteur, Timestamp datedebut, Timestamp datefin, String observation) {
        this.id = id;
        this.idacteur = idacteur;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.observation = observation;
    }

    public Indispo_acteur(int idacteur, Timestamp datedebut, Timestamp datefin, String observation) {
        this.idacteur = idacteur;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.observation = observation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdacteur() {
        return idacteur;
    }

    public void setIdacteur(int idacteur) {
        this.idacteur = idacteur;
    }

    public Timestamp getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Timestamp datedebut) {
        this.datedebut = datedebut;
    }

    public Timestamp getDatefin() {
        return datefin;
    }

    public void setDatefin(Timestamp datefin) {
        this.datefin = datefin;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
    
    
}
