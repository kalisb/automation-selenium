package web.technology.selenium.framework.service.api;

import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.runtime.io.FileResourceLoader;
import org.apache.commons.io.FileUtils;
import org.codehaus.groovy.control.CompilationUnit;
import web.technology.selenium.framework.DriverBase;
import web.technology.selenium.framework.model.UFTFeature;
import web.technology.selenium.framework.tests.BasicSteps;
import cucumber.runtime.model.CucumberFeature;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kalisb on 01.07.17.
 */
public interface ProjectTestService {
    public  static String runTests(UFTFeature feature) throws IOException {
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
        PrintStream out = System.out;
        File output = new File("output.txt");
        try {
            FileUtils.touch(output);
            System.setOut(new PrintStream(new FileOutputStream(output), true));
            //System.setErr(new PrintStream(new FileOutputStream(output), true));
        } catch (Exception e) {

        }
        DriverBase.instantiateDriverObject();
        TestNGCucumberRunner runner = new TestNGCucumberRunner(BasicSteps.class);
        List<String> featurePaths = new ArrayList<>();
        featurePaths.add(new File(tempDir).getAbsolutePath());
        List<CucumberFeature> cucumberFeatures = CucumberFeature.load(new FileResourceLoader(), featurePaths, new ArrayList<>());
        for (CucumberFeature cucumberFeature : cucumberFeatures) {
            runner.runCucumber(cucumberFeature);
        }
        runner.finish();
        System.setOut(out);
        try {
        	return FileUtils.readFileToString(output, Charset.defaultCharset());
        } finally {
            FileUtils.forceDelete(new File("web"));
            FileUtils.forceDeleteOnExit(new File(tempDir));
		}
    } 
    public void saveFeature(UFTFeature feature);
    public UFTFeature getFeature(int id);
    public List<UFTFeature> listFeatures(int projectId);
    void updateFeature(UFTFeature feature);
}
