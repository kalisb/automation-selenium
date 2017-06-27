package web.technology.selenium.framework.core.view;

import org.openqa.selenium.WebDriver;
import web.technology.selenium.framework.DriverBase;

/**
 * Created by kalisb on 28.05.17.
 */
public interface View {

    default WebDriver browser() {
        return DriverBase.getDriver();
    }
}
