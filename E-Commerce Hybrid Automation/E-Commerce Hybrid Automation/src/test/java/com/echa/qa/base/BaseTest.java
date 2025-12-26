package com.echa.qa.base;

import com.echa.qa.api.AuthApi;
import com.echa.qa.utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url") + "/login");
    }
    protected void loginViaApi(String email, String password) {

        String authCookie =
                AuthApi.loginAndGetAuthCookie(email, password);

        driver.get(ConfigReader.get("base.url"));

        Cookie cookie = new Cookie(
                "NOPCOMMERCE.AUTH",
                authCookie,
                ".demowebshop.tricentis.com",
                "/",
                null
        );

        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }
    @AfterMethod
    public void quit() {
        driver.quit();
    }


}
