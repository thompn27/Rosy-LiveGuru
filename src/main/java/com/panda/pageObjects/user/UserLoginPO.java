package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import org.openqa.selenium.WebDriver;

public class UserLoginPO extends ObjectCommon {
    private WebDriver driver;

    public UserLoginPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
