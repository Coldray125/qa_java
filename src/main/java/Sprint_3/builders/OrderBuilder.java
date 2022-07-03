package Sprint_3.builders;

import Sprint_3.pojo.CreateOrderPojo;
import Sprint_3.requests.GetOrderListRequest;
import Sprint_3.requests.PostOrderRequest;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static Sprint_3.configs.ValidFieldData.*;

public class OrderBuilder {
    @Step("Создание заказа POST /api/v1/orders")
    public Response createOrder(Object object) {
        return new PostOrderRequest().OrderCreate(object);
    }



    @Step("Получения тела запроса для запроса POST /api/v1/orders")
    public String getCreateOrderResponseMessage(Object object) {
        Response response = new PostOrderRequest().OrderCreate(object);
        String responseBody = response.getBody().asString();

        return responseBody;
    }


    @Step("Получение списка заказов GET /api/v1/orders")
    public Response getOrderList() {
        return new GetOrderListRequest().GetOrderList();
    }


    @Step("Получение id заказа после его создания с валидными данными POST /api/v1/orders")
    public String createValidOrderAndReturnId() {

        CreateOrderPojo createOrderPojo = new CreateOrderPojo()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAddress(address)
                .setMetroStation(metroStation)
                .setPhone(phone)
                .setRentTime(rentTime)
                .setDeliveryData(deliveryData)
                .setComment(comment)
                .setColor(color);

        Response response = new PostOrderRequest().OrderCreate(createOrderPojo);
        String responseBody = response.getBody().asString();
        JsonPath js = new JsonPath(responseBody);
        String id = js.getString("track");

        return id;
    }

}
