package StepDefinition;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Steps {
    WebDriver driver = null;

    @Given("^open the browser$")
    public void openBrowser() {
        String driverLocation = System.getProperty("driver.location", "/Users/shrejain/AndroidStudioProjects/GIT_HUB/CucumberFrameworkExample/src/test/resources/chromedriver");
        System.setProperty("webdriver.chrome.driver", driverLocation);

        //System.out.println("Location = " + driverLocation);

        driver= new ChromeDriver();
        driver.manage().window().maximize();
    }

    @When("^open wixsite$")
    public void goToFacebook() {
        driver.navigate().to("https://shreyas18jan.wixsite.com/thecommonsite");
    }

    @Then("^assert page heading$")
    public void assertHeading() {
        Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"comp-ipwajna6\"]/h1/span/a")).getText(),"The Common guy");
        driver.close();
    }

    public void addingDummyMethod() {
        System.out.println("Simple Method for Code Coverage example");
    }
}
