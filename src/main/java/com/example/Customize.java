package com.example;

import org.openqa.selenium.By;

public class Customize {

    By topVisual = By.className("top-visual");
    By leftVisual = By.className("left-visual");
    By rightVisual = By.className("right-visual");
    By chooseColor = By.className("accent-color");
    By serif = By.className("serif-btn");
    By sans = By.id("sans-btn");
    By mono = By.className("monospace-btn");

    public By pressTopVisual() {
        return topVisual;
    }

    public By pressLeftVisual() {
        return leftVisual;
    }

    public By pressRightVisual() {
        return rightVisual;
    }

    public By pressChooseColor() {
        return chooseColor;
    }

    public By pressSerif() {
        return serif;
    }

    public By pressSans() {
        return sans;
    }

    public By pressMono() {
        return mono;
    }
}
