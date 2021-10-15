package com.epam.sum_university.invoker;

import org.openqa.selenium.WebDriver;

@FunctionalInterface
public interface WebDriverInvoker {
    WebDriver invokeWebDriver();
}
