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

public class ExperienceTest {

    String URL = "https://resumegenerator.austinchang.ca/";
    Experience experience;
    WebDriver driver;
    WebElement resumeContainer;
    String containerText;
    int statusCode;
    WebElement addExperience;
    WebElement expandExperience;
    WebElement companyName;
    WebElement positionTitle;
    WebElement employmentType;
    WebElement startDate;
    WebElement endDate;
    WebElement location;
    WebElement description;
    WebDriverWait wait;
    JavascriptExecutor js;;

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
        experience = new Experience();
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

    @Given("existing experience is present")
    public void resume_contains_content1() {
        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCode);
    }

    @When("user deletes and edits the existing experience")
    public void personal_details_edited1() {
        By expandButton = By.cssSelector(".add-experience-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[p[@class='collapsed-form-title' and text()='Top Hat Software Inc.']]")));
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        js.executeScript("arguments[0].click();", button);
        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName));
        positionTitle = driver.findElement(experience.addPositionTitle());
        employmentType = driver.findElement(experience.addEmploymentType());
        startDate = driver.findElement(experience.addExperienceStartDate());
        endDate = driver.findElement(experience.addExperienceEndDate());
        location = driver.findElement(experience.addExperienceLocation());
        description = driver.findElement(experience.addDescription());
        try {
            clearWebField(companyName);
            clearWebField(positionTitle);
            clearWebField(employmentType);
            clearWebField(startDate);
            clearWebField(endDate);
            clearWebField(location);
            clearWebField(description);
            companyName.sendKeys("Triple O Studios");
            positionTitle.sendKeys("Software Development Engineer 2");
            employmentType.sendKeys("Full-Time");
            startDate.sendKeys("June 6, 1998");
            endDate.sendKeys("Present");
            location.sendKeys("San Diego, California, USA");
            description.sendKeys("Built an application that improved company's bottom line by 25%");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("changes are reflected")
    public void changes_reflected1() {
        containerText = resumeContainer.getText();
        // System.out.println("Text inside the textarea is " + containerText);
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
    }

    @When("user hides existing experience")
    public void hideExperience() {
        By expandButton = By.cssSelector(".add-experience-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        By hideBy = By.xpath(
                "//button[p[text()='Top Hat Software Inc.']]/i[contains(@class, 'fa-regular') and contains(@class, 'fa-eye')]");
        WebElement hideButton = wait.until(ExpectedConditions.elementToBeClickable(hideBy));
        js.executeScript("arguments[0].scrollIntoView(true);", hideButton);
        js.executeScript("arguments[0].click();", hideButton);
    }

    @Then("existing experience is hidden")
    public void verifyHidden() {
        containerText = resumeContainer.getText();
        Assert.assertEquals(containerText.contains("Full-Stack Software Engineer"), false);
        Assert.assertEquals(containerText.contains("Top Hat Software Inc."), false);
        Assert.assertEquals(containerText.contains("Toronto, Canada"), false);
        Assert.assertEquals(containerText.contains("Full-Time"), false);
        Assert.assertEquals(containerText.contains("05/22"), false);
    }

    @When("new experience is added")
    public void addExperience() {
        By expandButton = By.cssSelector(".add-experience-section button.expand-section");
        WebElement section = wait.until(ExpectedConditions.elementToBeClickable(experience.expandExperienceSection()));
        WebElement expand = section.findElement(expandButton);
        js.executeScript("arguments[0].scrollIntoView(true);", expand);
        js.executeScript("arguments[0].click();", expand);

        addExperience = wait.until(ExpectedConditions.elementToBeClickable(experience.createExperience()));
        js.executeScript("arguments[0].scrollIntoView(true);", addExperience);
        js.executeScript("arguments[0].click();", addExperience);

        companyName = wait.until(ExpectedConditions.elementToBeClickable(experience.addCompanyName()));
        positionTitle = wait.until(ExpectedConditions.elementToBeClickable(experience.addPositionTitle()));
        startDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceStartDate()));
        endDate = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceEndDate()));
        location = wait.until(ExpectedConditions.elementToBeClickable(experience.addExperienceLocation()));

        companyName.sendKeys("Terranova");
        positionTitle.sendKeys("Product Manager");
        startDate.sendKeys("July 4, 2021");
        endDate.sendKeys("January 23, 2023");
        location.sendKeys("Berlin, Germany");
    }

    @Then("new experience is on resume")
    public void verifyResume() {
        containerText = resumeContainer.getText();
        // System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("Terranova"), true);
        Assert.assertEquals(containerText.contains("Product Manager"), true);
        Assert.assertEquals(containerText.contains("July 4, 2021"), true);
        Assert.assertEquals(containerText.contains("January 23, 2023"), true);
        Assert.assertEquals(containerText.contains("Berlin, Germany"), true);
    }

    public void clearWebField(WebElement element) {
        while (!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }
}
