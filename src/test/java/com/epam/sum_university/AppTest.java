package com.epam.sum_university;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.epam.sum_university.Properties.GIT_HUB_LOGIN;
import static com.epam.sum_university.Properties.GIT_HUB_PASS;

public class AppTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closeDriver() {
        driver.close();
        driver.quit();
    }

    @Test
    public void firstTest() {
        driver.get("https://www.google.com");
    }

    @Test
    public void gitHubLoginTest() {
        driver.get("https://github.com/login");

        WebElement loginField = driver.findElement(By.id("login_field"));
        loginField.sendKeys(GIT_HUB_LOGIN);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(GIT_HUB_PASS);

        WebElement sighInButton = driver.findElement(By.name("commit"));
        sighInButton.click();

        WebElement profileInfoButton = driver.findElement(
                By.xpath("//button[@aria-label=\"Open user account menu\"]"));
        profileInfoButton.click();

        WebElement userInfoText = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                        "//div[@class=\"Overlay-headerContentWrap\"]//span[@class=\"Truncate-text\"]")));

//        WebElement userInfoText = driver.findElement(
//                By.xpath("//div[@class=\"Overlay-headerContentWrap\"]//span[@class=\"Truncate-text\"]"));
        Assert.assertEquals(userInfoText.getText(), "juliaHrabovska");
    }

    @DataProvider(name = "wrongCredentials")
    public Object[][] wrongCredentials() {
        return new Object[][] {
                {"qwerty", GIT_HUB_PASS},
                {GIT_HUB_LOGIN, "qwerty"},
                {"qwerty", "qwerty"}
        };
    }

    @Test(dataProvider = "wrongCredentials")
    public void gitHubLoginNegativeTest(String login, String password) {
        driver.get("https://github.com/login");

        WebElement loginField = driver.findElement(By.id("login_field"));
        loginField.sendKeys(login);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(password);

        WebElement sighInButton = driver.findElement(By.name("commit"));
        sighInButton.click();

        WebElement errorLabel = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("js-flash-container")));

        Assert.assertEquals("Incorrect username or password.", errorLabel.getText());
    }
}
