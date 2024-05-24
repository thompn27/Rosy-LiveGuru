package com.panda.pageObjects;

import com.panda.pageObjects.user.PandaUserAccountInfoPageObject;
import com.panda.pageObjects.user.PandaUserHomePageObject;
import com.panda.pageObjects.user.PandaUserRegisterPageObject;
import org.openqa.selenium.WebDriver;

public class PageGeneratorManage {
    public static PandaUserRegisterPageObject getUserRegisterPage(WebDriver driver) {
        return new PandaUserRegisterPageObject(driver);
    }

    public static PandaUserHomePageObject getUserHomePage(WebDriver driver) {
        return new PandaUserHomePageObject(driver);
    }

    public static PandaUserAccountInfoPageObject getUserAccountInfoPage(WebDriver driver) {
        return new PandaUserAccountInfoPageObject(driver);
    }
}
