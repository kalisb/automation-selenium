package web.technology.selenium.framework.service.impl;

import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.runtime.io.FileResourceLoader;
import cucumber.runtime.model.CucumberFeature;
import groovy.lang.GroovyClassLoader;
import org.apache.bcel.util.ClassLoader;
import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.control.CompilationUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.technology.selenium.framework.DriverBase;
import web.technology.selenium.framework.dao.api.FeatureDao;
import web.technology.selenium.framework.model.UFTFeature;
import web.technology.selenium.framework.model.UFTScenario;
import web.technology.selenium.framework.model.UFTStep;
import web.technology.selenium.framework.service.api.ProjectTestService;
import web.technology.selenium.framework.tests.BasicSteps;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by kalisb on 01.07.17.
 */
@Service
@Transactional
public class ProjectTestServiceImpl implements ProjectTestService {

    @Autowired
    FeatureDao featureDao;

    @Override
    public void runTests(UFTFeature feature) {
        String tempDir = "features" + System.nanoTime() + "/";
        try {
            File testFile = new File(tempDir + feature.getTitle().replace(" ", "_") + ".groovy");
            File featureFile = new File(tempDir + feature.getTitle().replace(" ", "_") + ".feature");
            FileUtils.touch(testFile);
            FileUtils.touch(featureFile);
            FileUtils.writeByteArrayToFile(testFile, feature.getDataImpl());
            FileUtils.writeByteArrayToFile(featureFile, feature.getData());
            BasicSteps.class.getClassLoader().loadClass("web.technology.selenium.framework.DriverBase");
            CompilationUnit compileUnit = new CompilationUnit();
            compileUnit.addSource(testFile);
            compileUnit.compile();
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
            method.setAccessible(true);
            method.invoke(BasicSteps.class.getClassLoader(), new Object[]{new File("./" + feature.getTitle().replace(" ", "_") + ".class").getParentFile().toURI().toURL()});
            BasicSteps.class.getClassLoader().loadClass("web.technology.selenium.framework.tests." + feature.getTitle().replace(" ", "_"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.setProperty("phantomjs.binary.path", "C:\\Program Files\\Git\\automation-selenium\\src\\test\\resources\\selenium_standalone_binaries\\windows\\phantomjs\\64bit\\phantomjs.exe");
        DriverBase.instantiateDriverObject();
        TestNGCucumberRunner runner = new TestNGCucumberRunner(BasicSteps.class);
        List<String> featurePaths = new ArrayList<>();
        featurePaths.add(new File(tempDir).getAbsolutePath());
        List<CucumberFeature> cucumberFeatures = CucumberFeature.load(new FileResourceLoader(), featurePaths, new ArrayList<>());
        for (CucumberFeature cucumberFeature : cucumberFeatures) {
            runner.runCucumber(cucumberFeature);
        }
        runner.finish();
    }

    @Override
    public List<UFTFeature> listFeatures(int projectId) {
        return featureDao.listProjectFeatures(projectId);
    }

    @Override
    public void saveFeature(UFTFeature feature) {
        String tempPath = "features" + System.nanoTime() + "/";
        File featureFile = new File(tempPath + feature.getTitle().replace(" ", "_") + ".feature");
        File featureClass = new File(tempPath + feature.getTitle().replace(" ", "_") + ".groovy");
        try {
            FileUtils.touch(featureFile);
            FileUtils.touch(featureClass);
            FileUtils.write(featureClass, "package web.technology.selenium.framework.tests\n\n", Charset.defaultCharset());
            FileUtils.write(featureClass, "import cucumber.api.java.*\n", Charset.defaultCharset(), true);
            FileUtils.write(featureClass, "import cucumber.api.java.en.*\n", Charset.defaultCharset(), true);
            FileUtils.write(featureClass, "import org.openqa.selenium.*\n", Charset.defaultCharset(), true);
            FileUtils.write(featureClass, "import org.testng.annotations.Test\n", Charset.defaultCharset(), true);
            FileUtils.write(featureClass, "import web.technology.selenium.framework.DriverBase\n\n", Charset.defaultCharset(), true);
            FileUtils.write(featureClass, "@Test(groups = \"cucumber\")\n", Charset.defaultCharset(), true);
            FileUtils.write(featureClass, "public class " + feature.getTitle().replace(" ", "_") + " extends DriverBase {\n\n", Charset.defaultCharset(), true);
            FileUtils.write(featureClass, "\tprivate WebDriver driver;\n", Charset.defaultCharset(), true);
            FileUtils.write(featureClass, "\t@Before\n", Charset.defaultCharset(), true);
            FileUtils.write(featureClass, "\tpublic void setup() {\n", Charset.defaultCharset(), true);
            FileUtils.write(featureClass, "\t\tdriver = getDriver();\n", Charset.defaultCharset(), true);
            FileUtils.write(featureClass, "\t}\n", Charset.defaultCharset(), true);
            FileUtils.write(featureFile, "Feature: " + feature.getTitle() + "\n", Charset.defaultCharset());
            FileUtils.write(featureFile, "  " +feature.getDescription() + "\n\n", Charset.defaultCharset(), true);
            for (UFTScenario scenario : feature.getScenarios()) {
                FileUtils.write(featureFile, "Scenario: " + scenario.getTitle() + "\n", Charset.defaultCharset(), true);
                for (UFTStep step: scenario.getSteps()) {
                    FileUtils.write(featureFile, "  " + step.getType() + " " + step.getTitle() + "\n", Charset.defaultCharset(), true);
                    FileUtils.write(featureClass, "\t@" + step.getType() + "(\"^" + step.getTitle() + "\\$\")\n", Charset.defaultCharset(), true);
                    FileUtils.write(featureClass, "\tpublic void " + step.getTitle().toLowerCase().replace(" ", "_") + "() {\n", Charset.defaultCharset(), true);
                    FileUtils.write(featureClass, "\t\t" + step.getCode() + "\n", Charset.defaultCharset(), true);
                    FileUtils.write(featureClass, "\t}\n", Charset.defaultCharset(), true);
                }
            }
            FileUtils.write(featureClass, "}\n", Charset.defaultCharset(), true);
            feature.setData(FileUtils.readFileToByteArray(featureFile));
            feature.setDataImpl(FileUtils.readFileToByteArray(featureClass));
            FileUtils.deleteDirectory(new File(tempPath));
            featureDao.save(feature);
        } catch (IOException e) {

        }
    }

    @Override
    public UFTFeature getFeature(int id) {
    	String tempPath = "features" + System.nanoTime() + "/";
        UFTFeature feature = featureDao.findById(id);
        File featureFile = new File(tempPath + feature.getTitle().replaceAll(" ", "_") + ".feature");
        File featureImpl = new File(tempPath + feature.getTitle().replaceAll(" ", "_") + ".groovy");
        feature.setScenarios(new ArrayList<>());
        try {
            FileUtils.touch(featureFile);
            FileUtils.touch(featureImpl);
            FileUtils.writeByteArrayToFile(featureFile, feature.getData());
            FileUtils.writeByteArrayToFile(featureImpl, feature.getData());
            List<String> lines = FileUtils.readLines(featureFile, Charset.defaultCharset());
            for (String line : lines) {
                if (line.contains("Scenario: ")) {
                    UFTScenario scenario = new UFTScenario();
                    scenario.setTitle(line.replace("Scenario: ", ""));
                    scenario.setSteps(new ArrayList<>());
                    feature.getScenarios().add(scenario);
                } else if (line.contains("When") || line.contains("Then") ||
                        line.contains("And") || line.contains("Given")) {
                    UFTScenario scenario = feature.getScenarios().get(feature.getScenarios().size() - 1);
                    UFTStep step = new UFTStep();
                    if (line.contains("When")) {
                        step.setType("When");
                    } else if (line.contains("Then")) {
                        step.setType("Then");
                    } else if (line.contains("And")) {
                        step.setType("And");
                    } else {
                        step.setType("Given");
                    }
                    step.setTitle(line.replace("  " + step.getType(), ""));
                    scenario.getSteps().add(step);
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return feature;
    }

}
