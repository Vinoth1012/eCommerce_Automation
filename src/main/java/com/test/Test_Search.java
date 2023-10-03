package com.test;

import org.testng.annotations.Test;

import com.pages.Homepage;
import com.pages.Results;
import com.utils.Base;

public class Test_Search {
	
  @Test
  public void Validate_Search_And_Price_Filter() throws Exception {
	  Base base = new Base();
	  Homepage home = new Homepage();
	  Results result = new Results();
	  
	  base.launchBrowser();
	  base.launchApp();
	  
	  home.waitForPageLoad();
	  home.enterInput("Search", "Samsung Mobiles");
	  home.buttonClick("Search");
	  
	  result.waitForPageLoad();
	  result.applyFilter("Price", "\\u20B91,000 - \\u20B95,000");
	  result.verifyResults("Price", "\\u20B91,000 - \\u20B95,000");
	  
	  
	  
	  
	  
	  
  }
  
  
  
}
