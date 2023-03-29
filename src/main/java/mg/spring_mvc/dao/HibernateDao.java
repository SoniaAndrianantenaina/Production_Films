package mg.spring_mvc.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import mg.spring_mvc.model.Film_Auteur;
import mg.spring_mvc.model.Personnage;
import mg.spring_mvc.model.Scene;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class HibernateDao {

    private SessionFactory sessionFactory;

    public <T> T create(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    public <T> T findById(Class<T> clazz, Serializable id) {
        Session session = sessionFactory.openSession();
        T entity = (T) session.get(clazz, id);
        session.close();
        return entity;
    }

    public <T> List<T> findAll(Class<T> tClass) {
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(tClass).list();
        session.close();
        return results;
    }

    public <T> List<T> findWhere(T entity) {
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass()).add(example).list();
        session.close();
        return results;
    }

    public <T> List<T> paginateWhere(T entity, int offset, int size) {
        Session session = sessionFactory.openSession();
        Example example = Example.create(entity).ignoreCase();
        List<T> results = session.createCriteria(entity.getClass())
                .add(example)
                .setFirstResult(offset)
                .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size) {
        Session session = sessionFactory.openSession();
        List<T> results = session.createCriteria(clazz)
                .setFirstResult(offset)
                .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public <T> List<T> paginate(Class<T> clazz, int offset, int size, String orderBy, boolean orderAsc) {
        Session session = sessionFactory.openSession();
        Order order = (orderAsc) ? Order.asc(orderBy) : Order.desc(orderBy);
        List<T> results = session.createCriteria(clazz)
                .addOrder(order)
                .setFirstResult(offset)
                .setMaxResults(offset + size).list();
        session.close();
        return results;
    }

    public void deleteById(Class tClass, Serializable id) {
        delete(findById(tClass, id));
    }

    public void delete(Object entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    public <T> T update(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Object> selectWpagination(Class<?> clazz, int first_element, int max_result) throws Exception {
        List<Object> data = new ArrayList<>();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from " + clazz.getSimpleName() + " where (statut = 1) and (date_publication <= now())");
            query.setFirstResult(first_element);
            query.setMaxResults(max_result);
            for (Object row : query.list()) {
                data.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
        return data;
    }

    public List<Object> selectWhere(Class<?> clazz, String pattern) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.like("titre", "%" + pattern + "%").ignoreCase());
            criteria.add(Restrictions.eq("statut", 1));
            return criteria.list();
        } finally {
            session.close();
        }
    }

    public Object login(Class<?> clazz, String username, String password) {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.eq("email", username));
            criteria.add(Restrictions.eq("mdp", password));
            Object user = criteria.uniqueResult();
            return user;
        } finally {
            session.close();
        }
    }

    public List<Object> selectAdmin(Class<?> clazz) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.eq("statut", 0));
            return criteria.list();
        } finally {
            session.close();
        }
    }

    public List<Personnage> getPersonnageSelectionne(int idScene) {
        Session session = sessionFactory.openSession();
        try {
            List<Personnage> result = new ArrayList<>();
            String sql = "SELECT sp.idScene, sp.idPerso, p.id, p.nom FROM Scene_Personnage sp join Personnage p on p.id = sp.idPerso WHERE sp.idScene = :idScene";
            List<Object[]> sc = session.createNativeQuery(sql)
                    .setParameter("idScene", idScene)
                    .getResultList();
            for (Object[] sperso : sc) {
                Personnage perso = new Personnage((int) sperso[2], (String) sperso[3]);
                result.add(perso);
            }
            return result;
        } finally {
            session.close();
        }
    }
    public List<Object> selectPersoScene(Class<?> clazz, String pattern, int idscene) throws Exception {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(clazz);
            criteria.add(Restrictions.eq("idscene", idscene));
            return criteria.list();
        } finally {
            session.close();
        }
    }
    public int getFilm(int idAuteur) {
        Session session = sessionFactory.openSession();
        int idFilm = 0;
        try {
            Query query = session.createNativeQuery("SELECT idFilm FROM film_auteur WHERE idAuteur=:idAuteur");
            query.setParameter("idAuteur", idAuteur);
            idFilm = (Integer) query.uniqueResult();
        } finally {
            session.close();
        }
        return idFilm;
    }
    
     public  List<Personnage> GetSceneActeur(int idScene) {
        Session session = sessionFactory.openSession();
        List<Personnage> liste = new ArrayList<>();
        try {
             liste= session.createNativeQuery("select id,nom,email from personnage a where a.id in (select idperso from scene_personnage sp where idscene="+idScene+")",Personnage.class).getResultList();   
            
        } finally {
            session.close();
        }
        return liste;
    }
     
      public List<Film_Auteur> GetAuteurs(int idFilm) {
        Session session = sessionFactory.openSession();
        List<Film_Auteur> results = new ArrayList<>();
        try {
                results= session.createNativeQuery("select idfilm,idauteur FROM film_auteur WHERE idfilm="+idFilm,Film_Auteur.class).getResultList();   
            
        } finally {
            session.close();
        }
        return results;
    }
      
    public int GetFilmOrNot(int idAuteur, int idFilm){
        Session session = sessionFactory.openSession();
        int val = 1;
        try{
            val = (Integer)session.createNativeQuery("select * from GetFilmOrNot("+idAuteur+","+idFilm+")").getSingleResult();
        }finally{
            session.close();
        }
        return val;
    }
      //Valisoa
      
      public List<Scene> paginateWhereCondition (String critere, int offset, int size){
        Session session = sessionFactory.openSession();
        critere = "%" + critere + "%";
        List<Scene> results = session.createCriteria(Scene.class)
                .add(Restrictions.or(Restrictions.ilike("nom", critere,MatchMode.ANYWHERE)))
                .setFirstResult(offset)
                .setMaxResults(offset+size).list();
        session.close();
        return results;
    }

    public List<Scene> dsuppaginateWhereCondition (String critere,int idfilm,int offset, int size){
        Session session = sessionFactory.openSession();
        critere = "%" + critere + "%";
        List<Scene> results = session.createCriteria(Scene.class)
                .add(Restrictions.or(Restrictions.ilike("nom", critere,MatchMode.ANYWHERE)))
                .add(((Restrictions.eq("idfilm", idfilm))))
                .setFirstResult(offset)
                .setMaxResults(offset+size).list();
        session.close();
        return results;
    }
    public List<Scene> dinfpaginateWhereCondition (String critere,int idfilm,int offset, int size){
        Session session = sessionFactory.openSession();
        critere = "%" + critere + "%";
        List<Scene> results = session.createCriteria(Scene.class)
                .add(Restrictions.or(Restrictions.ilike("nom", critere,MatchMode.ANYWHERE)))
                .add(((Restrictions.eq("idfilm", idfilm))))
                .setFirstResult(offset)
                .setMaxResults(offset+size).list();
        session.close();
        return results;
    }
}
