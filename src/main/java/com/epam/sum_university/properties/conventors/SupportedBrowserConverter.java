package com.epam.sum_university.properties.conventors;

import com.epam.sum_university.enumeration.SupportedBrowsers;

public final class SupportedBrowserConverter {
    public static SupportedBrowsers valueOfWebBrowser(String webBrowser) {
        return switch (webBrowser) {
            case "local_chrome" -> SupportedBrowsers.LOCAL_CHROME;
            case "local_firefox" -> SupportedBrowsers.LOCAL_FIREFOX;
            case "remote_chrome" -> SupportedBrowsers.REMOTE_CHROME;
            default -> throw new IllegalArgumentException("No appropriate browser found");
        };
    }
}
