import constants.BaseUrl;
import courier.Courier;
import courier.CourierClient;
import courier.CourierLoginRequest;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.BeforeClass;

public class BaseTest {

    protected Courier courier;
    protected CourierClient courierClient;

    @BeforeClass
    public static void initialize() {
        RestAssured.baseURI = BaseUrl.BASE_URL;
    }

    @After
    public void tearDown() {
        if (courier != null) {
            CourierLoginRequest loginRequest = new CourierLoginRequest(
                    courier.getLogin(),
                    courier.getPassword()
            );
            Response loginResponse = courierClient.loginCourier(loginRequest);
            Integer courierId = loginResponse.body().path("id");
            if (courierId != null) {
                deleteCourier(courierId);
            }
        }
    }

    @Step("Авторизация курьера для удаления: {loginRequest}")
    private Response loginCourier(CourierLoginRequest loginRequest) {
        return courierClient.loginCourier(loginRequest);
    }

    @Step("Удаление курьера по ID: {id}")
    private void deleteCourier(Integer id) {
        courierClient.deleteCourier(id);
    }

}
