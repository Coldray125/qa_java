package Sprint_3.builders;

import Sprint_3.pojo.CreateCourierPojo;
import Sprint_3.pojo.LoginCourierPojo;
import Sprint_3.requests.DeleteCourierRequest;
import Sprint_3.requests.PostCreateCourierRequest;
import Sprint_3.requests.PostLoginCourierRequest;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CourierBuilder {

    @Step("Создание учетной записи курьера через запрос POST /api/v1/courierCreate")
    public Response createCourier(String login, String password, String firstname) {
        CreateCourierPojo createCourier = new CreateCourierPojo(login, password, firstname);
        return new PostCreateCourierRequest().CourierCreate(createCourier);
    }
   @Step("Получение текста ответа на запрос POST /api/v1/courierCreate")
    public String getCreateCourierResponseMessage(String login, String password, String firstname, String path) {
        CreateCourierPojo createCourier = new CreateCourierPojo(login, password, firstname);
        Response response = new PostCreateCourierRequest().CourierCreate(createCourier);
        String responseBody = response.getBody().asString();
        JsonPath js = new JsonPath(responseBody);
        String message = js.getString(path);

        return message;
    }
    @Step("Авторизация курьером через запрос POST /api/v1/courier/login")
    public Response loginCourier(String login, String password) {
        LoginCourierPojo loginCourier = new LoginCourierPojo(login, password);
        return new PostLoginCourierRequest().CourierLogin(loginCourier);
    }
    @Step("Получение текста ответа на запрос GET /api/v1/courierCreate")
    public String getLoginCourierResponseMessage(String login, String password) {
        LoginCourierPojo loginCourier = new LoginCourierPojo(login, password);
        Response response = new PostLoginCourierRequest().CourierLogin(loginCourier);
        String responseBody = response.getBody().asString();
        JsonPath js = new JsonPath(responseBody);
        String message = js.getString("message");

        return message;
    }
    @Step("Получение id из текста ответа в запросе POST /api/v1/courierCreate")
    public String getCourierId(String login, String password) {
        LoginCourierPojo loginCourier = new LoginCourierPojo(login, password);
        Response response = new PostLoginCourierRequest().CourierLogin(loginCourier);
        String responseBody = response.getBody().asString();
        JsonPath js = new JsonPath(responseBody);
        String id = js.getString("id");

        return id;
    }

    @Step("Удаление курьера через запрос DELETE /api/v1/courier/{id}")
    public void deleteCourier(String login, String password) throws Exception {
        LoginCourierPojo loginCourier = new LoginCourierPojo(login, password);
        Response response = new PostLoginCourierRequest().CourierLogin(loginCourier);
        String responseBody = response.getBody().asString();
        JsonPath js = new JsonPath(responseBody);
        String id = js.getString("id");

        if (response.getStatusCode() != 200) {
            throw new Exception("Ошибка удаления курьера" + " StatusCode " + response.getStatusCode());
        }
         new DeleteCourierRequest().DeleteCourier(id);
    }

}