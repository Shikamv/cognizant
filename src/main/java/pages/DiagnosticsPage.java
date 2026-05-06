package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticsPage extends BasePage {

    public DiagnosticsPage(WebDriver driver) {
        super(driver);
    }

    private By cityNames =
            By.xpath("//div[text()='TOP CITIES']" +
                    "/following-sibling::ul//div[contains(@class,'o-f-color--primary')]");

    public List<String> getTopCities() {

        // Trigger lazy loading
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollBy(0, 800);");

        List<String> cities = new ArrayList<>();

        // Retry loop to handle React re-render
        for (int attempt = 1; attempt <= 3; attempt++) {
            try {
                List<WebElement> elements = wait.until(
                        ExpectedConditions.presenceOfAllElementsLocatedBy(cityNames)
                );

                cities.clear();
                for (WebElement el : elements) {
                    cities.add(el.getText().trim());
                }

                return cities; // success

            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying due to stale elements... Attempt " + attempt);
            }
        }

        throw new RuntimeException("Failed to fetch Top Cities due to DOM refresh");
    }
}