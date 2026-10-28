import generators.GeneratorOrder;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import order.Order;
import order.OrderClient;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private static final String TRACK_FIELD = "track";
    private static final Logger log = LoggerFactory.getLogger(CreateOrderTest.class);

    private String firstName;
    private String lastName;
    private String address;
    private String stationMetro;
    private String numberPhone;
    private Integer timeRent;
    private String dateDelivery;
    private String comment;
    private String[] color;

    private final OrderClient orderClient = new OrderClient();
    private final GeneratorOrder generatorOrder = new GeneratorOrder();
    private Integer trackId;

    public CreateOrderTest(String firstName, String lastName, String address, String stationMetro, String numberPhone, Integer timeRent, String dateDelivery, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.stationMetro = stationMetro;
        this.numberPhone = numberPhone;
        this.timeRent = timeRent;
        this.dateDelivery = dateDelivery;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters(name = "{index}: comment = {7}")
    public static Object[][] getDataTest() {
        return new Object[][]{
                {"Ринат", "Ринатов", "улица Тестовая, д.1", "Бауманская", "87776665544", 1, "2026.05.09", "Черный цвет", new String[]{"BLACK"}},
                {"Рината", "Ринатова", "улица Тестовая, д.2", "Университет", "78887776655", 2, "2026.05.10", "Серый цвет", new String[]{"GREY"}},
                {"Рауль", "Гонсалес", "улица Тестовая, д.3", "Динамо", "79998887766", 3, "2026.05.11", "Черный цвет и Серый цвет", new String[]{"BLACK", "GREY"}},
                {"Криштиану", "Роналду", "улица Тестовая, д.4", "Парк Победы", "89998887766", 4, "2026.05.12", "", new String[]{}},
        };
    }

    @After
    public void deleteOrder() {
        if (trackId != null && trackId > 0) {
            cancelOrder(trackId);
        }
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Тест с использованием параметризации. Позитивные сценарии")
    public void createOrderTest() {
        Order order = generateOrder();
        Response response = sendCreateOrderRequest(order);
        trackId = extractTrackId(response);
        assertCreatedOrder(response);
    }

    @Step("Генерация заказа из параметров")
    public Order generateOrder() {
        Order order = generatorOrder.getOrder(firstName, lastName, address, stationMetro, numberPhone, timeRent, dateDelivery, comment, color);
        log.info("Создание заказа: {}", order);
        return order;
    }

    @Step("Отправка запроса на создание заказа")
    public Response sendCreateOrderRequest(Order order) {
        Response response = orderClient.createOrder(order);
        log.info("Ответ от сервера: {}", response.body().asString());
        return response;
    }

    @Step("Проверка, заказ успешно создан: код 201, тело ответа содержит track")
    public void assertCreatedOrder(Response response) {
        response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .assertThat()
                .body(TRACK_FIELD, notNullValue());
    }

    @Step("Извлечение номера заказа track")
    public Integer extractTrackId(Response response) {
        Integer track = response.body().path(TRACK_FIELD);
        log.info("Создан заказ №: {}", track);
        return track;
    }

    @Step("Отмена заказа по номеру: {track}")
    public void cancelOrder(int track) {
        Response response = orderClient.cancelOrder(track);
        log.info("Ответ на отмену заказа: {}", response.body().asString());
        response.then().statusCode(anyOf(equalTo(HttpStatus.SC_OK), equalTo(HttpStatus.SC_BAD_REQUEST), equalTo(HttpStatus.SC_NOT_FOUND)));
    }

}
