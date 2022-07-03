package Sprint_3.requests;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Sprint_3.configs.EndPointList.orderCreate;
import static io.restassured.RestAssured.given;

public class PostOrderRequest {
    @Step("Отправка запроса на endpoint POST /api/v1/order для создания заказа")
    public Response OrderCreate(Object object) {
        return given()
                .header("Content-type", "application/json")
                .body(object)
                .when()
                .post(orderCreate)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();
    }
}