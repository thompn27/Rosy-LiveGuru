package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import com.panda.pageUIs.user.UserAccountInfoUI;
import org.openqa.selenium.WebDriver;

public class UserAccountDashboardPageObject extends ObjectCommon {
    private WebDriver driver;

    public UserAccountDashboardPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isDisplaySuccessfulMessage() {
        waitForElementVisible(UserAccountInfoUI.REGISTER_SUCCESSFUL_MESSAGE);
        return isDisplayElement(UserAccountInfoUI.REGISTER_SUCCESSFUL_MESSAGE);
    }
}
