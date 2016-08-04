package com.endava.test;

import com.endava.PageObjects.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Set;

/**
 * Created by vpetrache on 8/2/2016.
 */
public class OriflameTest extends TestBaseClass{

    @Test
    public void accessProductDetails() {
    IngrijireCorp ingrijireCorp = homePage.accessingIngrijireCorp();
        Integer totalArticles = ingrijireCorp.getTotalArticles();
        System.out.println("There are " + totalArticles + " articles in Ingrijire Corp page.");
        Assert.assertTrue(totalArticles > 0);

        ingrijireCorp.highestPriceFilter();
        String productName = ingrijireCorp.firstProductName();
        System.out.println("The first product in the list is: " + productName);

        String priceFirstProduct = ingrijireCorp.firstProductPrice();
        System.out.println("The first product's price is: " + priceFirstProduct + "RON");

        ProductDetailsPage productDetailsPage = ingrijireCorp.accessProductDetails();
        String productDetailsName = productDetailsPage.getProductName();
        String productDetailsPrice = productDetailsPage.getProductPrice();
        Assert.assertEquals(productName, productDetailsName);
        Assert.assertEquals(priceFirstProduct, productDetailsPrice);
        productDetailsPage.getBackToIngrijireCorp();

        NoutatiPage noutatiPage = ingrijireCorp.getPaginaNoutati();
        Integer noutatiArticlesNumber =noutatiPage.ArticlesNoutati();
        System.out.println("There are " + noutatiArticlesNumber + " articles in Noutati page.");
        Assert.assertTrue(noutatiArticlesNumber > 0);


        Integer basket0Item = noutatiPage.GetBasket();
        noutatiPage.AddArticle();
        Integer basket1Item = noutatiPage.GetBasket();
        Assert.assertTrue(basket0Item != basket1Item);

        CosPage cosPage = noutatiPage.getShoppingCartPage();
        Double points = cosPage.getPoints();
        Double expectedPoints = points * 3;

        Double pretTotal = cosPage.getTotalPrice();
        Double expectedPrice = pretTotal * 3;

        cosPage.IncreaseQuantity();

        Double updatedPoints = cosPage.getPoints();
        Assert.assertEquals(expectedPoints, updatedPoints);
        System.out.println("You get " + updatedPoints + " points for making this order.");

        Double updatedPrice = cosPage.getTotalPrice();
        Assert.assertEquals(expectedPrice, updatedPrice);
        System.out.println("The Total Price is " + updatedPrice + " RON.");
    }

    @Test
    public void PentruElTest(){
    PentruElPage pentruElPage = homePage.accessingPentruEl();
        pentruElPage.accessCeasuriPage();
        String productName = pentruElPage.productName();
        System.out.println(productName + " is the name of the product");
        String productRating = pentruElPage.numberOfStars();
        System.out.println(productName + " has " + productRating + " stars.");

    ProductDetailsPage productDetailsPage = pentruElPage.accessingProductPage();
        String pageURL = productDetailsPage.getCurrentPageURL();

    EmailPage emailPage = productDetailsPage.getEmailLink();
        String productURL = emailPage.getProductUrl();
        String nameOfProduct = emailPage.getProductName();
        String pageName = emailPage.getPageName();

        Assert.assertEquals("Email catre prieteni", pageName);
        Assert.assertEquals(pageURL, productURL);
        Assert.assertEquals(productName, nameOfProduct);

    }

}
