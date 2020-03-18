package com.github.dmitryalexeevi4.cucumber.stepdefs;

import com.codeborne.selenide.SelenideElement;
import com.github.dmitryalexeevi4.MainPage;
import com.github.dmitryalexeevi4.OverviewPage;
import io.cucumber.java.ru.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.actions;

public class PageSteps {
    MainPage mainPage = new MainPage();
    OverviewPage overviewPage = new OverviewPage();

    @Дано("пользователь входит на страницу Обзор")
    public void пользователь_входит_на_страницу_Обзор() {
        mainPage.openNavBarSection("overview");
    }


    @И("текст во вкладке должен содержать слово Обзор")
    public void текст_во_вкладке_должен_содержать_слово_Обзор() {
        $(overviewPage.pageHeader().shouldHave(text(overviewPage.firstTitleWord())));
    }


    @Также("на странице должен отображаться блок Финансовая свобода")
    public void на_странице_должен_отображаться_блок() {
        $(overviewPage.financialFreedom.shouldBe(visible));
    }


    @Ктомуже("указанная сумма должна соответствовать формату")
    public void указанная_сумма_должна_соответствовать_формату() {
        $(overviewPage.financialFreedom.should(matchText("\\d{1,3}\\s\\d{3}\\s\\d{3}\\.\\d{2}\\s\\₽")));
    }


    @Допустим("пользователь наводит курсор на блок Финансовая свобода")
    public void пользователь_наводит_курсор_на_блок() {
        actions().moveToElement(overviewPage.financialFreedom).perform();
    }


    @Затем("должна отобразиться строка Мои финансы")
    public void должна_отобразиться_строка() {
        $(overviewPage.findElementByClassName("my-assets").shouldBe(visible));
    }


    @А("указанная сумма должна соответсвовать формату")
    public void указанная_сумма_должна_соответсвовать_формату() {
        $(overviewPage.findElementByClassName("my-assets").shouldHave(matchText("\\d{1,3}\\s\\d{3}\\s\\d{3}\\.\\d{2}\\s\\₽")));
    }
}
