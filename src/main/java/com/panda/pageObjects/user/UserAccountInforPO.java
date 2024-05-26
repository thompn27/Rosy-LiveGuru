package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import org.openqa.selenium.WebDriver;

public class UserAccountInforPO extends ObjectCommon {
    private WebDriver driver;
    public UserAccountInforPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
