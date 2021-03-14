# CucumberFrameworkExample
This is just a basic Example framework which uses JUnit, Selenium with Cucumber to Open a browser, load wixsite URL, Assert the Heading and just quit the browser.

### STEPS TO CREATE YOUR OWN CUCUMBER FRAMEWORK

1 <br>
**Create a new Project in Intellij:(Or another IDE)** <br>
File > New > Project > Maven Project > Enter Name of your project eg: CucumberFrameworkExample > Click on Finish
 <br>
 <br>
2  <br>
**Installation:**<br>
Select the latest version here and Add maven dependency in the pom.xml file<br>
cucumber-java  : https://mvnrepository.com/artifact/io.cucumber/cucumber-java <br>
cucumber-junit : https://mvnrepository.com/artifact/io.cucumber/cucumber-junit <br>
cucumber-core : https://mvnrepository.com/artifact/io.cucumber/cucumber-core <br>
<br>
For my example, I'm taking chrome driver dependency for example sceanrio.<br>
selenium-chrome-driver : https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-chrome-driver <br>
<br>
Download Selenium Chrome driver and Add downloaded file in the resources directory.<br>
Here - https://chromedriver.chromium.org/downloads
<br> <br>
3 <br>
Create 2 folders inside java directory.
- Features and <br>
- StepDefinition<br>
 <br>
 
4 <br>
Create a new file under Features folder with the file name - example.feature and add the Steps.<br>
Example content is given here: <br>

```
Feature: WixsiteExample

Scenario: Login functionality exists
  Given open the browser
  When open wixsite
  Then assert page heading
```
 <br>
5 <br>
Create new java file inside StepDefinition folder with the name - Steps.java and add definition for the mentioned steps.<br>
Example content of the java file : <br>

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

 <br>
6 <br>
We have feature file and we have step definition ready now we need to mp them.<br>
We need to use @CucumberOptions to map these 2 above things.<br>
Example content is added here:<br>

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
 
 <br>
Perfect, This framework is now ready to run.<br>
<br>
7 <br>

- Simple right click on the file which we created just now > and Click on Run 'RunTest' option.<br>

- To run from command line, use the following command :<br>

```java -cp <CLASS_PATH> cucumber.api.cli.Main features```
 <br><br>
- To use maven command , use the following command :<br>

```mvn clean install test -Ddriver.location=<DRIVER_PATH>```
 <br>
<br>
CLASS_PATH all the dependency jars should be mentioned.<br>
 <br>
This will execute the feature file and will create the HTML report in target/cucumber-reports.html
 <br> <br>
To automate more cases, We just have to add more files in the Features folder and corresponding Step definition in StepDefinition folder.

8 <br>
**If Code Coverage needs to be added here, Follow the steps:**<br>
jacoco-maven-plugin : https://mvnrepository.com/artifact/org.jacoco/jacoco-maven-plugin


Example pom.xml content for the same: <br>

```
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.6</version>
    <configuration>
        <dataFile>${project.basedir}/target/jacoco.exec</dataFile>
        <outputDirectory>${project.basedir}/target/code-coverage</outputDirectory>
        <classDumpDir>${project.basedir}/target/classes</classDumpDir>
    </configuration>
    <executions>
        <execution>
            <id>prepare-jacoco-agent</id>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>post-integration-test</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

And execute the Above execution command in Step 7.<br>
This will create a folder inside target directory called code-coverage.<br>
This folder will have all the classes used in the automation, got to the specific folder and open index.html file in browser.<br>
![ScreenShot](/src/test/resources/codeCoverageScreenShot.png)