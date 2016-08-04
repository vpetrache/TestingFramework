package com.endava.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Created by vpetrache on 8/3/2016.
 */
public class NoutatiPage {

    @FindBy(xpath = "//div[@class='filter-count']/span[@class='total-count']")
    private WebElement articles;

    @FindBy(xpath = "(//div[@class='w-info']/span[@class='name'])[2]")
    private WebElement productName;

    @FindBy(xpath = "(//div[@class='w-info'])[2]")
    private WebElement image;

    @FindBy(xpath = "(//div[@class='figure']/span[contains(.,'Comand')])[2]")
    private WebElement comandaRapida;

    @FindBy(xpath = "//div[@id='cboxLoadedContent']//input[@type='submit']")
    private WebElement adaugareInCos;

    @FindBy(xpath = "//div[@id='cboxLoadedContent']")
    private WebElement popUp;

    @FindBy(xpath = "//span[@class='basket-items']")
    private WebElement basketItems;

    private WebDriver driver;

    public NoutatiPage(WebDriver driver){

        this.driver=driver;
    }

    public Integer ArticlesNoutati(){
        String totalArticles= articles.getText();
        return Integer.parseInt(totalArticles);
    }

    public Integer GetBasket(){

        String basketItem = basketItems.getText();
        if(basketItem.isEmpty()) {
            basketItem = ("0");
        }
        return Integer.parseInt(basketItem);
    }

    public void AddArticle(){
        Actions builder = new Actions(driver);
        builder.moveToElement(image)
                .click(comandaRapida)
                .perform();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(popUp));
        adaugareInCos.click();

        wait.until(ExpectedConditions.textToBePresentInElement(basketItems, "1"));
    }

    public CosPage getShoppingCartPage(){
        
        basketItems.click();
        CosPage cosPage = PageFactory.initElements(driver, CosPage.class);
        cosPage.WaitForCosPage();
        return cosPage;
    }

    public void WaitForNoutatiPage(){

        WebDriverWait wait= new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.textToBePresentInElement(articles, ""));
        wait.until(ExpectedConditions.visibilityOf(productName));
    }
}
