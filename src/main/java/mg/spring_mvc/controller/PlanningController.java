/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mg.spring_mvc.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mg.spring_mvc.dao.HibernateDao;
import mg.spring_mvc.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author 26132
 */
@Controller
public class PlanningController {

    @Autowired
    HibernateDao dao;

    Scene scene = new Scene();

    @GetMapping("/planning")
    public String getPlanning(Model model) {

        return "planning";
    }

    @GetMapping("/planningScene")
    public String getAllScene(Model model,HttpServletRequest request) throws Exception {
        HttpSession sess = request.getSession();
        int idfilm = (int) sess.getAttribute("idFilm");
        List<Scene> scenes = scene.selectFinished(dao,idfilm);
        model.addAttribute("listscene", scenes);
        return "planning";
    }

    @PostMapping("/Planifier")
    public String planifier(Model model, String datedebut, String datefin, String[] scenes, String type,HttpServletRequest request) throws Exception {
        LocalDate dd = LocalDate.parse(datedebut);
        LocalDate df = LocalDate.parse(datefin);
        HttpSession sess = request.getSession();
        int idfilm = (int) sess.getAttribute("idFilm");
        System.out.println("idfilmm : "+idfilm);

        System.out.println("dd : " + dd);
        System.out.println("df : " + df);
        System.out.println("type : " + type);

        for (int i = 0; i < scenes.length; i++) {
            System.out.println("scenes : " + scenes[i]);
        }

        Planning plan = new Planning();
        ArrayList<ArrayList<Planning>> plannings = plan.generatePlanning(dao, dd, df, scenes, type);
        System.out.println("planning taille --- " + plannings.size());
        List<Scene> listscenes = scene.selectFinished(dao,idfilm);
        model.addAttribute("listscene", listscenes);
        model.addAttribute("action", "planning");
        model.addAttribute("plannings", plannings);
       

        return "proposition";
    }
    
    @GetMapping("/retour_planning")
    public String retour(Model model,HttpServletRequest request) throws Exception {
        HttpSession sess = request.getSession();
        int idfilm = (int) sess.getAttribute("idFilm");
        List<Scene> scenes = scene.selectFinished(dao,idfilm);
        model.addAttribute("listscene", scenes);
        return "planning";
    }

    @PostMapping("/valider_proposition")
    public String valider(Model model, String[] planning) throws Exception {
        ArrayList<Planning> plannings = new ArrayList<Planning>();
        for (int i = 0; i < planning.length; i++) {
            System.out.println(planning[i]);
            String[] info = planning[i].split(",");
            Planning plan = new Planning();
            plan.setDate(LocalDate.parse(info[0]));
            plan.setScene(Integer.parseInt(info[1]), dao);
            plan.setPlateau(Integer.parseInt(info[2]), dao);
            plan.setIdtype(Integer.parseInt(info[3]));
            plan.setTime(LocalTime.parse(info[4]));
            dao.create(plan);

            plan.getScene().setEtat(2);
            dao.update(plan.getScene());

            LocalDateTime datetime_debut = LocalDateTime.of(plan.getDate(), plan.getTime());
            Timestamp debut = Timestamp.valueOf(datetime_debut);

            LocalTime time = LocalTime.parse(plan.getScene().getDuree(dao));
            LocalDateTime datetime_fin = LocalDateTime.of(plan.getDate(), plan.getTime().plusHours(Long.parseLong(String.valueOf(time.getHour()))).plusMinutes(Long.parseLong(String.valueOf(time.getMinute()))));
            Timestamp fin = Timestamp.valueOf(datetime_fin);

            for (int j = 0; j < plan.getScene().listPersonage(dao).size(); j++) {
                int idacteur = plan.getScene().listPersonage(dao).get(j).getId();

                Indispo_acteur ia = new Indispo_acteur(idacteur, debut, fin, "tournage");
                dao.create(ia);
            }

            Indispo_plateau ip = new Indispo_plateau(plan.getPlateau().getId(), Date.valueOf(plan.getDate()), "utilise pour tournage");
            dao.create(ip);
        }
        return "redirect:/planningScene";
    }

}
