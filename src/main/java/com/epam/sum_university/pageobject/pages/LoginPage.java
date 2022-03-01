package com.epam.sum_university.pageobject.pages;

import com.epam.sum_university.pageobject.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "login_field")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement pass;

    @FindBy(name = "commit")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public LoginPage open() {
        webDriver.get("https://github.com/login");
        return this;
    }

    public HomePage login(String username, String pass) {
        this.username.sendKeys(username);
        this. pass.sendKeys(pass);
        loginButton.click();

        return new HomePage(webDriver);
    }
}
