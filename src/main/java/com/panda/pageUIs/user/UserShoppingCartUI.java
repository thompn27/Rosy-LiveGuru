package com.panda.pageUIs.user;

public class UserShoppingCartUI {
    public static final String ADD_TO_CART_SUCCESS_MESSAGE = "xpath=//li[@class='success-msg']//span[text()='Sony Xperia was added to your shopping cart.']";
    public static final String DISCOUNT_CODE_INPUT_BOX = "css=div.discount-form input#coupon_code";
    public static final String DISCOUNT_APPLY_BUTTON = "xpath=//div[@class='discount-form']//span[text()='%s']";
    public static final String PRODUCT_AMOUNT = "xpath=//td[contains(text(),'%s')]/following-sibling::td//span";
    public static final String GRAND_TOTAL = "xpath=//strong[text()='Grand Total']/parent::td/following-sibling::td//span";
    public static final String QTY_TEXTBOX = "xpath=//input[@class='input-text qty']";
    public static final String UPDATE_QTY_BUTTON = "xpath=//button[@class='button btn-update']";
    public static final String ERROR_QTY_MESSAGE = "xpath=//li[@class='error-msg']//span[text()='Some of the products cannot be ordered in requested quantity.']";
    public static final String MAXIMUM_QTY_MESSAGE = "xpath=//a[text()='Sony Xperia']/parent::h2/following-sibling::p[contains(text(),'The maximum quantity allowed for purchase is 500.')]";
    public static final String EMPTY_CART_LINK = "css=button#empty_cart_button";
    public static final String SHOPPING_CART_IS_EMPTY_MESSAGE = "xpath=//div[@class='page-title']//h1[text()='Shopping Cart is Empty']";
}
