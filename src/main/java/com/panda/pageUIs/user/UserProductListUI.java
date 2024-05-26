package com.panda.pageUIs.user;

public class UserProductListUI {
    public static final String PRODUCT_BY_NAME = "xpath=//a[text()='%s']";
    public static final String ADD_TO_CART_BUTTON_BY_PRODUCT_NAME = "xpath=//a[text()='%s']//parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']";
    public static final String WISH_LIST_LINK_OR_COMPARE_LINK = "xpath=//a[text()='%s']/parent::h2/following-sibling::div[@class='actions']//a[@class='%s']";
    public static final String COMPARE_BUTTON = "xpath=//button[@title='Compare']";
}
