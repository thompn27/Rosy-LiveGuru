package com.panda.commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    private WebDriver driver;
    private long longTime = 40;
    private long shortTime = 6;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public static BasePage getBasePageObject(WebDriver driver) {
        return new BasePage(driver);
    }

    public void openPageUrl(String urlName) {
        driver.get(urlName);
    }

    public String getTitlePage() {
        return driver.getTitle();
    }

    public String getCurrentUrlPage() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public void backToPage() {
        driver.navigate().back();
    }

    public void forwardToPage() {
        driver.navigate().forward();
    }

    public void refreshThePage() {
        driver.navigate().refresh();
    }

    private Alert waitAlertPresence(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert() {
        waitAlertPresence(driver).accept();
    }

    public void cancelAlert() {
        waitAlertPresence(driver).dismiss();
    }

    public String getTextAlert() {
        return waitAlertPresence(driver).getText();
    }

    public void sendKeyToAlert(String textValue) {
        waitAlertPresence(driver).sendKeys(textValue);
    }

    public void switchToWindowByID(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public By getByXpathLocator(String locator) {
        return By.xpath(locator);
    }

    // id=, class=, name=, css=, xpath=,
    public By getByLocator(String locatorType) {
        By by = null;
        if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=") || locatorType.startsWith("CLASS=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Not locator");
        }
        return by;
    }

    public String getDynamicXpath(String locatorType, String... dynamicValues) {
        if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("XPATH=")) {
            locatorType = String.format(locatorType, (Object[]) dynamicValues);
        }
        return locatorType;
    }

    public WebElement getWebElement(String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    public WebElement getWebElement(String locatorType, String... dynamicValues) {
        return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
    }

    public List<WebElement> getListWebElements(String locator) {
        return driver.findElements(getByLocator(locator));
    }

    public List<WebElement> getListWebElements(String locator, String... dynamicValues) {
        return driver.findElements(getByLocator(getDynamicXpath(locator, dynamicValues)));
    }

    public void clickToElement(String locator) {
        getWebElement(locator).click();
    }

    public void clickToElement(String locator, String... dynamicValues) {
        getWebElement(getDynamicXpath(locator, dynamicValues)).click();
    }

    public void sendKeyToElement(String locator, String textValue) {
        WebElement element = getWebElement(locator);
        element.clear();
        element.sendKeys(textValue);
    }

    public void sendKeyToElement(String locator, String textValue, String... dynamicValues) {
        WebElement element = getWebElement(getDynamicXpath(locator, dynamicValues));
        element.clear();
        element.sendKeys(textValue);
    }

    public String getTextElement(String locator) {
        return getWebElement(locator).getText();
    }

    public String getTextElement(String locator, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).getText();
    }

    public void selectItemInDropdownByDefault(String locator, String textvalue) {
        Select select = new Select(getWebElement(locator));
        select.selectByVisibleText(textvalue);
        //select.selectByValue(textvalue);
        //select.selectByIndex(iD);

    }

    public void selectItemInDropdownByDefault(String locator, String textvalue, String... dynamicValues) {
        Select select = new Select(getWebElement(getDynamicXpath(locator, dynamicValues)));
        select.selectByVisibleText(textvalue);
        //select.selectByValue(textvalue);
    }

    public String getSelectedItemInDropdownByDefault(String locator) {
        Select select = new Select(getWebElement(locator));
        return select.getFirstSelectedOption().getText();
    }

    public String getSelectedItemInDropdownByDefault(String locator, String... dynamicValues) {
        Select select = new Select(getWebElement(getDynamicXpath(locator, dynamicValues)));
        return select.getFirstSelectedOption().getText();
    }

    public Boolean isDropdownMultiple(String locator) {
        Select select = new Select(getWebElement(locator));
        return select.isMultiple();
    }

    public Boolean isDropdownMultiple(String locator, String... dynamicValues) {
        Select select = new Select(getWebElement(getDynamicXpath(locator, dynamicValues)));
        return select.isMultiple();
    }

    public void selectItemInCustomDropdown(String parentLocator, String childItemLocator, String expectedItem) {
        getWebElement(parentLocator).click();
        sleepInSecond(1);

        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
                sleepInSecond(1);

                item.click();
                sleepInSecond(1);
                break;
            }
        }
    }

    public String getAttributeValue(String locator, String attributeName) {
        return getWebElement(locator).getAttribute(attributeName);
    }

    public String getAttributeValue(String locator, String attributeName, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).getAttribute(attributeName);
    }

    public String getCssValue(String locator, String cssName) {
        return getWebElement(locator).getCssValue(cssName);
    }

    public String getCssValue(String locator, String cssName, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).getCssValue(cssName);
    }

    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getElementSize(String locator) {
        return getListWebElements(locator).size();
    }

    public int getElementSize(String locator, String... dynamicValues) {
        return getListWebElements(getDynamicXpath(locator, dynamicValues)).size();
    }

    public void checkToCheckboxOrRadio(String locator) {
        WebElement element = getWebElement(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkToCheckboxOrRadio(String locator, String... dynamicValues) {
        WebElement element = getWebElement(getDynamicXpath(locator, dynamicValues));
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void unCheckToCheckbox(String locator) {
        WebElement element = getWebElement(locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    public void unCheckToCheckbox(String locator, String... dynamicValues) {
        WebElement element = getWebElement(getDynamicXpath(locator, dynamicValues));
        if (element.isSelected()) {
            element.click();
        }
    }

    public boolean isDisplayElement(String locator) {
        return getWebElement(locator).isDisplayed();
    }

    public boolean isDisplayElement(String locator, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).isDisplayed();
    }

    public boolean isElementUnDisplayed(String locator) {
        overrideImplicitTimeout(shortTime);
        List<WebElement> elements = getListWebElements(locator);
        overrideImplicitTimeout(longTime);
        if (elements.isEmpty()) {
            return true;
        } else if (elements.size() > 0 && elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementUnDisplayed(String locator, String... dynamicValues) {
        overrideImplicitTimeout(shortTime);
        List<WebElement> elements = getListWebElements(getDynamicXpath(locator, dynamicValues));
        overrideImplicitTimeout(longTime);
        if (elements.size() == 0) {
            return true;
        } else if (elements.size() > 0 && elements.get(0).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    public void overrideImplicitTimeout(long timeOut) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
    }

    public boolean isEnableElement(String locator) {
        return getWebElement(locator).isEnabled();
    }

    public boolean isSelectedElement(String locator) {
        return getWebElement(locator).isSelected();
    }

    public boolean isSelectedElement(String locator, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).isSelected();
    }

    public void switchToFrameandIframe(String locator) {
        driver.switchTo().frame(getTextElement(locator));
    }

    public void switchToDefualtContent() {
        driver.switchTo().defaultContent();
    }

    public void doubleClick(String locator) {
        Actions action = new Actions(driver);
        action.doubleClick(getWebElement(locator)).perform();
    }

    public void hoverToElement(String locator) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(locator)).perform();
    }

    public void hoverToElement(String locator, String... dynamicValues) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(getDynamicXpath(locator, dynamicValues))).perform();
    }

    public void enterKeyboard(String locator, Keys keys) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(locator), keys).perform();
    }

    public void enterKeyboard(String locator, Keys keys, String... dynamicValues) {
        Actions action = new Actions(driver);
        action.sendKeys(getWebElement(getDynamicXpath(locator, dynamicValues)), keys).perform();
    }

    //    public void uploadOneFile(WebDriver driver, String locator, String projectPath){
