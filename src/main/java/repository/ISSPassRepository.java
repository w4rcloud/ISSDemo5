package repository;

import model.ISSPass;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ISSPassRepository {

    private final EntityManager entityManager;

    public ISSPassRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ISSPass add(ISSPass issPass) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(issPass);
            transaction.commit();
            return issPass;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
