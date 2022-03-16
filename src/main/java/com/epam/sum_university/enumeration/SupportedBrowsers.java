package com.epam.sum_university.enumeration;

import com.epam.sum_university.invoker.WebDriverInvoker;
import com.epam.sum_university.invoker.implementations.LocalChromeInvoker;
import com.epam.sum_university.invoker.implementations.LocalFirefoxInvoker;
import com.epam.sum_university.invoker.implementations.RemoteChromeInvoker;
import org.openqa.selenium.WebDriver;

public enum SupportedBrowsers {
    LOCAL_FIREFOX(new LocalFirefoxInvoker()),
    LOCAL_CHROME(new LocalChromeInvoker()),
    REMOTE_CHROME(new RemoteChromeInvoker());

    private WebDriverInvoker webDriverInvoker;

    SupportedBrowsers(WebDriverInvoker webDriverInvoker) {
        this.webDriverInvoker = webDriverInvoker;
    }

    public WebDriver getWebDriver() {
        return webDriverInvoker.invokeWebDriver();
    }
}
