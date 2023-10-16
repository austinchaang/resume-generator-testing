package com.example;

import org.openqa.selenium.By;

public class TemplateLoader {
    By clearResume = By.className("clear-resume");
    By loadTemplate = By.className("load-example");
    By printResume = By.className("print-resume");

    public By clearResume() {
        return clearResume;
    }

    public By loadTemplate() {
        return loadTemplate;
    }

    public By printResume() {
        return printResume;
    }

}
