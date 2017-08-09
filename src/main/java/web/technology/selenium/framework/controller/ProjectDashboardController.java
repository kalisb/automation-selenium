package web.technology.selenium.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.common.collect.Queues;

import web.technology.selenium.framework.config.DownloadHandler;
import web.technology.selenium.framework.config.DriverMap;
import web.technology.selenium.framework.config.FileRepository;
import web.technology.selenium.framework.config.OperatingSystem;
import web.technology.selenium.framework.config.SystemArchitecture;
import web.technology.selenium.framework.config.XMLParser;
import web.technology.selenium.framework.model.Project;
import web.technology.selenium.framework.model.UFTFeature;
import web.technology.selenium.framework.service.api.ProjectService;
import web.technology.selenium.framework.service.api.ProjectTestService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by kalisb on 27.06.17.
 */
@Controller
public class ProjectDashboardController {
	
	private Queue<Task> taskQueue = new LinkedList<>();

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

    @RequestMapping(value = "configs", method = RequestMethod.GET)
    public ModelAndView configure() throws IOException {
        ModelAndView model = new ModelAndView("configs");
        return model;
    }
    
    @Transactional
    @RequestMapping(value = "os", method = RequestMethod.GET)
    public String platform() {
    	Set<OperatingSystem> osTypeList =  OperatingSystem.getCurrentOperatingSystemAsAHashSet();
    	//Calculate system architecture
    	boolean thirtyTwoBitBinaries = false;
    	boolean sixtyFourBitBinaries = false;
    	boolean armBinaries = false;
    	
    	if (SystemArchitecture.getCurrentSystemArcitecture().equals(SystemArchitecture.ARCHITECTURE_64_BIT)) {
            sixtyFourBitBinaries = true;
        } else if (SystemArchitecture.getCurrentSystemArcitecture().equals(SystemArchitecture.ARCHITECTURE_ARM)) {
            armBinaries = true;
        } else {
            thirtyTwoBitBinaries = true;
        }
    	
    	DriverMap driverRepository;
    	InputStream xmlRepositoryMap = this.getClass().getResourceAsStream("/RepositoryMap.xml");
    	XMLParser parser = new XMLParser(xmlRepositoryMap, osTypeList, null, thirtyTwoBitBinaries, sixtyFourBitBinaries);
    	File binaries = new File("driver-binaries");
		File compressed = new File("compressed_files");
		binaries.mkdir();
		compressed.mkdir();
		try {
		DownloadHandler standaloneExecutableDownloader = new DownloadHandler(
                 binaries,
                 compressed,
                 FileRepository.buildDownloadableFileRepository(parser.getAllNodesInScope(), thirtyTwoBitBinaries, sixtyFourBitBinaries, armBinaries),
                 true);
         driverRepository = standaloneExecutableDownloader.ensureStandaloneExecutableFilesExist();
		} catch (Exception e) {
			return "error";
		}
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
