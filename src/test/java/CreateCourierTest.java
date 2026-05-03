import courier.Courier;
import courier.CourierClient;
import courier.CourierLoginRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;

public class CreateCourierTest extends BaseTest {

    private final CourierClient courierClient = new CourierClient();
    private Courier createdCourier;

    @After
    public void tearDown() {
        if (createdCourier != null) {
            CourierLoginRequest loginRequest = new CourierLoginRequest(
                    createdCourier.getLogin(),
                    createdCourier.getPassword()
            );
            Response login = loginCourier(loginRequest);
            Integer id = login.body().path("id");

            if (id != null) {
                deleteCourier(id);
            }
        }
    }

    @Test
    @DisplayName("Курьера можно создать. Запрос возвращает правильный код ответа. Успешный запрос возвращает ok: true")
    @Description("Проверка, создания курьера с валидными данными, login, password, firstName")
    public void shouldCreateCourierSuccessfully() {
        createdCourier = new Courier("_courier" + UUID.randomUUID(), "4567", "NameTest");
        createCourier(createdCourier)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров, и возвращается текст ошибки")
    @Description("Проверка, что при создании курьера с существующим логином, вернется ошибка")
    public void shouldNotAllowDuplicateCourier() {
        String login = "_courier" + UUID.randomUUID();
        createdCourier = new Courier(login, "4567", "First");
        createCourier(createdCourier)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
        Courier duplicateCourier = new Courier(login, "abcd", "Second");
        createCourier(duplicateCourier)
                .then()
                .statusCode(HttpStatus.SC_CONFLICT)
                .body("message", equalTo("Этот логин уже используется"));
    }

    @Test
    @DisplayName("Обязательные поля. Нельзя создать курьера без логина. Если одного из полей нет, запрос возвращает ошибку")
    @Description("Проверка, курьер не может быть создан, если не введен логин")
    public void shouldNotCreateCourierWithoutLogin() {
        Courier courier = new Courier(null, "Firstpass", "NoLogin");
        createCourier(courier)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Обязательные поля. Нельзя создать курьера без пароля. Если одного из полей нет, запрос возвращает ошибку")
    @Description("Проверка, курьер не может быть создан, если не введен пароль")
    public void shouldNotCreateCourierWithoutPassword() {
        Courier courier = new Courier("_courier" + UUID.randomUUID(), null, "NoPassword");
        createCourier(courier)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Обязательные поля. Курьер создается без имени")
    @Description("Проверка, курьер может быть создан, если не вводить имя")
    public void shouldCreatedCourierWithoutFirstName() {
        createdCourier = new Courier("_courier" + UUID.randomUUID(), "Secondpass123", null);
        createCourier(createdCourier)
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Обязательные поля. Курьер не создается без логина и пароля. Запрос возвращает ошибку")
    @Description("Проверка, курьер не может быть создан, если не ввести логин и пароль")
    public void shouldNotCreateCourierWithoutLoginAndPassword() {
        Courier courier = new Courier(null, null, "NoLoginAndPass");
        createCourier(courier)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Создание курьера: {courier}")
    private Response createCourier(Courier courier) {
        return courierClient.createCourier(courier);
    }

    @Step("Пустое значение логина, только пароль: {loginRequest}")
    private Response loginCourier(CourierLoginRequest loginRequest) {
        return courierClient.loginCourier(loginRequest);
    }

    @Step("Удаление курьера по ID: {id}")
    private void deleteCourier(Integer id) {
        courierClient.deleteCourier(id);
    }

}
