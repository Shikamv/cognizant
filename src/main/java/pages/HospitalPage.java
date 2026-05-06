package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class HospitalPage extends BasePage {

    public HospitalPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By locationBox =
            By.xpath("//input[@placeholder='Search location']");

    private By serviceSearch =
            By.xpath("//input[@data-input-box-id='omni-searchbox-keyword']");

    private By hospitalLinks =
            By.xpath("//a[contains(@href,'/hospital/')]");

    private By readMore =
            By.xpath("//span[contains(text(),'Read more')] | //a[contains(text(),'Read more')]");

    private By parkingText =
            By.xpath("//*[contains(text(),'Parking')]");

    private By ratingText =
            By.xpath("//span[contains(@class,'rating')]");


    private By open247Text =
            By.xpath("//*[contains(text(),'24') and contains(text(),'7')]");

    private void printHospitalDetails(
            String name,
            double rating,
            boolean open247,
            boolean hasParking) {

        System.out.println("-------------------------------------------");
        System.out.println("Hospital Name : " + name);
        System.out.println("Rating        : " + rating);
        System.out.println("Open 24 x 7   : " + open247);
        System.out.println("Parking       : " + hasParking);
    }
    // Select city (React‑safe, no stale elements)

    public void selectCity(String city) throws InterruptedException {

        WebElement location = waitForVisibility(locationBox);
        location.clear();
        location.sendKeys(city);

        // Wait for suggestion container
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[normalize-space()='Bangalore']")
        )).click();
        WebElement service = waitForVisibility(serviceSearch);
        service.clear();
        service.sendKeys("hospital");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='Hospital']")
        )).click();


//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//        Thread.sleep(5000);


        // Keyboard selection (MOST STABLE)
//        location.sendKeys(Keys.ARROW_DOWN);
//        location.sendKeys(Keys.ENTER);
    }


    //  Core logic: extract hospital links & validate

    public List<String> getValidHospitals() {

        List<WebElement> hospitals =
                waitForAllVisible(hospitalLinks);

        List<String> links = new ArrayList<>();
        for (WebElement ele : hospitals) {
            links.add(ele.getAttribute("href"));
        }

        List<String> validHospitals = new ArrayList<>();

        for (String link : links) {
            driver.get(link);

            String hospitalName = driver.getTitle();

            try {
                waitForVisibility(readMore).click();
            } catch (Exception ignored) {}

            boolean hasParking = false;
            try {
                driver.findElement(parkingText);
                hasParking = true;
            } catch (Exception ignored) {}

            boolean open247 = false;
            try {
                driver.findElement(By.xpath("//*[contains(text(),'24') and contains(text(),'7')]"));
                open247 = true;
            } catch (Exception ignored) {}

            double ratingValue = 0.0;
            try {
                String rating =
                        driver.findElement(ratingText).getText();
                ratingValue = Double.parseDouble(
                        rating.replaceAll("[^0-9.]", ""));
            } catch (Exception ignored) {}

            // PRINT VERIFICATION OUTPUT
            printHospitalDetails(
                    hospitalName,
                    ratingValue,
                    open247,
                    hasParking
            );

            // Apply final condition
            if (hasParking && open247 && (ratingValue > 3.5)) {
                validHospitals.add(hospitalName);
            }
        }

        return validHospitals;
    }
}