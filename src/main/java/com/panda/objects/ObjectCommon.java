package com.panda.objects;

import com.panda.commons.BasePage;
import com.panda.pageObjects.PageGeneratorManage;
import com.panda.pageObjects.user.UserHomePageObject;
import com.panda.pageObjects.user.UserProductsListPO;
import com.panda.pageUIs.BasePageUI;
import org.openqa.selenium.WebDriver;

public class ObjectCommon extends BasePage {
    private WebDriver driver;

    public ObjectCommon(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void clickOnAccountLinkOnHeader() {
        waitForElementClickable(BasePageUI.ACCOUNT_LINK_HEADER);
        clickToElement(BasePageUI.ACCOUNT_LINK_HEADER);
    }

    public void clickOnMenuLinkByNameAndArea(String areaName, String labelName) {
        waitForElementClickable(BasePageUI.MENU_LINK_BY_NAME_AND_AREA, areaName, labelName);
        clickToElement(BasePageUI.MENU_LINK_BY_NAME_AND_AREA, areaName, labelName);
    }

    public void clickOnSidebarLinkByName(String sideBarName) {
        waitForElementClickable(BasePageUI.SIDEBAR_LINK_BY_NAME, sideBarName);
        clickToElement(BasePageUI.SIDEBAR_LINK_BY_NAME, sideBarName);
    }

    public String getAccountInforByLabelAndAttribute(String labelName, String value) {
        return getAttributeValue(BasePageUI.INPUT_BOX_BY_LABELNAME, value, labelName);
    }

    public UserHomePageObject clickOnLogo() {
        waitForElementClickable(BasePageUI.LOGO_PAGE);
        clickToElement(BasePageUI.LOGO_PAGE);
        return PageGeneratorManage.getUserHomePage(driver);
    }

    public UserProductsListPO clickOnCategoryOnHeaderNavByName(String headerCategoryName) {
        waitForElementClickable(BasePageUI.HEADER_CATEGORY_NAME, headerCategoryName);
        clickToElement(BasePageUI.HEADER_CATEGORY_NAME, headerCategoryName);
        return PageGeneratorManage.getUserProductsListPO(driver);
    }

    public String getPriceByProductNameOnProductList(String productName) {
        waitForElementVisible(BasePageUI.PRODUCT_PRICE_BY_NAME, productName);
        return getTextElement(BasePageUI.PRODUCT_PRICE_BY_NAME, productName);
    }

    public String getSuccessMessage() {
        return getTextElement(BasePageUI.SUCCESS_MESSAGE);
    }

    public boolean isLogoPageDisplay() {
        waitForElementVisible(BasePageUI.LOGO_PAGE);
        return isDisplayElement(BasePageUI.LOGO_PAGE);
    }

    public void clickOnButtonByNameOnWishList(String buttonName) {
        waitForElementClickable(BasePageUI.BUTTON_NAME, buttonName);
        clickToElement(BasePageUI.BUTTON_NAME, buttonName);
    }

    public String getPandaWindowID() {
        return driver.getWindowHandle();
    }
}
