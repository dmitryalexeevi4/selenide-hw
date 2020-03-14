package com.github.dmitryalexeevi4;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;

import static com.codeborne.selenide.Selenide.$;

class LoginPage extends AbstractPage {
    private SelenideElement usernameField = $(By.name("username"));
    private SelenideElement passwordField = $(By.name("password"));
    private SelenideElement otpCodeField = $(By.name("otpCode"));

    void fieldsInsert(String username, String password) {
        usernameField.clear();
        usernameField.setValue(username);
        passwordField.clear();
        passwordField.setValue(password);
        findElementById("login-button").click();
    }

    void codeInsert(String code) {
        otpCodeField.clear();
        otpCodeField.setValue(code);
        findElementById("login-otp-button").click();
    }
}
