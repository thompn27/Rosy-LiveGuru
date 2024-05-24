package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import com.panda.pageUIs.user.PandaUserAccountInfoUI;
import org.openqa.selenium.WebDriver;

public class PandaUserAccountInfoPageObject extends ObjectCommon {
    private WebDriver driver;

    public PandaUserAccountInfoPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isDisplaySuccessfulMessage() {
        waitForElementVisible(PandaUserAccountInfoUI.REGISTER_SUCCESSFUL_MESSAGE);
        return isDisplayElement(PandaUserAccountInfoUI.REGISTER_SUCCESSFUL_MESSAGE);
    }
}
