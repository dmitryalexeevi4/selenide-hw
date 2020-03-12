package com.github.dmitryalexeevi4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestCase {
    Logger LOG = LoggerFactory.getLogger(TestCase.class);
    WebDriver webDriver;

    @BeforeClass
    public void webDriverSetup() {
        LOG.info("Настройка ChromeDriver");
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
    }

    @Test
    public void test() {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://idemo.bspb.ru");
        LoginPage loginPage = new LoginPage(webDriver);
        MainPage mainPage = new MainPage(webDriver);
        OverviewPage overviewPage = new OverviewPage(webDriver);

        LOG.info("Заполнение полей Username и Password...");
        loginPage.fieldsInsert("demo", "demo");

        LOG.info("Проверка на отображение формы двухфакторной авторизации...");
        Assert.assertTrue(loginPage.findElementById("login-form").isDisplayed());
        LOG.info("Форма отображена");

        LOG.info("Заполнение поля для кода...");
        loginPage.codeInsert("0000");

        LOG.info("Проверка на осуществление входа в систему...");
        Assert.assertTrue(mainPage.findElementById("user-greeting").isDisplayed());
        LOG.info("Вход осуществлен");

        LOG.info("Открытие страницы Обзор/Overview...");
        mainPage.openNavBarSection("overview");

        LOG.info("Проверка нахождения на странице \"Обзор\"");
        Assert.assertTrue(overviewPage.getPageTitle().contains(overviewPage.getPageHeader()));
        LOG.info("Успешно");

        LOG.info("Проверка отображения блока \"Финансовая свобода\"...");
        WebElement financialFreedom = overviewPage.findFinancialFreedom();
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.visibilityOf(financialFreedom));
        Assert.assertTrue(financialFreedom.isDisplayed());
        LOG.info("Блок отображен");

        LOG.info("Проверка указанной суммы на соответствие формату...");
        Assert.assertTrue(financialFreedom.getText().matches("\\d{1,3}\\s\\d{3}\\s\\d{3}\\.\\d{2}\\s\\₽"));
        LOG.info("Формату соответствует");

        LOG.info("Наведение курсора на блок Финансовой свободы");
        new Actions(webDriver).moveToElement(financialFreedom).perform();

        LOG.info("Проверка отображения \"Моих средств\"...");
        WebElement myAssets = overviewPage.findElementByClassName("my-assets");
        new WebDriverWait(webDriver, 2).until(ExpectedConditions.visibilityOf(myAssets));
        Assert.assertTrue(myAssets.isDisplayed());
        LOG.info("Cтрока отображена");

        String s = myAssets.getText();
        String moneyCount = s.substring(s.lastIndexOf(" ", 13)).trim();
        LOG.info(moneyCount);

        LOG.info("Проверка указанной суммы на соответствие формату...");
        Assert.assertTrue(moneyCount.matches("\\d{1,3}\\s\\d{3}\\s\\d{3}\\.\\d{2}\\s\\₽"));
        LOG.info("Формату соответствует");
    }

    @AfterClass
    public void webDriverQuit() {
        LOG.info("Закрытие ChromeDriver");
        webDriver.quit();
    }
}
