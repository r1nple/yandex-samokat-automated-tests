package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ErrorStatusOrderPage {
    WebDriver driver;

    public By errorStatusImage = By.cssSelector("img[alt='Not found']"); //Локатор с изображением "Такого заказа нет"

    //Конструктор
    public ErrorStatusOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод возвращение изображения с ошибкой
    public WebElement getErrorImg() {
        WebElement errorImage = driver.findElement(errorStatusImage);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(errorStatusImage));
        return errorImage;
    }
}
