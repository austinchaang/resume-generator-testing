package com.example;

import org.openqa.selenium.By;

public class Education {

    By expandEducationSection = By.className("add-education-section");
    By createEducation = By.id("create-education");
    By addSchoolName = By.id("school-name");
    By addDegree = By.id("degree");
    By addFieldOfStudy = By.id("field-of-study");
    By addEducationStartDate = By.id("educationstart-date");
    By addEducationEndDate = By.id("end-date");
    By addEducationLocation = By.id("education-location");
    By cancel = By.className("cancel");
    By save = By.className("save");
    By delete = By.className("delete");

    public By expandEducationSection() {
        return expandEducationSection;
    }

    public By createEducation() {
        return createEducation;
    }

    public By addSchoolName() {
        return addSchoolName;
    }

    public By addDegree() {
        return addDegree;
    }

    public By addFieldOfStudy() {
        return addFieldOfStudy;
    }

    public By addEducationStartDate() {
        return addEducationStartDate;
    }

    public By addEducationEndDate() {
        return addEducationEndDate;
    }

    public By addEducationLocation() {
        return addEducationLocation;
    }

    public By educationCancel() {
        return cancel;
    }

    public By educationSave() {
        return save;
    }

    public By educationDelete() {
        return delete;
    }
}
