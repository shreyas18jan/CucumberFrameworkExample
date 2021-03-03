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
        System.setProperty("webdriver.chrome.driver", "/Users/apple/Desktop/CucumberFrameworkExample/src/main/resources/chromedriver");
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
}
