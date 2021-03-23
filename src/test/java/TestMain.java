import api.ISSPassAPI;
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

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class TestMain {

    private static SessionFactory sessionFactory;
    private static Session session = null;
    private static ISSPositionRepository issPositionRepository;
    private static ISSPositionAPI issPositionAPI;
    private static ISSPassAPI issPassAPI;

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

    //assert fetched pass times within range: 22.03.2021 - 22.03.2023
    @Test
    public void testISSPassAPI_fetchingPassTimes() {
        issPassAPI = new ISSPassAPI();

        Long[] passTimes = issPassAPI.getPassTimes(52.23, 21.01);
        Arrays.stream(passTimes).forEach(pT -> assertTrue(1616407800 <= pT && pT <= 1679479800));
    }

    //assert fetched durations within range: 1ms - 1s
    @Test
    public void testISSPassAPI_fetchingDuration() {
        issPassAPI = new ISSPassAPI();
        Integer[] passTimes = issPassAPI.getDurations(52.23, 21.01);
        Arrays.stream(passTimes).forEach(pT -> assertTrue(1 <= pT && pT <= 1000));
    }
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


    @AfterAll
    public static void after() {
        session.close();
        sessionFactory.close();
    }

}
