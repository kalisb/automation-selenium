package web.technology.selenium.framework;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import web.technology.selenium.framework.config.DriverFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kalisb on 21.05.17.
 */
@CucumberOptions(
        format = {"html:target/tests/"}
)
public class DriverBase extends AbstractTestNGCucumberTests {

    private static List<DriverFactory> webDriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverFactory;

    @BeforeSuite(alwaysRun = true)
    public static void instantiateDriverObject() {
        driverFactory = new ThreadLocal<DriverFactory>() {
            @Override
            protected DriverFactory initialValue() {
                DriverFactory driverFactory = new DriverFactory();
                webDriverThreadPool.add(driverFactory);
                return driverFactory;
            }
        };
    }

    public static WebDriver getDriver() {
        return driverFactory.get().getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public static void clearCookies() {
        getDriver().manage().deleteAllCookies();
    }

    @AfterSuite(alwaysRun = true)
    public static void closeDriverObjects() {
        for (DriverFactory driverFactory : webDriverThreadPool) {
            driverFactory.quitDriver();
        }
    }
}
