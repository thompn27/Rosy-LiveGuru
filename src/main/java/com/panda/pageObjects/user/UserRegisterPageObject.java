package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import com.panda.pageObjects.PageGeneratorManage;
import com.panda.pageUIs.user.UserRegisterUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class UserRegisterPageObject extends ObjectCommon {
    private WebDriver driver;

    public UserRegisterPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @Step("Input text to textbox")
    public void inputToTextboxByLabel(String textBoxName, String labelName) {
        waitForElementVisible(UserRegisterUI.INPUT_TEXTBOX_BY_LABEL, labelName);
        sendKeyToElement(UserRegisterUI.INPUT_TEXTBOX_BY_LABEL, textBoxName, labelName);
    }

    @Step("Click on Register button")
    public UserAccountDashboardPageObject clickOnRegisterButton() {
        waitForElementClickable(UserRegisterUI.REGISTER_BUTTON);
        clickToElement(UserRegisterUI.REGISTER_BUTTON);
        return PageGeneratorManage.getUserAccountDashboardPage(driver);
    }
}
