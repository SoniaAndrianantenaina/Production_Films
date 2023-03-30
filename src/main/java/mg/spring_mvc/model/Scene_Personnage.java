/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.spring_mvc.model;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Scene_Personnage {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)       
    int idScene;
    int idPerso;

    public Scene_Personnage() {
    }

    public Scene_Personnage(int idScene, int idPersonnage) {
        this.idScene = idScene;
        this.idPerso = idPersonnage;
    }


    public int getIdScene() {
        return idScene;
    }

    public void setIdScene(int idScene) {
        this.idScene = idScene;
    }

    public int getIdPerso() {
        return idPerso;
    }

    public void setIdPerso(int idPerso) {
        this.idPerso = idPerso;
    }

}
