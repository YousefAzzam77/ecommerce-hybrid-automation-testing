package com.echa.qa.tests;

import com.echa.qa.base.BaseTest;
import com.echa.qa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HybridLoginTest extends BaseTest {

    @Test
    public void verifyLoginViaApiAndUi() {
        loginViaApi(
                ConfigReader.get("user.email"),
                ConfigReader.get("user.password")
        );

        Assert.assertTrue(
                driver.findElement(By.linkText("Log out")).isDisplayed(),
                "User is NOT logged in via API"
        );
    }
}
