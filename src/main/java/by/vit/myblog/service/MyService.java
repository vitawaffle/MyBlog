package by.vit.myblog.service;

/**
 * Application service interface.
 *
 * @param <T> - the type of entity with which the service works.
 * @param <ID> - entity identifier type.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
public interface MyService<T, ID> {

    /**
     * This method gets entity by id.
     *
     * @param id - entity id.
     * @return entity or null.
     */
    T getById(ID id);

    /**
     * This method saves entity.
     *
     * @param t - entity to save.
     * @return id of saved entity.
     */
    ID save(T t);

    /**
     * This method deletes entity by id.
     *
     * @param id - entity id.
     */
    void deleteById(ID id);

}
