import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OrderTests extends BaseUiTest {

    //Тестовый сценарий с верхней кнопкой "Заказать", с первым набором данных, и с черным самокатом.
    @Test
    public void upOrderTest() {
        startPage.acceptCookie();
        startPage.clickUpOrderButton();
        userInfoOrderPage.fillData("Ринат", "Ибрагимов", "Улица тестирования, 1", "Чистые пруды", "+79999999999");
        rentInfoOrderPage.fillDataRentBlackBox("28.03.2026", "Жду, не дождусь!");
        rentInfoOrderPage.clickButtonOrder();
        rentInfoOrderPage.clickButtonConfirm();
        rentInfoOrderPage.isWindowCheckStatusDisplayed();
        assertTrue("Заказ не оформлен", rentInfoOrderPage.isWindowCheckStatusDisplayed());
    }

    //Тестовый сценарий с нижней кнопкой "Заказать", вторым набором данных, и с серым самокатом.
    @Test
    public void downOrderTest() {
        startPage.acceptCookie();
        startPage.scrollToBlockDownOrderButton();
        startPage.clickDownOrderButton();
        userInfoOrderPage.fillData("Афанасий", "Афанасьев", "Улица Афанасьева, 1", "Комсомольская", "+78887773311");
        rentInfoOrderPage.fillDataRentGreyBox("23.03.2026", "Жду, не дождусь!");
        rentInfoOrderPage.clickButtonOrder();
        rentInfoOrderPage.clickButtonConfirm();
        rentInfoOrderPage.isWindowCheckStatusDisplayed();
        assertTrue("Заказ не оформлен", rentInfoOrderPage.isWindowCheckStatusDisplayed());
    }

    //Тестовый сценарий "Если ввести неправильный номер заказа, попадёшь на страницу статуса заказа. На ней должно быть написано, что такого заказа нет."
    @Test
    public void checkWrongOrderStatus() {
        startPage.acceptCookie();
        startPage.clickStatusButton();
        startPage.inputOrderNumber("999999");
        startPage.clickButtonGo();
        assertTrue("Сообщение об ошибке не отображается", errorStatusOrderPage.getErrorImg().isDisplayed());
    }
}
