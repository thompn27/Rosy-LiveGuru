package com.panda.objects;

import com.panda.commons.BasePage;
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
}
