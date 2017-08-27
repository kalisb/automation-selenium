package web.technology.selenium.framework;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import web.technology.selenium.framework.config.test.DriverFactory;

import java.util.logging.Logger;

/**
 * Created by kalisb on 21.05.17.
 */
@CucumberOptions(
		format = {"html:target/tests/"}
		)
public class DriverBase extends AbstractTestNGCucumberTests {
	private static DriverFactory driverFactory;
	private static Logger LOG = Logger.getLogger(DriverBase.class.getName());

	public static WebDriver getDriver() {
		driverFactory = new DriverFactory();
		return driverFactory.getDriver();
	}

	@AfterMethod(alwaysRun = true)
	public static void clearCookies() {
		getDriver().manage().deleteAllCookies();
	}

	@AfterSuite(alwaysRun = true)
	public static void closeDriverObjects() {
		driverFactory.quitDriver();

	}
}
