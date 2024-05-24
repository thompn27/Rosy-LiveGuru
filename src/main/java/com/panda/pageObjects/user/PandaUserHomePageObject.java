package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import org.openqa.selenium.WebDriver;

public class PandaUserHomePageObject extends ObjectCommon {
    private WebDriver driver;

    public PandaUserHomePageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}
