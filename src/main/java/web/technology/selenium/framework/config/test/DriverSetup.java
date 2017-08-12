package web.technology.selenium.framework.config.test;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by kalisb on 21.05.17.
 */
public interface DriverSetup {

    WebDriver getWebDriverObject(DesiredCapabilities desiredCapabilities);

    DesiredCapabilities getDesiredCapabilities(Proxy proxySettings);

}
