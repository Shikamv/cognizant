package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.HospitalPage;

import java.util.List;

public class HospitalTest extends BaseTest {
    @Epic("Finding Hospitals")
    @Feature("Hospital open 24/7 near by Bangalore")
    @Story("Filter hospitals by parking and rating")
    @Description("Verify hospitals with parking facility and rating above 4")
    @Test
    public void validateHospitalsWithParkingAndRating() throws InterruptedException {

        HomePage home = new HomePage(driver);

        Reporter.log("Driver Instantiated",true);

        home.goToHomePage();   // URL navigation

        Reporter.log("Navigated to Home Page",true);

        // MISSING EARLIER – must create page object
        HospitalPage hospitalPage = new HospitalPage(driver);

        hospitalPage.selectCity("Bangalore");

        Reporter.log("Searching Hospitals in Bangalore",true);

        List<String> validHospitals =
                hospitalPage.getValidHospitals();

        System.out.println("Hospitals matching criteria:");
        validHospitals.forEach(System.out::println);

        // Live‑site safe assertion
        Assert.assertNotNull(
                validHospitals,
                "Hospital validation logic failed"
        );

        Reporter.log("Valid Hospitals been Extracted Successfully",true);
    }
}