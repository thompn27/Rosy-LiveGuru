package com.panda.pageObjects.user;

import com.panda.objects.ObjectCommon;
import com.panda.pageUIs.user.UserShoppingCartUI;
import org.openqa.selenium.WebDriver;

public class UserShoppingCartPO extends ObjectCommon {
    private WebDriver driver;
    public UserShoppingCartPO(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    public boolean isAddToCartSuccessMessageDisplayed() {
        waitForElementVisible(UserShoppingCartUI.ADD_TO_CART_SUCCESS_MESSAGE);
        return isDisplayElement(UserShoppingCartUI.ADD_TO_CART_SUCCESS_MESSAGE);
    }

    public void inputADiscountCode(String discountCode) {
        waitForElementVisible(UserShoppingCartUI.DISCOUNT_CODE_INPUT_BOX);
        sendKeyToElement(UserShoppingCartUI.DISCOUNT_CODE_INPUT_BOX, discountCode);
    }

    public void clickOnApplyButton(String discountButtonName) {
        waitForElementClickable(UserShoppingCartUI.DISCOUNT_APPLY_BUTTON, discountButtonName);
        clickToElement(UserShoppingCartUI.DISCOUNT_APPLY_BUTTON, discountButtonName);
    }

    public double getAmountByTypePrice(String priceType) {
        waitForElementVisible(UserShoppingCartUI.PRODUCT_AMOUNT, priceType);
        return Double.parseDouble(getTextElement(UserShoppingCartUI.PRODUCT_AMOUNT, priceType).replace("$", ""));
    }

    public double getGrandTotal() {
        waitForElementVisible(UserShoppingCartUI.GRAND_TOTAL);
       return Double.parseDouble(getTextElement(UserShoppingCartUI.GRAND_TOTAL).replace("$", ""));
    }

    public boolean isDiscountAmountCorrect(String discount, String subTotal) {
        double discountPercentage = 0.05d;
        return getAmountByTypePrice(subTotal) * discountPercentage == -getAmountByTypePrice(discount);
    }

    public boolean isGrandTotalDiscountCorrect(String discount, String subTotal) {
        return getGrandTotal() == getAmountByTypePrice(subTotal) + getAmountByTypePrice(discount);
    }

    public void inputQty(String number) {
        waitForElementVisible(UserShoppingCartUI.QTY_TEXTBOX);
        clickToElement(UserShoppingCartUI.QTY_TEXTBOX);
        sendKeyToElement(UserShoppingCartUI.QTY_TEXTBOX, number);
    }

    public void clickOnUpdateButton() {
        waitForElementClickable(UserShoppingCartUI.UPDATE_QTY_BUTTON);
        clickToElement(UserShoppingCartUI.UPDATE_QTY_BUTTON);
    }

    public boolean isAnErrorMessageDisplayed() {
        waitForElementVisible(UserShoppingCartUI.ERROR_QTY_MESSAGE);
        return isDisplayElement(UserShoppingCartUI.ERROR_QTY_MESSAGE);
    }

    public boolean isMaximumQtyMessageDisplayed() {
        waitForElementVisible(UserShoppingCartUI.MAXIMUM_QTY_MESSAGE);
        return isDisplayElement(UserShoppingCartUI.MAXIMUM_QTY_MESSAGE);
    }

    public void clickOnEmptyCartLink() {
        waitForElementClickable(UserShoppingCartUI.EMPTY_CART_LINK);
        clickToElement(UserShoppingCartUI.EMPTY_CART_LINK);
    }

    public boolean isShoppingCartEmptyDisplayed() {
        waitForElementVisible(UserShoppingCartUI.SHOPPING_CART_IS_EMPTY_MESSAGE);
        return isDisplayElement(UserShoppingCartUI.SHOPPING_CART_IS_EMPTY_MESSAGE);
    }
}
