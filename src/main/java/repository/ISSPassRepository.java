package repository;

import model.ISSPass;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@SuppressWarnings("unchecked")

public class ISSPassRepository extends BaseRepository<ISSPass>{

    private final EntityManager entityManager;


    public ISSPassRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}