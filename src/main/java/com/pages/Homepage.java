package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.utils.Base;

public class Homepage extends Base {

	private By Search_Input = By.name("search");
	private By Search_Btn = By.xpath("//button[@title='Search']");
	private By Compare_Link = By.xpath("//a[@aria-label='Compare']");
	private By Wishlist_Link = By.id("//a[@aria-label='Wishlist']");
	private By Cart_Link = By.id("(//a[contains(@class,'cart')])[1]");
	
	
//	This method is to wait for page load
	public void waitForPageLoad() throws Exception {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(Search_Input));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	
//	This method is for user input action
	public void enterInput(String strField, String strValue) throws Exception {
		strField = strField.replaceAll(" ", "").toUpperCase().trim();
		strValue = strValue.trim();
		try {
			switch (strField) {
			case "SEARCH":
				driver.findElement(Search_Input).sendKeys(strValue);
				break;
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + strField);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
	}
	
	
//	This method is for button click action
	public void buttonClick(String strField) throws Exception {
		strField = strField.replaceAll(" ", "").toUpperCase().trim();
		WebElement we;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			switch (strField) {
			case "SEARCH":
				we = driver.findElement(Search_Btn);
				js.executeScript("arguments[0].click()", we);
				break;
				
			case "CART":
				driver.findElement(Cart_Link).click();
				break;
				
			case "WISHLIST":
				driver.findElement(Wishlist_Link).click();
				break;
				
			case "COMPARE":
				driver.findElement(Compare_Link).click();
				break;
				
			default:
				throw new IllegalArgumentException("Unexpected value: " + strField);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
	}
	
	
	
}
