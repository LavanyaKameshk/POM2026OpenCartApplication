package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;

public class AccountsPageTest extends BaseTest {
  
  @BeforeClass
  public void accPageSet() {
	  
	  accPage=loginPage.dologin(prop.getProperty("username"), prop.getProperty("password"));
	  
  }
  @Test
  public void accPageTitleTest() {
	  String accPageTitle=accPage.getAccountPageTitle();
	  Assert.assertEquals(accPageTitle, Account_Page_Title);
  }
  
  @Test
  public void accPageURLTest() {
	  String accPageURL=accPage.getAccountPageURL();
	  Assert.assertTrue(accPageURL.contains(Account_Page_Fraction_URL));
  }
  
  public void accPageHeaderList() {
	  List<String> actHeaderList=accPage.getAccPageHeaders();
	  Assert.assertEquals(actHeaderList, Acc_Header_List);
  }

}
