package com.test;

import org.testng.annotations.Test;

import com.pages.Homepage;
import com.pages.Results;
import com.utils.Base;

public class Test_Search extends Base {
	
  @Test
  public void Validate_Search_And_Price_Filter() throws Exception {
	  Homepage home = new Homepage();
	  Results result = new Results();
	  
	  launchBrowser();
	  launchApp();
	  
	  home.waitForPageLoad();
	  home.enterInput("Search", "HTC");
	  home.buttonClick("Search");
	  
	  result.waitForPageLoad();
	  result.applyFilter("Min Price", "1000");
	  result.applyFilter("Max Price", "2000");
	  
	  Thread.sleep(20000);
	  
  }
  
  
  
}
