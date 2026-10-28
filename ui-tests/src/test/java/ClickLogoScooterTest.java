import org.junit.Test;

public class ClickLogoScooterTest extends BaseUiTest {

    @Test
    public void checkUrlScooterTest() {
        String expectedUrl = "https://qa-scooter.praktikum-services.ru/";
        startPage.clickScooterButton();
        startPage.checkUrlScooter(expectedUrl);
    }
}
