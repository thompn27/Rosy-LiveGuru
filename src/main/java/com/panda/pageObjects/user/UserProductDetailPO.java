package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import com.panda.pageUIs.user.UserProductDetailUI;
import org.openqa.selenium.WebDriver;

public class UserProductDetailPO extends ObjectCommon {
    private WebDriver driver;
    public UserProductDetailPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getPriceByProductNameOnProductDetail() {
        waitForElementVisible(UserProductDetailUI.PRODUCT_PRICE);
        return getTextElement(UserProductDetailUI.PRODUCT_PRICE);
    }
}
