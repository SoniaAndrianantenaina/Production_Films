/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.spring_mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author 26132
 */
@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    int id;
    String intitule;

    public Action() {
    }

    public Action(int id, String intitule) {
        this.id = id;
        this.intitule = intitule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    
}
