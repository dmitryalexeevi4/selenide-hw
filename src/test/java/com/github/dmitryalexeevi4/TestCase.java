package com.github.dmitryalexeevi4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.*;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.xml.dom.Tag;

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

    @Test(groups = "login")
    public void loginPageTest() {
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://idemo.bspb.ru");

        new LoginPage(webDriver).login("demo", "demo");

        LOG.info("Проверка на отображение формы двухфакторной авторизации...");
        Assert.assertTrue(webDriver.findElement(By.id("login-form")).isDisplayed());
        LOG.info("Форма отображена");
    }

    @Test(groups = "login", dependsOnMethods = "loginPageTest")
    public void otpCodeTest() {
        new LoginPage(webDriver).login("0000");

        LOG.info("Проверка на осуществление входа в систему...");
        Assert.assertTrue(webDriver.findElement(By.id("user-greeting")).isDisplayed());
        LOG.info("Вход осуществлен");
    }

    @Test(dependsOnGroups = "login")
    public void sectionSelectTest() {
        new MainPage(webDriver).openNavBarSection("Обзор");

        LOG.info("Проверка нахождения на странице \"Обзор\"");
        Assert.assertTrue(new OverviewPage(webDriver).getPageHeader().contains("Обзор"));
        WebElement financialFreedomBlock = webDriver.findElement(By.xpath("//div[@id = 'can-spend']//span[@class='amount']"));
        LOG.info("Успешно");

        LOG.info("Проверка отображения блока \"Финансовая свобода\"...");
        Assert.assertTrue(financialFreedomBlock.isDisplayed());
        LOG.info("Блок отображен");

        LOG.info("Проверка указанной суммы на соответствие формату...");
        Assert.assertTrue(financialFreedomBlock.getText().matches("\\d{1,3}\\s\\d{3}\\s\\d{3}\\.\\d{2}\\s\\₽"));
        LOG.info("Формату соответствует");
    }

    @Test(dependsOnMethods = "sectionSelectTest")
    public void moveCursorToTest() throws InterruptedException {
        LOG.info("Наводим курсок на блок Финансовой свободы");
        new Actions(webDriver).moveToElement(webDriver.findElement(By.id("can-spend"))).perform();

        LOG.info("Проверка отображения \"Моих средств\"...");
        WebElement myAssets = webDriver.findElement(By.className("my-assets"));
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
