import org.junit.Test;

import java.util.Set;

public class ClickLogoYandexTest extends BaseUiTest{

    @Test
    public void checkUrlYandexTest() {
        String expectedUrl = "https://dzen.ru/?yredirect=true";
        startPage.clickYandexButton();
        String originalWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();
        for (String window : windowHandles) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        startPage.checkUrlYandex(expectedUrl);
    }
}
