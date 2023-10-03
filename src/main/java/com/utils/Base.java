package com.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	public static String strBaseUrl;
	public static String strBrowser;
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static ExtentReports report;
	public static ExtentTest test;
	
	
	public void launchBrowser() throws Exception
	{
		try {
			
			 report = new ExtentReports(System.getProperty("user.dir")+"\\ExtentReport\\ExtentReportResults.html");
			 test = report.startTest("ExtentDemo");
			 strBrowser = System.getProperty("browser").toUpperCase().trim();
			 switch(strBrowser) {
			 case "CHROME":
				 System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
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
			 wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception();
			}
	}
		
	
	public void launchApp() throws Exception
	{
		 strBaseUrl = Config.readPropertyFile("URL");
		 driver.get(strBaseUrl);
		}
		 
	
	public void closeBrowser() throws Exception
	{
		 driver.close();
		 driver.quit();
		 report.endTest(test);
		 report.flush();
//		 report.close();
		}
	
	
}
