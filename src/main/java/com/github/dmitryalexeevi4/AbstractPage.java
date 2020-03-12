package com.github.dmitryalexeevi4;

import org.openqa.selenium.*;


abstract class AbstractPage {
    protected final WebDriver webDriver;

    AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    String getPageHeader() {
        return webDriver.findElement(By.xpath("//div[@class = 'page-header']/h1")).getText().toLowerCase();
    }

    String getPageTitle() {
        return webDriver.getTitle().toLowerCase();
    }

    void openNavBarSection(String sectionId) {
        webDriver.findElement(By.xpath("//ul[@class = 'navigation-menu nav']//li[@id = '" + sectionId + "']")).click();
    }

    WebElement findElementByClassName(String className) {
        return webDriver.findElement(By.className(className));
    }

    WebElement findElementById(String id) {
        return webDriver.findElement(By.id(id));
    }
}
