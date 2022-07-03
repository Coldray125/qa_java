package Sprint_3.requests;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static Sprint_3.configs.EndPointList.courierCreate;
import static io.restassured.RestAssured.given;

public class PostCreateCourierRequest {
    @Step("Отправка запроса на endpoint POST /api/v1/courierCreate для создания учетной записи курьера")
    public Response CourierCreate(Object object) {
        return given()
                .header("Content-type", "application/json")
                .body(object)
                .when()
                .post(courierCreate)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();
    }
}