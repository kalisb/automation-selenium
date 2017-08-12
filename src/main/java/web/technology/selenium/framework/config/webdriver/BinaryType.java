package web.technology.selenium.framework.config.webdriver;

import java.util.ArrayList;

public enum BinaryType {
    CHROME(
            new ArrayList<String>() {{
                add("chromedriver.exe");
                add("chromedriver");
            }},
            "webdriver.chrome.driver"),
    PHANTOMJS(
            new ArrayList<String>() {{
                add("phantomjs.exe");
                add("phantomjs");
            }},
            "phantomjs.binary.path"),
    OPERA(
            new ArrayList<String>() {{
                add("operadriver.exe");
                add("operadriver");
            }},
            "webdriver.opera.driver"),
    FIREFOX(
            new ArrayList<String>() {{
                add("wires");
                add("wires.exe");
                add("geckodriver");
                add("geckodriver.exe");
            }},
            "webdriver.gecko.driver");

    private final ArrayList<String> binaryFilenames;
    private final String driverSystemProperty;

    BinaryType(ArrayList<String> binaryFilenames, String driverSystemProperty) {
        this.binaryFilenames = binaryFilenames;
        this.driverSystemProperty = driverSystemProperty;
    }

    public ArrayList<String> getBinaryFilenames() {
        return binaryFilenames;
    }

    public String getDriverSystemProperty() {
        return driverSystemProperty;
    }

    public String getBinaryTypeAsString() {
        return this.toString().toLowerCase();
    }
}
