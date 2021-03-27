import model.ISSPass;
import model.ISSPosition;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import repository.PassTimeAndDuration;
import service.ISSPassService;
import service.ISSPositionService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(ISSPosition.class)
                .addAnnotatedClass(ISSPass.class)
                .buildSessionFactory();
//

//


        System.out.println("=== ISS App ===\n");

        while (true) {
            int input;
            System.out.println("Get ISS Speed: 1\n" +
                    "Get ISS Pass: 2\n" +
                    "Get people in space: 3\n" +
                    "Exit: 4");

            System.out.print("Input option: ");
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input < 0 || input > 3) {
                    System.out.println("Enter number between 0-3 \n");
                    Thread.sleep(750);
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Enter number between 0-3 \n");
                continue;
            }

            switch (input) {
                case 0:
                    System.out.println("Goodbye!");
                    return;

                case 1:
                    ISSPositionService issPositionService = new ISSPositionService(sessionFactory.createEntityManager());

                case 2:
                    ISSPassService issPassService = new ISSPassService(sessionFactory.createEntityManager());
                    Double[] coordinates = getCoordinates();
                    issPassService.getPassesAndDurations(coordinates);
            }
        }
//
//        int x = 0;
//        while (x < 3) {
//            ISSPosition position = new ISSPosition()
//                    .setTimestamp(issPositionAPI.getTimestamp())
//                    .setLatitude(issPositionAPI.getLatitude())
//                    .setLongitude(issPositionAPI.getLongitude());
//
//            repository.add(position);
//            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa");
//            System.out.println(position);
//
////            Thread.sleep(1000);
//            x++;
//        }
//
//
//        // creating a ISSPass object using methods that fetch durations and pass/rise times for locations specified
//        // in the above Constructor
//        ISSPass issPass = new ISSPass(issPassAPI.getDurations(50.0,50.0), issPassAPI.getPassTimes(50.0, 50.0));
//
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

    }

    public static Double[] getCoordinates() {
        Double latitude;
        Double longitude;

        while (true) {
            System.out.print("Input latitude (between -80 and 80): ");
            try {
                latitude = new Scanner(System.in).nextDouble();
                if (latitude < -80 || latitude > 80) {
                    System.out.println("Between -80 and 80, dummy!");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Between -80 and 80, dummy!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Input longitude (between -180 and 180): ");
            try {
                longitude = new Scanner(System.in).nextDouble();
                if (longitude < -180 || longitude > 180) {
                    System.out.println("Between -180 and 180, dummy!");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Between -80 and 80, dummy!");
                continue;
            }
            break;
        }
        return new Double[]{latitude, longitude};
    }
}
