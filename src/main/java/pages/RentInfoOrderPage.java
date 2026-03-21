package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class RentInfoOrderPage {
    WebDriver driver;

    //Локаторы полей выбора данных аренды
    private By deliveryTime = By.xpath("//input[@placeholder='* Когда привезти самокат']"); //Локатор выбора даты
    private By fieldRentTime = By.className("Dropdown-placeholder"); //Локатор нажатия на поле срока аренды
    private By choiceRentTime = By.xpath("//div[@role='option'][contains(text(),'трое суток')]"); //Локатор выбора срока аренды из списка
    private By blackBoxScooter = By.id("black"); //Локатор черного цвета
    private By greyBoxScooter = By.id("grey"); //Локатор серого цвета
    private By commentCourier = By.xpath("//input[@placeholder='Комментарий для курьера']"); //Локатор поля "Комментарий"
    private By buttonBack = By.xpath("//button[contains(text(),'Назад')]"); //Локатор кнопки "Назад"
    private By buttonOrder = By.xpath("//div[@class='Order_Buttons__1xGrp']//button[contains(text(), 'Заказать')]"); //Локатор кнопки "Заказать"
    private By buttonConfirm = By.xpath("//button[contains(text(),'Да')]"); //Локатор кнопки "Да" в модальном окне
    private By buttonRefuse = By.xpath("//button[contains(text(),'Нет')]"); //Локатор кнопки "Нет" в модальном окне
    private By windowСonfirmationNewOrder = By.xpath("//button[contains(text(),'Посмотреть статус')]"); //Локатор блока с текстом "Заказ оформлен"

    //Конструктор
    public RentInfoOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Методы

    //Метод заполнить поле дата доставки
    public void fillDeliveryTime(String date) {
        driver.findElement(deliveryTime).sendKeys(date);
        driver.findElement(deliveryTime).sendKeys(Keys.ENTER);
    }

    //Метод заполнить поле срок аренды
    public void fillFieldRentTime() {
        driver.findElement(fieldRentTime).click();
        driver.findElement(choiceRentTime).click();
    }

    //Метод выбора черного самоката
    public void choseBlackBoxScooter() {
        driver.findElement(blackBoxScooter).click();
    }

    //Метод выбора серого самоката
    public void choseGreyBoxScooter() {
        driver.findElement(greyBoxScooter).click();
    }

    //Метод оставить комментарий для курьера
    public void fillCommentCourier(String comment) {
        driver.findElement(commentCourier).sendKeys(comment);
    }

    //Метод нажатия кнопки "Назад"
    public void clickButtonBack() {
        driver.findElement(buttonBack).click();
    }

    //Метод нажатия кнопки "Заказать"
    public void clickButtonOrder() {
        driver.findElement(buttonOrder).click();
    }

    //Метод нажатия кнопки "Да" в модальном окне
    public void clickButtonConfirm() {
        driver.findElement(buttonConfirm).click();
    }

    //Метод нажатия кнопки "Нет" в модальном окне
    public void clickButtonRefuse() {
        driver.findElement(buttonRefuse).click();
    }

    //Метод отображения текста "Заказ оформлен" в модальном окне
    public boolean isWindowCheckStatusDisplayed() {
        return driver.findElement(windowСonfirmationNewOrder).isDisplayed();
    }

    //Метод заполнения данных c черным самокатом
    public void fillDataRentBlackBox(String date, String comment) {
        fillDeliveryTime(date);
        fillFieldRentTime();
        choseBlackBoxScooter();
        fillCommentCourier(comment);
    }

    //Метод заполнения данных c серым самокатом
    public void fillDataRentGreyBox(String date, String comment) {
        fillDeliveryTime(date);
        fillFieldRentTime();
        choseGreyBoxScooter();
        fillCommentCourier(comment);
    }
}
