package com.github.dmitryalexeevi4;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

class OverviewPage extends AbstractPage {
    @FindBy (xpath = "//div[@id='can-spend']/span[@class = 'amount-holder']/span")
    protected WebElement financialFreedom;

    OverviewPage(WebDriver webDriver) {
        super(webDriver);
    }

    String finFreedomFunds() {
        return financialFreedom.getText();
    }
}
