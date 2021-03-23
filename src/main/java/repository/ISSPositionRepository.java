package repository;

import model.ISSPosition;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

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

    public List<ISSPosition> getAll() {
        return entityManager.createQuery("FROM ISSPosition").getResultList();
    }

    public ISSPosition findById(Long id) {
        return (ISSPosition) Optional.ofNullable(entityManager.find(ISSPosition.class, id)).orElse(null);
    }
}
