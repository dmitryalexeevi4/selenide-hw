package com.github.dmitryalexeevi4;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

class LoginPage extends AbstractPage {
    @FindBy(name = "username")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "otpCode")
    private WebElement otpCodeField;

    @FindBy(xpath = "//div[@class='secondary-links']/a[1]")
    private WebElement languageButton;


    LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    void languageSwitcher() {
        languageButton.click();
    }

    void fieldsInsert(String username, String password) {
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        findElementById("login-button").click();
    }

    void codeInsert(String code) {
        otpCodeField.clear();
        otpCodeField.sendKeys(code);
        findElementById("login-otp-button").click();
    }
}
