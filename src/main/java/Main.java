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

    }
}
