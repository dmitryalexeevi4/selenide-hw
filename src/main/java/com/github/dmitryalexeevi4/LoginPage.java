package com.github.dmitryalexeevi4;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends AbstractPage {
    private SelenideElement usernameField = $(By.name("username"));
    private SelenideElement passwordField = $(By.name("password"));
    private SelenideElement otpCodeField = $(By.name("otpCode"));

    public LoginPage fieldsInsert(String username, String password) {
        usernameField.clear();
        usernameField.setValue(username);
        passwordField.clear();
        passwordField.setValue(password);
        return this;
    }

    public LoginPage codeInsert(String code) {
        otpCodeField.clear();
        otpCodeField.setValue(code);
        return this;
    }

    public LoginPage loginButton() {
        $(By.xpath("//button")).click();
        return this;
    }
}
