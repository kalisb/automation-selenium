package web.technology.selenium.framework.service.api;

import web.technology.selenium.framework.model.UFTFeature;

import java.util.List;

/**
 * Created by kalisb on 01.07.17.
 */
public interface ProjectTestService {
    public void runTests(UFTFeature feature);
    public void saveFeature(UFTFeature feature);
    public UFTFeature getFeature(int id);
    public List<UFTFeature> listFeatures(int projectId);
}
