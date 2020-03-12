package com.github.dmitryalexeevi4;

import org.openqa.selenium.*;

class LoginPage extends AbstractPage {
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By otpCodeField = By.name("otpCode");

    LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    void fieldsInsert(String username, String password) {
        webDriver.findElement(usernameField).clear();
        webDriver.findElement(usernameField).sendKeys(username);
        webDriver.findElement(passwordField).clear();
        webDriver.findElement(passwordField).sendKeys(password);
        webDriver.findElement(By.id("login-button")).click();
    }

    void codeInsert(String code) {
        webDriver.findElement(otpCodeField).clear();
        webDriver.findElement(otpCodeField).sendKeys(code);
        webDriver.findElement(By.id("login-otp-button")).click();
    }
}
