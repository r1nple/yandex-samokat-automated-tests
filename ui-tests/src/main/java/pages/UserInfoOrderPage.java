package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class UserInfoOrderPage {
    WebDriver driver;

    //Локаторы полей ввода данных пользователя
    private By fieldName = By.xpath("//input[@placeholder='* Имя']"); //Локатор поля ввода "Имя"
    private By fieldSurname = By.xpath("//input[@placeholder='* Фамилия']"); //Локатор поля ввода "Фамилия"
    private By fieldAddress = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']"); //Локатор поля ввода "Адрес доставки"
    private By fieldMetroStation = By.xpath("//input[@placeholder='* Станция метро']"); //Локатор выпадающего списка станции метро
    private By fieldPhone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']"); //Локатор поля ввода "Номер телефона"
    private By buttonNext = By.xpath("//button[contains(text(),'Далее')]"); //Локатор кнопки "Далее"

    //Конструктор
    public UserInfoOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //Методы

    //Метод ввода в поле Имя
    public void enterFieldName(String name) {
        driver.findElement(fieldName).sendKeys(name);
    }

    //Метод ввода в поле Фамилия
    public void enterFieldSurname(String surname) {
        driver.findElement(fieldSurname).sendKeys(surname);
    }

    //Метод ввода в поле Адрес
    public void enterFieldAddress(String address) {
        driver.findElement(fieldAddress).sendKeys(address);
    }

    //Метод выбора станции метро
    public void enterFieldMetroStation(String metroStation) {
        driver.findElement(fieldMetroStation).click();
        driver.findElement(fieldMetroStation).sendKeys(metroStation);
        driver.findElement(fieldMetroStation).sendKeys(Keys.DOWN, Keys.ENTER);
    }

    //Метод ввода Номера телефона
    public void enterFieldPhoneNumber(String phoneNumber) {
        driver.findElement(fieldPhone).sendKeys(phoneNumber);
    }

    //Метод нажатия кнопки Далее
    public void clickButtonNext() {
        driver.findElement(buttonNext).click();
    }

    //Метод заполнения данных
    public void fillData(String name, String surname, String address, String metroStation, String phoneNumber) {
        enterFieldName(name);
        enterFieldSurname(surname);
        enterFieldAddress(address);
        enterFieldMetroStation(metroStation);
        enterFieldPhoneNumber(phoneNumber);
        clickButtonNext();
    }
}
