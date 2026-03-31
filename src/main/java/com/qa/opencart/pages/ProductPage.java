package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.qa.opencart.Utils.ElementUtil;

public class ProductPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	public ProductPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(driver);
	}

	

	private By productHeader=By.tagName("h1");
	private By productcount=By.cssSelector("ul.thumbnails img");
	private By productMetadata=By.xpath("(//div[@id=\"content\"]//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData=By.xpath("(//div[@id=\"content\"]//ul[@class='list-unstyled'])[2]/li");
	
	private Map<String, String> productmap;
	
	public String getProductHeader() {
		String Productname=eleUtil.waitForElementVisible(productHeader, DEFAULT_TIMEOUT).getText();
		System.out.println("The product header name is : "+Productname);
		return Productname;
	}
	
	public int getProductImageCount() {
		int Imagecount=eleUtil.waitForAllElementsVisible(productcount, LONG_DEFAULT_TIMEOUT).size();
		System.out.println("The product count is : "+Imagecount);
		return Imagecount;
	}
	
	public Map<String, String> getProductDetailsMap() {
		productmap=new LinkedHashMap<String, String>();
		productmap.put("productHeader", getProductHeader());
		productmap.put("productImages", String.valueOf(getProductImageCount()));
		getproductMetaData();
		getProductPriceData();
		System.out.println("The full product data is : " +productmap);
		return productmap;
	}
	
	public void getproductMetaData() {
		
		List<WebElement> Metalist=eleUtil.waitForAllElementsVisible(productMetadata, DEFAULT_TIMEOUT);
		for(WebElement e:Metalist) {
			String metadata=e.getText();
			String meta[]=metadata.split(":");
		    String metakey=meta[0].trim();
		    String metavalue=meta[1].trim();
		    productmap.put(metakey, metavalue);
			
		}
	}
	
	public void getProductPriceData() {
		
		List<WebElement> pricelist=eleUtil.waitForAllElementsVisible(productPriceData, DEFAULT_TIMEOUT);
		String productPrice=pricelist.get(0).getText();
		String exTaxPrice=pricelist.get(1).getText().split(":")[1].trim();	
		productmap.put("productprice", productPrice);
		productmap.put("extraPrice", exTaxPrice);
		
	}
}
