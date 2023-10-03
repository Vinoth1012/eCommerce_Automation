package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.utils.Base;

public class Homepage extends Base {

	private By Search_Input = By.id("twotabsearchtextbox");
	private By Search_Btn = By.id("nav-search-submit-button");
	private By Orders_Link = By.id("nav-orders");
	private By Cart_Link = By.id("nav-cart");
	private By Hamburger_Menu_Link = By.id("HamburgerMenuDesktop");
	
	
	
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
		try {
			switch (strField) {
			case "SEARCH":
				driver.findElement(Search_Btn).click();
				break;
				
			case "CART":
				driver.findElement(Cart_Link).click();
				break;
				
			case "ORDERS":
				driver.findElement(Orders_Link).click();
				break;
				
			case "HAMBURGERMENU":
				driver.findElement(Hamburger_Menu_Link).click();
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
