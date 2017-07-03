package web.technology.selenium.framework.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.technology.selenium.framework.dao.api.FeatureDao;
import web.technology.selenium.framework.model.UFTFeature;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kalisb on 02.07.17.
 */
@Repository
public class FeatureDaoImpl implements FeatureDao {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<UFTFeature> listAll() {
        return null;
    }

    @Override
    public void save(UFTFeature entity) {
        getSession().save(entity);
    }

    @Override
    public UFTFeature update(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public UFTFeature findById(int id) {
        return (UFTFeature) getSession().get(UFTFeature.class, id);
    }

    @Override
    public List<UFTFeature> listProjectFeatures(int projectId) {
        Criteria criteria = getSession().createCriteria(UFTFeature.class);
        return (List<UFTFeature>) criteria.list().stream()
                .filter(feature -> ((UFTFeature) feature).getProjectId() == projectId)
                .collect(Collectors.toList());
    }
}
