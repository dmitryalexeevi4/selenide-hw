package com.github.dmitryalexeevi4;

import org.openqa.selenium.*;

class OverviewPage extends AbstractPage {
    private By financialFreedom = By.xpath("//div[@id='can-spend']/span[@class = 'amount-holder']/span");

    OverviewPage(WebDriver webDriver) {
        super(webDriver);
    }

    WebElement findFinancialFreedom() {
        return webDriver.findElement(financialFreedom);
    }
}
