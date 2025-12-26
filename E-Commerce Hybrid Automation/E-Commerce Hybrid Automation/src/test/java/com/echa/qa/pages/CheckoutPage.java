package com.echa.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {
    private By billingAddress =
            By.id("billing-address-select");

    private By country =
            By.id("BillingNewAddress_CountryId");

    private By city =
            By.id("BillingNewAddress_City");
    private By address =
            By.id("BillingNewAddress_Address1");
    private By postalCode =
            By.id("BillingNewAddress_ZipPostalCode");
    private By phoneNumber =
            By.id("BillingNewAddress_PhoneNumber");
    private By billingContinue =
            By.xpath("//input[@onclick='Billing.save()']");
    private By shippingContinue =
            By.xpath("//input[@onclick='Shipping.save()']");
    private By shippingMethodContinue =
            By.xpath("//input[@onclick='ShippingMethod.save()']");
    private By paymentMethodContinue =
            By.xpath("//input[@onclick='PaymentMethod.save()']");
    private By paymentInfoContinue =
            By.xpath("//input[@onclick='PaymentInfo.save()']");
    private By confirmOrderButton =
            By.xpath("//input[@onclick='ConfirmOrder.save()']");
    private By orderSuccessMessage =
            By.xpath("//strong[contains(text(),'successfully processed')]");
    By orderNumberText =
            By.xpath("//ul[@class='details']/li[contains(text(),'Order number')]");



    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void completeCheckoutProcess() {
        if(driver.findElements(billingAddress).size() > 0) {
            Select billingAddressSelection =
                    new Select(driver.findElement(billingAddress));
            billingAddressSelection.selectByVisibleText("New Address");
        }
        Select countryName =
                new Select(driver.findElement(country));
        countryName.selectByVisibleText("Egypt");
        driver.findElement(city).sendKeys("Cairo");
        driver.findElement(address).sendKeys("New Cairo");
        driver.findElement(postalCode).sendKeys("12345");
        driver.findElement(phoneNumber).sendKeys("01123346421");
        wait.until(ExpectedConditions.elementToBeClickable(billingContinue)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PickUpInStore")));
        wait.until(ExpectedConditions.elementToBeClickable(shippingContinue)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shippingoption_0")));
        wait.until(ExpectedConditions.elementToBeClickable(shippingMethodContinue)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paymentmethod_0")));

        wait.until(ExpectedConditions.elementToBeClickable(paymentMethodContinue)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("payment-info-buttons-container")));

        wait.until(ExpectedConditions.elementToBeClickable(paymentInfoContinue)).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("confirm-order-buttons-container")));

        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton)).click();


    }

    public void waitForConfirmation() {
        wait
                .until(ExpectedConditions.visibilityOfElementLocated(By
                        .xpath("//strong[text()='Your order has been successfully processed!']")));

    }


    public boolean isOrderSuccessful() {
        return wait
                .until(ExpectedConditions.visibilityOfElementLocated(orderSuccessMessage))
                .isDisplayed();
    }

    public String getOrderNumber() {
        String text = driver.findElement(orderNumberText).getText().trim();
        return text.replace("Order number: ","").trim();
    }

}
