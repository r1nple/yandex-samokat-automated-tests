import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.ErrorStatusOrderPage;
import pages.RentInfoOrderPage;
import pages.StartPage;
import pages.UserInfoOrderPage;

public class BaseUiTest {

    WebDriver driver;
    StartPage startPage;
    UserInfoOrderPage userInfoOrderPage;
    RentInfoOrderPage rentInfoOrderPage;
    ErrorStatusOrderPage errorStatusOrderPage;

    //Открытие браузера
    @Before
    public void startBrowserChrome() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
        driver.manage().window().maximize();
        startPage = new StartPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        errorStatusOrderPage = new ErrorStatusOrderPage(driver);
        userInfoOrderPage = new UserInfoOrderPage(driver);
        rentInfoOrderPage = new RentInfoOrderPage(driver);
    }

    public void startBrowserFirefox() {
        driver = new FirefoxDriver();
        WebDriverManager.firefoxdriver().setup();
        driver.manage().window().maximize();
        startPage = new StartPage(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        errorStatusOrderPage = new ErrorStatusOrderPage(driver);
        userInfoOrderPage = new UserInfoOrderPage(driver);
        rentInfoOrderPage = new RentInfoOrderPage(driver);
    }

    public void startBrowser() {
        String browser = System.getProperty("browser", "chrome");
        if (browser.equals("chrome")) {
            startBrowserChrome();
        } else if (browser.equals("firefox")) {
            startBrowserFirefox();
        }
    }

    //Закрытие браузера
    @After
    public void closeBrowser() {
        driver.quit();
    }
}
