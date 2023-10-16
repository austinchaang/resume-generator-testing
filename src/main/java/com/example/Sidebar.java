package com.example;

import org.openqa.selenium.By;

public class Sidebar {
    By content = By.className("content");
    By customize = By.className("customize");

    public By pressContent() {
        return content;
    }

    public By pressCustomize() {
        return customize;
    }

}
