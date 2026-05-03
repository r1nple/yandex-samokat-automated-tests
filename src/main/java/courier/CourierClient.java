package courier;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import requests.RequestPassword;

import static io.restassured.RestAssured.given;

public class CourierClient {

    private static final String COURIER_PATH = "/api/v1/courier";
    private static final String LOGIN_PATH = "/api/v1/courier/login";

    @Step("Создание курьера")
    public Response createCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(COURIER_PATH);
    }

    @Step("Логин курьера")
    public Response loginCourier(CourierLoginRequest loginRequest) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(loginRequest)
                .when()
                .post(LOGIN_PATH);
    }

    @Step("Пустое значение логина, только пароль")
    public Response loginCourierWithoutLogin(String password) {
        RequestPassword requestPassword = new RequestPassword(password);
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(requestPassword)
                .when()
                .post(LOGIN_PATH);
    }

    @Step("Удаление курьера")
    public Response deleteCourier(int courierId) {
        return given()
                .header("Content-type", "application/json")
                .when()
                .delete(COURIER_PATH + "/" + courierId);
    }

}