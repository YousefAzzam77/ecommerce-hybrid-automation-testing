package com.echa.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    private By computersCategory = By.linkText("Computers");
    private By desktopsCategory = By.linkText("Desktops");
    private By simpleComputer = By.linkText("Simple Computer");

    public HomePage(WebDriver driver) {
        super(driver);
    }
    public void openDesktops() {
        driver.findElement(computersCategory).click();
        driver.findElement(desktopsCategory).click();
    }

    public void selectSimpleComputer() {
        driver.findElement(simpleComputer).click();
    }
}