//        String file1 = "file1";
//        String file1Path = projectPath + "/uploadFiles/" + file1;
//
//        WebElement element = getWebElement(driver, locator);
//        element.sendKeys(file1Path);
//
//    }
    public void uploadOneFile(String locator, String uploadFile, String fileName) {
        //uploadFile:
        String filePath = uploadFile + fileName;
        System.out.println(filePath);

        WebElement element = getWebElement(locator);
        element.sendKeys(filePath);
    }

    public void uploadMultipleFile(String locator, String uploadFile, String... fileNames) {
        String fullFileName = "";
        for (String file : fileNames) {
            fullFileName = uploadFile + file + "\n";
        }
        fullFileName = fullFileName.trim();

        WebElement element = getWebElement(locator);
        element.sendKeys(fullFileName);
    }

    public String getInnerText() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(String textExpected) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void highlightElement(String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(locator);
        String originalStyle = getAttributeValue(locator, "style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    public void clickToElementByJS(String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(locator));
    }

    public void clickToElementByJS(String locator, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", getWebElement(getDynamicXpath(locator, dynamicValues)));
    }

    public void scrollToElement(String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(locator));
    }

    public void scrollToElement(String locator, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(getDynamicXpath(locator, dynamicValues)));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(locator));
    }

    public boolean areJQueryAndJSLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    public String getElementValidationMessage(String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(locator));
        if (status) {
            return true;
        } else {
            return false;
        }
    }

    public void waitForElementVisible(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementVisible(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForAllElementVisible(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        explicitWait.until(ExpectedConditions.visibilityOfAllElements(getListWebElements(locator)));
    }

    public void waitForElementInVisible(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementInVisible(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForAllElementInVisible(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(locator)));
    }

    public void waitForElementClickable(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    public void waitForElementClickable(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForElementPresence(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(locatorType)));
    }

    public void waitForElementPresence(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
    }

    public void waitForAllElementPresence(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTime));
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locatorType)));
    }

    public Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();
    }

    public void addCookie(Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(1);
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
