import api.ISSPositionAPI;
import model.ISSPass;
import model.ISSPosition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.ISSPositionRepository;

import static org.junit.jupiter.api.Assertions.*;

public class TestMain {

    private static SessionFactory sessionFactory;
    private static Session session = null;
    private static ISSPositionRepository issPositionRepository;
    private static ISSPositionAPI issPositionAPI;

    // creating ISSPosition reference for use in below tests
    private static ISSPosition issPosition = null;

    @BeforeAll
    public static void before() {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ISSPosition.class)
                .addAnnotatedClass(ISSPass.class)
                .buildSessionFactory();

        session = sessionFactory.openSession();

        issPositionRepository = new ISSPositionRepository(sessionFactory.createEntityManager());

        issPositionAPI = new ISSPositionAPI();

        // method call to initialize variable holding entire API as String (private String fetchedAPI;)
        issPositionAPI.getLatitude();


    }

    //assert fetched API String contains below substrings
    @Test
    public void testISSPositionAPI_fetchingAPI() {

        assertTrue(issPositionAPI.getFetchedAPI().contains("message"));
        assertTrue(issPositionAPI.getFetchedAPI().contains("success"));
        assertTrue(issPositionAPI.getFetchedAPI().contains("timestamp"));
        assertTrue(issPositionAPI.getFetchedAPI().contains("iss_position"));
        assertTrue(issPositionAPI.getFetchedAPI().contains("longitude"));
        assertTrue(issPositionAPI.getFetchedAPI().contains("latitude"));
    }

    //assert fetched timestamp within range: 22.03.2021 - 22.03.2023
    @Test
    public void testISSPositionAPI_fetchingTimestamp() {
        assertTrue(1616407800 <= issPositionAPI.getTimestamp() && issPositionAPI.getTimestamp() <= 1679479800);
    }

    //assert fetched longitude within range: (-180) - (180)
    @Test
    public void testISSPositionAPI_fetchingLongitude() {
        assertTrue(-180 <= issPositionAPI.getLongitude() && issPositionAPI.getLongitude() <= 180);
    }

    //assert fetched longitude within range: (-80) - (80)
    @Test
    public void testISSPositionAPI_fetchingLatitude() {
        assertTrue(-80 <= issPositionAPI.getLatitude() && issPositionAPI.getLatitude() <= 80);
    }


    @Test
    public void testISSPositionObjectCreation() {
        issPosition = new ISSPosition()
                .setTimestamp(issPositionAPI.getTimestamp())
                .setLatitude(issPositionAPI.getLatitude())
                .setLongitude(issPositionAPI.getLongitude());
        assertNotNull(issPosition);
    }

    // 'z' added at the beginning of method name to process the below test after all others (Using Alphanumeric
    // Order) as ISSPosition object needs to be initialized before it can be added to DB below
    @Test
    public void zTestISSPositionRepositoryAdding() {
        assertEquals(0, issPositionRepository.getAll().size());
        issPositionRepository.add(issPosition);
        assertEquals(1, issPositionRepository.getAll().size());
        assertEquals(issPosition, issPositionRepository.findById(1L));
    }


//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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

//    @Test
//    public void testToBeImplemented() {
//        // creating an ISSPassAPI object with random coordinates - TODO: they will be provided by the user after-production
//        ISSPassAPI issPassAPI = new ISSPassAPI("50", "50");
//
//        // creating a ISSPass object using methods that fetch durations and pass/rise times for locations specified
//        // in the above Constructor
//        ISSPass issPass = new ISSPass(issPassAPI.getDurations(), issPassAPI.getPassTimes());
//
//        // creating ISSPassRepository object to store above ISSPass object in DB
//        ISSPassRepository issPassRepository = new ISSPassRepository(sessionFactory.createEntityManager());
//
//        // storing above object in DB
//        issPassRepository.add(issPass);
//        // TODO: 22.03.2021 convert Long and Integer from issPassAPI.getDurations(), issPassAPI.getPassTimes() to Duration /
//        //  Date
//        System.out.println(issPass);
//
//        // change 'duration' from Long to Duration
//        Duration duration = Duration.ofSeconds(issPass.getDuration1());
//
//        // below method converts Duration to String. EDIT - it
//        // TODO: 23.03.2021 use below method/logic as '@Overwrite toString()' OR create a class/interface that does that
//        //  and would be implemented by classes from this projects
//
//        // TODO: 23.03.2021 idea for method name: getIn_h:mm:ss_format()
//        System.out.println(String.format("%d:%02d:%02d", issPass.getDuration1() / 3600,
//                (issPass.getDuration1() % 3600) / 60, (issPass.getDuration1() % 60)));
//
//        System.out.println(duration);
//    }

    @AfterAll
    public static void after() {
        session.close();
        sessionFactory.close();
    }

}
