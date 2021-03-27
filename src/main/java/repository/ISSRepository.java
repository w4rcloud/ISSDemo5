package repository;

import model.Astronaut;
import model.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.IOException;
import java.util.List;

public class ISSRepository {
    private final EntityManager entityManager;

    public ISSRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void saveAstronauts(List<Astronaut> astronautsList){
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            for(Astronaut astronaut : astronautsList){
                entityManager.persist(astronaut);
            }
            transaction.commit();

        }catch (Exception e){
            if(transaction !=null){
                transaction.rollback();
            }
        }
    }

    public void savePosition(Position position){
        EntityTransaction transaction = null;
        try{
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(position);
            transaction.commit();

        }catch (Exception e){
            if(transaction !=null){
                transaction.rollback();
            }
        }
    }


    public double getCurrentSpeed() throws IOException {
        double speed = 0;
        double lat1 = 0;
        double lat2 = 0;
        double lng1 = 0;
        double lng2 = 0;
        long t1 = 0;
        long t2 = 0;
        List<Position> Positions = getLast2Positions();
        if (Positions.size() < 2) {
            System.out.println("Blad pobierania danych dot. pozycji");
            return speed;
        }
        int index = 0;
        for (Position P: Positions) {
            if (index == 0) {
                lat2 = P.getLatitude();
                lng2 = P.getLongitude();
                t2 = P.getTime();
            } else if (index == 1) {
                lat1 = P.getLatitude();
                lng1 = P.getLongitude();
                t1 = P.getTime();
            }
            index++;
        }
        speed = distanceFromInMeters(lat1,lng1,lat2,lng2)/(t2-t1);
        System.out.println("Prędkość ISS [m/s]: " + speed);
        System.out.println("Prędkość ISS [km/h]: " + speed * 3.6);
        return speed;
    }


// co gdy jest null (brak danych w tablicy)?
    public List<Position> getLast2Positions() {
        String query = "FROM Position ORDER BY id DESC";
        return entityManager.createQuery(query, Position.class)
                .setMaxResults(2).getResultList();
    }

    public double distanceFromInMeters(double lat1, double lng1, double lat2, double lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double dist = earthRadius * c;
        return dist;
    }

    public Position add(Position position) {
        EntityTransaction transaction = null;

        try {
            transaction = this.entityManager.getTransaction();
            transaction.begin();
            this.entityManager.persist(position);
            transaction.commit();
            return position;
        } catch (Exception var4) {
            if (transaction != null) {
                transaction.rollback();
            }

            var4.printStackTrace();
            return null;
        }
    }


}
