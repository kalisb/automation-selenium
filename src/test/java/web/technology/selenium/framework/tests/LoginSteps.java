package web.technology.selenium.framework.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import web.technology.selenium.framework.DriverBase;

@Test(groups = "cucumber")
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-html-report",
        "json:target/cucumber-report.json"},
        features = "target/test-classes/features/Login.feature",
        glue = "src/test/java/web/technology/selenium/framework/tests/LoginSteps.java",
        dryRun = false,
        monochrome = true,
        tags = "@login")
class LoginSteps extends DriverBase {

    private WebDriver driver;

    @Before
    public void setup() {
        driver = getDriver();
    }


    @Given("^I open puffin main page$")
    public void openPuffinMainPage() {
        //new MainPage().open();
        //driver.get("http://loremipsum2.fmi.uni-sofia.bg/WEBTECH/www_8ed_referats/");
    }

    @When("^I enter my valid user credentials$")
    public void loginValid() {
        //logIn("kbuhleva", "cska05051948");
        //new MainPage().search(phrase);
    }

    @Then("^verify that main menu is shown$")
    public void verifyLogedIn() {
       // System.out.println("Page title is: " + driver.getTitle());
        //assertThat(new ResultsPage().getResultTitles(), everyItem(containsString(pageName)));
    }

    @When("^I click logout$")
    public void logout() {
       // new ResultsPage().search(phrase);
       // driver.get("http://loremipsum2.fmi.uni-sofia.bg/WEBTECH/www_8ed_referats/logout.php");
    }

    @Then("^login page is shown$")
    public void returnToMainPage() {
       // System.out.println("Page title is: " + driver.getTitle());
        //assertThat(new ResultsPage().getResultTitles(), everyItem(containsString(pageName)));
    }


    @When("^I enter invalid credentials$")
    public void invalidLogin() {
      //  logIn("user", "password");
      //  driver.get("http://loremipsum2.fmi.uni-sofia.bg/WEBTECH/www_8ed_referats/logout.php");
    }

    @Then("^error message is shown$")
    public void errorOnInvalidInput() {
      /*  (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Object>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.findElement(By.className("error")).isDisplayed();}
        });

        Assert.assertEquals("Няма регистриран потребител с това име в системата", driver.findElement(By.className("error")).getText());
        //assertThat(new ResultsPage().getResultTitles(), everyItem(containsString(pageName)));*/
    }

    private void logIn(final String name, final String password) {
        WebElement nameField = driver.findElement(By.name("name"));
        WebElement passwordField = driver.findElement(By.name("pass"));
        WebElement logIn = driver.findElement(By.className("green"));

        nameField.clear();
        nameField.sendKeys(name);

        passwordField.clear();
        passwordField.sendKeys(password);

        System.out.println("Log in with: " + name);

        logIn.submit();

    }
/*
    private void logIn(final String name, final String password, WebDriver driver) {

        driver.get("http://loremipsum2.fmi.uni-sofia.bg/WEBTECH/www_8ed_referats/");

        WebElement nameField = driver.findElement(By.name("name"));
        WebElement passwordField = driver.findElement(By.name("pass"));
        WebElement logIn = driver.findElement(By.className("green"));

        nameField.clear();
        nameField.sendKeys(name);

        passwordField.clear();
        passwordField.sendKeys(password);

        System.out.println("Log in with: " + name);

        logIn.submit();

    }

    @Test
    public void validLogin() throws Exception {
        WebDriver driver = getDriver();
        logIn("kbuhleva", "cska05051948", driver);
        System.out.println("Page title is: " + driver.getTitle());
        driver.get("http://loremipsum2.fmi.uni-sofia.bg/WEBTECH/www_8ed_referats/logout.php");
    }

    @Test
    public void invalidLogin() throws Exception {
        WebDriver driver = getDriver();
        logIn("user", "password", driver);

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Object>() {
            public Boolean apply(WebDriver driverObject) {
                return driverObject.findElement(By.className("error")).isDisplayed();}
        });

        Assert.assertEquals("Няма регистриран потребител с това име в системата", driver.findElement(By.className("error")).getText());
    }
    */
}