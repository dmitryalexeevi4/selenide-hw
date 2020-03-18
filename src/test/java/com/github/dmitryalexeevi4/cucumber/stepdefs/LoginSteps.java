package com.github.dmitryalexeevi4.cucumber.stepdefs;

import com.github.dmitryalexeevi4.LoginPage;
import com.github.dmitryalexeevi4.MainPage;
import io.cucumber.java.ru.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();

    @Дано("пользователь входит на страницу логина")
    public void пользователь_входит_на_страницу_логина() {
        open("https://idemo.bspb.ru");
    }

    @И("заполняет поля логина и пароля")
    public void заполняет_поля_логина_и_пароля() {
        loginPage.fieldsInsert("demo", "demo");
    }

    @Когда("нажимает кнопку Войти")
    public void нажимает_кнопку_Войти() {
        loginPage.loginButton();
    }

    @Тогда("появляется форма двухфакторной авторизации")
    public void появляется_форма_двухфакторной_авторизации() {
        $(loginPage.findElementById("login-form").shouldBe(visible));
    }

    @Затем("заполненяет поле для кода")
    public void заполненяет_поле_для_кода() {
        loginPage.codeInsert("0000");
    }

    @Тогда("появлется блок приветствия")
    public void появлется_блок_приветствия() {
        $(mainPage.findElementById("user-greeting").shouldBe(visible));
    }
}
