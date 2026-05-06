package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pages.DiagnosticsPage;
import pages.HomePage;

import java.util.List;

public class DiagnosticsTest extends BaseTest {
    @Epic("Finding Hospitals")
    @Feature("Diagnostics Page")
    @Story("Fetch top cities from Diagnostics page")
    @Description("Verify that all top city names in Diagnostics page are captured and displayed")
    @Test
    public void extractTopCities() {

        // Navigate to Diagnostics page
        HomePage home = new HomePage(driver);
        Reporter.log("Driver Instantiated",true);
        home.goToDiagnostics();
        Reporter.log("Navigating to Diagnostics page",true);
        // Load Diagnostics page
        DiagnosticsPage diagnosticsPage = new DiagnosticsPage(driver);

        // Fetch top cities
        List<String> cities = diagnosticsPage.getTopCities();

        // Print for verification (demo-friendly)
        System.out.println("\nTop Cities for Diagnostics:");
        cities.forEach(System.out::println);

        //IMPORTANT: Proper assertion
        Assert.assertTrue(
                cities.size() > 0,
                "Top cities list should not be empty"
        );
        Reporter.log("Top Cities for Diagnosis has been Extracted Successfully",true);
    }
}