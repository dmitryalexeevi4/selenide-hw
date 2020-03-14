package com.github.dmitryalexeevi4;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.*;

import static com.codeborne.selenide.Selenide.*;

abstract class AbstractPage {

    String firstTitleWord() {
        return title().substring(0, title().indexOf(' '));
    }

    SelenideElement pageHeader() {
        return $(By.xpath("//div[@class = 'page-header']/h1"));
    }

    void openNavBarSection(String sectionId) {
        $(By.xpath("//ul[@class = 'navigation-menu nav']//li[@id = '" + sectionId + "']")).click();
    }

    SelenideElement findElementByClassName(String className) {
        return $(By.className(className));
    }

    SelenideElement findElementById(String id) {
        return $(By.id(id));
    }

}
