package com.echa.qa.tests;

import com.echa.qa.api.AuthApi;
import com.echa.qa.api.OrderApi;
import com.echa.qa.base.BaseTest;
import com.echa.qa.pages.*;
import com.echa.qa.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseTest extends BaseTest {
private String latestOrderNumber;
    @Test
    public void userCanPurchaseProductSuccessfully() {
        loginViaApi(
                ConfigReader.get("user.email"),
                ConfigReader.get("user.password")
        );

        HomePage home = new HomePage(driver);
        home.openDesktops();
        home.selectSimpleComputer();

        ProductPage product = new ProductPage(driver);
        product.addProductToCart();
        product.waitForSuccessMessage();
        Assert.assertTrue(product.isProductAdded(), "Product not added to cart");

        CartPage cart = new CartPage(driver);
        cart.openShoppingCart();
        cart.proceedToCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);

        checkout.completeCheckoutProcess();
        checkout.waitForConfirmation();
        latestOrderNumber = checkout.getOrderNumber();
        Assert.assertTrue(checkout.isOrderSuccessful(), "Order was not successful");
        String authCookie =
                AuthApi.loginAndGetAuthCookie(
                        ConfigReader.get("user.email"),
                        ConfigReader.get("user.password")
                );


        Assert.assertTrue(
                OrderApi.isOrderPresent(authCookie,latestOrderNumber),
                "No orders found via API"
        );
    }

}
