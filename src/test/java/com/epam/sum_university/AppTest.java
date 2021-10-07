package com.epam.sum_university;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

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
}
