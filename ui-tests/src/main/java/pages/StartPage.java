package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StartPage {
    WebDriver driver;

    //Локаторы главной страницы
    private By cookieButton = By.id("rcc-confirm-button"); // Локатор кнопки "Куки"
    private By upOrderButton = By.className("Button_Button__ra12g"); // Локатор верхней кнопки "Заказать"
    private By downOrderButton = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button[contains(text(), 'Заказать')]"); //Локатор нижней кнопки "Заказать"
    private By orderStatusButton = By.className("Header_Link__1TAG7"); // Локатор "Статус заказа"
    private By buttonGo = By.xpath("//button[text()='Go!']"); //Локатор кнопки "Go!"
    private By orderNumber = By.xpath("//input[@placeholder='Введите номер заказа']"); //Локатор поля ввода номера заказа
    private By yandexButton = By.xpath("//img[@alt='Yandex']"); //Локатор кнопки "Яндекс"
    private By scooterButton = By.xpath("//img[@alt='Scooter']"); //Локатор кнопки "Самокат"
    private By blockDownOrderButton = By.className("Home_RoadMap__2tal_"); //Локатор блока с нижней кнопкой "Заказать"
    private By questionsHeader = By.className("Home_FourPart__1uthg"); //Локатор нижней части главной страницы с вопросами

    //Локаторы кнопок на вопросы в разделе "Вопросы о важном"
    public By costQuestion = By.id("accordion__heading-0"); //Локатор для первого вопроса про стоимость
    public By severalScootersQuestion = By.id("accordion__heading-1"); //Локатор для второго вопроса про несколько самокатов
    public By timeRentalQuestion = By.id("accordion__heading-2"); //Локатор для третьего вопроса про время аренды
    public By todayOrderQuestion = By.id("accordion__heading-3"); //Локатор для четвертого вопроса про заказ сегодня
    public By changeOrderQuestion = By.id("accordion__heading-4"); //Локатор для пятого вопроса про изменения в заказе
    public By chargingScooterQuestion = By.id("accordion__heading-5"); //Локатор для шестого вопроса про зарядку
    public By cancellationOrderQuestion = By.id("accordion__heading-6"); //Локатор для седьмого вопроса про отмену заказа
    public By areaDeliveryQuestion = By.id("accordion__heading-7"); //Локатор для восьмого вопроса про доставку в область

    //Локаторы для просмотра ответов в разделе "Вопросы о важном"
    public By costAnswer = By.id("accordion__panel-0"); //Локатор для первого ответа про стоимость
    public By severalScootersAnswer = By.id("accordion__panel-1"); //Локатор для второго ответа про несколько самокатов
    public By timeRentalAnswer = By.id("accordion__panel-2"); //Локатор для третьего ответа про время аренды
    public By todayOrderAnswer = By.id("accordion__panel-3"); //Локатор для четвертого ответа про заказ сегодня
    public By changeOrderAnswer = By.id("accordion__panel-4"); //Локатор для пятого ответа про изменения в заказе
    public By chargingScooterAnswer = By.id("accordion__panel-5"); //Локатор для шестого ответа про зарядку
    public By cancellationOrderAnswer = By.id("accordion__panel-6"); //Локатор для седьмого ответа про отмену заказа
    public By areaDeliveryAnswer = By.id("accordion__panel-7"); //Локатор для восьмого ответа про доставку в область

    //Конструктор
    public StartPage(WebDriver driver) {
        this.driver = driver;
    }

    //Методы

    //Метод "Принять куки"
    public void acceptCookie() {
        driver.findElement(cookieButton).click();
    }

    //Метод клика на верхнюю кнопку "Заказать"
    public void clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
    }

    //Метод прокрутки страницы к нижней кнопке "Заказать"
    public void scrollToBlockDownOrderButton() {
        WebElement element = driver.findElement(blockDownOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Метод клика на нижнюю кнопку "Заказать"
    public void clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
    }

    //Метод прокрутки страницы к блоку "Вопросы о важном"
    public void scrollToQuestionsHeader() {
        WebElement element = driver.findElement(questionsHeader);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Метод клика на вопрос
    public void clickQuestion(int number) {
        By questionButton = By.id("accordion__heading-" + number);
        driver.findElement(questionButton).click();
    }

    //Метод получения текста вопросов
    public String getQuestionText(int number) {
        By questionsText = By.id("accordion__heading-" + number);
        WebElement element = driver.findElement(questionsText);
        if (element.isDisplayed()) {
            return element.getText();
        } else {
            return "Текст не отображается";
        }
    }

    //Метод получения текста ответов
    public String getAnswerText(int number) {
        By answersText = By.id("accordion__panel-" + number);
        WebElement element = driver.findElement(answersText);
        if (element.isDisplayed()) {
            return element.getText();
        } else {
            return "Текст не отображается";
        }
    }

    //Метод клика на кнопку "Статус заказа"
    public void clickStatusButton() {
        driver.findElement(orderStatusButton).click();
    }

    //Метод ввода номера заказа в поле Номера заказа
    public void inputOrderNumber(String number) {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(orderNumber));
        driver.findElement(orderNumber).sendKeys(number);
    }

    //Метод клика на кнопку "Go"
    public void clickButtonGo() {
        driver.findElement(buttonGo).click();
    }

    //Метод нажатие кнопки "Яндекс"
    public void clickYandexButton() {
        driver.findElement(yandexButton).click();
    }

    //Метод перехода на главную страницу Яндекса
    public void checkUrlYandex(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }

    //Метод нажатие кнопки "Самокат"
    public void clickScooterButton() {
        driver.findElement(scooterButton).click();
    }

    //Метод сравнения URL "Самокат"
    public void checkUrlScooter(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}
