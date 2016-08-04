package com.endava.PageObjects;

import com.gargoylesoftware.htmlunit.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vpetrache on 8/3/2016.
 */
public class ProductDetailsPage {

@FindBy(xpath = "//a[@href='/bath-body?sort=pricehigh']")
    private WebElement InapoiButton;

@FindBy(xpath = "//h1[@class='name']")
    private WebElement productName;

@FindBy(xpath = "//form[@data-ux='product scrollbar']//span[@class='price mainCurrency']")
    private WebElement productPrice;

@FindBy(xpath = "//div[@class='w-share-icons']/a[@title='Email']")
    private WebElement emailIcon;

    private WebDriver driver;

    public ProductDetailsPage(WebDriver driver){

        this.driver=driver;
    }

    public String getProductName(){
        String Name = productName.getText();
        return Name;
    }

    public String getProductPrice(){
        String price = productPrice.getText();
        Pattern p=Pattern.compile("[0-9,]+");
        Matcher m=p.matcher(price);
        if (m.find()) {
            return m.group();
        }
        return "Unspecified Price for product: " + productName;

    }

    public IngrijireCorp getBackToIngrijireCorp(){

        InapoiButton.click();
        IngrijireCorp ingrijireCorp = PageFactory.initElements(driver, IngrijireCorp.class);
            ingrijireCorp.waitForPage();
        return ingrijireCorp;

    }

    public String getCurrentPageURL(){

        String currentURL = driver.getCurrentUrl();
        return currentURL;
    }

    public EmailPage getEmailLink(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        String window1 = driver.getWindowHandle();
        emailIcon.click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        EmailPage emailPage = PageFactory.initElements(driver, EmailPage.class);
        emailPage.waitForEmailPage();
        return emailPage;
    }

    public void waitforDetailsPage(){

        WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOf(productPrice));
            wait.until(ExpectedConditions.visibilityOf(productName));

    }
}
