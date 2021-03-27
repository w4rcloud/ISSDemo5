package repository;

import model.ISSPosition;

import javax.persistence.EntityManager;

public class ISSPositionRepository extends BaseRepository<ISSPosition> {

    private final EntityManager entityManager;

    public ISSPositionRepository(EntityManager entityManager) throws ClassNotFoundException {
        super(entityManager);
        this.entityManager = entityManager;
    }
}
