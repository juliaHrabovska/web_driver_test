package com.epam.sum_university.uitests;

import com.epam.sum_university.BaseTest;
import com.epam.sum_university.pageobject.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static com.epam.sum_university.properties.Properties.GIT_HUB_LOGIN;
import static com.epam.sum_university.properties.Properties.GIT_HUB_PASS;

public class LoginTest extends BaseTest {

    @AfterTest
    public void tearDown() {
        quit();
    }

    @Test
    public void gitHubLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        String userInformation = loginPage
                .open()
                .login(GIT_HUB_LOGIN, GIT_HUB_PASS)
                        .openProfileDropDown()
                                .getUserInformationLabel();

        Assert.assertEquals("juliaHrabovska", userInformation);
    }
}
