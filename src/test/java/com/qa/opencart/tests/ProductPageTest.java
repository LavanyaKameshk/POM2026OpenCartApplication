package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Utils.CSVUtil;
import com.qa.opencart.base.BaseTest;

public class ProductPageTest extends BaseTest{
	@BeforeClass
	public void ProductInfoSetup() {
		accPage=loginPage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	/*@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][] {
			{"MacBook","MacBook Air"},
			{"MacBook Pro","MacBook Pro"},
			{"imac","iMac"}
		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void getProductHeader(String searchey, String productname) {
		resultPage=accPage.doSearch(searchey);
		productPage=resultPage.selectProduct(productname);
		String actProductHeader=productPage.getProductHeader();
		Assert.assertEquals(actProductHeader, productname);
		
	}
	*/
	
	@DataProvider
	public Object[][] getProductTestDataCount() {
		return new Object[][] {
			{"MacBook","MacBook Air",4},
			{"MacBook","MacBook Pro",4},
			{"imac","iMac",3}
		};
	}
	
	@DataProvider
	public Object[][] getProductCSVData() {
		return CSVUtil.csvData("product");
	}
	
	@Test(dataProvider = "getProductTestDataCount")
	public void getImagecount(String searchkey, String productname, int imagecount) {
		resultPage=accPage.doSearch(searchkey);
		productPage=resultPage.selectProduct(productname);
        int actImagecount=productPage.getProductImageCount();
		Assert.assertEquals(actImagecount, imagecount);
	}
	
	
	/*@Test
	public void productInfoTest() {
		resultPage=accPage.doSearch("MacBook");
		productPage=resultPage.selectProduct("MacBook");
		Map<String, String> actualProductdatadetails=productPage.getProductDetailsMap();
		SoftAssert softassert=new SoftAssert();
		softassert.assertEquals(actualProductdatadetails.get("Brand"),"Apple");
		softassert.assertEquals(actualProductdatadetails.get("productprice"),"$602.00");
		softassert.assertEquals(actualProductdatadetails.get("extraPrice"),"$500.00");
		softassert.assertAll();
		
	}
*/
}
