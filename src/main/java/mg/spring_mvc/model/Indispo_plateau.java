/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.spring_mvc.model;

import java.sql.Date;
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
public class Indispo_plateau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int idplateau;
    @Column(name = "daty")
    Date date;
    String observation;

    public Indispo_plateau() {
    }

    public Indispo_plateau(int id, int idplateau, Date date, String observation) {
        this.id = id;
        this.idplateau = idplateau;
        this.date = date;
        this.observation = observation;
    }

    public Indispo_plateau(int idplateau, Date date, String observation) {
        this.idplateau = idplateau;
        this.date = date;
        this.observation = observation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdplateau() {
        return idplateau;
    }

    public void setIdplateau(int idplateau) {
        this.idplateau = idplateau;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
    
    
}
