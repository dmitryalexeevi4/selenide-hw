package com.github.dmitryalexeevi4;

import org.openqa.selenium.*;

public class MainPage {
    private final WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void openNavBarSection(String section) {
        webDriver.findElement(By.xpath("//ul[@class = 'navigation-menu nav']//li//a[.='"+section+"']")).click();
    }
}
