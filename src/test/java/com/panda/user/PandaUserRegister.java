package com.panda.user;

import com.panda.commons.BaseTest;
import com.panda.data.UserInfo;
import com.panda.objects.ObjectCommon;
import com.panda.pageObjects.PageGeneratorManage;
import com.panda.pageObjects.user.PandaUserAccountInfoPageObject;
import com.panda.pageObjects.user.PandaUserHomePageObject;
import com.panda.pageObjects.user.PandaUserRegisterPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PandaUserRegister extends BaseTest {

    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        pandaUserHomePageObject = PageGeneratorManage.getUserHomePage(driver);
        userInfo = UserInfo.getUserInfo();
        firstName = userInfo.getFirstName();
        lastName = userInfo.getLastName();
        emailAddress = firstName + lastName + getRandNumber() + "@gmail.com";
        password = userInfo.getPassword();
    }

    @Test
    public void User_01_Register() {
        log.info("Home: click on Account link");
        pandaUserHomePageObject.clickOnAccountLinkOnHeader();

        log.info("Home: click on Register link");
        pandaUserHomePageObject.clickOnMenuLinkByNameAndArea("page-header-container", "Register");
        pandaUserRegisterPageObject = PageGeneratorManage.getUserRegisterPage(driver);
        pandaUserRegisterPageObject.inputToTextboxByLabel(firstName, "firstname");
        pandaUserRegisterPageObject.inputToTextboxByLabel(lastName, "lastname");
        pandaUserRegisterPageObject.inputToTextboxByLabel(emailAddress, "email_address");
        pandaUserRegisterPageObject.inputToTextboxByLabel(password, "password");
        pandaUserRegisterPageObject.inputToTextboxByLabel(password, "confirmation");
        pandaUserAccountInfoPageObject = pandaUserRegisterPageObject.clickOnRegisterButton();
        Assert.assertTrue(pandaUserAccountInfoPageObject.isDisplaySuccessfulMessage());
    }

    @AfterClass
    public void AfterClass() {
        closeBrowserDriver();
    }
    private WebDriver driver;
    private PandaUserHomePageObject pandaUserHomePageObject;
    private PandaUserRegisterPageObject pandaUserRegisterPageObject;
    private PandaUserAccountInfoPageObject pandaUserAccountInfoPageObject;
    private UserInfo userInfo;
    private String firstName, lastName, emailAddress, password;

}
