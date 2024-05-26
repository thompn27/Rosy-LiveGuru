package com.panda.pageObjects;

import com.panda.pageObjects.user.*;
import org.openqa.selenium.WebDriver;

public class PageGeneratorManage {
    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    public static UserHomePageObject getUserHomePage(WebDriver driver) {
        return new UserHomePageObject(driver);
    }

    public static UserAccountDashboardPageObject getUserAccountDashboardPage(WebDriver driver) {
        return new UserAccountDashboardPageObject(driver);
    }
    public static UserAccountInforPO getUserAccountInforPO(WebDriver driver){
        return new UserAccountInforPO(driver);
    }
    public static UserLoginPO getUserLoginPO(WebDriver driver){
        return new UserLoginPO(driver);
    }
    public static UserProductsListPO getUserProductsListPO(WebDriver driver){
        return new UserProductsListPO(driver);
    }
    public static UserProductDetailPO getUserProductDetailPO(WebDriver driver){
        return new UserProductDetailPO(driver);
    }
    public static UserShoppingCartPO getUserShoppingCartPO(WebDriver driver){
        return new UserShoppingCartPO(driver);
    }
    public static UserComparePO getUserComparePO(WebDriver driver){
        return new UserComparePO(driver);
    }
    public static UserWishListPO getUserWishListPO(WebDriver driver){
        return new UserWishListPO(driver);
    }
    public static UserShareWishListPO getUserShareWishListPO(WebDriver driver){
        return new UserShareWishListPO(driver);
    }
}
