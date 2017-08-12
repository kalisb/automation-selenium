package web.technology.selenium.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import web.technology.selenium.framework.model.task.DriverTask;
import web.technology.selenium.framework.model.Project;
import web.technology.selenium.framework.model.task.Task;
import web.technology.selenium.framework.model.UFTFeature;
import web.technology.selenium.framework.service.api.ProjectService;
import web.technology.selenium.framework.service.api.ProjectTestService;
import web.technology.selenium.framework.service.api.SchedulerService;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;

/**
 * Created by kalisb on 27.06.17.
 */
@Controller
public class ProjectDashboardController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectTestService testService;
    
	@Autowired
	SchedulerService scheduler;

    @RequestMapping(value = {"/", "projects"}, method = RequestMethod.GET)
    public ModelAndView show() {
        ModelAndView model = new ModelAndView("projects");
        model.addObject("projects", projectService.listProjects());
        model.addObject("tasks", scheduler.getAllTasks());
        return model;
    }
    
    @ResponseBody
    @RequestMapping(value = "tasks", method = RequestMethod.GET, produces = "application/json")
    public Collection<Task> tasks() {
        return scheduler.getAllTasks();
    }

    @RequestMapping(value = "configs", method = RequestMethod.GET)
    public ModelAndView configure() {
        ModelAndView model = new ModelAndView("configs");
        model.addObject("tasks", scheduler.getAllTasks());
        return model;
    }
    
    @Transactional
    @RequestMapping(value = "os/{browser}", method = RequestMethod.GET)
    public String platform(@PathVariable(value = "browser") String browser) {
    	scheduler.add(new DriverTask(browser));
        return "configs";
    }

    @Transactional
	@RequestMapping(value = "test/{id}", method = RequestMethod.GET)
    public String test(@PathVariable(value = "id") int id) throws IOException {
        UFTFeature feature = testService.getFeature(id);
        return ProjectTestService.runTests(feature);
    }

    @RequestMapping(value = "projects/new", method = RequestMethod.GET)
    public ModelAndView newProject() {
    	ModelAndView model = new ModelAndView();
    	model.addObject("project", new Project());
        return model;
    }

    @RequestMapping(value = "projects/new", method = RequestMethod.POST)
    public String newProject(@Valid Project project, BindingResult result) {
    	if (result.hasErrors()) {
            return "projects/new";
    	}
        projectService.insert(project);
        return "redirect:/projects";
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

    @Transactional
    @RequestMapping(value = "projects/edit/{id}/features", method = RequestMethod.POST)
    public RedirectView saveProject(@PathVariable(value = "id") int id, @ModelAttribute(value = "feature") UFTFeature feature) {
        feature.setProjectId(id);
        testService.saveFeature(feature);
        RedirectView model = new RedirectView("/projects/edit/" + id);
        model.addStaticAttribute("project", projectService.findById(id));
        return model;
    }

    @Transactional
    @RequestMapping(value = "features/update", method = RequestMethod.POST)
    public String saveProject(@ModelAttribute(value = "feature") UFTFeature feature) {
        feature.setDataImpl(feature.getDataImplStr().getBytes(Charset.defaultCharset()));
        feature.setData(feature.getDataStr().getBytes(Charset.defaultCharset()));
        testService.updateFeature(feature);
        return "redirect:/projects/edit/" + feature.getProjectId();
    }

    @RequestMapping(value = "projects/edit/{id}/features/{featureId}", method = RequestMethod.GET)
    public ModelAndView editFeature(@PathVariable(value = "id") int id,
                                    @PathVariable(value = "featureId") int featureId) {
        ModelAndView model = new ModelAndView("projects/update");
        model.addObject("project", projectService.findById(id));
        model.addObject("feature", testService.getFeature(featureId));
        return model;
    }
}
