package com.github.dmitryalexeevi4.cucumber.stepdefs;

import com.github.dmitryalexeevi4.*;
import io.cucumber.java.ru.*;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;

public class PersonPageSteps {
    PersonPage personPage = new PersonPage();
    DepositPage depositPage = new DepositPage();

    @Дано("пользователь входит на сайт {string}")
    public void openLink(String link) {
        open(link);
        $(By.cssSelector("a.cookie-warning__close")).click();
    }

    @Тогда("открывается страница {string}")
    public void personPageTitleCheck(String pageName) {
        Assert.assertEquals(personPage.getTitle(), "«Сбербанк» - " + pageName);
    }

    @Затем("пользователь открывает страницу {string}")
    public void openSection(String pageName) {
        personPage.openTab("Вклады").openSection("Вклады");
        Assert.assertEquals(depositPage.getTitle(), "«Сбербанк» - " + pageName);
    }
}
