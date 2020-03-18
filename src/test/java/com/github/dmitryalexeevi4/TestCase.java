package com.github.dmitryalexeevi4;

import com.codeborne.selenide.SelenideElement;
import org.slf4j.*;
import org.testng.annotations.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class TestCase {
    Logger LOG = LoggerFactory.getLogger(TestCase.class);

    @Test
    public void test() {
        open("https://idemo.bspb.ru");

        LoginPage loginPage = new LoginPage();
        MainPage mainPage = new MainPage();
        OverviewPage overviewPage = new OverviewPage();

        LOG.info("Заполнение полей Username и Password...");
        loginPage
                .fieldsInsert("demo", "demo")
                .loginButton();

        LOG.info("Проверка на отображение формы двухфакторной авторизации...");
        $(loginPage.findElementById("login-form").shouldBe(visible));
        LOG.info("Форма отображена");

        LOG.info("Заполнение поля для кода...");
        loginPage
                .codeInsert("0000")
                .loginButton();

        LOG.info("Проверка на осуществление входа в систему...");
        $(mainPage.findElementById("user-greeting").shouldBe(visible));
        LOG.info("Вход осуществлен");

        LOG.info("Открытие страницы Обзор/Overview...");
        mainPage.openNavBarSection("overview");

        LOG.info("Проверка нахождения на странице \"Обзор\"");
        $(overviewPage.pageHeader().shouldHave(text(overviewPage.firstTitleWord())));
        LOG.info("Успешно");

        LOG.info("Проверка отображения блока \"Финансовая свобода\"...");
        $(overviewPage.financialFreedom.shouldBe(visible));
        LOG.info("Блок отображен");

        LOG.info("Проверка указанной суммы на соответствие формату...");
        $(overviewPage.financialFreedom.should(matchText("\\d{1,3}\\s\\d{3}\\s\\d{3}\\.\\d{2}\\s\\₽")));
        LOG.info("Формату соответствует");

        LOG.info("Наведение курсора на блок Финансовой свободы");
        actions().moveToElement(overviewPage.financialFreedom).perform();

        LOG.info("Проверка отображения \"Моих средств\"...");
        SelenideElement myAssets = overviewPage.findElementByClassName("my-assets");
        $(myAssets.shouldBe(visible));
        LOG.info("Cтрока отображена");

        LOG.info("Проверка указанной суммы на соответствие формату...");
        $(myAssets.shouldHave(matchText("\\d{1,3}\\s\\d{3}\\s\\d{3}\\.\\d{2}\\s\\₽")));
        LOG.info("Формату соответствует");
    }
}
