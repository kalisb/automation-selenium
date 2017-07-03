package web.technology.selenium.framework.dao.api;

import java.util.List;

/**
 * Created by kalisb on 02.07.17.
 */
public interface AbstractDao<T> {

    public List<T> listAll();
    public void save(T entity);
    public T update(int id);
    public void delete(int id);
    public T findById(int id);

}
