package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import com.panda.pageUIs.user.UserWishListUI;
import org.openqa.selenium.WebDriver;

public class UserWishListPO extends ObjectCommon {
    private WebDriver driver;
    public UserWishListPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isProductWishListDisplayedByName(String productName) {
        waitForElementVisible(UserWishListUI.PRODUCT_NAME, productName);
        return isDisplayElement(UserWishListUI.PRODUCT_NAME, productName);
    }
}
