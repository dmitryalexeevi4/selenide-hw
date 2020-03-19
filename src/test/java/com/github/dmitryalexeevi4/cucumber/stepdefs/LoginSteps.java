package com.github.dmitryalexeevi4.cucumber.stepdefs;

import com.github.dmitryalexeevi4.LoginPage;
import com.github.dmitryalexeevi4.MainPage;
import io.cucumber.java.ru.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginSteps {
    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();

    @Дано("пользователь входит на страницу авторизации")
    public void openLink() {
        open("https://idemo.bspb.ru");
    }

    @И("заполняет поля логина и пароля")
    public void firstLoginAction() {
        loginPage.fieldsInsert("demo", "demo");
    }

    @Когда("нажимает кнопку Войти")
    public void pressLoginButton() {
        loginPage.loginButton();
    }

    @Тогда("появляется форма двухфакторной авторизации")
    public void loginFormCheck() {
        $(loginPage.findElementById("login-form").shouldBe(visible));
    }

    @Затем("заполненяет поле для кода")
    public void secondLoginAction() {
        loginPage.codeInsert("0000");
    }

    @Тогда("появлется блок приветствия")
    public void userGreetingCheck() {
        $(mainPage.findElementById("user-greeting").shouldBe(visible));
    }
}
