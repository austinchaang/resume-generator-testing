package com.example;

import org.openqa.selenium.By;

public class Experience {

    By expandExperienceSection = By.className("add-experience-section");
    By createExperience = By.className("create-form");
    By addCompanyName = By.id("company-name");
    By addPositionTitle = By.id("position-title");
    By addEmploymentType = By.id("employment-type");
    By addExperienceStartDate = By.id("experience-start-date");
    By addExperienceEndDate = By.id("experience-end-date");
    By addExperienceLocation = By.id("experience-location");
    By addDescription = By.id("description");
    By cancel = By.className("cancel");
    By save = By.className("save");
    By delete = By.className("delete");

    public By expandExperienceSection() {
        return expandExperienceSection;
    }

    public By createExperience() {
        return createExperience;
    }

    public By addCompanyName() {
        return addCompanyName;
    }

    public By addPositionTitle() {
        return addPositionTitle;
    }

    public By addEmploymentType() {
        return addEmploymentType;
    }

    public By addExperienceStartDate() {
        return addExperienceStartDate;
    }

    public By addExperienceEndDate() {
        return addExperienceEndDate;
    }

    public By addExperienceLocation() {
        return addExperienceLocation;
    }

    public By addDescription() {
        return addDescription;
    }

    public By experienceCancel() {
        return cancel;
    }

    public By experienceSave() {
        return save;
    }

    public By experienceDelete() {
        return delete;
    }

}
