import Sprint_3.builders.CourierBuilder;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import java.util.HashMap;

import static Sprint_3.configs.ListURL.ScooterURL;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;

public class CourierLoginTests {

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

    @DisplayName("Check response code after courier login with valid credentials")
    @Description("Возврат кода 200 в случае валидных данных авторизации курьера")
    @Test
    public void courierLoginValidCredentialsResponseCode() {
        courierBuilder.createCourier(
                randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword"),
                randomCourierData.get("courierFirstname"));

        Response response = courierBuilder.loginCourier(
                randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword"));

        Assert.assertEquals(200, response.getStatusCode());
    }

    @DisplayName("Check courier login request returns response id in message")
    @Description("Проверка возврата id в теле запроса")
    @Test
    public void courierLoginReturnsId() {
        courierBuilder.createCourier(
                randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword"),
                randomCourierData.get("courierFirstname"));

        String id = courierBuilder.getCourierId(
                randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword"));

        assertThat(id, CoreMatchers.not(emptyOrNullString()));
    }

    @DisplayName("Check response code after courier login with wrong login")
    @Description("Возврат кода ошибки 404 в случае неправильно указанного логина")
    @Test
    public void courierLoginWrongLoginResponseCode() {
        courierBuilder.createCourier(
                randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword"),
                randomCourierData.get("courierFirstname"));

        Response response = courierBuilder.loginCourier(
                randomCourierData.get("courierLogin") + "1",
                randomCourierData.get("courierPassword"));

        Assert.assertEquals(404, response.getStatusCode());
    }

    @DisplayName("Check response code after courier login with wrong password")
    @Description("Возврат кода ошибки 404 в случае неправильно указанного пароля")
    @Test
    public void courierLoginWrongPasswordResponseCode() {
        courierBuilder.createCourier(
                randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword"),
                randomCourierData.get("courierFirstname"));

        Response response = courierBuilder.loginCourier(
                randomCourierData.get("courierLogin"),
                randomCourierData.get("courierPassword") + "1");

        Assert.assertEquals(404, response.getStatusCode());
    }

    @DisplayName("Check response message after courier login with non-existent credentials")
    @Description("Проверка текста ошибки в теле ответа при авторизации курьера с несуществующей учетной записью")
    @Test
    public void courierLoginWrongCredentialsResponseMessage() {
        String courierLogin = RandomStringUtils.randomAlphabetic(6);
        String courierPassword = RandomStringUtils.randomNumeric(6);

        String responseMessage = courierBuilder.getLoginCourierResponseMessage(courierLogin, courierPassword);

        Assert.assertEquals("Учетная запись не найдена", responseMessage);
    }

    @DisplayName("Check response code after courier login without login and password")
    @Description("Проверка кода в ответe при авторизации курьера без логина и пароля")
    @Test
    public void courierLoginWithoutCredentialsResponseCode() {
        String courierLogin = "";
        String courierPassword = "";

        Response response = courierBuilder.loginCourier(courierLogin, courierPassword);
        Assert.assertEquals(400, response.getStatusCode());
    }

    @DisplayName("Check response message after courier login without login")
    @Description("Проверка текста ответа при авторизации курьера без логина")
    @Test
    public void courierLoginWithoutLoginMessage() {
        String courierLogin = "";
        String courierPassword = RandomStringUtils.randomNumeric(6);

        String responseMessage = courierBuilder.getLoginCourierResponseMessage(courierLogin, courierPassword);

        Assert.assertEquals("Недостаточно данных для входа", responseMessage);
    }

    @DisplayName("Check response message after courier login without password")
    @Description("Проверка текста ответа при авторизации курьера без пароля")
    @Test
    public void courierLoginWithoutPasswordMessage() {
        String courierLogin = RandomStringUtils.randomAlphabetic(6);
        String courierPassword = "";

        String responseMessage = courierBuilder.getLoginCourierResponseMessage(courierLogin, courierPassword);

        Assert.assertEquals("Недостаточно данных для входа", responseMessage);
    }
}