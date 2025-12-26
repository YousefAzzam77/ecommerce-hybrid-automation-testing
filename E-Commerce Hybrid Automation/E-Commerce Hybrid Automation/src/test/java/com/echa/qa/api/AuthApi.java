package com.echa.qa.api;

import com.echa.qa.utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthApi {
    public static String loginAndGetAuthCookie(String email, String password) {
        RestAssured.baseURI = ConfigReader.get("base.url");
        Response response = RestAssured
                .given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("Email", email)
                .formParam("Password", password)
                .formParam("RememberMe", "false")
                .when()
                .post("/login")
                .then()
                .statusCode(302)
                .extract()
                .response();
        String authCookie = response.getCookie("NOPCOMMERCE.AUTH");

        if (authCookie == null) {
            throw new RuntimeException("Authentication cookie not found");
        }
        return authCookie;
    }

}
