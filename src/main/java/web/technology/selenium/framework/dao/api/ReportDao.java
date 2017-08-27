package web.technology.selenium.framework.dao.api;

import web.technology.selenium.framework.model.UFTReport;

import java.util.List;

/**
 * Created by kalisb on 27.08.17.
 */
public interface ReportDao extends AbstractDao<UFTReport> {

    public List<UFTReport> listProjectReports(int projectId);
}
