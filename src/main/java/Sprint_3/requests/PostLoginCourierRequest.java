package Sprint_3.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static Sprint_3.configs.EndPointList.courierLogin;
import static io.restassured.RestAssured.given;

public class PostLoginCourierRequest {
    @Step("Отправка запроса на endpoint POST /api/v1/courier/login для входа курьера в учетную запись")
    public Response CourierLogin(Object object) {
        return given()
                .header("Content-type", "application/json")
                .body(object)
                .when()
                .post(courierLogin);
    }
}