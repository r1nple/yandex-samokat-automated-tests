package order;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import constants.BaseUrl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import requests.RequestTrack;

import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final String ORDER_PATH = "/api/v1/orders";
    private static final String CANCEL_ORDER_PATH = "/api/v1/orders/cancel";

    static {
        RestAssured.baseURI = BaseUrl.BASE_URL;
        RestAssured.useRelaxedHTTPSValidation();
    }

    private RequestSpecification spec() {
        return given()
                .baseUri(BaseUrl.BASE_URL)
                .header("Content-type", "application/json");
    }

    @Step("Создание заказа")
    public Response createOrder(Order order) {
        return spec()
                .body(order)
                .when()
                .post(ORDER_PATH);
    }

    @Step("Список заказов")
    public Response getOrders() {
        return spec()
                .when()
                .get(ORDER_PATH);
    }

    @Step("Отмена заказа по: {track}")
    public Response cancelOrder(int track) {
        RequestTrack requestTrack = new RequestTrack(track);
        return spec()
                .body(requestTrack)
                .when()
                .put(CANCEL_ORDER_PATH);
    }

}
