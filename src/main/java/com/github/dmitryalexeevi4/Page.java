package com.github.dmitryalexeevi4;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Page {

    public Page openTab(String tabName) {
        $(By.xpath("//ul[@class = 'lg-menu__list']/li/button/span[text() = '" + tabName + "']")).click();
        return this;
    }

    public Page openSection(String sectionName) {
        new Actions(getWebDriver()).moveToElement($(By.xpath("//ul[@class = 'lg-menu__sub-list']/li/a[text() = '" + sectionName + "']"))).click().perform();
        return this;
    }

    public Page clickInnerTab(String innerTabName) {
        $(By.xpath("//ul[@class='tabs-container__nav-tabs']/li/a[text()= '" + innerTabName + "']")).click();
        return this;
    }
}
