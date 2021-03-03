# CucumberFrameworkExample
This is just a basic Example framework which uses JUnit, Selenium with Cucumber to Open a browser, load wixsite URL, Assert the Heading and just quit the browser.

### STEPS TO CREATE YOUR OWN CUCUMBER FRAMEWORK

1.
**Create a new Project in Intellij:(Or another IDE)**
File > New > Project > Maven Project > Enter Name of your project eg: CucumberFrameworkExample > Click on Finish

2.1.
**Installation:**
Select the latest version here and Add maven dependency in the pom.xml file
cucumber-java  : https://mvnrepository.com/artifact/io.cucumber/cucumber-java
cucumber-junit : https://mvnrepository.com/artifact/io.cucumber/cucumber-junit
cucumber-core : https://mvnrepository.com/artifact/io.cucumber/cucumber-core

2.2.
For my example, I'm taking chrome driver dependency for example sceanrio.
selenium-chrome-driver : https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-chrome-driver

Download Selenium Chrome driver and Add downloaded file in the resources directory.
Here - https://chromedriver.chromium.org/downloads

3.
Create 2 folders inside java directory.
- Features and 
- StepDefinition

4.
Create a new file under Features folder with the file name - example.feature and add the Steps.
Example content is given here:
```
Feature: WixsiteExample

Scenario: Login functionality exists
  Given open the browser
  When open wixsite
  Then assert page heading
```

5.
Create new java file inside StepDefinition folder with the name - Steps.java and add definition for the mentioned steps.
Example content of the java file :
```
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
```

6.
We have feature file and we have step definition ready now we need to mp them.
We need to use @CucumberOptions to map these 2 above things.
Example content is added here:
```
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/main/java/Features",
        glue={"StepDefinition"},
        plugin = {"pretty", "html:target/cucumber-reports.html"})
public class RunTest
{

}
```

Perfect, This framework is now ready to run.

7.1.
Simple right click on the file which we created just now > and Click on Run 'RunTest' option.

7.2.
To run from command line, use the following command :
```java -cp <CLASS_PATH> cucumber.api.cli.Main features```

CLASS_PATH all the dependency jars should be mentioned.

This will execute the feature file and will create the HTML report in target/cucumber-reports.html

To automate more cases, We just have to add more files in the Features folder and corresponding Step definition in StepDefinition folder.
