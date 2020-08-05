import core.behavior.CheckForTheRelevanceOfPricesByParameters;
import core.behavior.SelectDates;
import core.browser.DriverManager;
import core.browser.DriverManagerFactory;
import core.configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SearchStringOperations;

public class TestSearchStringOperations {
    DriverManager driverManager;
    WebDriver driver;
    SearchStringOperations searchStringOperations;
    SelectDates selectDates;
    CheckForTheRelevanceOfPricesByParameters checkForTheRelevanceOfPricesByParameters;

    @BeforeTest
    public void beforeTest() {
        driverManager = DriverManagerFactory.getManager(Configuration.getDriverType());
        driver = driverManager.getDriver();
    }

    @AfterTest
    public void afterTest() {
        driverManager.quitDriver();
    }

    @Test(priority = 1)
    public void openSite() {
        driver.get("http://booking.com");
        Assert.assertEquals("Booking.com | Официальный сайт | Лучшие отели и другое жилье", driver.getTitle());
    }

    @Test(priority = 2)
    public void startSearch() {
        searchStringOperations = new SearchStringOperations(driver);
        searchStringOperations.enterCity();
    }

    @Test(priority = 3)
    public void enterDate() {
        searchStringOperations.clickDate();
        selectDates = new SelectDates(driver);
        selectDates.selectDate();
    }

    @Test(priority = 4)
    public void sortByPrice() {
        searchStringOperations.clickSortMoney();
    }

    @Test(priority = 5)
    public void chooseAHotelBasedOnTheBestReviews() throws InterruptedException {
        Thread.sleep(4000);
        searchStringOperations.clickEnterSortBar();
        searchStringOperations.clickEnterByBestRating();
    }

    @Test(priority = 6)
    public void enterTheHotelPage() throws InterruptedException {
        Thread.sleep(4000);
        searchStringOperations.clickSelectHotel();
    }

    @Test(priority = 7)
    public void checkingParametersForRelevance() {
        checkForTheRelevanceOfPricesByParameters = new CheckForTheRelevanceOfPricesByParameters(driver);
        checkForTheRelevanceOfPricesByParameters.checkPrice();
    }
}
