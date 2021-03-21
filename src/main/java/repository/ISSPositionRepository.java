package repository;

import model.ISSPosition;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ISSPositionRepository {

    private final EntityManager entityManager;

    public ISSPositionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ISSPosition add(ISSPosition issPosition) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(issPosition);
            transaction.commit();
            return issPosition;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
