package web.technology.selenium.framework.service.api;

import web.technology.selenium.framework.model.Project;

import java.util.List;

/**
 * Created by kalisb on 01.07.17.
 */
public interface ProjectService {

    public List<Project> listProjects();
    public void insert(Project project);
    public void delete(int projectId);
    public Project findById(int id);
}
