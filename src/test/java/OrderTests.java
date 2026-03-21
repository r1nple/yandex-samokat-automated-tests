import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OrderTests extends BaseUiTest {

    //Тестовый сценарий с верхней кнопкой "Заказать", с первым набором данных, и с черным самокатом.
    @Test
    public void upOrderTest() {
        startPage.acceptCookie();
        startPage.clickUpOrderButton();
        userInfoOrderPage.enterFieldName("Ринат");
        userInfoOrderPage.enterFieldSurname("Ибрагимов");
        userInfoOrderPage.enterFieldAddress("Улица тестирования, 1");
        userInfoOrderPage.enterFieldMetroStation("Чистые пруды");
        userInfoOrderPage.enterFieldPhoneNumber("+79999999999");
        userInfoOrderPage.clickButtonNext();
        rentInfoOrderPage.fillDeliveryTime("28.03.2026");
        rentInfoOrderPage.fillFieldRentTime();
        rentInfoOrderPage.choseBlackBoxScooter();
        rentInfoOrderPage.fillCommentCourier("И в ту же минуту по улицам курьеры, курьеры, курьеры... можете представить себе, тридцать пять тысяч одних курьеров! Каково положение, я спрашиваю?");
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
