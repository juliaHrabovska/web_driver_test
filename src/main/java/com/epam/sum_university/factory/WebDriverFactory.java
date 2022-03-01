package com.epam.sum_university.factory;

import com.epam.sum_university.properties.holder.PropertyHolder;
import com.epam.sum_university.properties.conventors.SupportedBrowserConverter;
import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
    public WebDriver getWebDriver() {
        return SupportedBrowserConverter.valueOfWebBrowser(
                new PropertyHolder().readProperty("browser")).getWebDriver();
    }
}
