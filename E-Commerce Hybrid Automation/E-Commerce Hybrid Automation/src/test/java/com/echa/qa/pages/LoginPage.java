package com.echa.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By email = By.id("Email");
    private By password = By.id("Password");
    private By login = By.cssSelector("input[value = 'Log in']");
    private By logout = By.linkText("Log out");

    private By errortext = By.className("validation-summary-errors");


    public void login(String userEmail, String pass) {
        driver.findElement(email).sendKeys(userEmail);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(login).click();
    }

    public void waitForSuccessfulLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(logout));
    }

    public void waitForError() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errortext));
    }

    public boolean isLogoutVisible() {
        return driver.findElement(logout).isDisplayed();
    }

}
