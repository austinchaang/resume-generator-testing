package com.example;

import org.openqa.selenium.By;

public class Sidebar {
    By content = By.xpath("//button[@id='content']");
    By customize = By.xpath("//button[@id='customize']");

    public By pressContent() {
        return content;
    }

    public By pressCustomize() {
        return customize;
    }

}
