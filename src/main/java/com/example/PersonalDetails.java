package com.example;

import org.openqa.selenium.By;

public class PersonalDetails {
    By name = By.id("full-name");
    By title = By.id("title");
    By email = By.id("email");
    By phoneNumber = By.id("phone-number");
    By address = By.id("address");
    By url = By.id("url");

    public By pressNameField() {
        return name;
    }

    public By pressTitleField() {
        return title;
    }

    public By pressEmailField() {
        return email;
    }

    public By pressPhoneNumberField() {
        return phoneNumber;
    }

    public By pressAddressField() {
        return address;
    }

    public By pressURLField() {
        return url;
    }

}
