package web.technology.selenium.framework.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.technology.selenium.framework.dao.api.ProjectDao;
import web.technology.selenium.framework.model.Project;

import java.util.List;

/**
 * Created by kalisb on 01.07.17.
 */
@Repository
public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Project> listAll() {
        Criteria criteria = getSession().createCriteria(Project.class);
        return (List<Project>) criteria.list();
    }

    @Override
    public void save(Project project) {
        getSession().saveOrUpdate(project);
    }

    @Override
    public void update(Project project) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Project findById(int id) {
        return (Project) getSession().get(Project.class, id);
    }
}
