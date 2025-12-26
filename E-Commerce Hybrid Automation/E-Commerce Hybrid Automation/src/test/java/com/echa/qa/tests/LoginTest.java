package com.echa.qa.tests;

import com.echa.qa.base.BaseTest;
import com.echa.qa.pages.LoginPage;
import com.echa.qa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void VerifyLoginWithValidCredentials() {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login(ConfigReader.get("user.email"),ConfigReader.get( "user.password"));
        loginpage.waitForSuccessfulLogin();
        Assert.assertTrue(loginpage.isLogoutVisible(),"Login Failed");

    }

    @Test
    public void VerifyLoginWithInValidEmail() {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login("invalidemail@email.com", ConfigReader.get("user.password"));
        loginpage.waitForError();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),"Login Succeed");

    }

    @Test
    public void VerifyLoginWithInValidPassword() {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login(ConfigReader.get("user.email"), "invalid");
        loginpage.waitForError();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),"Login Succeed");

    }

    @Test
    public void VerifyLoginWithEmptyCredentials() {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login("", "");
        loginpage.waitForError();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"),"Login Succeed");

    }

}
