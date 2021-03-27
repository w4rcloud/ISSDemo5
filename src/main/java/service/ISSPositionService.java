package service;

import api.ISSPositionAPI;
import repository.ISSPositionRepository;

import javax.persistence.EntityManager;

public class ISSPositionService {

    private EntityManager entityManager;
    private ISSPositionRepository issPositionRepository;
    private ISSPositionAPI issPositionAPI = new ISSPositionAPI();

    public ISSPositionService(EntityManager entityManager) {
        this.entityManager = entityManager;
        try {
            issPositionRepository = new ISSPositionRepository(entityManager);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getVelocity() {
        issp
    }
}
