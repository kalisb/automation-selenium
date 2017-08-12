package web.technology.selenium.framework.dao.api;

import java.util.List;

/**
 * Created by kalisb on 02.07.17.
 */
public interface AbstractDao<T> {

    public List<T> listAll();
    public void save(T entity);
    public void update(T entity);
    public void delete(T entity);
    public T findById(int id);

}
