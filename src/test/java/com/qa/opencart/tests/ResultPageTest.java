package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ResultPageTest extends BaseTest {
	
	@BeforeClass
	public void searchPageSetup() {
		accPage=loginPage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void SearchTest() {
		resultPage=accPage.doSearch("macbook");
		int actProductSearchCount=resultPage.getResultPageCount();
		Assert.assertEquals(actProductSearchCount, 3);
	}

}
