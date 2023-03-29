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
 * @author AnjaNali
 */
@Entity
public class Film_Auteur {
    @Id
    int idFilm;
    int idAuteur;

    public Film_Auteur(int idFilm, int idAuteur) {
        this.idFilm = idFilm;
        this.idAuteur = idAuteur;
    }

    public Film_Auteur() {
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }
}
