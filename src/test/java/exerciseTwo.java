//1. Go To http://www.royalcaribbean.com/
//2. In the 'Find a Cruise' field, then Destinations field, select Transatlantic.
//3. In the Departure Port field, click Amsterdam
//4. Search for a Cruise and indentify it. E.g. 16 nights Iceland & Greenland Cruise


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class exerciseTwo {
    private WebDriver driver;

    @Before

    public void setUp(){
        EdgeOptions options= new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--headless");
        driver = new EdgeDriver(options);  // EdgeDriver initialization
        driver.manage().window().maximize();
    }

    @Test
    public void searchForACruise() throws InterruptedException {

        // open the browser on royalCaraibbean webpage
        driver.get("https://www.royalcaribbean.com"); // open royalCaraibbean

        //seek for the Accept All Cookies button and click on it
        try {
        WebElement acceptAllCookies = driver.findElement(By.id("onetrust-accept-btn-handler"));
        acceptAllCookies.click();
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Butonul Cookie Accept All nu a fost găsit.");
        }

        //we look for FIND A CRUISE link from the main menu
        WebElement findACruiseLink = driver.findElement(By.id("rciHeaderMenuItem-1"));
        findACruiseLink.click();

        //wait for the menu to load and see the Destination dropdown-menu
        WebDriverWait waitForResults = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> menuDropDownButton = driver.findElements(By.xpath("//span[@class='MuiButton-endIcon MuiButton-iconSizeMedium css-pt151d']"));
        WebElement destinationsButton = menuDropDownButton.get(3);
        waitForResults.until(ExpectedConditions.elementToBeClickable(destinationsButton));

        // click on Destinations dropdown-menu
        WebElement destinationDropDownMenu = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-disableElevation MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-disableElevation filter-dropdown-destination filter-button css-pjauuf']"));
        destinationDropDownMenu.click();

        /* wait for the page to load before you click on TransAtlantic option */
        waitForResults.until(ExpectedConditions.visibilityOfElementLocated(By.id("destination-card-label-T.ATL")));

        // click on Transatlantic from the new menu window that appears
        WebElement transatlanticOption = driver.findElement(By.id("destination-card-label-T.ATL"));
        transatlanticOption.click();

        //click on Destinations See results button
        WebElement destinationSeeResults = driver.findElement(By.xpath("//section[@class='components__FooterWrap-sc-zsi07w-6 hoUUnj']"));
        destinationSeeResults.click();

        // click on DeparturePort dropdown-menu
        WebElement departurePort = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-disableElevation MuiButton-root MuiButton-text MuiButton-textPrimary MuiButton-sizeMedium MuiButton-textSizeMedium MuiButton-disableElevation filter-dropdown-departurePort filter-button css-pjauuf']"));
        departurePort.click();

        // click on departure from Europe - Amsterdam, Netherlands option
        WebElement departureFromAmsterdam = driver.findElement(By.id("departure-port-label-AMS"));
        departureFromAmsterdam.click();

        //click on DeparturePort See results button
        WebElement departurePortSeeResults = driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeLarge MuiButton-containedSizeLarge MuiButton-disableElevation MuiButton-root MuiButton-contained MuiButton-containedPrimary MuiButton-sizeLarge MuiButton-containedSizeLarge MuiButton-disableElevation components__ShowResults-sc-zsi07w-7 hlBRAJ css-sdj567']"));
        departurePortSeeResults.click();

       //wait to scroll down
        waitForResults.until(ExpectedConditions.visibilityOfElementLocated(By.id("card-view-dates-button-SR22AMS-349315616")));
       // scroll down
        WebElement scroolDownToDeal = driver.findElement(By.id("card-view-dates-button-SR22AMS-349315616"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", scroolDownToDeal);

        // find the 16 nights Iceland & Greenland Cruise deal
        WebElement findDealNumberOfNights = driver.findElement(By.xpath("//div[@id='card-title-JW16AMS-3205746230']//h3[@class='RefinedCruiseCardstyles__RefinedCruiseCardTotalNights-sc-l09mih-3 kfUXwb']"));
        WebElement findDeal = driver.findElement(By.xpath("//div[@id='card-title-JW16AMS-3205746230']//h4[@class='RefinedCruiseCardstyles__RefinedCruiseCardName-sc-l09mih-4 bJlgBm']"));

        String dealReturned = findDealNumberOfNights.getText()+findDeal.getText();

        //AssertEquals
        try {
            assertEquals(dealReturned, "16 Nights" + "Iceland & Greenland Cruise");
            System.out.println("searchForACruise Test - PASSED");
        } catch (AssertionError e) {
            System.out.println("searchForACruise Test - FAILED");
        }

    }

    }
