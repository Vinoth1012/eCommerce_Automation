package com.pages;

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
		try {
			switch (strCriteria) {
			case "":
				
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
