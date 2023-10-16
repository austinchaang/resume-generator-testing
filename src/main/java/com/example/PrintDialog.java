package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PrintDialog {

    WebDriver driver = new ChromeDriver();
    By destinationSelect = By.id("md-select");
    By selectPDF;
    By saveButton = By.className("action-button");

    public By selectDestination() {
        return destinationSelect;
    }

    public void PDFSelect() {
        WebElement dropdown = driver.findElement(selectDestination());
        Select select = new Select(dropdown);
        select.selectByVisibleText("Save as PDF");
    }

    public By saveButton() {
        return saveButton;
    }

}
