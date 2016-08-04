package com.endava.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.fail;

/**
 * Created by vpetrache on 8/2/2016.
 */
public class IngrijireCorp{

    @FindBy(xpath = "//div[@class='filter-count']/span[@class='total-count']")
    private WebElement articles;

    @FindBy(xpath = "//span[@class='k-dropdown-wrap k-state-default']/span[@class='k-select']")
    private WebElement filterList;

    @FindBy(xpath= "//ul[@id='top-filter_listbox']/li[3]")
    private WebElement option;

    @FindBy(xpath = "(//div[@class='w-info']/span[@class='name'])[1]")
    private WebElement name;

    @FindBy(xpath = "(//section/a[@data-ux='quick-shop'])[1]")
    private WebElement productLink;

    @FindBy(xpath = "(//div[@class='w-info']/div[@class='offer'])[1]/span[@class='price mainCurrency']")
    private WebElement price;

    @FindBy(xpath = "//ul[@class='links-list']//a[@href='/bath-body/new']")
    private WebElement paginaNoutati;

    private WebDriver driver;

    public IngrijireCorp(WebDriver driver){

        this.driver=driver;
    }

    public Integer getTotalArticles(){
        String totalArticles = articles.getText();
        return Integer.parseInt(totalArticles);

    }

    public void highestPriceFilter(){

        filterList.click();
        option.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlContains("bath-body?sort=pricehigh"));

    }

    public String firstProductName(){
        String firstProductName = name.getText();
        return firstProductName;
    }

    public String firstProductPrice(){
        String firstProductPrice = price.getText();
        Pattern p=Pattern.compile("[0-9,]+");
        Matcher m=p.matcher(firstProductPrice);
        if (m.find()) {
            return m.group();
        }
        return "Unspecified Price for product: " + name;
    }

    public ProductDetailsPage accessProductDetails(){

       productLink.click();
       ProductDetailsPage productDetailsPage = PageFactory.initElements(driver, ProductDetailsPage.class);
        productDetailsPage.waitforDetailsPage();
        return productDetailsPage;

    }

    public NoutatiPage getPaginaNoutati(){

        paginaNoutati.click();
        NoutatiPage noutatiPage = PageFactory.initElements(driver, NoutatiPage.class);
        noutatiPage.WaitForNoutatiPage();
        return noutatiPage;
    }

    public void waitForPage(){

        WebDriverWait wait = new WebDriverWait(driver, 10);

        wait.until(ExpectedConditions.visibilityOf(name));
        wait.until(ExpectedConditions.textToBePresentInElement(articles, ""));
        wait.until(ExpectedConditions.visibilityOf(filterList));
   }
}