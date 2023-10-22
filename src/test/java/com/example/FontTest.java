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

public class FontTest {

    String URL = "https://resumegenerator.austinchang.ca/";
    Education education;
    Experience experience;
    PersonalDetails personalDetails;
    Sidebar sidebar;
    Customize customize;
    WebDriver driver;
    WebElement resumeContainer;
    String containerText;
    int statusCode;
    WebElement addEducation;
    WebElement expandEducation;
    WebElement schoolName;
    WebElement degree;
    WebElement fieldOfStudy;
    WebElement educationStartDate;
    WebElement educationEndDate;
    WebElement educationLocation;
    WebElement addExperience;
    WebElement expandExperience;
    WebElement companyName;
    WebElement positionTitle;
    WebElement employmentType;
    WebElement experienceStartDate;
    WebElement experienceEndDate;
    WebElement experienceLocation;
    WebElement description;
    WebElement name;
    WebElement title;
    WebElement email;
    WebElement phoneNumber;
    WebElement address;
    WebElement url;
    WebElement save;
    WebElement delete;
    WebElement cancel;
    WebElement contentButton;
    WebElement customizeButton;
    WebElement serifBtn;
    WebElement monoBtn;
    WebDriverWait wait;
    JavascriptExecutor js;
    By font;

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
        experience = new Experience();
        personalDetails = new PersonalDetails();
        sidebar = new Sidebar();
        customize = new Customize();
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

