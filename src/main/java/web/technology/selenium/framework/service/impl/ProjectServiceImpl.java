package web.technology.selenium.framework.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.technology.selenium.framework.dao.api.ProjectDao;
import web.technology.selenium.framework.model.Project;
import web.technology.selenium.framework.service.api.ProjectService;

import java.util.List;

/**
 * Created by kalisb on 01.07.17.
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Override
    public List<Project> listProjects() {
        return projectDao.listAll();
    }

    @Override
    public void insert(Project project) {

        projectDao.save(project);
    }

    @Override
    public void delete(int projectId) {

    }

    @Override
    public Project findById(int id) {
        return projectDao.findById(id);
    }
}
