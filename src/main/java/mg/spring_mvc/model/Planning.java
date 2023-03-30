/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.spring_mvc.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import mg.spring_mvc.dao.HibernateDao;

/**
 *
 * @author 26132
 */
@Entity
public class Planning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    LocalDate date;
    @OneToOne
    @JoinColumn(name = "idscene")
    Scene scene;
    @OneToOne
    @JoinColumn(name = "idplateau")
    Plateau plateau;
    int idtype;
    LocalTime time;

    public Planning() {
    }

    public Planning(LocalDate date, Scene scene, Plateau plateau, int idtype, LocalTime time) {
        this.date = date;
        this.scene = scene;
        this.plateau = plateau;
        this.idtype = idtype;
        this.time = time;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
    
    public void setScene(int idscene,HibernateDao dao){
        this.scene = dao.findById(Scene.class, idscene);
    }
    
    public void setPlateau(int idplateau,HibernateDao dao){
        this.plateau = dao.findById(Plateau.class, idplateau);
    }

    @Override
    public String toString() {
        String val = date + "," + scene.id + "," + plateau.id + "," + idtype + "," + time;
        return val;
    }
    

    public boolean WeekEndChecker(LocalDate date) {
        boolean val = false;
        Calendar cal = Calendar.getInstance();

        cal.set(date.getYear(), date.getMonthValue(), date.getDayOfMonth());

        DayOfWeek dayofweek = date.getDayOfWeek();

        if (dayofweek.getValue() == DayOfWeek.SATURDAY.getValue() || dayofweek.getValue() == DayOfWeek.SUNDAY.getValue()) {
            val = true;
        }

        return val;
    }

    public ArrayList<ArrayList<Planning>> generatePlanning(HibernateDao dao, LocalDate startDate, LocalDate endDate, String[] idscenes, String type) {
        ArrayList<ArrayList<Planning>> plannings = new ArrayList<ArrayList<Planning>>();
        ArrayList<Planning> plan = planifier(dao, startDate, endDate, idscenes, type);

        for (LocalDate shootingDay = startDate; shootingDay.isBefore(endDate) || shootingDay.isEqual(endDate);
                shootingDay = shootingDay.plusDays(1)) {
            ArrayList<Planning> pl = new ArrayList<Planning>();

                for (int i = 0; i < plan.size(); i++) {
                    if (plan.get(i).getDate().isEqual(shootingDay)) {
                        pl.add(plan.get(i));
                    }
                }
            plannings.add(pl);
        }

        return plannings;
    }

    public ArrayList<Planning> planifier(HibernateDao dao, LocalDate startDate, LocalDate endDate, String[] idscenes, String type) {
        System.out.println("planifier");
        ArrayList<Planning> result = new ArrayList<>();

        //get All Scene
        ArrayList<Scene> scenes = new ArrayList<Scene>();
        for (int i = 0; i < idscenes.length; i++) {
            Scene scene = (Scene) dao.findById(Scene.class, Integer.parseInt(idscenes[i]));
            scenes.add(scene);
            System.out.println("scene titre : " + scenes.get(i).getNom());
        }

        //configuration heure de travail
        Duration remainingDuration = Duration.ofHours(8);

        //get All day between start and end
        for (LocalDate shootingDay = startDate; shootingDay.isBefore(endDate) || shootingDay.isEqual(endDate); shootingDay = shootingDay.plusDays(1)) {
            System.out.println("shootingDay ----- " + shootingDay);
            if (remainingDuration.isNegative() || remainingDuration.isZero()) {
                break;
            }
                
                List<Plateau> plateaux = dao.getPlateau(shootingDay);
                ArrayList<Boolean> disponibilite = new ArrayList<>();
                for (int i = 0; i < plateaux.size(); i++) {
                    LocalTime shootingTime = LocalTime.parse("08:00:00");

                    for (int j = 0; j < scenes.size(); j++) {
                        System.out.println("probleme --------------- "+scenes.get(j).getDuree(dao));
                        System.out.println("idscene ----------------"+scenes.get(j).id);
                        LocalTime time = LocalTime.parse(scenes.get(j).getDuree(dao));
                        System.out.println("scene " + scenes.get(j).nom);
                        Duration scene_duration = Duration.ofHours(Long.parseLong(String.valueOf(time.getHour()))).plus(Duration.ofMinutes(Long.parseLong(String.valueOf(time.getMinute()))));
                        ArrayList<Personnage> scene_personnage = scenes.get(j).listPersonage(dao);

                        for (Personnage acteur : scene_personnage) {
                            int isdispo = dao.isDispo(acteur.getId(), shootingDay, shootingTime);
                            if (isdispo == 0) {
                                disponibilite.add(true);
                                System.out.println("------------- dispo");
                            }
                        }
                        if (disponibilite.size() == scene_personnage.size()) {
                            remainingDuration = remainingDuration.minus(scene_duration);

                            System.out.println("remaining ---- " + remainingDuration.toString());

                            Planning planningTournage = new Planning();
                            planningTournage.setScene(scenes.get(j));
                            planningTournage.setDate(shootingDay);
                            planningTournage.setPlateau(plateaux.get(i));
                            planningTournage.setIdtype(Integer.parseInt(type));
                            planningTournage.setTime(shootingTime);
                            result.add(planningTournage);

                            scenes.remove(j);

                            disponibilite = new ArrayList<>();
                            shootingTime = shootingTime.plusHours(Long.parseLong(String.valueOf(time.getHour()))).plusMinutes(Long.parseLong(String.valueOf(time.getMinute())));
                            j = 0;
                        }

                        if (remainingDuration.isZero() || remainingDuration.isNegative()) {
                            break;
                        }

                    }
                    remainingDuration = Duration.ofHours(8);
                }
        }
        return result;
    }

}
