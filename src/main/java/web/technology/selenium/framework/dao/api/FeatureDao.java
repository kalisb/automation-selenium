package web.technology.selenium.framework.dao.api;

import web.technology.selenium.framework.model.UFTFeature;

import java.util.List;

/**
 * Created by kalisb on 02.07.17.
 */
public interface FeatureDao extends AbstractDao<UFTFeature> {

    public List<UFTFeature> listProjectFeatures(int projectId);
}
