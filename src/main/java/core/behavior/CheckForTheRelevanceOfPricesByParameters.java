package core.behavior;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class CheckForTheRelevanceOfPricesByParameters {
    private WebDriver webDriver;

    public CheckForTheRelevanceOfPricesByParameters(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }
    public void checkPrice() {
        WebElement rangeCheckPrice = webDriver.findElement(By.xpath("//a[@data-id=\"pri-1\"]"));
        String attributeValuePrice = rangeCheckPrice.getAttribute("data-value");
        int maxDays = 7;
        int maxPrice = Integer.parseInt(attributeValuePrice) * maxDays;

        Set<String> windowHandles = webDriver.getWindowHandles();
        for (String window : windowHandles) {
            webDriver.switchTo().window(window);
        }
        WebElement actualPriceHotel = webDriver.findElement(By.xpath("(//div[@class=\"bui-price-display__value prco-text-nowrap-helper prco-font16-helper\"])[1]"));
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
