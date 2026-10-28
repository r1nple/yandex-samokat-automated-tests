import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.OrderClient;
import org.apache.http.HttpStatus;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.notNullValue;

public class OrderTest {

    public static final String ORDERS_FIELD = "orders";
    private static final String RESPONSE = "Ответ от сервера: {}";
    private static final Logger log = LoggerFactory.getLogger(OrderTest.class);
    private final OrderClient orderClient = new OrderClient();

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверка, в теле возвращается список заказов")
    public void getOrdersTest() {
        Response response = sendGetOrderRequest();
        logResponse(response);
        checkResponseStatusAndBody(response);
    }

    @Step("Лог для тела ответа")
    public void logResponse(Response response) {
        log.info(RESPONSE, response.body().asString());
    }

    @Step("Запрос на получение списка заказов")
    public Response sendGetOrderRequest() {
        return orderClient.getOrders();
    }

    @Step("Проверка, код ответа и тело не пустые: поле {ORDERS_FIELD} не пустое")
    public void checkResponseStatusAndBody(Response response) {
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .assertThat()
                .body(ORDERS_FIELD, notNullValue());
    }

}
