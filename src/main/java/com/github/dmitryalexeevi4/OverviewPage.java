package com.github.dmitryalexeevi4;

import org.openqa.selenium.*;


public class OverviewPage {
    private final WebDriver webDriver;

    public OverviewPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getPageHeader() {
        return webDriver.findElement(By.className("page-header")).getText();
    }
}
