import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestSearchStringOperations {
    private static final int TIMEOUT = 10;
    private WebDriver webDriver;
    int maxPrice;

    @FindBy(xpath = "//input[@name='ss']")
    private WebElement searchCity;

    @FindBy(xpath = "//div[@class=\"xp__dates-inner\"]")
    private WebElement clickDateBar;

    @FindBy(xpath = "//span[@aria-label='")
    private WebElement enterDepartureDate;

    @FindBy(xpath = "//span[@aria-label='")
    private WebElement enterReturnDate;

    @FindBy(xpath = "//a[@data-id=\"pri-1\"]")
    private WebElement sortMoney;

    @FindBy(xpath = "//li[@id='sortbar_dropdown_container']")
    private WebElement enterSortBar;

    @FindBy(xpath = "//a[@data-category='bayesian_review_score']")
    private WebElement enterByBestRating;

    @FindBy(xpath = "(//A[@class=\"hotel_name_link url\"][1])")
    private WebElement selectHotel;

    @FindBy(xpath = "//a[@data-id=\"pri-1\"]")
    private WebElement rangeCheckPrice;

    @FindBy(xpath = "(//div[@class=\"bui-price-display__value prco-inline-block-maker-helper\"])[1]")
    private WebElement actualPriceHotel;

    public TestSearchStringOperations(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterCity() {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(searchCity)).sendKeys("Париж");
    }

    public void clickDate() {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(clickDateBar)).click();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMMM");
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 3);
        Date addThreeDays = calendar.getTime();
        String departureDate = dateFormat.format(addThreeDays) + " " + monthFormat.format(addThreeDays) + " " + yearFormat.format(addThreeDays);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        Date addSevenDays = calendar.getTime();
        String returnDate = dateFormat.format(addSevenDays) + " " + monthFormat.format(addSevenDays) + " " + yearFormat.format(addSevenDays);
    }

    public void clickEnterDepartureDate() {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(enterDepartureDate)).click();
    }

    public void clickEnterReturnDate() {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(enterReturnDate)).click();
    }

    public void clickSortMoney() {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(sortMoney));
        Actions actions = new Actions(webDriver);
        String attributeValueHotel = sortMoney.getAttribute("data-count");
        int byZero = Integer.parseInt(attributeValueHotel);
        if (byZero == 0) {
            System.out.println("Opps, No hotels found for your parameters!");
        } else {
            System.out.println("Excellent! Hotels with your parameters found: " + byZero + " hotels.");
        }
        actions.moveToElement(sortMoney).build().perform();
        sortMoney.click();
    }

    public void clickEnterSortBar() {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(enterSortBar)).click();
    }

    public void clickEnterByBestRating() {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(enterByBestRating)).click();
    }

    public void clickSelectHotel() {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(selectHotel)).click();
    }

    public void clickRangeCheckPrice() {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(rangeCheckPrice));
        String atributeValuePrice = rangeCheckPrice.getAttribute("data-value");
        int maxDays = 7;
        maxPrice = Integer.parseInt(atributeValuePrice) * maxDays;
    }

    public void clickActualPriceHotel() {
        (new WebDriverWait(webDriver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(actualPriceHotel));
        String actualPrice = actualPriceHotel.getText();
        String editPrice = actualPrice.replace("BYN ", "");
        int parseActualPrice = Integer.parseInt(editPrice);
        if (parseActualPrice < maxPrice) {
            System.out.println("Good! The price is suitable. Actual: " + parseActualPrice + "BYN. Max: " + maxPrice + "BYN.");
        } else {
            System.out.println("No! The price does not fit the conditions");
        }
    }
}
