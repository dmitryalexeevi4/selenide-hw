package com.github.dmitryalexeevi4.cucumber.stepdefs;

import com.github.dmitryalexeevi4.*;
import io.cucumber.java.ru.*;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;

public class PersonPageSteps {
    Page personPage = new Page();

    @Дано("пользователь входит на сайт {string}")
    public void openLink(String link) {
        open(link);
        $(By.cssSelector("a.cookie-warning__close")).click();
    }

    @Тогда("открывается страница {string}")
    public void personPageTitleCheck(String pageName) {
        Assert.assertEquals(title(), "«Сбербанк» - " + pageName);
    }

    @Затем("пользователь открывает страницу Подбор вкладов")
    public void openSection() {
        personPage.openTab("Вклады").openSection("Вклады");
    }
}
