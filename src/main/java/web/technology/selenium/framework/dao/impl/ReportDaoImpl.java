package web.technology.selenium.framework.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.technology.selenium.framework.dao.api.ReportDao;
import web.technology.selenium.framework.model.UFTFeature;
import web.technology.selenium.framework.model.UFTReport;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kalisb on 27.08.17.
 */
@Repository
public class ReportDaoImpl implements ReportDao {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<UFTReport> listAll() {
        return null;
    }

    @Override
    public void save(UFTReport report) {
        getSession().save(report);
    }

    @Override
    public void update(UFTReport entity) {

    }

    @Override
    public void delete(UFTReport report) {
        getSession().delete(report);
    }

    @Override
    public UFTReport findById(int id) {
        return (UFTReport) getSession().get(UFTReport.class, id);
    }

    @Override
    public List<UFTReport> listProjectReports(int projectId) {
        Criteria criteria = getSession().createCriteria(UFTFeature.class);
        return (List<UFTReport>) criteria.list().stream()
                .filter(report -> ((UFTReport) report).getProjectId() == projectId)
                .collect(Collectors.toList());
    }
}
