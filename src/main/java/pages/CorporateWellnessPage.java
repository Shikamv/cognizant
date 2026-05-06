package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.ScreenshotUtils;

public class CorporateWellnessPage extends BasePage {

    public CorporateWellnessPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "name")
    private WebElement name;

    @FindBy(id = "organizationName")
    private WebElement organization;

    @FindBy(id = "contactNumber")
    private WebElement contactNumber;

    @FindBy(id = "officialEmailId")
    private WebElement email;

    @FindBy(xpath = "//button[contains(text(),'Schedule')]")
    private WebElement scheduleButton;


    public boolean submitInvalidFormAndCapture() {

        //  Invalid inputs
        name.sendKeys("123");
        organization.sendKeys("");
        contactNumber.sendKeys("abc");
        email.sendKeys("wrong-email");

        boolean isDisabled = !scheduleButton.isEnabled();

        if (isDisabled) {

            //  Capture only INVALID elements
            if (!name.getAttribute("value").matches("[A-Za-z ]+")) {
                ScreenshotUtils.takeElementScreenshot(
                        name, "Invalid_Name");
            }

            if (organization.getAttribute("value").isEmpty()) {
                ScreenshotUtils.takeElementScreenshot(
                        organization, "Invalid_Organization");
            }

            if (!contactNumber.getAttribute("value").matches("\\d{10}")) {
                ScreenshotUtils.takeElementScreenshot(
                        contactNumber, "Invalid_ContactNumber");
            }

            if (!email.getAttribute("value")
                    .matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                ScreenshotUtils.takeElementScreenshot(
                        email, "Invalid_Email");
            }
        }

        return isDisabled;
    }
}