package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.DEFAULT_TIMEOUT;
import static com.qa.opencart.constants.AppConstants.Login_Page_Fraction_URL;
import static com.qa.opencart.constants.AppConstants.Login_Page_Title;

import java.util.ArrayList;
import java.util.List;

import static com.qa.opencart.constants.AppConstants.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By Headers=By.xpath("//div[@id='content']//h2");
	private By search=By.name("search");
	private By searchicon=By.cssSelector("div#search button");
	
	public AccountsPage(WebDriver driver)
	{
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	public String getAccountPageTitle() {
		String title=eleUtil.waitFotTitleIs(Account_Page_Title, DEFAULT_TIMEOUT);
		System.out.println("The login Page Title is : " +title);
		return title;
	}
	
	public String getAccountPageURL() {
		String url=eleUtil.waitForURLContains(Account_Page_Fraction_URL, DEFAULT_TIMEOUT);
		System.out.println("The login Page URL is : " +url);
		return url;
	}
	
	public List<String> getAccPageHeaders() {
		List<WebElement> HeaderList=eleUtil.getElements(Headers);
		List<String> Headersdata=new ArrayList<String>();
		for(WebElement e:HeaderList) {
			String Text=e.getText();
			System.out.println("The Header Text is : "+Text);
			Headersdata.add(Text);
			}
		return Headersdata;
	}
	
	public ResultPage doSearch(String searchkey) {
		System.out.println("The search key product name is : " +searchkey);
		eleUtil.doSendKeys(search, searchkey);
		eleUtil.doClick(searchicon);
		return new ResultPage(driver);
	}
	

}
