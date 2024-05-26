package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import com.panda.pageUIs.user.UserCompareUI;
import org.openqa.selenium.WebDriver;

public class UserComparePO extends ObjectCommon {
    private WebDriver driver;
    public UserComparePO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public boolean isCompareTitleDisplayed() {
        waitForElementVisible(UserCompareUI.COMPARE_PRODUCT_HEADER);
        return isDisplayElement(UserCompareUI.COMPARE_PRODUCT_HEADER);
    }

    public boolean isProductByNameDisplayed(String productName) {
        waitForElementVisible(UserCompareUI.PRODUCT_NAME_IN_COMPARE_TABLE, productName);
        return isDisplayElement(UserCompareUI.PRODUCT_NAME_IN_COMPARE_TABLE, productName);
    }

    public void clickOnCloseWindowButton() {
        waitForElementClickable(UserCompareUI.CLOSE_WINDOW_BUTTON);
        clickToElement(UserCompareUI.CLOSE_WINDOW_BUTTON);
        sleepInSecond(1);
    }

    public void goToTheProductListPage(String parentWindowID) {
        sleepInSecond(1);
        closeAllWindowsWithoutParent(parentWindowID);
    }
}
