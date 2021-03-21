package demo;

import model.*;
import openNotify.OpenNotifyInformation;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.ISSRepository;

import java.io.IOException;

public class HibernateDemo {
    public static void main(String[] args) throws IOException {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Astronaut.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(PassPrediction.class)
                .addAnnotatedClass(Position.class)
                .addAnnotatedClass(Speed.class)
                .buildSessionFactory();

        ISSRepository issRepository = new ISSRepository(sessionFactory.createEntityManager());
        issRepository.saveAstronauts(OpenNotifyInformation.getPeopleInSpace());



    }
}
