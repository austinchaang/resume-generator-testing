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

public class TemplateLoaderTest {

    String URL = "https://resumegenerator.austinchang.ca/";
    TemplateLoader templateLoader;
    WebDriver driver;
    PersonalDetails personalDetails;

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
        System.setProperty("webdriver.chrome.driver",
                "/Applications/chromedriver_mac64/chromedriver");
        templateLoader = new TemplateLoader();
        personalDetails = new PersonalDetails();
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("resume has content")
    public void resume_has_content() {
        driver.manage().window().maximize();
        driver.get(URL);
        int statusCode = getStatusCode(URL);
        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCode);
    }

    @When("the clear resume button is pressed")
    public void the_clear_resume_button_is_pressed() {
        driver.findElement(templateLoader.clearResume()).click();
    }

    @Then("content should be removed")
    public void content_should_be_removed() {
        WebElement resumeContainer = driver.findElement(By.className("resume-container"));
        WebElement name = driver.findElement(personalDetails.pressNameField());
        WebElement title = driver.findElement(personalDetails.pressTitleField());
        WebElement email = driver.findElement(personalDetails.pressEmailField());
        WebElement phoneNumber = driver.findElement(personalDetails.pressPhoneNumberField());
        WebElement address = driver.findElement(personalDetails.pressAddressField());
        WebElement url = driver.findElement(personalDetails.pressURLField());
        String containerText = resumeContainer.getText();
        Assert.assertEquals(containerText.isEmpty(), true);
        Assert.assertEquals(name.getText().isEmpty(), true);
        Assert.assertEquals(title.getText().isEmpty(), true);
        Assert.assertEquals(email.getText().isEmpty(), true);
        Assert.assertEquals(phoneNumber.getText().isEmpty(), true);
        Assert.assertEquals(address.getText().isEmpty(), true);
        Assert.assertEquals(url.getText().isEmpty(), true);
    }

    @Given("resume has no content")
    public void resume_has_no_content() {
        driver.manage().window().maximize();
        driver.get(URL);
        int statusCode = getStatusCode(URL);
        Assert.assertEquals(HttpURLConnection.HTTP_OK, statusCode);
        driver.findElement(templateLoader.clearResume()).click();
    }

    @When("load template pressed")
    public void load_template_pressed() {
        driver.findElement(templateLoader.loadTemplate()).click();
    }

    @Then("content appears")
    public void content_appears() {
        WebElement resumeContainer = driver.findElement(By.className("resume-container"));
        String containerText = resumeContainer.getText();
        Assert.assertEquals(containerText.isEmpty(), false);
        // System.out.println("Content:" + containerText);
    }
}