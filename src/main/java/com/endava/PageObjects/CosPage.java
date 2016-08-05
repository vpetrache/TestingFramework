package com.endava.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vpetrache on 8/3/2016.
 */
public class CosPage {
    @FindBy(xpath = "//span[@class='product-number']")
    private WebElement productCode;

    @FindBy(xpath = "//span[@class='k-numeric-wrap k-state-default']/input[@class='k-input hidden']")
    private WebElement quantity;

    @FindBy(xpath = "//div[@class='basket-products-table']//li[@class='bp bp-1 mobile-on']")
    private WebElement points;

    @FindBy(xpath = "//span[@title='Increase value']")
    private WebElement increaseButton;

    @FindBy(xpath = "//span[@title='Decrease value']")
    private WebElement decreaseButton;

    @FindBy(xpath = "//li[@class='total-price']/span[@class='price-box total-price-1']")
    private WebElement totalPrice;

    @FindBy(xpath = "//h1[@class='title']")
    private WebElement labelToClick;

    private WebDriver driver;

    public CosPage(WebDriver driver){
        this.driver=driver;
    }


    //there's a bug on shopping cart page, Puncte is comma separated, but if I modify the quantity, the point value
//    // is point separated
//    public void DecreaseQuantityToGetPoints(){
//        decreaseButton.click();
//    }

    public void IncreaseQuantity(){

        increaseButton.click();
        increaseButton.click();
        labelToClick.click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.attributeContains(quantity, "aria-valuenow", "3"));
    }

    public Double getPoints(){

        String t = points.getText();
        String totalPoints = t.replace(",", ".");
        return Double.parseDouble(totalPoints);

    }

    public Double getTotalPrice(){
        String price = totalPrice.getText();
        Pattern p = Pattern.compile("[0-9,]+");
        Matcher m = p.matcher(price);
        String s = "";
        if (m.find()) {
           s=m.group();
        }
        String pretFaraVirgula = s.replace(",", ".");
        return Double.parseDouble(pretFaraVirgula);
   }

    public void WaitForCosPage(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(productCode));
    }
}
