package base;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.DriverFactory;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.time.Duration;


public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = DriverFactory.initDriver();
        driver.get(ConfigReader.getProperty("url"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
