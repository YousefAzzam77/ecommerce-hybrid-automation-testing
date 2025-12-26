package com.echa.qa.api;

import com.echa.qa.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class OrderApi {

    public static Response getOrders(String authCookie) {

        RestAssured.baseURI = ConfigReader.get("base.url");

        return RestAssured
                .given()
                .cookie("NOPCOMMERCE.AUTH", authCookie)
                .when()
                .get("/customer/orders")
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static boolean hasOrders(String authCookie) {
        Response response = getOrders(authCookie);
        return response.asString().contains("Order Number");
    }

    public static boolean isOrderPresent(String authCookie, String orderNumber) {

        Response response = getOrders(authCookie);
        return response.asString().contains(orderNumber);
    }
}
