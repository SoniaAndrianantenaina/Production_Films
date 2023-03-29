package mg.spring_mvc.controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.Time;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import mg.spring_mvc.dao.HibernateDao;
import mg.spring_mvc.model.Auteur;
import mg.spring_mvc.model.Film;
import mg.spring_mvc.model.Personnage;
import mg.spring_mvc.model.Personnage_Action;
import mg.spring_mvc.model.Scene;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmController {

    @Autowired
    HibernateDao dao;

    @GetMapping("/listPersoScene")
    public String listPersonnageScene(Model model, HttpServletRequest request) {
        Personnage p1 = new Personnage(1, "personnage1");
        Personnage p2 = new Personnage(2, "personnage2");
        List<Personnage> perso = new ArrayList<>();
        perso.add(p1);
        perso.add(p2);

        HttpSession session = request.getSession();
        Scene scene = (Scene) session.getAttribute("scene");
        System.out.println(scene);
        List<Personnage> persos = dao.getPersonnageSelectionne(scene.getId());

        model.addAttribute("personnages", persos);
        return "listPersonnages";
    }

    @GetMapping("/scene_Personnage")
    public String showPersonnageScene(@RequestParam("id") String id, Model model) {

        int idP = Integer.parseInt(id);
//        System.out.println("idParam"+idP);
        Personnage pers = dao.findById(Personnage.class, idP);
//        System.out.println("id"+pers.getId());
        model.addAttribute("personnage", pers);
        return "personnageSceneForm";
    }

    @GetMapping("/personnageSceneTraitement")
    public String traitementScene(HttpServletRequest request, Model model, String[] id, String[] action, String[] expression, String[] dialogue, String[] debut, String[] fin) {
        HttpSession sess = request.getSession();
        Scene scene = (Scene) sess.getAttribute("scene");
        int idScene = scene.getId();
        System.out.println("idscene : " + idScene);

        //inserer PersonnageAction
        model.addAttribute("action", action);
        model.addAttribute("expression", expression);
        model.addAttribute("dialogue", dialogue);
        model.addAttribute("debut", debut);
        model.addAttribute("fin", fin);
        int idP = Integer.parseInt(id[0]);

        String[] action1 = new String[action.length];

        System.out.println(action.length);
        for (int i = 0; i < action.length; i++) {

            Personnage_Action pa = null;
            System.out.println(debut);
            Time d = Time.valueOf(debut[i]);
            Time f = Time.valueOf(fin[i]);

            int idAction = 0;
            if (action[i].equals("") == false) {
                action1[i] = action[i];
                idAction = 1;
                pa = new Personnage_Action(idP, idAction, idScene, action1[i], d, f);
                dao.create(pa);
            }
            if (dialogue.length != 0) {
                if (expression[i].equals("") == false) {
                    action1[i] = expression[i];
                    idAction = 2;
                    pa = new Personnage_Action(idP, idAction, idScene, action1[i], d, f);
                    dao.create(pa);
                }
            }
            if (dialogue.length != 0) {
                if (dialogue[i].equals("") == false) {
                    action1[i] = dialogue[i];
                    idAction = 3;
                    pa = new Personnage_Action(idP, idAction, idScene, action1[i], d, f);
                    dao.create(pa);
                }
            }

            System.out.println("tafiditra :" + i);
        }
        return "redirect:/listPersoScene";
    }
    
    
    //Sonia
    @GetMapping("/listeFilm")
    public String ListFilm(Model model) {
        List<Film> film = dao.findAll(Film.class);
        model.addAttribute("listeFilm", film);
        return "home";
    }

    @GetMapping("/login")
    public String GetLogin(HttpServletRequest request) {
        int idFilm = Integer.parseInt(request.getParameter("idFilm"));
        HttpSession session = request.getSession(true);
        session.setAttribute("idFilm", idFilm);
        return "login";
    }

    @GetMapping("/accueil")
    public String Login(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        int idFilm = (int)session.getAttribute("idFilm");
//        FilmAuteur fa = new FilmAuteur();
//        fa.setIdFilm(idFilm);
        
        String nom = request.getParameter("nom");
        String mdp = request.getParameter("mdp");
        Auteur a = new Auteur();
        a.setMdp(mdp);
        a.setNom(nom);
        System.out.println("mdp: "+mdp);
        System.out.println("nom :"+nom);
        List<Auteur> aut = dao.findWhere(a);
        if (aut.size() != 0) {
            int idAuteur = aut.get(0).getId();
            System.out.println("idAuteur :"+idAuteur);
            int valiny = dao.GetFilmOrNot(idAuteur,idFilm);
            if(valiny==1){
                session.setAttribute("idAuteur",idAuteur);
                return "redirect:/liste";
            
            }
              
        }
        
        model.addAttribute("erreur", "Mot de passe incorrect");
        return "login";
        
    }

    @GetMapping("/logout")
    public String Logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "login";
    }
    
    @GetMapping("/detailsScene")
    public String GetDetailsScene(Model model,HttpServletRequest request ){
        int idScene = Integer.parseInt(request.getParameter("idScene"));
        Scene s = dao.findById(Scene.class, idScene);
        List<Personnage> listeActeurs = dao.GetSceneActeur(idScene);
        model.addAttribute("listeActeurs", listeActeurs);
        model.addAttribute("model", s);
        return "detailsScene";
    }
    //Valisoa
     @RequestMapping("/liste")
    public String front(Model model, HttpSession session, HttpServletRequest request) {
        //maka idfilm
        int idfilm = (int)session.getAttribute("idFilm");
        
        String mot = "";
        Object[] page = new Object[0];
        if (request.getParameter("mots") != null) {
            mot = request.getParameter("mots");
        }

        //maka session anle auteur
        session = request.getSession();
        //maka idfilm
      
        //smoll list
        int nbrParPage = 4;
        double nombre = Math.ceil(((double) dao.dinfpaginateWhereCondition(mot,idfilm,1, 100).size()) / nbrParPage);
        page = new Object[(int) nombre];
        int nbr = 0;
        if (request.getParameter("nbr") != null) {
            nbr = (Integer.parseInt(request.getParameter("nbr")) - 1) * nbrParPage;
            System.out.print(nbr);
        }
        String path = session.getServletContext().getRealPath("/");
        List<Scene> liste = dao.dinfpaginateWhereCondition(mot,idfilm, nbr, nbrParPage);
                
        String[] etats = {"En cours", "Script finished", "Already planified"};
        String bouton = "<button class=\"nav-link btn btn-danger btn-sm ml-lg-3\" type=\"submit\">Terminer</button>";
        model.addAttribute("idfilm", idfilm);
        model.addAttribute("bouton", bouton);
        model.addAttribute("etats", etats);
        model.addAttribute("liste", liste);
        model.addAttribute("page", (int) nombre);
        model.addAttribute("path", path);
        model.addAttribute("hibernate", dao);
        return "Lister";
    }
     @RequestMapping("/Terminer")
    public String update(Scene scene, HttpServletRequest request) {
        int idscene = Integer.parseInt(request.getParameter("idscene"));
        Scene new_scene = dao.findById(Scene.class, idscene); // récupère son ID
        new_scene.setEtat(1); // met à jour l'état de la scène
        dao.update(new_scene); // met à jour la scène dans la base 
        return "redirect:liste";
    }
}
