package com.panda.user;

import com.panda.commons.BaseTest;
import com.panda.data.UserInfo;
import com.panda.pageObjects.PageGeneratorManage;
import com.panda.pageObjects.user.UserAccountDashboardPageObject;
import com.panda.pageObjects.user.UserHomePageObject;
import com.panda.pageObjects.user.UserRegisterPageObject;
import com.panda.pageObjects.user.UserAccountInforPO;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Set;

public class PandaUser_01_Register extends BaseTest {

    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        userHomePageObject = PageGeneratorManage.getUserHomePage(driver);
        userInfo = UserInfo.getUserInfo();
        firstName = userInfo.getFirstName();
        lastName = userInfo.getLastName();
        emailAddress = firstName + lastName + getRandNumber() + "@gmail.com";
        password = userInfo.getPassword();
    }

    @Test
    public void User_01_Register() {
        log.info("Home: click on Account link");
        userHomePageObject.clickOnAccountLinkOnHeader();

        log.info("Home: click on Register link");
        userHomePageObject.clickOnMenuLinkByNameAndArea("page-header-container", "Register");
        userRegisterPageObject = PageGeneratorManage.getUserRegisterPage(driver);
        userRegisterPageObject.inputToTextboxByLabel(firstName, "firstname");
        userRegisterPageObject.inputToTextboxByLabel(lastName, "lastname");
        userRegisterPageObject.inputToTextboxByLabel(emailAddress, "email_address");
        userRegisterPageObject.inputToTextboxByLabel(password, "password");
        userRegisterPageObject.inputToTextboxByLabel(password, "confirmation");
        userAccountDashboardPageObject = userRegisterPageObject.clickOnRegisterButton();
        Assert.assertTrue(userAccountDashboardPageObject.isDisplaySuccessfulMessage());
    }
    @Test
    public void User_02_Verify_Register_Infor(){
        log.info("Account Dashboard: click on Account infor link");
        userAccountDashboardPageObject.clickOnSidebarLinkByName("Account Information");
        userAccountInforPO = PageGeneratorManage.getUserAccountInforPO(driver);

        log.info("Account infor: verify information");
        Assert.assertEquals(userAccountInforPO.getAccountInforByLabelAndAttribute("firstname", "value"), firstName);
        Assert.assertEquals(userAccountInforPO.getAccountInforByLabelAndAttribute("lastname", "value"), lastName);
        Assert.assertEquals(userAccountInforPO.getAccountInforByLabelAndAttribute("email", "value"), emailAddress);
    }
    @Test
    public void setLoginCookie(){
        log.info("Login: get cookies");
        userHomePageObject = userAccountInforPO.clickOnLogo();
        loggedCookies = userHomePageObject.getAllCookies();
        driver.quit();
    }

    @AfterClass
    public void AfterClass() {
        closeBrowserDriver();
    }
    private WebDriver driver;
    private UserHomePageObject userHomePageObject;
    private UserRegisterPageObject userRegisterPageObject;
    private UserAccountDashboardPageObject userAccountDashboardPageObject;
    private UserAccountInforPO userAccountInforPO;
    private UserInfo userInfo;
    private String firstName, lastName, emailAddress, password;
    public static Set<Cookie> loggedCookies;

}
