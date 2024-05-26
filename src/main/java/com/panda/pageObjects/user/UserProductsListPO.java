package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import com.panda.pageObjects.PageGeneratorManage;
import com.panda.pageUIs.user.UserProductListUI;
import org.openqa.selenium.WebDriver;

public class UserProductsListPO extends ObjectCommon {
    private WebDriver driver;

    public UserProductsListPO(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public UserProductDetailPO clickOnProductByName(String productName) {
        waitForElementClickable(UserProductListUI.PRODUCT_BY_NAME, productName);
        clickToElement(UserProductListUI.PRODUCT_BY_NAME, productName);
        return PageGeneratorManage.getUserProductDetailPO(driver);
    }

    public UserShoppingCartPO clickOnAddToCartButtonByProductName(String productName) {
        waitForElementClickable(UserProductListUI.ADD_TO_CART_BUTTON_BY_PRODUCT_NAME, productName);
        clickToElement(UserProductListUI.ADD_TO_CART_BUTTON_BY_PRODUCT_NAME, productName);
        return PageGeneratorManage.getUserShoppingCartPO(driver);
    }

    public void clickWishListOrCompareByProductNameAndLinkName(String productName, String linkType) {
        waitForElementClickable(UserProductListUI.WISH_LIST_LINK_OR_COMPARE_LINK, productName, linkType);
        clickToElement(UserProductListUI.WISH_LIST_LINK_OR_COMPARE_LINK, productName, linkType);
    }

    public void clickOnCompareButton() {
        waitForElementClickable(UserProductListUI.COMPARE_BUTTON);
        clickToElement(UserProductListUI.COMPARE_BUTTON);
    }

    public void goToComparePage(String title) {
        sleepInSecond(1);
        switchToWindowByTitle(title);
    }
}
