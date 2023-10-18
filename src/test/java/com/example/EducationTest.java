package com.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.net.HttpURLConnection;
import java.net.URL;
import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;

public class EducationTest {

    String URL = "https://resumegenerator.austinchang.ca/";
    Education education;
    WebDriver driver;
    WebElement resumeContainer;
    String containerText;
    int statusCode;
    WebElement addEducation;
    WebElement expandEducation;
    WebElement schoolName;
    WebElement degree;
    WebElement fieldOfStudy;
    WebElement startDate;
    WebElement endDate;
    WebElement location;
    WebElement save;
    WebElement delete;
    WebElement cancel;
    WebDriverWait wait;
    JavascriptExecutor js;

    private static int getStatusCode(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode = connection.getResponseCode();
            connection.disconnect();
            return statusCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver_mac64/chromedriver");
        education = new Education();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        statusCode = getStatusCode(URL);
        wait = new WebDriverWait(driver, 10);
        resumeContainer = wait
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("resume-container"))));
        containerText = resumeContainer.getText();
        js = (JavascriptExecutor) driver;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("existing education is present")
    public void resume_contains_content() {
        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCode);
    }

    @When("user edits the existing education")
    public void existingEducationEdit() {
        By expandButton = By.cssSelector(".add-education-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(education.expandEducationSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='Simon Fraser University']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);
        schoolName = wait.until(ExpectedConditions.elementToBeClickable(education.addSchoolName()));
        degree = wait.until(ExpectedConditions.elementToBeClickable(education.addDegree()));
        startDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        endDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        location = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));
        try {
            clearWebField(schoolName);
            clearWebField(degree);
            clearWebField(fieldOfStudy);
            clearWebField(startDate);
            clearWebField(endDate);
            clearWebField(location);
            schoolName.sendKeys("University of Saskatchewan");
            degree.sendKeys("BComm");
            fieldOfStudy.sendKeys("Human Resources");
            startDate.sendKeys("07/08/09");
            endDate.sendKeys("Present");
            location.sendKeys("Saskatoon, Saskatchewan");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("verify edits are made")
    public void verifyEdits() {
        containerText = resumeContainer.getText();
        // System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("University of Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("BComm"), true);
        Assert.assertEquals(containerText.contains("Human Resources"), true);
        Assert.assertEquals(containerText.contains("07/08/09"), true);
        Assert.assertEquals(containerText.contains("Present"), true);
        Assert.assertEquals(containerText.contains("Saskatoon, Saskatchewan"), true);

        Assert.assertEquals(containerText.contains("Simon Fraser University"), false);
        Assert.assertEquals(containerText.contains("Bachelor in Science"), false);
        Assert.assertEquals(containerText.contains("Computer Science"), false);
    }

    @When("user hides existing education")
    public void hideExistingEducation() {
        By expandButton = By.cssSelector(".add-education-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(education.expandEducationSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        By hideBy = By.xpath(
                "//button[p[text()='Simon Fraser University']]/i[contains(@class, 'fa-regular') and contains(@class, 'fa-eye')]");
        WebElement hideButton = wait.until(ExpectedConditions.elementToBeClickable(hideBy));
        js.executeScript("arguments[0].scrollIntoView(true);", hideButton);
        js.executeScript("arguments[0].click();", hideButton);
    }

    @Then("existing education is hidden")
    public void verifyExistingEducationHidden() {
        containerText = resumeContainer.getText();
        Assert.assertEquals(containerText.contains("Simon Fraser University"), false);
        Assert.assertEquals(containerText.contains("Bachelor in Science"), false);
        Assert.assertEquals(containerText.contains("Computer Science"), false);
        Assert.assertEquals(containerText.contains("Vancouver, BC"), false);
        Assert.assertEquals(containerText.contains("07/2016"), false);
        Assert.assertEquals(containerText.contains("07/2022"), false);

    }

    @When("new education is added")
    public void addNewEducation() {
        By expandButton = By.cssSelector(".add-education-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(education.expandEducationSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addEducation = wait.until(ExpectedConditions.elementToBeClickable(education.createEducation()));
        js.executeScript("arguments[0].scrollIntoView(true);", addEducation);
        js.executeScript("arguments[0].click();", addEducation);

        schoolName = wait.until(ExpectedConditions.elementToBeClickable(education.addSchoolName()));
        degree = wait.until(ExpectedConditions.elementToBeClickable(education.addDegree()));
        startDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        endDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        location = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));

        schoolName.sendKeys("University of Saskatchewan");
        degree.sendKeys("BComm");
        fieldOfStudy.sendKeys("Human Resources");
        startDate.sendKeys("07/08/09");
        endDate.sendKeys("Present");
        location.sendKeys("Saskatoon, Saskatchewan");
    }

    @Then("new education is on resume")
    public void verifyNewEducationAdded() {
        containerText = resumeContainer.getText();
        // System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("University of Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("BComm"), true);
        Assert.assertEquals(containerText.contains("Human Resources"), true);
        Assert.assertEquals(containerText.contains("07/08/09"), true);
        Assert.assertEquals(containerText.contains("Present"), true);
        Assert.assertEquals(containerText.contains("Saskatoon, Saskatchewan"), true);
    }

    @When("new education is saved and edited")
    public void editNewEducation() {
        By expandButton = By.cssSelector(".add-education-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(education.expandEducationSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addEducation = wait.until(ExpectedConditions.elementToBeClickable(education.createEducation()));
        js.executeScript("arguments[0].scrollIntoView(true);", addEducation);
        js.executeScript("arguments[0].click();", addEducation);

        schoolName = wait.until(ExpectedConditions.elementToBeClickable(education.addSchoolName()));
        degree = wait.until(ExpectedConditions.elementToBeClickable(education.addDegree()));
        startDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        endDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        location = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));

        schoolName.sendKeys("University of Saskatchewan");
        degree.sendKeys("BComm");
        fieldOfStudy.sendKeys("Human Resources");
        startDate.sendKeys("07/08/09");
        endDate.sendKeys("Present");
        location.sendKeys("Saskatoon, Saskatchewan");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Check if edits appear
        containerText = resumeContainer.getText();
        // System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("University of Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("BComm"), true);
        Assert.assertEquals(containerText.contains("Human Resources"), true);
        Assert.assertEquals(containerText.contains("07/08/09"), true);
        Assert.assertEquals(containerText.contains("Saskatoon, Saskatchewan"), true);

        // Make edits
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='University of Saskatchewan']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);

        schoolName = wait.until(ExpectedConditions.elementToBeClickable(education.addSchoolName()));
        degree = wait.until(ExpectedConditions.elementToBeClickable(education.addDegree()));
        startDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        endDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        location = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));
        clearWebField(schoolName);
        clearWebField(degree);
        clearWebField(startDate);
        clearWebField(endDate);
        clearWebField(location);
        clearWebField(fieldOfStudy);
        schoolName.sendKeys("University of Waterloo");
        degree.sendKeys("Bachelor in Engineering");
        fieldOfStudy.sendKeys("Nanonengineering");
        startDate.sendKeys("10/11/12");
        endDate.sendKeys("13/14/15");
        location.sendKeys("Waterloo, Ontario");
    }

    @Then("new education edits appear on resume")
    public void editsAppear() {
        containerText = resumeContainer.getText();
        // System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("University of Waterloo"), true);
        Assert.assertEquals(containerText.contains("Bachelor in Engineering"), true);
        Assert.assertEquals(containerText.contains("Nanonengineering"), true);
        Assert.assertEquals(containerText.contains("10/11/12"), true);
        Assert.assertEquals(containerText.contains("13/14/15"), true);
        Assert.assertEquals(containerText.contains("Waterloo, Ontario"), true);

        Assert.assertEquals(containerText.contains("University of Saskatchewan"), false);
        Assert.assertEquals(containerText.contains("BComm"), false);
        Assert.assertEquals(containerText.contains("Human Resources"), false);
        Assert.assertEquals(containerText.contains("07/08/09"), false);
        Assert.assertEquals(containerText.contains("Saskatoon, Saskatchewan"), false);
    }

    @When("new education is saved and hidden")
    public void newEducationSavedAndHidden() {
        By expandButton = By.cssSelector(".add-education-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(education.expandEducationSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addEducation = wait.until(ExpectedConditions.elementToBeClickable(education.createEducation()));
        js.executeScript("arguments[0].scrollIntoView(true);", addEducation);
        js.executeScript("arguments[0].click();", addEducation);

        schoolName = wait.until(ExpectedConditions.elementToBeClickable(education.addSchoolName()));
        degree = wait.until(ExpectedConditions.elementToBeClickable(education.addDegree()));
        startDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        endDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        location = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));

        schoolName.sendKeys("University of Waterloo");
        degree.sendKeys("Bachelor in Engineering");
        fieldOfStudy.sendKeys("Nanonengineering");
        startDate.sendKeys("10/11/12");
        endDate.sendKeys("13/14/15");
        location.sendKeys("Waterloo, Ontario");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Hide new education
        By hideBy = By.xpath(
                "//button[p[text()='University of Waterloo']]/i[contains(@class, 'fa-regular') and contains(@class, 'fa-eye')]");
        WebElement hideButton = wait.until(ExpectedConditions.elementToBeClickable(hideBy));
        js.executeScript("arguments[0].scrollIntoView(true);", hideButton);
        js.executeScript("arguments[0].click();", hideButton);
    }

    @Then("new education is hidden")
    public void verifyNewEducationHidden() {
        containerText = resumeContainer.getText();
        System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("University of Waterloo"), false);
        Assert.assertEquals(containerText.contains("Bachelor in Engineering"), false);
        Assert.assertEquals(containerText.contains("Nanonengineering"), false);
        Assert.assertEquals(containerText.contains("10/11/12"), false);
        Assert.assertEquals(containerText.contains("13/14/15"), false);
        Assert.assertEquals(containerText.contains("Waterloo, Ontario"), false);
    }

    @When("new education is saved and then deleted")
    public void newEducationSavedAndDeleted() {
        By expandButton = By.cssSelector(".add-education-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(education.expandEducationSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addEducation = wait.until(ExpectedConditions.elementToBeClickable(education.createEducation()));
        js.executeScript("arguments[0].scrollIntoView(true);", addEducation);
        js.executeScript("arguments[0].click();", addEducation);

        schoolName = wait.until(ExpectedConditions.elementToBeClickable(education.addSchoolName()));
        degree = wait.until(ExpectedConditions.elementToBeClickable(education.addDegree()));
        startDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        endDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        location = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));

        schoolName.sendKeys("University of Waterloo");
        degree.sendKeys("Bachelor in Engineering");
        fieldOfStudy.sendKeys("Nanonengineering");
        startDate.sendKeys("10/11/12");
        endDate.sendKeys("13/14/15");
        location.sendKeys("Waterloo, Ontario");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Delete new education
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='University of Waterloo']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);

        delete = wait.until(ExpectedConditions.elementToBeClickable(education.educationDelete()));
        js.executeScript("arguments[0].scrollIntoView(true);", delete);
        js.executeScript("arguments[0].click();", delete);
    }

    @Then("new education is deleted from resume")
    public void verifyNewEducationDeleted() {
        containerText = resumeContainer.getText();
        // System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("University of Waterloo"), false);
        Assert.assertEquals(containerText.contains("Bachelor in Engineering"), false);
        Assert.assertEquals(containerText.contains("Nanonengineering"), false);
        Assert.assertEquals(containerText.contains("10/11/12"), false);
        Assert.assertEquals(containerText.contains("13/14/15"), false);
        Assert.assertEquals(containerText.contains("Waterloo, Ontario"), false);
    }

    @When("existing education is deleted")
    public void existingEducationDeleted() {
        By expandButton = By.cssSelector(".add-education-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(education.expandEducationSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='Simon Fraser University']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);

        delete = wait.until(ExpectedConditions.elementToBeClickable(education.educationDelete()));
        js.executeScript("arguments[0].scrollIntoView(true);", delete);
        js.executeScript("arguments[0].click();", delete);
    }

    @Then("existing education is not on resume")
    public void verifyExistingEducationDeleted() {
        containerText = resumeContainer.getText();
        // System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("Simon Fraser University"), false);
        Assert.assertEquals(containerText.contains("Bachelor in Science"), false);
        Assert.assertEquals(containerText.contains("Computer Science"), false);
        Assert.assertEquals(containerText.contains("Vancouver, BC"), false);
        Assert.assertEquals(containerText.contains("07/2016"), false);
        Assert.assertEquals(containerText.contains("07/2022"), false);
    }

    @When("edits are made to an existing education and cancel button is pressed")
    public void editAndCancelExistingEducation() {
        By expandButton = By.cssSelector(".add-education-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(education.expandEducationSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='Simon Fraser University']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);

        // Edits are made
        schoolName = wait.until(ExpectedConditions.elementToBeClickable(education.addSchoolName()));
        degree = wait.until(ExpectedConditions.elementToBeClickable(education.addDegree()));
        startDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        endDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        location = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));
        clearWebField(schoolName);
        clearWebField(degree);
        clearWebField(startDate);
        clearWebField(endDate);
        clearWebField(location);
        clearWebField(fieldOfStudy);
        schoolName.sendKeys("University of Waterloo");
        degree.sendKeys("Bachelor in Engineering");
        fieldOfStudy.sendKeys("Nanonengineering");
        startDate.sendKeys("10/11/12");
        endDate.sendKeys("13/14/15");
        location.sendKeys("Waterloo, Ontario");

        cancel = wait.until(ExpectedConditions.elementToBeClickable(education.educationCancel()));
        js.executeScript("arguments[0].scrollIntoView(true);", cancel);
        js.executeScript("arguments[0].click();", cancel);
    }

    @Then("changes will not be made to existing education")
    public void changesNotMadeExistingEducation() {
        containerText = resumeContainer.getText();
        // System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("University of Waterloo"), false);
        Assert.assertEquals(containerText.contains("Bachelor in Engineering"), false);
        Assert.assertEquals(containerText.contains("Nanonengineering"), false);
        Assert.assertEquals(containerText.contains("10/11/12"), false);
        Assert.assertEquals(containerText.contains("13/14/15"), false);
        Assert.assertEquals(containerText.contains("Waterloo, Ontario"), false);

        Assert.assertEquals(containerText.contains("Simon Fraser University"), true);
        Assert.assertEquals(containerText.contains("Bachelor in Science"), true);
        Assert.assertEquals(containerText.contains("Computer Science"), true);
        Assert.assertEquals(containerText.contains("Vancouver, BC"), true);
        Assert.assertEquals(containerText.contains("07/2016"), true);
        Assert.assertEquals(containerText.contains("07/2022"), true);
    }

    @When("new education is added, saved, edited again, and cancelled")
    public void newEducationAddedSavedEditedCancelled() {
        By expandButton = By.cssSelector(".add-education-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(education.expandEducationSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addEducation = wait.until(ExpectedConditions.elementToBeClickable(education.createEducation()));
        js.executeScript("arguments[0].scrollIntoView(true);", addEducation);
        js.executeScript("arguments[0].click();", addEducation);

        schoolName = wait.until(ExpectedConditions.elementToBeClickable(education.addSchoolName()));
        degree = wait.until(ExpectedConditions.elementToBeClickable(education.addDegree()));
        startDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        endDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        location = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));

        schoolName.sendKeys("University of Waterloo");
        degree.sendKeys("Bachelor in Engineering");
        fieldOfStudy.sendKeys("Nanonengineering");
        startDate.sendKeys("10/11/12");
        endDate.sendKeys("13/14/15");
        location.sendKeys("Waterloo, Ontario");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Edit and cancel edits
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='University of Waterloo']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);

        schoolName = wait.until(ExpectedConditions.elementToBeClickable(education.addSchoolName()));
        degree = wait.until(ExpectedConditions.elementToBeClickable(education.addDegree()));
        startDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        endDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        location = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));
        clearWebField(schoolName);
        clearWebField(degree);
        clearWebField(startDate);
        clearWebField(endDate);
        clearWebField(location);
        clearWebField(fieldOfStudy);
        schoolName.sendKeys("University of Plymouth");
        degree.sendKeys("BKin");
        fieldOfStudy.sendKeys("Kineiosloy");
        startDate.sendKeys("16/09/12");
        endDate.sendKeys("17/10/13");
        location.sendKeys("Plymouth, England");

        cancel = wait.until(ExpectedConditions.elementToBeClickable(education.educationCancel()));
        js.executeScript("arguments[0].scrollIntoView(true);", cancel);
        js.executeScript("arguments[0].click();", cancel);
    }

    @Then("the edit on the new education does not appear")
    public void verifyNewEducationEditDoesNotAppear() {
        containerText = resumeContainer.getText();
        System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("University of Waterloo"), true);
        Assert.assertEquals(containerText.contains("Bachelor in Engineering"), true);
        Assert.assertEquals(containerText.contains("Nanonengineering"), true);
        Assert.assertEquals(containerText.contains("10/11/12"), true);
        Assert.assertEquals(containerText.contains("13/14/15"), true);
        Assert.assertEquals(containerText.contains("Waterloo, Ontario"), true);

        Assert.assertEquals(containerText.contains("University of Plymouth"), false);
        Assert.assertEquals(containerText.contains("BKin"), false);
        Assert.assertEquals(containerText.contains("Kinesiology"), false);
        Assert.assertEquals(containerText.contains("16/09/12"), false);
        Assert.assertEquals(containerText.contains("17/10/13"), false);
        Assert.assertEquals(containerText.contains("Plymouth, England"), false);
    }

    public void clearWebField(WebElement element) {
        while (!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

}
