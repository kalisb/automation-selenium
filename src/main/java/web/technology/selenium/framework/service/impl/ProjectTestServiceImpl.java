package web.technology.selenium.framework.service.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.technology.selenium.framework.dao.api.FeatureDao;
import web.technology.selenium.framework.model.UFTFeature;
import web.technology.selenium.framework.model.UFTScenario;
import web.technology.selenium.framework.model.UFTStep;
import web.technology.selenium.framework.service.api.ProjectTestService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by kalisb on 01.07.17.
 */
@Service
@Transactional
public class ProjectTestServiceImpl implements ProjectTestService {

    @Autowired
    FeatureDao featureDao;

    @Override
    public List<UFTFeature> listFeatures(int projectId) {
        return featureDao.listProjectFeatures(projectId);
    }

    @Override
    public void updateFeature(UFTFeature feature) {
        featureDao.update(feature);
    }

    @Override
    public void deleteFeature(UFTFeature feature) {
        featureDao.delete(feature);
    }

    @Override
    public void saveFeature(UFTFeature feature) {
        String tempPath = System.getProperty("java.io.tmpdir") + "/features" + System.nanoTime() + "/";
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
    	String tempPath = System.getProperty("java.io.tmpdir") + "/features" + System.nanoTime() + "/";
        UFTFeature feature = featureDao.findById(id);
        File featureFile = new File(tempPath + feature.getTitle().replaceAll(" ", "_") + ".feature");
        File featureImpl = new File(tempPath + feature.getTitle().replaceAll(" ", "_") + ".groovy");
        try {
            FileUtils.touch(featureFile);
            FileUtils.touch(featureImpl);
            FileUtils.writeByteArrayToFile(featureFile, feature.getData());
            FileUtils.writeByteArrayToFile(featureImpl, feature.getData());
            FileUtils.deleteDirectory(new File(tempPath));
        } catch(IOException e) {
            e.printStackTrace();
        }
        return feature;
    }

}
