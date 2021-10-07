package com.epam.sum_university;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.epam.sum_university.Properties.GIT_HUB_LOGIN;
import static com.epam.sum_university.Properties.GIT_HUB_PASS;
import static org.testng.Assert.assertTrue;


/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void openMainPageTest() {
        System.setProperty(
                "webdriver.chrome.driver",
                "/Users/Yuliia_Hrabovska/IdeaProjects/web_driver_test/src/test/resources/webdriver/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://www.google.com");

        driver.close();
        driver.quit();
    }

    @Test
    public void findElementsTest() {
        System.setProperty(
                "webdriver.chrome.driver",
                "/Users/Yuliia_Hrabovska/IdeaProjects/web_driver_test/src/test/resources/webdriver/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.google.com");

        WebElement searchField = driver.findElement(By.name("q"));
        Assert.assertTrue(searchField.isDisplayed(), "Search Field should be displayed");

        driver.close();
        driver.quit();
    }

    @Test
    public void amazonLogoTest() {
        System.setProperty(
                "webdriver.chrome.driver",
                "/Users/Yuliia_Hrabovska/IdeaProjects/web_driver_test/src/test/resources/webdriver/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");

        WebElement logo = driver.findElement(By.id("nav-logo-sprites"));
        Assert.assertTrue(logo.isDisplayed(), "Logo should be displayed");

        driver.close();
        driver.quit();
    }

    @Test
    public void amazonSearchTest() {
        System.setProperty(
                "webdriver.chrome.driver",
                "/Users/Yuliia_Hrabovska/IdeaProjects/web_driver_test/src/test/resources/webdriver/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.amazon.com/");

        WebElement searchField = driver.findElement(By.id("twotabsearchtextbox"));
        searchField.sendKeys("laptops" + Keys.ENTER);

        WebElement searchResult = driver.findElement(By.xpath("//div[@class=\"sg-col-inner\"]//span[last()]"));
        assertTrue(searchResult.isEnabled(), "Search result is present");

        WebElement firstResult = driver.findElement(By.xpath("//div[contains(@class,\"s-result-item\")][1]//a"));
        firstResult.click();

        WebElement addToCardButton = driver.findElement(By.id("add-to-cart-button"));
        addToCardButton.click();

        WebElement cardButton = driver.findElement(By.id("nav-cart"));
        cardButton.click();

        WebElement deleteElementFromCardButton = driver.findElement(By.xpath("//input[contains(@name,'delete')]"));
        deleteElementFromCardButton.click();

        WebElement cardIsEmptyLabel = driver.findElement(By.xpath("//div[@id=\"sc-active-cart\"]//h1"));
        Assert.assertTrue(cardIsEmptyLabel.isDisplayed(), "Logo should be displayed");
//
//        WebElement cardIsEmptyLabel = new WebDriverWait(driver, 10)
//                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\\\"sc-active-cart\\\"]//h1")));

        driver.close();
        driver.quit();
    }

    @Test
    public void gitHubLoginTest() {
        System.setProperty(
                "webdriver.chrome.driver",
                "/Users/Yuliia_Hrabovska/IdeaProjects/web_driver_test/src/test/resources/webdriver/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://github.com/login");

        WebElement loginField = driver.findElement(By.id("login_field"));
        loginField.sendKeys(GIT_HUB_LOGIN);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(GIT_HUB_PASS);

        WebElement signInButton = driver.findElement(By.name("commit"));
        signInButton.click();

        WebElement profileDropDownButton = driver.findElement(By.xpath("//summary[@class=\"Header-link\"]/img"));
        profileDropDownButton.click();

        WebElement userInformationLabel = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text()=\"juliaHrabovska\"]")));

//        WebElement userInformationLabel = driver.findElement(By.xpath("//strong[text()=\"juliaHrabovska\"]"));
        Assert.assertEquals("juliaHrabovska", userInformationLabel.getText());

        driver.quit();
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
    public void gitHubLoginNegativeTest(String login, String pass) {
        System.setProperty(
                "webdriver.chrome.driver",
                "/Users/Yuliia_Hrabovska/IdeaProjects/web_driver_test/src/test/resources/webdriver/chromedriver");

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://github.com/login");

        WebElement loginField = driver.findElement(By.id("login_field"));
        loginField.sendKeys(login);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(pass);

        WebElement signInButton = driver.findElement(By.name("commit"));
        signInButton.click();

        WebElement errorLabel = new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("js-flash-container")));

        Assert.assertEquals("Incorrect username or password.", errorLabel.getText());

        driver.quit();
    }
}
