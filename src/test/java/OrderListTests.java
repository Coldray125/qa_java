import Sprint_3.builders.OrderBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static Sprint_3.configs.ListURL.ScooterURL;

public class OrderListTests {

    OrderBuilder orderBuilder = new OrderBuilder();


    @Before
    public void setUp() {
        RestAssured.baseURI = ScooterURL;
    }

    @DisplayName("Check order Id is in the order list response body")
    @Description("Проверка наличия orderId в ответе на запрос GET /api/v1/orders")
    @Test
    public void OrderIdIsInTheOrderList() {
        String orderId = orderBuilder.createValidOrderAndReturnId();
        Response responseBody = orderBuilder.getOrderList();

        Assert.assertTrue("OrderId отсустует в ответе на запрос",responseBody.asString().contains(orderId));
    }

}