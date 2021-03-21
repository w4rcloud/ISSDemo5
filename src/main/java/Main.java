import api.ISSPositionAPI;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ISSPosition;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.ISSPositionRepository;

public class Main {

    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ISSPosition.class)
                .buildSessionFactory();

        ISSPositionRepository repository = new ISSPositionRepository(sessionFactory.createEntityManager());

        ISSPositionAPI api = new ISSPositionAPI();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        //executor service - watki w sda
        //strategia pacy z gitem - jak nazwyac branche/jak nazwyac commity

// TODO: 21.03.2021 move below to different Thread and fix timestamp to Date
        while (true) {
            ISSPosition position = new ISSPosition()
                    .setLatitude(api.getLatitude())
                    .setLongitude(api.getLongitude());

            repository.add(position);

            System.out.println(position);

        }
    }
}
