package com.panda.user;

import com.panda.commons.BaseTest;
import com.panda.pageObjects.PageGeneratorManage;
import com.panda.pageObjects.user.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class PandaUser_02_Login extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void BeforeClass(String browserName){
        driver = getBrowserDriver(browserName);
        userHomePageObject = PageGeneratorManage.getUserHomePage(driver);
        userHomePageObject.clickOnAccountLinkOnHeader();
        userHomePageObject.clickOnMenuLinkByNameAndArea("page-header-container", "Log In");
        userLoginPO = PageGeneratorManage.getUserLoginPO(driver);
        userLoginPO.addCookie(PandaUser_01_Register.loggedCookies);
        userLoginPO.refreshThePage();
    }
    @Test
    public void User_01_Verify_Product_Price(){
        log.info("Login page: Click on Mobile");
        userProductsListPO = userLoginPO.clickOnCategoryOnHeaderNavByName("Mobile");

        log.info("Profuct list: get price of product: Sony Xperia");
        priceOnProductList = userProductsListPO.getPriceByProductNameOnProductList("Sony Xperia");

        log.info("Product list: click on product: Sony Xperia");
        userProductDetailPO = userProductsListPO.clickOnProductByName("Sony Xperia");

        log.info("Product detail: get price of product: Sony Xperia");
        priceOnProductDetail = userProductDetailPO.getPriceByProductNameOnProductDetail();

        log.info("Verify price of product between product list and product detail pages");
        Assert.assertEquals(priceOnProductDetail, priceOnProductList);
    }
    @Test
    public void User_02_Check_Discount_Code_Is_Working(){
        log.info("Product detail: click on Mobile category");
        userProductsListPO = userProductDetailPO.clickOnCategoryOnHeaderNavByName("Mobile");

        log.info("Product list: Add a product to cart");
        userShoppingCartPO = userProductsListPO.clickOnAddToCartButtonByProductName("Sony Xperia");

        log.info("Shopping cart: Verify Add to cart success message displayed");
        Assert.assertTrue(userShoppingCartPO.isAddToCartSuccessMessageDisplayed());

        log.info("Shopping cart: enter a discount code");
        userShoppingCartPO.inputADiscountCode(discountCode);

        log.info("Shopping cart: click on Apply button");
        userShoppingCartPO.clickOnApplyButton("Apply");

        log.info("Shopping cart: verify the discount amount");
        userShoppingCartPO.getAmountByTypePrice("Subtotal");
        userShoppingCartPO.getAmountByTypePrice("Discount");
        userShoppingCartPO.getGrandTotal();
        Assert.assertTrue(userShoppingCartPO.isDiscountAmountCorrect("Discount", "Subtotal"));
        Assert.assertTrue(userShoppingCartPO.isGrandTotalDiscountCorrect("Discount", "Subtotal"));
    }
    @Test
    public void User_03_Check_Maximum_QTY(){
        log.info("Shopping cart: click on Mobile category");
        userProductsListPO = userShoppingCartPO.clickOnCategoryOnHeaderNavByName("Mobile");

        log.info("Product list: Add a product to cart");
        userShoppingCartPO = userProductsListPO.clickOnAddToCartButtonByProductName("Sony Xperia");

        log.info("Shopping cart: edit qty to 501");
        userShoppingCartPO.inputQty("501");

        log.info("Shopping cart: click on Update button");
        userShoppingCartPO.clickOnUpdateButton();

        log.info("Shopping cart: verify message displayed");
        Assert.assertTrue(userShoppingCartPO.isAnErrorMessageDisplayed());
        Assert.assertTrue(userShoppingCartPO.isMaximumQtyMessageDisplayed());

        log.info("Shopping cart: Click on Empty cart link");
        userShoppingCartPO.clickOnEmptyCartLink();

        log.info("Shopping cart: verify cart empty");
        Assert.assertTrue(userShoppingCartPO.isShoppingCartEmptyDisplayed());
    }

    @Test
    public void User_04_Compare_Two_Products(){
        String pandanWindowID;
        log.info("Shopping cart: click on Mobile category");
        userProductsListPO = userShoppingCartPO.clickOnCategoryOnHeaderNavByName("Mobile");
        pandanWindowID = userProductsListPO.getPandaWindowID();

        log.info("Product list: click on Add to compare 2 products");
        userProductsListPO.clickWishListOrCompareByProductNameAndLinkName("Sony Xperia", "link-compare");
        Assert.assertEquals(userProductsListPO.getSuccessMessage(), "The product Sony Xperia has been added to comparison list.");

        userProductsListPO.clickWishListOrCompareByProductNameAndLinkName("IPhone", "link-compare");
        Assert.assertEquals(userProductsListPO.getSuccessMessage(), "The product IPhone has been added to comparison list.");

        log.info("Product list: click on Compare button");
        userProductsListPO.clickOnCompareButton();

        log.info("Product list: switch to compare window");
        userProductsListPO.goToComparePage("Products Comparison List - Magento Commerce");
        userComparePO = PageGeneratorManage.getUserComparePO(driver);
        Assert.assertTrue(userComparePO.isCompareTitleDisplayed());

        log.info("Compare page: verify 2 products are compared");
        Assert.assertTrue(userComparePO.isProductByNameDisplayed("IPhone"));
        //Assert.assertTrue(userComparePO.isSkuProductByNameDisplayed("MOB0002"));

        Assert.assertTrue(userComparePO.isProductByNameDisplayed("Sony Xperia"));
        //Assert.assertTrue(userComparePO.isSkuProductByNameDisplayed("MOB001"));

        log.info("Compare page: click on Close window");
        userComparePO.clickOnCloseWindowButton();

        log.info("Product list: switch to Product list page");
        userComparePO.goToTheProductListPage(pandanWindowID);
        userProductsListPO = PageGeneratorManage.getUserProductsListPO(driver);
        Assert.assertTrue(userProductsListPO.isLogoPageDisplay());
    }
    @Test
    public void User_05_Share_Wish_List(){
        userProductsListPO.clickOnCategoryOnHeaderNavByName("TV");

        log.info("Product list: click on Add to wish list Iphone");
        userProductsListPO.clickWishListOrCompareByProductNameAndLinkName("LG LCD", "link-wishlist");

        log.info("Wish list page: verify wish list success message displayed");
        userWishListPO = PageGeneratorManage.getUserWishListPO(driver);
        Assert.assertTrue(userWishListPO.getSuccessMessage().contains("LG LCD has been added to your wishlist."));

        log.info("Wish list page: click on Share wishlist button");
        userWishListPO.clickOnButtonByNameOnWishList("Share Wishlist");
        userShareWishListPO = PageGeneratorManage.getUserShareWishListPO(driver);

        log.info("Share wishlist page: enter an email and a message");
        userShareWishListPO.inputToTextAreaByLabel("rosy90@gmail.com", "emails");
        userShareWishListPO.inputToTextAreaByLabel("rosy test", "message");

        log.info("Share wishlist page: click on Share wishlist button");
        userShareWishListPO.clickOnButtonByNameOnWishList("Share Wishlist");

        log.info("Wish list page: verify share wish list success message displayed");
        userWishListPO = PageGeneratorManage.getUserWishListPO(driver);
        Assert.assertTrue(userWishListPO.getSuccessMessage().contains("Your Wishlist has been shared."));
        Assert.assertTrue(userWishListPO.isProductWishListDisplayedByName("LG LCD"));
    }
    @Test
    public void User_06_Purchase_A_Product(){

    }


    @AfterClass(alwaysRun = true)
    public void AfterClass(){
        closeBrowserDriver();
    }
    private WebDriver driver;
    private UserHomePageObject userHomePageObject;
    private UserLoginPO userLoginPO;
    private UserProductsListPO userProductsListPO;
    private UserProductDetailPO userProductDetailPO;
    private UserShoppingCartPO userShoppingCartPO;
    private UserComparePO userComparePO;
    private UserWishListPO userWishListPO;
    private UserShareWishListPO userShareWishListPO;
    private String priceOnProductList, priceOnProductDetail;
    private String discountCode = "GURU50";
    private double subTotalAmount, discountAmount, grandTotalAmount;
}
