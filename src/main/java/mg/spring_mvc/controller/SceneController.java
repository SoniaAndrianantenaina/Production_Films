package mg.spring_mvc.controller;

import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mg.spring_mvc.dao.HibernateDao;
import mg.spring_mvc.model.Auteur;
import mg.spring_mvc.model.Film;
import mg.spring_mvc.model.Personnage;
import mg.spring_mvc.model.Scene;
import mg.spring_mvc.model.Scene_Personnage;

//import mg.spring_mvc.model.Personnage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SceneController {

    @Autowired
    HibernateDao dao;

    @GetMapping("/createscene")
    public String createscene(Model model,HttpServletRequest request) {
        HttpSession sess = request.getSession();
        int idf = (int)sess.getAttribute("idFilm");
        List<Personnage> personnages = dao.findAll(Personnage.class);
        Film film1 = new Film();
        film1.setId(idf);
        model.addAttribute("personnage", personnages);
        model.addAttribute("film", film1);
        return "createscene2";
    }

    @GetMapping("/insertScene")
    public void insertSujet(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, Exception {
        int idfilm = Integer.parseInt(request.getParameter("idfilm"));
        String nomscene = request.getParameter("nomscene");
        String[] personnage = (request.getParameterValues("personnage"));
        HttpSession sess = request.getSession();
        int idaut = (int)sess.getAttribute("idAuteur");
        
        Scene scene = new Scene();
        scene.setNom(nomscene);
        scene.setIdfilm(idfilm);
        scene.setEtat(0);
        scene.setIdauteur(idaut);

        dao.create(scene);
        List<Scene> listscene = dao.findAll(Scene.class);
        Scene lastscene = listscene.get(listscene.size() - 1);
        Scene_Personnage[] sp = new Scene_Personnage[personnage.length];
        System.out.println(sp.length);
        for (int i = 0; i < personnage.length; i++) {
            sp[i] = new Scene_Personnage(lastscene.getId(), Integer.parseInt(personnage[i]));
            System.out.println("idperso : "+sp[i].getIdPerso());
            dao.create(sp[i]);
        }
        sess.setAttribute("scene", scene);
//        return("/listPersoScene");

        RequestDispatcher disp = request.getRequestDispatcher("/listPersoScene");
        try {

            disp.forward(request, response);
        } catch (Exception e) {
            throw e;
        }
    }
}
