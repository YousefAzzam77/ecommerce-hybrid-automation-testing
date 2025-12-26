package com.echa.qa.tests;

import com.echa.qa.base.BaseTest;
import com.echa.qa.pages.CartPage;
import com.echa.qa.pages.HomePage;
import com.echa.qa.pages.LoginPage;
import com.echa.qa.pages.ProductPage;
import com.echa.qa.utils.ConfigReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutNegativeTest extends BaseTest {
    @Test
    public void userCannotCheckoutWithoutLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.openDesktops();
        homePage.selectSimpleComputer();

        ProductPage product = new ProductPage(driver);
        product.addProductToCart();
        product.waitForSuccessMessage();
        Assert.assertTrue(product.isProductAdded(), "Product not added to cart");

        CartPage cart = new CartPage(driver);
        cart.openShoppingCart();
        cart.proceedToCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "Checkout completes without login");
    }

    @Test
    public void userCannotAddInvalidQuantity() {
        loginViaApi(
                ConfigReader.get("user.email"),
                ConfigReader.get("user.password")
        );
        HomePage home = new HomePage(driver);
        home.openDesktops();
        home.selectSimpleComputer();

        ProductPage product = new ProductPage(driver);
        product.addProductToCartWithInvalidQuantity();
        product.waitForErrorMessage();
        Assert.assertTrue(product.isProductNotAdded(), "Product is added with invalid quantity");
    }
}
