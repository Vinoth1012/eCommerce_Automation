package com.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public static String strBaseUrl;
	public static String strBrowser;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Actions action;
	public static ExtentReports report;
	public static ExtentTest test;
	
	public void launchBrowser() throws Exception
	{
		try {
			
			 report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReport\\ExtentReportResults.html");
			 test = report.startTest("ExtentDemo");
			 strBrowser = Config.readPropertyFile("Browser").toUpperCase().trim();
			 switch(strBrowser) {
			 case "CHROME":
				 WebDriverManager.chromedriver().setup();
				 driver = new ChromeDriver();
				 driver.manage().window().maximize();
				 break;
				 
			 case "EDGE":
				 WebDriverManager.edgedriver().setup();
				 driver = new EdgeDriver();
				 driver.manage().window().maximize();
				 break;
				 
			 case "FIREFOX":
				 WebDriverManager.firefoxdriver().setup();
				 driver = new FirefoxDriver();
				 driver.manage().window().maximize();
				 break;
				
			 default:
				 throw new Exception(strBrowser + " not valid case for launchBrowser");
			 }
			 
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			 action = new Actions(driver);
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception();
			}
	}
		
	
	public void launchApp() throws Exception
	{
		try {
			strBaseUrl = Config.readPropertyFile("URL");
			driver.get(strBaseUrl);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}		 
	
	public void closeBrowser() throws Exception
	{
		try {
			driver.close();
			driver.quit();
			report.endTest(test);
			report.flush();
//		 report.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}	
	
	
	@AfterSuite
	public void tearDown() throws Exception 
	{
		try {
			driver.close();
			driver.quit();
			report.endTest(test);
			report.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		
	}
	
	
}
