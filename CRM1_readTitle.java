package alchemy;

import org.testng.annotations.Test;

import Library.CRM.CRMpom;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class CRM1_readTitle {
		private WebDriver driver;
		private CRMpom CRMpom;
		private String baseUrl;
		private static Properties properties;
	
		@BeforeClass
		public void setUpBeforeClass() throws IOException
		{
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/input.properties");
			properties.load(inStream);
			driver = new FirefoxDriver();
			baseUrl = properties.getProperty("baseURL");
			driver.get(baseUrl);
		}
		
		@Test
		public void readTitle()
		{	
			//read title
	        String title = driver.getTitle();
	        Assert.assertEquals("SuiteCRM", title);
		}
	 
		@AfterClass
		public void afterClass() 
		{
			driver.close();
		}

}
