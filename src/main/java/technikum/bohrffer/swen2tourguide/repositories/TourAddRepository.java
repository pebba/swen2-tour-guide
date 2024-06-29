package technikum.bohrffer.swen2tourguide.repositories;

import technikum.bohrffer.swen2tourguide.models.Tour;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class TourAddRepository {
    private SessionFactory sessionFactory;

    public TourAddRepository() {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            this.sessionFactory = configuration.buildSessionFactory();
        }
        catch (Throwable ex){
            System.err.println("Initial SessionFactory creation failed." + ex);
        }
    }

    public void insertTour(Tour tour) {
        Session session = sessionFactory.openSession();
        //Session session = null;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(tour);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

}
