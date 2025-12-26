package com.echa.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.security.Key;

public class ProductPage extends BasePage {

    private By processorType = By.id("product_attribute_75_5_31_96");
    private By quantity = By.id("addtocart_75_EnteredQuantity");
    private By addProductToCartButton = By.id("add-to-cart-button-75");
    private By successMessage =
            By.xpath("//p[text()='The product has been added to your ']");
    private By quantityErrorMessage =
            By.xpath("//p[text()='Quantity should be positive']");



    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart() {
        driver.findElement(processorType).click();
        driver.findElement(addProductToCartButton).click();
    }

    public void addProductToCartWithInvalidQuantity() {
        driver.findElement(processorType).click();
        driver.findElement(quantity).sendKeys(Keys.BACK_SPACE,"0");
        driver.findElement(addProductToCartButton).click();
    }

    public void waitForSuccessMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
    }

    public void waitForErrorMessage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityErrorMessage));
    }

    public boolean isProductAdded() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public boolean isProductNotAdded() {
        return driver.findElement(quantityErrorMessage).isDisplayed();
    }
}
