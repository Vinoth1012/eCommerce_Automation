package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.utils.Base;


public class Results extends Base {

	private By Results_Header = By.xpath("//h1");
	
	private By Filter_Price_Range_Min = By.xpath("(//input[@aria-label='Minimum Price'])[2]");
	private By Filter_Price_Range_Max = By.xpath("(//input[@aria-label='Maximum Price'])[2]");
	
	
//	This method is to wait for page load
	public void waitForPageLoad() throws Exception {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(Results_Header));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
	}
	
	
//	This method is for applying filter to the results
	public void applyFilter(String strCriteria, String strValue) throws Exception {
		strCriteria = strCriteria.replaceAll(" ", "").toUpperCase().trim();
		strValue = strValue.trim();
		WebElement we;
		try {
			switch (strCriteria) {
			case "MINPRICE":
			case "MINIMUMPRICE":
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Filter_Price_Range_Min));
				we = driver.findElement(Filter_Price_Range_Min);
				we.clear();
				we.sendKeys(strValue);
				break;

			case "MAXPRICE":
			case "MAXIMUMPRICE":
				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Filter_Price_Range_Max));
				we = driver.findElement(Filter_Price_Range_Max);
				we.clear();
				we.sendKeys(strValue);
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
			/*
			case "PRICE":
//				wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(Results_Price));
//				wePage = driver.findElements(Results_Page_Count);
				pageCount = Integer.parseInt(wePage.get(0).getText().trim());
//				iteration for all pages
				for(int i = 1; i <= pageCount; i++) {
//					weList = driver.findElements(Results_Price);	
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
				*/
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + strCriteria);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
		
		
	}
	
}
