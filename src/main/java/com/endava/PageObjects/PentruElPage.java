package com.endava.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by vpetrache on 8/4/2016.
 */
public class PentruElPage {
    @FindBy(xpath = "//ul[@class='links-list']/li/a[@href='/men/accessories']")
    private WebElement leftSideMenu;

    @FindBy(xpath = "//li/a[@href='/men/accessories/watches']")
    private WebElement ceasuriPage;

    @FindBy(xpath = "(//div[@class='w-info']/span[@class='name'])[last()]")
    private WebElement lastProduct;

    @FindBy(xpath = "(//div[@class='ui-rating']//span[@class='stars'])[last()]")
    private WebElement stars;

    private WebDriver driver;

    public PentruElPage(WebDriver driver){
        this.driver=driver;
    }

    public void accessCeasuriPage(){
        leftSideMenu.click();
        ceasuriPage.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://ro.oriflame.com/men/accessories/watches"));
        wait.until(ExpectedConditions.visibilityOf(lastProduct));
    }

    public String productName(){
        String productName = lastProduct.getText();
        return productName;
    }

    public String numberOfStars(){
        String productStars = stars.getText();
        return productStars;
    }

    public ProductDetailsPage accessingProductPage(){
        lastProduct.click();
        ProductDetailsPage productDetailsPage = PageFactory.initElements(driver, ProductDetailsPage.class);
        productDetailsPage.waitforDetailsPage();
        return productDetailsPage;
    }

    public void waitForPentruElPage(){

        WebDriverWait wait= new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(leftSideMenu));
    }
}
