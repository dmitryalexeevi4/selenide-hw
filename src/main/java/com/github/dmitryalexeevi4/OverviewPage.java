package com.github.dmitryalexeevi4;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

class OverviewPage extends AbstractPage {
    SelenideElement financialFreedom = $(By.xpath("//div[@id='can-spend']/span[@class = 'amount-holder']/span"));
}
