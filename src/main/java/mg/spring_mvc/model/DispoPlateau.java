/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.spring_mvc.model;

import java.sql.Date;

/**
 *
 * @author 26132
 */
public class DispoPlateau {
    int id;
    int idplateau;
    Date date;

    public DispoPlateau() {
    }

    public DispoPlateau(int idplateau, Date date) {
        this.idplateau = idplateau;
        this.date = date;
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
    
    
}
