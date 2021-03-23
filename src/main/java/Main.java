import api.ISSPassAPI;
import api.ISSPositionAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ISSPass;
import model.ISSPosition;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.ISSPassRepository;
import repository.ISSPositionRepository;

import java.time.Duration;

public class Main {

    public static void main(String[] args) throws JsonProcessingException, InterruptedException {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ISSPosition.class)
                .addAnnotatedClass(ISSPass.class)
                .buildSessionFactory();

        ISSPositionRepository repository = new ISSPositionRepository(sessionFactory.createEntityManager());

        ISSPositionAPI api = new ISSPositionAPI();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //executor service - watki w sda
        //strategia pacy z gitem - jak nazwyac branche/jak nazwyac commity

//        while (true) {
//            ISSPosition position = new ISSPosition()
//                    .setTimestamp(api.getTimestamp())
//                    .setLatitude(api.getLatitude())
//                    .setLongitude(api.getLongitude());
//
//            repository.add(position);
//
//            System.out.println(position);
//
//            Thread.sleep(1000);
//        }

        // creating an ISSPassAPI object with random coordinates - TODO: they will be provided by the user after-production
        ISSPassAPI issPassAPI = new ISSPassAPI("50", "50");

        // creating a ISSPass object using methods that fetch durations and pass/rise times for locations specified
        // in the above Constructor
        ISSPass issPass = new ISSPass(issPassAPI.getDurations(), issPassAPI.getPassTimes());

        // creating ISSPassRepository object to store above ISSPass object in DB
        ISSPassRepository issPassRepository = new ISSPassRepository(sessionFactory.createEntityManager());

        // storing above object in DB
        issPassRepository.add(issPass);
// TODO: 22.03.2021 convert Long and Integer from issPassAPI.getDurations(), issPassAPI.getPassTimes() to Duration /
//  Date
        System.out.println(issPass);

        // change 'duration' from Long to Duration
        Duration duration = Duration.ofSeconds(issPass.getDuration1());

        // below method converts Duration to String. EDIT - it
        // TODO: 23.03.2021 use below method/logic as '@Overwrite toString()' OR create a class/interface that does that
        //  and would be implemented by classes from this projects

        // TODO: 23.03.2021 idea for method name: getIn_h:mm:ss_format()
        System.out.println(String.format("%d:%02d:%02d", issPass.getDuration1() / 3600,
                (issPass.getDuration1() % 3600) / 60, (issPass.getDuration1() % 60)));

        System.out.println(duration);

    }
}
