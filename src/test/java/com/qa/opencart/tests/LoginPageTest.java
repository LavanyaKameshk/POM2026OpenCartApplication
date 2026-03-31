package com.qa.opencart.tests;

import org.testng.Assert;
import static com.qa.opencart.constants.AppConstants.*;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Feature("Open Cart-Login feature")
@Epic("Epic 100 :Designing pages for opencart applicatio")
@Story("User story for login page functionality")
public class LoginPageTest extends BaseTest{
	@Description("checking login page title of allure")
	@Severity(SeverityLevel.MINOR)
	@Owner("Lavanya")
	@Test(description = "Login Page Title checking")
	public void loginPageTitleTest() {
		String actTitle= loginPage.getLoginPageTitle();
		ChainTestListener.log("checking the login page title: " +actTitle );
		Assert.assertEquals(actTitle,Login_Page_Title);
	}
	
	@Description("checking login page URL of allure")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Lavanya")
	
	@Test(description = "Login Page URL")
	public void loginPageURLTest() {
		String actURL= loginPage.getLoginPageURL();
		Assert.assertTrue(actURL.contains(Login_Page_Fraction_URL));
	}
	
	
	@Description("checking login forgot pwd of allure")
	@Severity(SeverityLevel.NORMAL)
	@Owner("Lavanya")
	@Test(description = "checking for Forgot Pwd link exist in Login Page")
	public void lPforgotPwdExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("checking login user")
	@Severity(SeverityLevel.BLOCKER)
	@Owner("Lavanya")
	@Test(priority = Short.MAX_VALUE, description = "Login with Valid Credentials")
	public void loginPageloginTest() {
	accPage=loginPage.dologin(prop.getProperty("username"),prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountPageTitle(),Account_Page_Title);
		
	}
	
	

}
