package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import com.panda.pageObjects.PageGeneratorManage;
import com.panda.pageUIs.user.PandaUserRegisterUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class PandaUserRegisterPageObject extends ObjectCommon {
    private WebDriver driver;

    public PandaUserRegisterPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Input text to textbox")
    public void inputToTextboxByLabel(String textBoxName, String labelName) {
        waitForElementVisible(PandaUserRegisterUI.INPUT_TEXTBOX_BY_LABEL, labelName);
        sendKeyToElement(PandaUserRegisterUI.INPUT_TEXTBOX_BY_LABEL, textBoxName, labelName);
    }

    @Step("Click on Register button")
    public PandaUserAccountInfoPageObject clickOnRegisterButton() {
        waitForElementClickable(PandaUserRegisterUI.REGISTER_BUTTON);
        clickToElement(PandaUserRegisterUI.REGISTER_BUTTON);
        return PageGeneratorManage.getUserAccountInfoPage(driver);
    }
}
