package repository;

import lombok.NoArgsConstructor;
import model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
@NoArgsConstructor
public abstract class BaseRepository<E extends BaseEntity> {

    private EntityManager entityManager;
    private String classType;
    private String entityName;

    protected BaseRepository(EntityManager entityManager) throws ClassNotFoundException {
        this.entityManager = entityManager;
        classType = getClassType();
        entityName = getEntityName();
    }

    public void add(E entity) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    private String getClassType() {
        Type mySuperclass = getClass().getGenericSuperclass();
        Type tType = ((ParameterizedType) mySuperclass).getActualTypeArguments()[0];
        return tType.toString().split(" ")[1];
    }

    private String getEntityName() throws ClassNotFoundException {
        Field[] fields = Class.forName(classType).getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().toLowerCase().contains("name")) {
                return field.getName();
            }
        }
        return null;
    }

    public List<E> getAll() {
        return entityManager.createQuery("FROM " + classType).getResultList();
    }

    public E findById(Long id) {
        try {
            return (E) Optional.ofNullable(entityManager.find(Class.forName(classType), id)).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
