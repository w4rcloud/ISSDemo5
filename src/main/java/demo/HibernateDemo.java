package demo;

import api.PositionAPI;
import model.*;
import openNotify.OpenNotifyInformation;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.ISSRepository;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.nio.file.attribute.PosixFileAttributes;
import java.util.List;

public class HibernateDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Astronaut.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(PassPrediction.class)
                .addAnnotatedClass(Position.class)
                .addAnnotatedClass(Speed.class)
                .buildSessionFactory();

        ISSRepository issRepository = new ISSRepository(sessionFactory.createEntityManager());
        //issRepository.saveAstronauts(OpenNotifyInformation.getPeopleInSpace());
        PositionAPI api = new PositionAPI();

        int x = 0;
        while (x < 5) {
            Position position = new Position(api.getTimestamp(), api.getLongitude(), api.getLatitude());
            issRepository.add(position);
            issRepository.savePosition(position);
            System.out.println("*****************************");
            System.out.println(position);
            Thread.sleep(2000);
            x++;
        }

        issRepository.getCurrentSpeed();



    }





}
