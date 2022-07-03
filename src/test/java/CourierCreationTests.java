import Sprint_3.builders.CourierBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;

import java.util.HashMap;

import static Sprint_3.configs.ListURL.ScooterURL;


public class CourierCreationTests {

    static CourierBuilder courierBuilder = new CourierBuilder();
    static HashMap<String, String> randomCourierData = new HashMap<>();

    @BeforeClass
    public static void generateRandomData() {
        randomCourierData.put("courierLogin", RandomStringUtils.randomAlphabetic(6));
        randomCourierData.put("courierPassword", RandomStringUtils.randomNumeric(6));
        randomCourierData.put("courierFirstname", RandomStringUtils.randomAlphabetic(6));
    }

    @AfterClass
    public static void deleteCourier() throws Exception {
        courierBuilder.deleteCourier(randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword"));
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = ScooterURL;
    }


    @DisplayName("Check response code after successful courier creation")
    @Description("Проверка кода ответа при успешном создании курьера")
    @Test
    public void validCourierCreationResponseCode() {
        Response response = courierBuilder.createCourier(
                randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword"),
                randomCourierData.get("courierFirstname"));

        Assert.assertEquals(201, response.getStatusCode());
    }

    //успешный запрос возвращает ok: true;
    @DisplayName("Check response message after successful courier creation")
    @Description("Проверка текста ответа при успешном создании курьера")
    @Test
    public void validCourierCreationResponseMessage() {
        String responseMessage = courierBuilder.getCreateCourierResponseMessage(
                randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword"),
                randomCourierData.get("courierFirstname"), "ok");

        Assert.assertEquals("true", responseMessage);
    }

    @DisplayName("Check response message after courier creation with existing login")
    @Description("Проверка текста ответа при создании курьера с существующим логином")
    @Test
    public void CourierCreationWithRecurringLogin() {
        courierBuilder.createCourier(
                randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword"),
                randomCourierData.get("courierFirstname"));

        String responseMessage = courierBuilder.getCreateCourierResponseMessage(randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword"),
                randomCourierData.get("courierFirstname"), "message");

        Assert.assertEquals("Этот логин уже используется", responseMessage);
    }

    @DisplayName("Check response message after courier creation without login")
    @Description("Проверка текста ответа при создании курьера без логина")
    @Test
    public void CourierCreationWithoutLoginMessage() {
        String courierLogin = "";
        String courierPassword = RandomStringUtils.randomNumeric(6);
        String courierFirstname = RandomStringUtils.randomAlphabetic(6);

        String responseMessage = courierBuilder.getCreateCourierResponseMessage(courierLogin, courierPassword, courierFirstname, "message");

        Assert.assertEquals("Недостаточно данных для создания учетной записи", responseMessage);
    }

    @DisplayName("Check response message after courier creation without password")
    @Description("Проверка текста ответа при создании курьера без пароля")
    @Test
    public void CourierCreationWithoutPasswordMessage() {
        String courierLogin = RandomStringUtils.randomAlphabetic(6);
        ;
        String courierPassword = "";
        String courierFirstname = RandomStringUtils.randomAlphabetic(6);

        String responseMessage = courierBuilder.getCreateCourierResponseMessage(courierLogin, courierPassword, courierFirstname, "message");

        Assert.assertEquals("Недостаточно данных для создания учетной записи", responseMessage);
    }
}