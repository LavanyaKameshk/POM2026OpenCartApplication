package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.qa.opencart.constants.AppConstants.*;

import com.qa.opencart.Utils.ElementUtil;

public class ResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public ResultPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}
	
	private By productcount=By.cssSelector("div.product-thumb");
	
	
	public int getResultPageCount() {
		int ProductCount=eleUtil.waitForAllElementsVisible(productcount, DEFAULT_TIMEOUT).size();
		System.out.println("The Product search count is :" +ProductCount);
		return ProductCount;
	}
	
	public ProductPage selectProduct(String productname) {
		System.out.println("The Product name is : " +productname);
		eleUtil.doClick(By.linkText(productname));
		return new ProductPage(driver);
		
	}

}
