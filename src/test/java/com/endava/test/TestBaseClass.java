package com.endava.test;

import com.endava.PageObjects.HomePage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by vpetrache on 8/2/2016.
 */
public class TestBaseClass {

    private static WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public static void setup()
    {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://www.oriflame.ro");

    }

    @Before
    public void initPageObjects(){homePage = PageFactory.initElements(driver, HomePage.class);
    }


    @AfterClass
    public static void teardown(){
        driver.quit();
    }

}
