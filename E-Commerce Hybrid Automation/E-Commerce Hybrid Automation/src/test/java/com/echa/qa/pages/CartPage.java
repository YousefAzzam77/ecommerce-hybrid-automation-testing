package com.echa.qa.pages;

import com.echa.qa.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    private By cartLink = By.linkText("Shopping cart");
    private By termsOfService = By.id("termsofservice");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void openShoppingCart() {
        driver.findElement(cartLink).click();

    }

    public void proceedToCheckout() {
        driver.findElement(termsOfService).click();
        driver.findElement(checkoutButton).click();
    }
}