    @Given("default font is sans")
    public void resume_contains_content() {
        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCode);
    }

    @When("existing content on resume is edited in sans font")
    public void editExistingContentSansFont() {
        // Edit existing Personal Details
        name = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressNameField()));
        title = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressTitleField()));
        email = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressEmailField()));
        phoneNumber = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressPhoneNumberField()));
        address = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressAddressField()));
        url = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressURLField()));
        clearWebField(name);
        clearWebField(title);
        clearWebField(email);
        clearWebField(phoneNumber);
        clearWebField(address);
        clearWebField(url);
        name.sendKeys("Brian Garrovillas");
        title.sendKeys("Software Test Engineer");
        email.sendKeys("brian.gvillas@outlook.com");
        phoneNumber.sendKeys("647-908-0920");
        address.sendKeys("354-9833 66th Avenue South, Calgary, Alberta");

        // Edit existing Education
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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));
        clearWebField(schoolName);
        clearWebField(degree);
        clearWebField(fieldOfStudy);
        clearWebField(educationStartDate);
        clearWebField(educationEndDate);
        clearWebField(educationLocation);
        schoolName.sendKeys("University of Saskatchewan");
        degree.sendKeys("BComm");
        fieldOfStudy.sendKeys("Human Resources");
        educationStartDate.sendKeys("07/08/09");
        educationEndDate.sendKeys("Present");
        educationLocation.sendKeys("Saskatoon, Saskatchewan");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);
        // Edit existing Experience
        expandButton = By.cssSelector(".add-experience-section button.expand-section");
        section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);
        button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='Top Hat Software Inc.']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);
        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName));
        employmentType = wait.until(ExpectedConditions.elementToBeClickable(experience.addEmploymentType()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));
        description = wait.until(ExpectedConditions.elementToBeClickable(experience.addDescription()));
        clearWebField(companyName);
        clearWebField(positionTitle);
        clearWebField(employmentType);
        clearWebField(experienceStartDate);
        clearWebField(experienceEndDate);
        clearWebField(experienceLocation);
        clearWebField(description);
        companyName.sendKeys("Triple O Studios");
        positionTitle.sendKeys("Software Development Engineer 2");
        employmentType.sendKeys("Full-Time");
        experienceStartDate.sendKeys("June 6, 1998");
        experienceEndDate.sendKeys("Present");
        experienceLocation.sendKeys("San Diego, California, USA");
        description.sendKeys("Built an application that improved company's bottom line by 25%");
        save = wait.until(ExpectedConditions.elementToBeClickable(experience.experienceSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);
    }

    @Then("sans font remains")
    public void sansFontRemains() {
        containerText = resumeContainer.getText();
        // Personal Details tests
        Assert.assertEquals(containerText.contains("Brian Garrovillas"), true);
        Assert.assertEquals(containerText.contains("Software Test Engineer"), true);
        Assert.assertEquals(containerText.contains("brian.gvillas@outlook.com"), true);
        Assert.assertEquals(containerText.contains("647-908-0920"), true);
        Assert.assertEquals(containerText.contains("354-9833 66th Avenue South, Calgary, Alberta"), true);
        Assert.assertEquals(containerText.contains("bill.campbell@gmail.com"), false);
        Assert.assertEquals(containerText.contains("Burnaby, BC"), false);
        // Education tests
        Assert.assertEquals(containerText.contains("University of Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("BComm"), true);
        Assert.assertEquals(containerText.contains("Human Resources"), true);
        Assert.assertEquals(containerText.contains("07/08/09"), true);
        Assert.assertEquals(containerText.contains("Present"), true);
        Assert.assertEquals(containerText.contains("Saskatoon, Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("Simon Fraser University"), false);
        Assert.assertEquals(containerText.contains("Bachelor in Science"), false);
        Assert.assertEquals(containerText.contains("Computer Science"), false);
        // Experience tests
        Assert.assertEquals(containerText.contains("Triple O Studios"), true);
        Assert.assertEquals(containerText.contains("Software Development Engineer 2"), true);
        Assert.assertEquals(containerText.contains("Full-Time"), true);
        Assert.assertEquals(containerText.contains("June 6, 1998"), true);
        Assert.assertEquals(containerText.contains("Present"), true);
        Assert.assertEquals(containerText.contains("Built an application that improved company's bottom line by 25%"),
                true);
        Assert.assertEquals(containerText.contains("San Diego, California, USA"), true);
        Assert.assertEquals(containerText.contains("Full-Stack Software Engineer"), false);
        Assert.assertEquals(containerText.contains("Top Hat Software Inc."), false);
        // Verify that sans font is still present
        font = By.xpath("//button[@id='sans-btn' and @data-selected='true']");
        WebElement fontElement = driver.findElement(font);
        String elementClasses = fontElement.getAttribute("data-selected");
        Assert.assertEquals(elementClasses, "true");
    }

    @When("new content is added in sans font")
    public void newContentAddedSansFont() {
        // Add new Education
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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));

        schoolName.sendKeys("University of Saskatchewan");
        degree.sendKeys("BComm");
        fieldOfStudy.sendKeys("Human Resources");
        educationStartDate.sendKeys("07/08/09");
        educationEndDate.sendKeys("Present");
        educationLocation.sendKeys("Saskatoon, Saskatchewan");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Add new Experience
        expandButton = By.cssSelector(".add-experience-section button.expand-section");
        section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addExperience = wait.until(ExpectedConditions.elementToBeClickable(experience.createExperience()));
        js.executeScript("arguments[0].scrollIntoView(true);", addExperience);
        js.executeScript("arguments[0].click();", addExperience);

        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));

        companyName.sendKeys("Terranova");
        positionTitle.sendKeys("Product Manager");
        experienceStartDate.sendKeys("July 4, 2021");
        experienceEndDate.sendKeys("January 23, 2023");
        experienceLocation.sendKeys("Berlin, Germany");
    }

    @Then("font is still sans")
    public void verifySansFont() {
        containerText = resumeContainer.getText();
        // Education tests
        Assert.assertEquals(containerText.contains("University of Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("BComm"), true);
        Assert.assertEquals(containerText.contains("Human Resources"), true);
        Assert.assertEquals(containerText.contains("07/08/09"), true);
        Assert.assertEquals(containerText.contains("Present"), true);
        Assert.assertEquals(containerText.contains("Saskatoon, Saskatchewan"), true);
        // Experience tests
        Assert.assertEquals(containerText.contains("Terranova"), true);
        Assert.assertEquals(containerText.contains("Product Manager"), true);
        Assert.assertEquals(containerText.contains("July 4, 2021"), true);
        Assert.assertEquals(containerText.contains("January 23, 2023"), true);
        Assert.assertEquals(containerText.contains("Berlin, Germany"), true);
        // Verify that sans font is still present
        font = By.xpath("//button[@id='sans-btn' and @data-selected='true']");
        WebElement fontElement = driver.findElement(font);
        String elementClasses = fontElement.getAttribute("data-selected");
        Assert.assertEquals(elementClasses, "true");
    }

    @When("new content is edited in sans font")
    public void newContentEditedSansFont() {
        // Edit new Education
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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));

        schoolName.sendKeys("University of Saskatchewan");
        degree.sendKeys("BComm");
        fieldOfStudy.sendKeys("Human Resources");
        educationStartDate.sendKeys("07/08/09");
        educationEndDate.sendKeys("Present");
        educationLocation.sendKeys("Saskatoon, Saskatchewan");

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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));
        clearWebField(schoolName);
        clearWebField(degree);
        clearWebField(educationStartDate);
        clearWebField(educationEndDate);
        clearWebField(educationLocation);
        clearWebField(fieldOfStudy);
        schoolName.sendKeys("University of Waterloo");
        degree.sendKeys("Bachelor in Engineering");
        fieldOfStudy.sendKeys("Nanonengineering");
        educationStartDate.sendKeys("10/11/12");
        educationEndDate.sendKeys("13/14/15");
        educationLocation.sendKeys("Waterloo, Ontario");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Edit new Experience
        expandButton = By.cssSelector(".add-experience-section button.expand-section");
        section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addExperience = wait.until(ExpectedConditions.elementToBeClickable(experience.createExperience()));
        js.executeScript("arguments[0].scrollIntoView(true);", addExperience);
        js.executeScript("arguments[0].click();", addExperience);

        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));

        companyName.sendKeys("Meta");
        positionTitle.sendKeys("Product Owner");
        experienceStartDate.sendKeys("December 10, 1998");
        experienceEndDate.sendKeys("May 10, 2000");
        experienceLocation.sendKeys("Brighton, England");

        save = wait.until(ExpectedConditions.elementToBeClickable(experience.experienceSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Check if edits appear
        containerText = resumeContainer.getText();
        Assert.assertEquals(containerText.contains("Meta"), true);
        Assert.assertEquals(containerText.contains("Product Owner"), true);
        Assert.assertEquals(containerText.contains("December 10, 1998"), true);
        Assert.assertEquals(containerText.contains("May 10, 2000"), true);
        Assert.assertEquals(containerText.contains("Brighton, England"), true);

        // Make edits
        button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='Meta']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);

        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));
        clearWebField(companyName);
        clearWebField(positionTitle);
        clearWebField(experienceStartDate);
        clearWebField(experienceEndDate);
        clearWebField(experienceLocation);
        companyName.sendKeys("Studio TTT");
        positionTitle.sendKeys("Technical Product Manager");
        experienceStartDate.sendKeys("07/08/1992");
        experienceEndDate.sendKeys("09/09/09");
        experienceLocation.sendKeys("Portland, OR, USA");

        save = wait.until(ExpectedConditions.elementToBeClickable(experience.experienceSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

    }

    @Then("font remains sans")
    public void checkSansFontRemains() {
        containerText = resumeContainer.getText();
        // Education tests
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
        // Experience tests
        Assert.assertEquals(containerText.contains("Studio TTT"), true);
        Assert.assertEquals(containerText.contains("Technical Product Manager"), true);
        Assert.assertEquals(containerText.contains("07/08/1992"), true);
        Assert.assertEquals(containerText.contains("09/09/09"), true);
        Assert.assertEquals(containerText.contains("Portland, OR, USA"), true);
        Assert.assertEquals(containerText.contains("Meta"), false);
        Assert.assertEquals(containerText.contains("Product Owner"), false);
        Assert.assertEquals(containerText.contains("December 10, 1998"), false);
        Assert.assertEquals(containerText.contains("May 10, 2000"), false);
        Assert.assertEquals(containerText.contains("Brighton, England"), false);
        // Verify that sans font is still present
        font = By.xpath("//button[@id='sans-btn' and @data-selected='true']");
        WebElement fontElement = driver.findElement(font);
        String elementClasses = fontElement.getAttribute("data-selected");
        Assert.assertEquals(elementClasses, "true");
    }

    @When("font is changed to serif and existing content is edited")
    public void editExistingContentSerifFont() {
        // Change font to serif
        customizeButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressCustomize()));
        js.executeScript("arguments[0].scrollIntoView(true);", customizeButton);
        js.executeScript("arguments[0].click();", customizeButton);

        serifBtn = wait.until(ExpectedConditions.elementToBeClickable(customize.pressSerif()));
        js.executeScript("arguments[0].scrollIntoView(true);", serifBtn);
        js.executeScript("arguments[0].click();", serifBtn);

        contentButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressContent()));
        js.executeScript("arguments[0].scrollIntoView(true);", contentButton);
        js.executeScript("arguments[0].click();", contentButton);

        // Edit existing Personal Details
        name = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressNameField()));
        title = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressTitleField()));
        email = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressEmailField()));
        phoneNumber = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressPhoneNumberField()));
        address = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressAddressField()));
        url = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressURLField()));
        clearWebField(name);
        clearWebField(title);
        clearWebField(email);
        clearWebField(phoneNumber);
        clearWebField(address);
        clearWebField(url);
        name.sendKeys("Brian Garrovillas");
        title.sendKeys("Software Test Engineer");
        email.sendKeys("brian.gvillas@outlook.com");
        phoneNumber.sendKeys("647-908-0920");
        address.sendKeys("354-9833 66th Avenue South, Calgary, Alberta");

        // Edit existing Education
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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));
        clearWebField(schoolName);
        clearWebField(degree);
        clearWebField(fieldOfStudy);
        clearWebField(educationStartDate);
        clearWebField(educationEndDate);
        clearWebField(educationLocation);
        schoolName.sendKeys("University of Saskatchewan");
        degree.sendKeys("BComm");
        fieldOfStudy.sendKeys("Human Resources");
        educationStartDate.sendKeys("07/08/09");
        educationEndDate.sendKeys("Present");
        educationLocation.sendKeys("Saskatoon, Saskatchewan");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);
        // Edit existing Experience
        expandButton = By.cssSelector(".add-experience-section button.expand-section");
        section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);
        button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='Top Hat Software Inc.']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);
        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName));
        employmentType = wait.until(ExpectedConditions.elementToBeClickable(experience.addEmploymentType()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));
        description = wait.until(ExpectedConditions.elementToBeClickable(experience.addDescription()));
        clearWebField(companyName);
        clearWebField(positionTitle);
        clearWebField(employmentType);
        clearWebField(experienceStartDate);
        clearWebField(experienceEndDate);
        clearWebField(experienceLocation);
        clearWebField(description);
        companyName.sendKeys("Triple O Studios");
        positionTitle.sendKeys("Software Development Engineer 2");
        employmentType.sendKeys("Full-Time");
        experienceStartDate.sendKeys("June 6, 1998");
        experienceEndDate.sendKeys("Present");
        experienceLocation.sendKeys("San Diego, California, USA");
        description.sendKeys("Built an application that improved company's bottom line by 25%");
        save = wait.until(ExpectedConditions.elementToBeClickable(experience.experienceSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);
    }

    @Then("serif font remains")
    public void serifRemains() {
        containerText = resumeContainer.getText();
        // Personal Details tests
        Assert.assertEquals(containerText.contains("Brian Garrovillas"), true);
        Assert.assertEquals(containerText.contains("Software Test Engineer"), true);
        Assert.assertEquals(containerText.contains("brian.gvillas@outlook.com"), true);
        Assert.assertEquals(containerText.contains("647-908-0920"), true);
        Assert.assertEquals(containerText.contains("354-9833 66th Avenue South, Calgary, Alberta"), true);
        Assert.assertEquals(containerText.contains("bill.campbell@gmail.com"), false);
        Assert.assertEquals(containerText.contains("Burnaby, BC"), false);
        // Education tests
        Assert.assertEquals(containerText.contains("University of Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("BComm"), true);
        Assert.assertEquals(containerText.contains("Human Resources"), true);
        Assert.assertEquals(containerText.contains("07/08/09"), true);
        Assert.assertEquals(containerText.contains("Present"), true);
        Assert.assertEquals(containerText.contains("Saskatoon, Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("Simon Fraser University"), false);
        Assert.assertEquals(containerText.contains("Bachelor in Science"), false);
        Assert.assertEquals(containerText.contains("Computer Science"), false);
        // Experience tests
        Assert.assertEquals(containerText.contains("Triple O Studios"), true);
        Assert.assertEquals(containerText.contains("Software Development Engineer 2"), true);
        Assert.assertEquals(containerText.contains("Full-Time"), true);
        Assert.assertEquals(containerText.contains("June 6, 1998"), true);
        Assert.assertEquals(containerText.contains("Present"), true);
        Assert.assertEquals(containerText.contains("Built an application that improved company's bottom line by 25%"),
                true);
        Assert.assertEquals(containerText.contains("San Diego, California, USA"), true);
        Assert.assertEquals(containerText.contains("Full-Stack Software Engineer"), false);
        Assert.assertEquals(containerText.contains("Top Hat Software Inc."), false);
        // Verify that serif font is still present
        font = By.xpath("//button[@class='serif-btn' and @data-selected='true']");
        WebElement fontElement = driver.findElement(font);
        String elementClasses = fontElement.getAttribute("data-selected");
        Assert.assertEquals(elementClasses, "true");
    }

    @When("font is changed to serif and new content is added")
    public void addNewContentSerifFont() {
        // Change font to serif
        customizeButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressCustomize()));
        js.executeScript("arguments[0].scrollIntoView(true);", customizeButton);
        js.executeScript("arguments[0].click();", customizeButton);

        serifBtn = wait.until(ExpectedConditions.elementToBeClickable(customize.pressSerif()));
        js.executeScript("arguments[0].scrollIntoView(true);", serifBtn);
        js.executeScript("arguments[0].click();", serifBtn);

        contentButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressContent()));
        js.executeScript("arguments[0].scrollIntoView(true);", contentButton);
        js.executeScript("arguments[0].click();", contentButton);

        // Add new Education
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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));

        schoolName.sendKeys("University of Saskatchewan");
        degree.sendKeys("BComm");
        fieldOfStudy.sendKeys("Human Resources");
        educationStartDate.sendKeys("07/08/09");
        educationEndDate.sendKeys("Present");
        educationLocation.sendKeys("Saskatoon, Saskatchewan");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Add new Experience
        expandButton = By.cssSelector(".add-experience-section button.expand-section");
        section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addExperience = wait.until(ExpectedConditions.elementToBeClickable(experience.createExperience()));
        js.executeScript("arguments[0].scrollIntoView(true);", addExperience);
        js.executeScript("arguments[0].click();", addExperience);

        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));

        companyName.sendKeys("Terranova");
        positionTitle.sendKeys("Product Manager");
        experienceStartDate.sendKeys("July 4, 2021");
        experienceEndDate.sendKeys("January 23, 2023");
        experienceLocation.sendKeys("Berlin, Germany");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);
    }

    @Then("font is still serif")
    public void verifySerifFontRemains() {
        containerText = resumeContainer.getText();
        containerText = resumeContainer.getText();
        // Education tests
        Assert.assertEquals(containerText.contains("University of Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("BComm"), true);
        Assert.assertEquals(containerText.contains("Human Resources"), true);
        Assert.assertEquals(containerText.contains("07/08/09"), true);
        Assert.assertEquals(containerText.contains("Present"), true);
        Assert.assertEquals(containerText.contains("Saskatoon, Saskatchewan"), true);
        // Experience tests
        Assert.assertEquals(containerText.contains("Terranova"), true);
        Assert.assertEquals(containerText.contains("Product Manager"), true);
        Assert.assertEquals(containerText.contains("July 4, 2021"), true);
        Assert.assertEquals(containerText.contains("January 23, 2023"), true);
        Assert.assertEquals(containerText.contains("Berlin, Germany"), true);
        // Verify that serif font is still present
        font = By.xpath("//button[@class='serif-btn' and @data-selected='true']");
        WebElement fontElement = driver.findElement(font);
        String elementClasses = fontElement.getAttribute("data-selected");
        Assert.assertEquals(elementClasses, "true");
    }

    @When("font is changed to serif and new content is edited")
    public void serifFontAndNewContentEdited() {
        // Change font to serif
        customizeButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressCustomize()));
        js.executeScript("arguments[0].scrollIntoView(true);", customizeButton);
        js.executeScript("arguments[0].click();", customizeButton);

        serifBtn = wait.until(ExpectedConditions.elementToBeClickable(customize.pressSerif()));
        js.executeScript("arguments[0].scrollIntoView(true);", serifBtn);
        js.executeScript("arguments[0].click();", serifBtn);

        contentButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressContent()));
        js.executeScript("arguments[0].scrollIntoView(true);", contentButton);
        js.executeScript("arguments[0].click();", contentButton);

        // Edit new Education
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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));

        schoolName.sendKeys("University of Saskatchewan");
        degree.sendKeys("BComm");
        fieldOfStudy.sendKeys("Human Resources");
        educationStartDate.sendKeys("07/08/09");
        educationEndDate.sendKeys("Present");
        educationLocation.sendKeys("Saskatoon, Saskatchewan");

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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));
        clearWebField(schoolName);
        clearWebField(degree);
        clearWebField(educationStartDate);
        clearWebField(educationEndDate);
        clearWebField(educationLocation);
        clearWebField(fieldOfStudy);
        schoolName.sendKeys("University of Waterloo");
        degree.sendKeys("Bachelor in Engineering");
        fieldOfStudy.sendKeys("Nanonengineering");
        educationStartDate.sendKeys("10/11/12");
        educationEndDate.sendKeys("13/14/15");
        educationLocation.sendKeys("Waterloo, Ontario");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Edit new Experience
        expandButton = By.cssSelector(".add-experience-section button.expand-section");
        section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addExperience = wait.until(ExpectedConditions.elementToBeClickable(experience.createExperience()));
        js.executeScript("arguments[0].scrollIntoView(true);", addExperience);
        js.executeScript("arguments[0].click();", addExperience);

        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));

        companyName.sendKeys("Meta");
        positionTitle.sendKeys("Product Owner");
        experienceStartDate.sendKeys("December 10, 1998");
        experienceEndDate.sendKeys("May 10, 2000");
        experienceLocation.sendKeys("Brighton, England");

        save = wait.until(ExpectedConditions.elementToBeClickable(experience.experienceSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Check if edits appear
        containerText = resumeContainer.getText();
        Assert.assertEquals(containerText.contains("Meta"), true);
        Assert.assertEquals(containerText.contains("Product Owner"), true);
        Assert.assertEquals(containerText.contains("December 10, 1998"), true);
        Assert.assertEquals(containerText.contains("May 10, 2000"), true);
        Assert.assertEquals(containerText.contains("Brighton, England"), true);

        // Make edits
        button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='Meta']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);

        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));
        clearWebField(companyName);
        clearWebField(positionTitle);
        clearWebField(experienceStartDate);
        clearWebField(experienceEndDate);
        clearWebField(experienceLocation);
        companyName.sendKeys("Studio TTT");
        positionTitle.sendKeys("Technical Product Manager");
        experienceStartDate.sendKeys("07/08/1992");
        experienceEndDate.sendKeys("09/09/09");
        experienceLocation.sendKeys("Portland, OR, USA");

        save = wait.until(ExpectedConditions.elementToBeClickable(experience.experienceSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

    }

    @Then("font remains serif")
    public void verifySerifRemains() {
        containerText = resumeContainer.getText();
        // Education tests
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
        // Experience tests
        Assert.assertEquals(containerText.contains("Studio TTT"), true);
        Assert.assertEquals(containerText.contains("Technical Product Manager"), true);
        Assert.assertEquals(containerText.contains("07/08/1992"), true);
        Assert.assertEquals(containerText.contains("09/09/09"), true);
        Assert.assertEquals(containerText.contains("Portland, OR, USA"), true);
        Assert.assertEquals(containerText.contains("Meta"), false);
        Assert.assertEquals(containerText.contains("Product Owner"), false);
        Assert.assertEquals(containerText.contains("December 10, 1998"), false);
        Assert.assertEquals(containerText.contains("May 10, 2000"), false);
        Assert.assertEquals(containerText.contains("Brighton, England"), false);
        // Verify that serif font is still present
        font = By.xpath("//button[@class='serif-btn' and @data-selected='true']");
        WebElement fontElement = driver.findElement(font);
        String elementClasses = fontElement.getAttribute("data-selected");
        Assert.assertEquals(elementClasses, "true");
    }

    @When("font is changed to mono and existing content is edited")
    public void editExistingContentMonoFont() {
        // Change font to mono
        customizeButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressCustomize()));
        js.executeScript("arguments[0].scrollIntoView(true);", customizeButton);
        js.executeScript("arguments[0].click();", customizeButton);

        monoBtn = wait.until(ExpectedConditions.elementToBeClickable(customize.pressMono()));
        js.executeScript("arguments[0].scrollIntoView(true);", monoBtn);
        js.executeScript("arguments[0].click();", monoBtn);

        contentButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressContent()));
        js.executeScript("arguments[0].scrollIntoView(true);", contentButton);
        js.executeScript("arguments[0].click();", contentButton);

        // Edit existing Personal Details
        name = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressNameField()));
        title = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressTitleField()));
        email = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressEmailField()));
        phoneNumber = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressPhoneNumberField()));
        address = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressAddressField()));
        url = wait.until(ExpectedConditions.elementToBeClickable(personalDetails.pressURLField()));
        clearWebField(name);
        clearWebField(title);
        clearWebField(email);
        clearWebField(phoneNumber);
        clearWebField(address);
        clearWebField(url);
        name.sendKeys("Brian Garrovillas");
        title.sendKeys("Software Test Engineer");
        email.sendKeys("brian.gvillas@outlook.com");
        phoneNumber.sendKeys("647-908-0920");
        address.sendKeys("354-9833 66th Avenue South, Calgary, Alberta");

        // Edit existing Education
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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));
        clearWebField(schoolName);
        clearWebField(degree);
        clearWebField(fieldOfStudy);
        clearWebField(educationStartDate);
        clearWebField(educationEndDate);
        clearWebField(educationLocation);
        schoolName.sendKeys("University of Saskatchewan");
        degree.sendKeys("BComm");
        fieldOfStudy.sendKeys("Human Resources");
        educationStartDate.sendKeys("07/08/09");
        educationEndDate.sendKeys("Present");
        educationLocation.sendKeys("Saskatoon, Saskatchewan");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);
        // Edit existing Experience
        expandButton = By.cssSelector(".add-experience-section button.expand-section");
        section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);
        button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='Top Hat Software Inc.']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);
        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName));
        employmentType = wait.until(ExpectedConditions.elementToBeClickable(experience.addEmploymentType()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));
        description = wait.until(ExpectedConditions.elementToBeClickable(experience.addDescription()));
        clearWebField(companyName);
        clearWebField(positionTitle);
        clearWebField(employmentType);
        clearWebField(experienceStartDate);
        clearWebField(experienceEndDate);
        clearWebField(experienceLocation);
        clearWebField(description);
        companyName.sendKeys("Triple O Studios");
        positionTitle.sendKeys("Software Development Engineer 2");
        employmentType.sendKeys("Full-Time");
        experienceStartDate.sendKeys("June 6, 1998");
        experienceEndDate.sendKeys("Present");
        experienceLocation.sendKeys("San Diego, California, USA");
        description.sendKeys("Built an application that improved company's bottom line by 25%");
        save = wait.until(ExpectedConditions.elementToBeClickable(experience.experienceSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);
    }

    @Then("mono font remains")
    public void monoFontRemains() {
        containerText = resumeContainer.getText();
        // Personal Details tests
        Assert.assertEquals(containerText.contains("Brian Garrovillas"), true);
        Assert.assertEquals(containerText.contains("Software Test Engineer"), true);
        Assert.assertEquals(containerText.contains("brian.gvillas@outlook.com"), true);
        Assert.assertEquals(containerText.contains("647-908-0920"), true);
        Assert.assertEquals(containerText.contains("354-9833 66th Avenue South, Calgary, Alberta"), true);
        Assert.assertEquals(containerText.contains("bill.campbell@gmail.com"), false);
        Assert.assertEquals(containerText.contains("Burnaby, BC"), false);
        // Education tests
        Assert.assertEquals(containerText.contains("University of Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("BComm"), true);
        Assert.assertEquals(containerText.contains("Human Resources"), true);
        Assert.assertEquals(containerText.contains("07/08/09"), true);
        Assert.assertEquals(containerText.contains("Present"), true);
        Assert.assertEquals(containerText.contains("Saskatoon, Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("Simon Fraser University"), false);
        Assert.assertEquals(containerText.contains("Bachelor in Science"), false);
        Assert.assertEquals(containerText.contains("Computer Science"), false);
        // Experience tests
        Assert.assertEquals(containerText.contains("Triple O Studios"), true);
        Assert.assertEquals(containerText.contains("Software Development Engineer 2"), true);
        Assert.assertEquals(containerText.contains("Full-Time"), true);
        Assert.assertEquals(containerText.contains("June 6, 1998"), true);
        Assert.assertEquals(containerText.contains("Present"), true);
        Assert.assertEquals(containerText.contains("Built an application that improved company's bottom line by 25%"),
                true);
        Assert.assertEquals(containerText.contains("San Diego, California, USA"), true);
        Assert.assertEquals(containerText.contains("Full-Stack Software Engineer"), false);
        Assert.assertEquals(containerText.contains("Top Hat Software Inc."), false);
        // Verify that mono font is still present
        font = By.xpath("//button[@class='monospace-btn' and @data-selected='true']");
        WebElement fontElement = driver.findElement(font);
        String elementClasses = fontElement.getAttribute("data-selected");
        Assert.assertEquals(elementClasses, "true");
    }

    @When("font is changed to mono and new content is added")
    public void addNewContentMonoFont() {
        // Change font to mono
        customizeButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressCustomize()));
        js.executeScript("arguments[0].scrollIntoView(true);", customizeButton);
        js.executeScript("arguments[0].click();", customizeButton);

        monoBtn = wait.until(ExpectedConditions.elementToBeClickable(customize.pressMono()));
        js.executeScript("arguments[0].scrollIntoView(true);", monoBtn);
        js.executeScript("arguments[0].click();", monoBtn);

        contentButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressContent()));
        js.executeScript("arguments[0].scrollIntoView(true);", contentButton);
        js.executeScript("arguments[0].click();", contentButton);

        // Add new Education
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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));

        schoolName.sendKeys("University of Saskatchewan");
        degree.sendKeys("BComm");
        fieldOfStudy.sendKeys("Human Resources");
        educationStartDate.sendKeys("07/08/09");
        educationEndDate.sendKeys("Present");
        educationLocation.sendKeys("Saskatoon, Saskatchewan");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Add new Experience
        expandButton = By.cssSelector(".add-experience-section button.expand-section");
        section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addExperience = wait.until(ExpectedConditions.elementToBeClickable(experience.createExperience()));
        js.executeScript("arguments[0].scrollIntoView(true);", addExperience);
        js.executeScript("arguments[0].click();", addExperience);

        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));

        companyName.sendKeys("Terranova");
        positionTitle.sendKeys("Product Manager");
        experienceStartDate.sendKeys("July 4, 2021");
        experienceEndDate.sendKeys("January 23, 2023");
        experienceLocation.sendKeys("Berlin, Germany");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);
    }

    @Then("font is still mono")
    public void verifyMonoFontRemains() {
        containerText = resumeContainer.getText();
        // Education tests
        Assert.assertEquals(containerText.contains("University of Saskatchewan"), true);
        Assert.assertEquals(containerText.contains("BComm"), true);
        Assert.assertEquals(containerText.contains("Human Resources"), true);
        Assert.assertEquals(containerText.contains("07/08/09"), true);
        Assert.assertEquals(containerText.contains("Present"), true);
        Assert.assertEquals(containerText.contains("Saskatoon, Saskatchewan"), true);
        // Experience tests
        Assert.assertEquals(containerText.contains("Terranova"), true);
        Assert.assertEquals(containerText.contains("Product Manager"), true);
        Assert.assertEquals(containerText.contains("July 4, 2021"), true);
        Assert.assertEquals(containerText.contains("January 23, 2023"), true);
        Assert.assertEquals(containerText.contains("Berlin, Germany"), true);
        // Verify that mono font is still present
        font = By.xpath("//button[@class='monospace-btn' and @data-selected='true']");
        WebElement fontElement = driver.findElement(font);
        String elementClasses = fontElement.getAttribute("data-selected");
        Assert.assertEquals(elementClasses, "true");
    }

    @When("font is changed to mono and new content is edited")
    public void monoFontAndNewContentEdited() {
        // Change font to mono
        customizeButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressCustomize()));
        js.executeScript("arguments[0].scrollIntoView(true);", customizeButton);
        js.executeScript("arguments[0].click();", customizeButton);

        monoBtn = wait.until(ExpectedConditions.elementToBeClickable(customize.pressMono()));
        js.executeScript("arguments[0].scrollIntoView(true);", monoBtn);
        js.executeScript("arguments[0].click();", monoBtn);

        contentButton = wait.until(ExpectedConditions.elementToBeClickable(sidebar.pressContent()));
        js.executeScript("arguments[0].scrollIntoView(true);", contentButton);
        js.executeScript("arguments[0].click();", contentButton);

        // Edit new Education
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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));

        schoolName.sendKeys("University of Saskatchewan");
        degree.sendKeys("BComm");
        fieldOfStudy.sendKeys("Human Resources");
        educationStartDate.sendKeys("07/08/09");
        educationEndDate.sendKeys("Present");
        educationLocation.sendKeys("Saskatoon, Saskatchewan");

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
        educationStartDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationStartDate()));
        educationEndDate = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationEndDate()));
        educationLocation = wait.until(ExpectedConditions.elementToBeClickable(education.addEducationLocation()));
        fieldOfStudy = wait.until(ExpectedConditions.elementToBeClickable(education.addFieldOfStudy()));
        clearWebField(schoolName);
        clearWebField(degree);
        clearWebField(educationStartDate);
        clearWebField(educationEndDate);
        clearWebField(educationLocation);
        clearWebField(fieldOfStudy);
        schoolName.sendKeys("University of Waterloo");
        degree.sendKeys("Bachelor in Engineering");
        fieldOfStudy.sendKeys("Nanonengineering");
        educationStartDate.sendKeys("10/11/12");
        educationEndDate.sendKeys("13/14/15");
        educationLocation.sendKeys("Waterloo, Ontario");

        save = wait.until(ExpectedConditions.elementToBeClickable(education.educationSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Edit new Experience
        expandButton = By.cssSelector(".add-experience-section button.expand-section");
        section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addExperience = wait.until(ExpectedConditions.elementToBeClickable(experience.createExperience()));
        js.executeScript("arguments[0].scrollIntoView(true);", addExperience);
        js.executeScript("arguments[0].click();", addExperience);

        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));

        companyName.sendKeys("Meta");
        positionTitle.sendKeys("Product Owner");
        experienceStartDate.sendKeys("December 10, 1998");
        experienceEndDate.sendKeys("May 10, 2000");
        experienceLocation.sendKeys("Brighton, England");

        save = wait.until(ExpectedConditions.elementToBeClickable(experience.experienceSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

        // Check if edits appear
        containerText = resumeContainer.getText();
        Assert.assertEquals(containerText.contains("Meta"), true);
        Assert.assertEquals(containerText.contains("Product Owner"), true);
        Assert.assertEquals(containerText.contains("December 10, 1998"), true);
        Assert.assertEquals(containerText.contains("May 10, 2000"), true);
        Assert.assertEquals(containerText.contains("Brighton, England"), true);

        // Make edits
        button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='Meta']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);

        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        experienceStartDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        experienceEndDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        experienceLocation = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));
        clearWebField(companyName);
        clearWebField(positionTitle);
        clearWebField(experienceStartDate);
        clearWebField(experienceEndDate);
        clearWebField(experienceLocation);
        companyName.sendKeys("Studio TTT");
        positionTitle.sendKeys("Technical Product Manager");
        experienceStartDate.sendKeys("07/08/1992");
        experienceEndDate.sendKeys("09/09/09");
        experienceLocation.sendKeys("Portland, OR, USA");

        save = wait.until(ExpectedConditions.elementToBeClickable(experience.experienceSave()));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        js.executeScript("arguments[0].click();", save);

    }

    @Then("font remains mono")
    public void verifyMonoRemains() {
        containerText = resumeContainer.getText();
        // Education tests
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
        // Experience tests
        Assert.assertEquals(containerText.contains("Studio TTT"), true);
        Assert.assertEquals(containerText.contains("Technical Product Manager"), true);
        Assert.assertEquals(containerText.contains("07/08/1992"), true);
        Assert.assertEquals(containerText.contains("09/09/09"), true);
        Assert.assertEquals(containerText.contains("Portland, OR, USA"), true);
        Assert.assertEquals(containerText.contains("Meta"), false);
        Assert.assertEquals(containerText.contains("Product Owner"), false);
        Assert.assertEquals(containerText.contains("December 10, 1998"), false);
        Assert.assertEquals(containerText.contains("May 10, 2000"), false);
        Assert.assertEquals(containerText.contains("Brighton, England"), false);
        // Verify that mono font is still present
        font = By.xpath("//button[@class='monospace-btn' and @data-selected='true']");
        WebElement fontElement = driver.findElement(font);
        String elementClasses = fontElement.getAttribute("data-selected");
        Assert.assertEquals(elementClasses, "true");
    }

    public void clearWebField(WebElement element) {
        while (!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

}
