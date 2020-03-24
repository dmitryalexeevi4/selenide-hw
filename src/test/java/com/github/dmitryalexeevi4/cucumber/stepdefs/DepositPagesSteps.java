package com.github.dmitryalexeevi4.cucumber.stepdefs;

import io.cucumber.java.ru.И;
import org.openqa.selenium.By;
import com.github.dmitryalexeevi4.DepositPage;
import io.cucumber.java.ru.*;
import org.testng.Assert;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.*;

public class DepositPagesSteps {
    DepositPage depositPage = new DepositPage();

    @И("кликает на вкладку {string}")
    public void openInnerTab(String innerTabName) {
        Assert.assertEquals(title(), "«Сбербанк» - Подбор вкладов");
        depositPage.clickInnerTab(innerTabName);
    }

    @Дано("чекбоксы")
    public void checkboxesCheck(List<String> dataTable) {
        depositPage.getCheckboxes()
                .shouldHaveSize(4)
                .shouldHave(exactTexts(dataTable));

        depositPage.getCheckbox(2)
                .shouldBe(checked);
    }

    @И("вкладки")
    public void tabsCheck(List<String> dataTable) {
        depositPage.getTabs()
                .shouldHaveSize(3)
                .shouldHave(textsInAnyOrder(dataTable));
    }

    @Когда("пользователь кликает на чекбоксы")
    public void checkboxClicking() {
        depositPage
                .clickCheckbox(0)
                .clickCheckbox(1);
    }

    @Тогда("остается одна вкладка, {string}")
    public void checkVisibleTabs(String tabName) {
        depositPage.getTabs()
                .shouldHaveSize(1)
                .shouldHave(texts(tabName));
    }

    @Затем("пользователь нажимает на кнопку Подробнее")
    public void buttonClick() {
        depositPage.moreButton.click();
    }

    @И("открывает страницу {string}")
    public void depositManagePageTitleCheck(String pageName) {
        switchTo().window(1);
        Assert.assertEquals(title(), "«Сбербанк» - " + pageName);
        $(By.cssSelector(".product-teaser-full-width > .kit-grid h2.kit-heading")).shouldHave(text(pageName));
    }
}
