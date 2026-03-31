package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Utils.ElementUtil;
import com.qa.opencart.Utils.LogUtil;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Step;

import static com.qa.opencart.constants.AppConstants.*;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private static final Logger log = LogManager.getLogger(LogUtil.class);
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By email = By.id("input-email");
	private By password=By.id("input-password");
	private By login=By.xpath("//input[@value='Login']");
	private By forgotpassword=By.linkText("Forgotten Password");
	private By Register=By.linkText("Register");
	
	
	@Step("getting login Page Title")
	public String getLoginPageTitle() {
		String title=eleUtil.waitFotTitleIs(Login_Page_Title, DEFAULT_TIMEOUT);
		//System.out.println("The login Page Title is : " +title);
		log.info("The login Page Title is :"+title);
		return title;
	}
	
	@Step("getting login Page URL")
	public String getLoginPageURL() {
		String url=eleUtil.waitForURLContains(Login_Page_Fraction_URL, DEFAULT_TIMEOUT);
		System.out.println("The login Page URL is : " +url);
		return url;
	}
	
	@Step("getting login Page forgot password exist")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.isElementDisplayed(forgotpassword);
		
	}
	@Step("login Page user credentials username:{0} and password:{1}")
	public AccountsPage dologin(String username, String pwd) {
		System.out.println("User Credentials : "+":"+"Username is : "+username+":"+"Password is:" +pwd);
		eleUtil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(username);
		//driver.findElement(email).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(login);
		//String title= eleUtil.waitFotTitleIs(Account_Page_Title, DEFAULT_TIMEOUT);
		//System.out.println("The acount page title is : "+title);
		
		return new AccountsPage(driver);
	}
	
	@Step("Navigate to Register Page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.clickWhenReady(Register, DEFAULT_TIMEOUT);
		return new RegisterPage(driver);
	}

}
