package com.echa.qa.tests;

import com.echa.qa.api.AuthApi;
import com.echa.qa.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiLoginTest {
    @Test
    public void VerifyApiLogin() {
        String cookie = AuthApi.loginAndGetAuthCookie(
                ConfigReader.get("user.email"),
                ConfigReader.get("user.password")
        );
        Assert.assertNotNull(cookie,"Cookie is null");
        System.out.println("Auth cookie retrieved successfully.");

    }

}
