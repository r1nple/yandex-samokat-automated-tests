import courier.Courier;
import courier.CourierClient;
import courier.CourierLoginRequest;
import generators.GeneratorCourier;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class LoginCourierTest extends BaseTest {

    private final CourierClient courierClient = new CourierClient();
    private final GeneratorCourier generatorCourier = new GeneratorCourier();
    private Courier courier;

    @Before
    public void setUp() {
        courier = generatorCourier.getRandom();
        createCourier(courier);
    }

    @Test
    @DisplayName("Курьер может авторизоваться. Для авторизации нужно передать все обязательные поля. Успешный запрос возвращает id")
    @Description("Проверка, курьер может авторизоваться с учетными данными")
    public void courierCanLogin() {
        CourierLoginRequest loginRequest = new CourierLoginRequest(courier.getLogin(), courier.getPassword());
        Response response = loginCourier(loginRequest);
        assertSuccessfulLogin(response);
    }

    @Test
    @DisplayName("Система вернёт ошибку, если неправильно указать логин")
    @Description("Проверка, курьер не может авторизоваться с неверным логином")
    public void loginWithInvalidLogin() {
        CourierLoginRequest loginRequest = new CourierLoginRequest("invalid_login", courier.getPassword());
        Response response = loginCourier(loginRequest);
        assertErrorResponse(response, HttpStatus.SC_NOT_FOUND, "Учетная запись не найдена");
    }

    @Test
    @DisplayName("Система вернёт ошибку, если неправильно указать пароль")
    @Description("Проверка, курьер не может авторизоваться с неверным паролем")
    public void loginWithInvalidPassword() {
        CourierLoginRequest loginRequest = new CourierLoginRequest(courier.getLogin(), "invalid_password");
        Response response = loginCourier(loginRequest);
        assertErrorResponse(response, HttpStatus.SC_NOT_FOUND, "Учетная запись не найдена");
    }

    @Test
    @DisplayName("Если какого-то поля(логина) нет, запрос возвращает ошибку")
    @Description("Проверка, нельзя авторизоваться, если не введен логин")
    public void loginWithoutLogin() {
        Response response = loginCourierWithoutLogin(courier.getPassword());
        assertErrorResponse(response, HttpStatus.SC_BAD_REQUEST, "Недостаточно данных для входа");
    }

    @Test
    @DisplayName("Если какого-то поля(пароля) нет, запрос возвращает ошибку")
    @Description("Проверка, нельзя авторизоваться, если не введен пароль")
    public void loginWithoutLPassword() {
        CourierLoginRequest loginRequest = new CourierLoginRequest(courier.getLogin(), "");
        Response response = loginCourier(loginRequest);
        assertErrorResponse(response, HttpStatus.SC_BAD_REQUEST, "Недостаточно данных для входа");
    }

    @Step("Создание курьера: {courier}")
    private void createCourier(Courier courier) {
        courierClient.createCourier(courier);
    }

    @Step("Авторизация курьера: {loginRequest}")
    private Response loginCourier(CourierLoginRequest loginRequest) {
        return courierClient.loginCourier(loginRequest);
    }

    @Step("Авторизация без логина")
    private Response loginCourierWithoutLogin(String password) {
        return courierClient.loginCourierWithoutLogin(password);
    }

    @Step("Проверка успешной авторизации по id")
    private void assertSuccessfulLogin(Response response) {
        response.then()
                .statusCode(HttpStatus.SC_OK)
                .body("id", notNullValue());
    }

    @Step("Проверка ошибки: статус {statusCode} и сообщения '{message}'")
    private void assertErrorResponse(Response response, int statusCode, String message) {
        response.then()
                .statusCode(statusCode)
                .body("message", equalTo(message));
    }

}
