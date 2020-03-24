package com.github.dmitryalexeevi4;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class DepositPage extends Page {
    public SelenideElement moreButton = $(By.cssSelector(".offered-products__button-wrap_more"));

    public ElementsCollection getCheckboxes() {
        $(By.cssSelector("tabs-container__tab-panel-active iframe")).is(exist);
        getWebDriver().switchTo().frame(0);
        return $$(By.cssSelector("label.kitt-checkbox-group__checkbox"));
    }

    public ElementsCollection getTabs() {
        return $$(".offered-products__item");
    }

    public SelenideElement getCheckbox(int index) {
        return $$(By.cssSelector("label.kitt-checkbox-group__checkbox")).get(index).find(By.cssSelector("input"));
    }

    public DepositPage clickCheckbox(int index) {
        $$(By.cssSelector(".kitt-checkbox__control")).get(index).click();
        return this;
    }
}
