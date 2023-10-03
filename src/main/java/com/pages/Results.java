package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.utils.Base;


public class Results extends Base {

	private By Filter_Delivery_Day_Checkbox = By.xpath("//div[@id='s-refinements']//div[@id='deliveryRefinements']//ul//li//input");
	private By Filter_Delivery_Day_Label = By.xpath("//div[@id='s-refinements']//div[@id='deliveryRefinements']//ul//li");
	private By Filter_Customer_Review = By.xpath("//div[@id='s-refinements']//div[@id='reviewsRefinements']//ul//li//a//i//span");
	private By Filter_Price_Range = By.xpath("//div[@id='s-refinements']//div[@id='priceRefinements']//ul[1]//li//a//span");
	private By Filter_Price_Range_Low_Input = By.xpath("//div[@id='s-refinements']//div[@id='priceRefinements']//ul[1]//li//input[@id='low-price']");
	private By Filter_Price_Range_High_Input = By.xpath("//div[@id='s-refinements']//div[@id='priceRefinements']//ul[1]//li//input[@id='high-price']"); 
	private By Filter_Price_Range_Go_Button = By.xpath("//div[@id='s-refinements']//div[@id='priceRefinements']//ul[1]//li//input[@type='submit']");
	
	private By Results_Price = By.xpath("//div[contains(@class,'s-result-list s-search-results')]//div[@data-csa-c-type='item']//span[@class='a-price-whole']");
	
	private By Results_Page_Count = By.xpath("//div[@role='navigation']//span[@class='s-pagination-strip']//a[text()='Next']//preceding-sibling::span[1]");
	private By Results_Next_Page = By.xpath("//div[@role='navigation']//span[@class='s-pagination-strip']//a[text()='Next']");
	private By Language_Link = By.id("icp-touch-link-language");
	
	
//	This method is to wait for page load
	public void waitForPageLoad() throws Exception {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(Language_Link));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
	}
	
	
//	This method is for applying filter to the results
	public void applyFilter(String strCriteria, String strValue) throws Exception {
		strCriteria = strCriteria.replaceAll(" ", "").toUpperCase().trim();
		strValue = strValue.trim();
		List <WebElement> weList;
		Boolean flag = false;
		try {
			switch (strCriteria) {
			case "PRICE":
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Filter_Price_Range));
				weList = driver.findElements(Filter_Price_Range);
				for (int i = 0; i < weList.size(); i++) {
					if(weList.get(i).getText().trim().equals(strValue)) {
						weList.get(i).click();
						flag = true;
					}
				}
				if(!flag)
					throw new Exception(strValue + " not available for Price filter");
				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + strCriteria);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
	}
	
	
//	This method is for verifying the results are based on the filter refinements
	public void verifyResults(String strCriteria, String strValue) throws Exception {
		strCriteria = strCriteria.replaceAll(" ", "").toUpperCase().trim();
		strValue = strValue.trim();
		List <WebElement> weList, wePage;
		int pageCount = 0;
		String strActualValue = "";
		String[] strArrExpected = strValue.split("-");
		Double doubleActualValue, expectedValueLowRange, expectedValueHighRange;
		try {
			switch (strCriteria) {
			case "PRICE":
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Results_Price));
				wePage = driver.findElements(Results_Page_Count);
				pageCount = Integer.parseInt(wePage.get(0).getText().trim());
//				iteration for all pages
				for(int i = 1; i <= pageCount; i++) {
					weList = driver.findElements(Results_Price);	
//					iteration for all items in a page
					for (int j = 0; j < weList.size(); j++) {
						strActualValue = weList.get(j).getText();
						doubleActualValue = Double.valueOf(strActualValue.replaceAll(",", ""));
						expectedValueLowRange = Double.valueOf(strArrExpected[0]);
						expectedValueHighRange = Double.valueOf(strArrExpected[1]);
						if(!(expectedValueLowRange < doubleActualValue && doubleActualValue < expectedValueHighRange))
							throw new Exception("Results are not refined based on the selected price range");
					}
				}
				break;
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + strCriteria);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
		
		
	}
	
}
