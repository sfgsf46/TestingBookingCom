package core.behavior;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelectDates {
    private WebDriver webDriver;

    public SelectDates(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void selectDate() {
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

        WebElement enterDepartureDate = webDriver.findElement(By.xpath("//span[@aria-label='" + departureDate + "']"));
        enterDepartureDate.click();

        WebElement enterReturnDate = webDriver.findElement(By.xpath("//span[@aria-label='" + returnDate + "']"));
        enterReturnDate.click();
        enterReturnDate.submit();
    }
}
