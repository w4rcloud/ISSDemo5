package repository;

import model.Astronaut;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

}
