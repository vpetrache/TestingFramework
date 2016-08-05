package com.endava.PageObjects;

import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by vpetrache on 8/2/2016.
 */
public class HomePage {

    @FindBy(xpath = "//li/a[contains(.,'ngrijire Corp')]")
    private WebElement ingrijireCorp;

    @FindBy(xpath = "//li/a[@data-category='men']")
    private WebElement pentruEl;

    @FindBy(xpath = "//button[@class='k-button']")
    private WebElement inchideButton;

    private WebDriver driver;

    public HomePage(WebDriver driver){

        this.driver=driver;
    }

    public IngrijireCorp accessingIngrijireCorp() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(inchideButton));
        inchideButton.click();
        wait.until(ExpectedConditions.visibilityOf(ingrijireCorp));
        ingrijireCorp.click();

        IngrijireCorp ingrijireCorp = PageFactory.initElements(driver, IngrijireCorp.class);
        ingrijireCorp.waitForPage();
        return ingrijireCorp;
    }


    public PentruElPage accessingPentruEl() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(inchideButton));
        inchideButton.click();
        wait.until(ExpectedConditions.visibilityOf(pentruEl));
        pentruEl.click();
        PentruElPage pentruElPage = PageFactory.initElements(driver, PentruElPage.class);
        pentruElPage.waitForPentruElPage();
        return pentruElPage;
    }

}


