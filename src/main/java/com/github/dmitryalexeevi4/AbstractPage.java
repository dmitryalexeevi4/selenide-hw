package com.github.dmitryalexeevi4;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;

import static com.codeborne.selenide.Selenide.*;

public abstract class AbstractPage {

    public String firstTitleWord() {
        return title().substring(0, title().indexOf(' '));
    }

    public SelenideElement pageHeader() {
        return $(By.xpath("//div[@class = 'page-header']/h1"));
    }

    public AbstractPage openNavBarSection(String sectionId) {
        $(By.xpath("//ul[@class = 'navigation-menu nav']//li[@id = '" + sectionId + "']")).click();
        return this;
    }

    public SelenideElement findElementByClassName(String className) {
        return $(By.className(className));
    }

    public SelenideElement findElementById(String id) {
        return $(By.id(id));
    }

}
