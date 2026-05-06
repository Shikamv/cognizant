package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.CorporateWellnessPage;
import pages.HomePage;

public class CorporateWellnessTest extends BaseTest {

    @Epic("Finding Hospitals")
    @Feature("Corporate Wellness")
    @Story("Submit invalid corporate wellness details")
    @Description("Verify warning alert message is displayed when invalid details are submitted in Corporate Wellness form")
    @Test
    public void validateInvalidCorporateWellnessForm() {

        HomePage home = new HomePage(driver);
        Reporter.log("Driver Instantiated",true);
        home.goToCorporateWellness();
        Reporter.log("Navigating to Corporate Wellness Page",true);
        CorporateWellnessPage cw =
                new CorporateWellnessPage(driver);

        boolean isDisabled =
                cw.submitInvalidFormAndCapture();

        Assert.assertTrue(
                isDisabled,
                "Schedule button should be disabled for invalid input"
        );
        Reporter.log("Invalid Output Captured Successfully",true);
    }
}
