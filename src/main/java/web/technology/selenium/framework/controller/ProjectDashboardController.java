package web.technology.selenium.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import web.technology.selenium.framework.model.Project;
import web.technology.selenium.framework.model.UFTFeature;
import web.technology.selenium.framework.service.api.ProjectService;
import web.technology.selenium.framework.service.api.ProjectTestService;

/**
 * Created by kalisb on 27.06.17.
 */
@Controller
public class ProjectDashboardController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectTestService testService;

    @RequestMapping(value = {"/", "projects"}, method = RequestMethod.GET)
    public ModelAndView show() {
        ModelAndView model = new ModelAndView("projects");
        model.addObject("projects", projectService.listProjects());
        return model;
    }

    @RequestMapping(value = "test/{id}", method = RequestMethod.GET)
    public void test(@PathVariable(value = "id") int id) {
        UFTFeature feature = testService.getFeature(id);
        testService.runTests(feature);
    }

    @RequestMapping(value = "projects/new", method = RequestMethod.GET)
    public String newProject() {
        return "projects/new";
    }

    @RequestMapping(value = "projects/new", method = RequestMethod.POST)
    public RedirectView newProject(@ModelAttribute(value = "project") Project project) {
        projectService.insert(project);
        RedirectView model = new RedirectView("/");
        model.addStaticAttribute("projects", projectService.listProjects());
        return model;
    }

    @RequestMapping(value = "projects/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editProject(@PathVariable(value = "id") int id) {
        ModelAndView model = new ModelAndView("projects/edit");
        model.addObject("project", projectService.findById(id));
        model.addObject("features", testService.listFeatures(id));
        return model;
    }

    @RequestMapping(value = "projects/edit/{id}/features", method = RequestMethod.GET)
    public ModelAndView addFeature(@PathVariable(value = "id") int id) {
        ModelAndView model = new ModelAndView("projects/features");
        model.addObject("project", projectService.findById(id));
        return model;
    }

    @RequestMapping(value = "projects/edit/{id}/features", method = RequestMethod.POST)
    public RedirectView saveProject(@PathVariable(value = "id") int id, @ModelAttribute(value = "feature") UFTFeature feature) {
        feature.setProjectId(id);
        testService.saveFeature(feature);
        RedirectView model = new RedirectView("/projects/edit/" + id);
        model.addStaticAttribute("project", projectService.findById(id));
        return model;
    }

    @RequestMapping(value = "projects/edit/{id}/features/{featureId}", method = RequestMethod.GET)
    public ModelAndView editFeature(@PathVariable(value = "id") int id,
                                    @PathVariable(value = "featureId") int featureId) {
        ModelAndView model = new ModelAndView("projects/features");
        model.addObject("project", projectService.findById(id));
        model.addObject("feature", testService.getFeature(featureId));
        return model;
    }
}
