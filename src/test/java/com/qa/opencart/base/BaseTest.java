package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.Utils.LogUtil;
import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultPage;

import io.qameta.allure.Description;


//@Listeners(ChainTestListener.class)
public class BaseTest {
	
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected ResultPage resultPage;
	protected ProductPage productPage;
	protected RegisterPage registerPage;
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df=new DriverFactory();
		prop=df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		driver=df.initdriver(prop);
		
		loginPage=new LoginPage(driver);
		
		
	}
	
	@BeforeMethod
	public void beforeMethod(ITestContext result) {
		LogUtil.info("-----Strating testcases-----" +result.getName());
	}
	
	@AfterMethod //will be running after each @test method
	public void attachScreenshot(ITestResult result) {
		if(!result.isSuccess()) {//only for failure test cases -- true
			LogUtil.info("---screenshot is taken---");
			
			ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		}
		ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
		LogUtil.info("-----Ending testcases-----" +result.getMethod().getMethodName());
	}
	
	@Description("closing the browser..")
	@AfterTest
	public void tearDown() {
		
		driver.quit();
		LogUtil.info("---Closing the browser---");
	}

}
