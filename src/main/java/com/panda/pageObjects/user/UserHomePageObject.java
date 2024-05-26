package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import org.openqa.selenium.WebDriver;

public class UserHomePageObject extends ObjectCommon {
    private WebDriver driver;

    public UserHomePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}
