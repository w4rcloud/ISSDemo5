package service;

import api.ISSPassAPI;
import repository.ISSPassRepository;
import repository.PassTimeAndDuration;

import javax.persistence.EntityManager;

public class ISSPassService {

    private EntityManager entityManager;
    private ISSPassRepository issPassRepository;
    private ISSPassAPI issPassAPI = new ISSPassAPI();

    public ISSPassService(EntityManager entityManager) {
        this.entityManager = entityManager;
        issPassRepository = new ISSPassRepository(entityManager);
    }

    public void getPassAndStore(Double[] coordinates) {
        issPassAPI.getPassesAndDurations(coordinates[0], coordinates[1]);
    }

    public PassTimeAndDuration getPassesAndDurations(Double[] coordinates){
        return issPassAPI.getPassesAndDurations(coordinates[0], coordinates[1]);
    }
}
