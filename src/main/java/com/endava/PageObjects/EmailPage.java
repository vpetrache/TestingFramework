package com.endava.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by vpetrache on 8/4/2016.
 */
public class EmailPage {
    @FindBy(xpath = "//div/span[@class='at-expanded-menu-page-title']")
    private WebElement productName;

    @FindBy(xpath = "//div/span[@class='at-expanded-menu-page-url']")
    private WebElement productUrl;

    @FindBy(xpath = "//span[@class='at-expanded-menu-title']")
    private WebElement pageName;

    private WebDriver driver;

    public EmailPage(WebDriver driver){
        this.driver=driver;
    }

    public String getProductName(){
        String name = productName.getText();
        return name;
    }

    public String getProductUrl() {
        String url = productUrl.getText();
        return url;
    }

    public String getPageName(){
        String page = pageName.getText();
        return page;
    }

    public void waitForEmailPage(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(productName));
        wait.until(ExpectedConditions.visibilityOf(productUrl));

    }
}
