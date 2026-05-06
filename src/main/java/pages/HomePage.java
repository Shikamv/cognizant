package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //  Direct navigation (Practo is URL-driven)

    public void goToHomePage() {
        driver.get("https://www.practo.com/");
    }


    public void goToDiagnostics() {
        driver.get("https://www.practo.com/tests");
    }


    public void goToCorporateWellness() {
        driver.get("https://www.practo.com/plus/corporate");
    }
}