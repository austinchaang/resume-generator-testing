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

public class PersonalDetailsTest {

    String URL = "https://resumegenerator.austinchang.ca/";
    PersonalDetails personalDetails;
    WebDriver driver;
    WebElement resumeContainer;
    String containerText;
    int statusCode;
    WebElement name;
    WebElement title;
    WebElement email;
    WebElement phoneNumber;
    WebElement address;
    WebElement url;

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
        personalDetails = new PersonalDetails();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
        statusCode = getStatusCode(URL);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        resumeContainer = wait
                .until(ExpectedConditions.elementToBeClickable(driver.findElement(By.className("resume-container"))));
        containerText = resumeContainer.getText();
        name = driver.findElement(personalDetails.pressNameField());
        title = driver.findElement(personalDetails.pressTitleField());
        email = driver.findElement(personalDetails.pressEmailField());
        phoneNumber = driver.findElement(personalDetails.pressPhoneNumberField());
        address = driver.findElement(personalDetails.pressAddressField());
        url = driver.findElement(personalDetails.pressURLField());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("resume contains content")
    public void resume_contains_content1() {
        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCode);
        // System.out.println("Content Before:" + containerText);
    }

    @When("personal details are edited 1")
    public void personal_details_edited1() {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        // String currentText = name.getAttribute("value");
        // System.out.println("Text inside the textarea is " + currentText);
    }

    @Then("changes reflected on resume 1")
    public void changes_reflected1() {
        containerText = resumeContainer.getText();
        // System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("Brian Garrovillas"), true);
        Assert.assertEquals(containerText.contains("Software Test Engineer"), true);
        Assert.assertEquals(containerText.contains("brian.gvillas@outlook.com"), true);
        Assert.assertEquals(containerText.contains("647-908-0920"), true);
        Assert.assertEquals(containerText.contains("354-9833 66th Avenue South, Calgary, Alberta"), true);

        Assert.assertEquals(containerText.contains("bill.campbell@gmail.com"), false);
        Assert.assertEquals(containerText.contains("Burnaby, BC"), false);
    }

    @When("personal details are edited 2")
    public void personal_details_edited2() {
        clearWebField(name);
        clearWebField(title);
        clearWebField(email);
        clearWebField(phoneNumber);
        clearWebField(address);
        clearWebField(url);
        try {
            name.sendKeys("Walter Jaskaran Sandhu-White");
            title.sendKeys("Project Manager/Coordinator");
            email.sendKeys("wjaskaran-sandhu@gmail.com");
            phoneNumber.sendKeys("+1 564 474 2323");
            address.sendKeys("5463 South Simcoe Dr., Kelowna, BC");
            url.sendKeys("https://www.walterwhite.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // String currentText = name.getAttribute("value");
    }

    @Then("changes reflected on resume 2")
    public void changes_reflected2() {
        containerText = resumeContainer.getText();
        // System.out.println("Text inside the textarea is " + containerText);
        Assert.assertEquals(containerText.contains("Walter Jaskaran Sandhu-White"),
                true);
        Assert.assertEquals(containerText.contains("Project Manager/Coordinator"),
                true);
        Assert.assertEquals(containerText.contains("wjaskaran-sandhu@gmail.com"),
                true);
        Assert.assertEquals(containerText.contains("+1 564 474 2323"), true);
        Assert.assertEquals(containerText.contains("5463 South Simcoe Dr., Kelowna, BC"), true);
        Assert.assertEquals(containerText.contains("https://www.walterwhite.com"), true);

        Assert.assertEquals(containerText.contains("brian.gvillas@outlook.com"),
                false);
        Assert.assertEquals(containerText.contains("647-908-0920"), false);
    }

    public void clearWebField(WebElement element) {
        while (!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

}