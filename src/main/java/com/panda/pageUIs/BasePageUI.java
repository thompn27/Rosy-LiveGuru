package com.panda.pageUIs;

public class BasePageUI {
    public static final String MENU_LINK_BY_NAME_AND_AREA = "xpath=//div[@class='%s']//a[@title='%s']";
    public static final String ACCOUNT_LINK_HEADER = "xpath=//div[@class='page-header-container']//span[text()='Account']";
    public static final String SIDEBAR_LINK_BY_NAME = "xpath=//div[contains(@class,'col-left sidebar')]//a[text()='%s']";
    public static final String INPUT_BOX_BY_LABELNAME = "xpath=//input[@id='%s']";
    public static final String LOGO_PAGE = "xpath=//a[@class='logo']";
    public static final String HEADER_CATEGORY_NAME = "xpath=//div[@id='header-nav']//a[text()='%s']";
    public static final String PRODUCT_PRICE_BY_NAME = "xpath=//a[text()='%s']/parent::h2/following-sibling::div//span[@class='price']";
    public static final String SUCCESS_MESSAGE = "xpath=//li[@class='success-msg']//span";
    public static final String BUTTON_NAME = "xpath=//button[@title='%s']";
}
