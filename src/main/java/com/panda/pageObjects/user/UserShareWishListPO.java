package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import com.panda.pageUIs.user.UserShareWishListUI;
import org.openqa.selenium.WebDriver;

public class UserShareWishListPO extends ObjectCommon {
    private WebDriver driver;
    public UserShareWishListPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void inputToTextAreaByLabel(String inputValue, String labelName) {
        waitForElementVisible(UserShareWishListUI.TEXT_AREA_BY_LABELNAME, labelName);
        sendKeyToElement(UserShareWishListUI.TEXT_AREA_BY_LABELNAME, inputValue, labelName);
    }
}
