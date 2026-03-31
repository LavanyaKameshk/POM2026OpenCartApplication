package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.Utils.LogUtil;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	
	public WebDriver initdriver(Properties prop) {
		LogUtil.info("Properties: "+prop);
		
		String browsername=prop.getProperty("browser");
		//System.out.println("The browser name is : "+browsername);
		LogUtil.info("The browser name :"+browsername);
		ChainTestListener.log("The browser name is : "+browsername);
		highlight=prop.getProperty("highlight");
		optionsManager=new OptionsManager(prop);
		switch (browsername.toLowerCase().trim()) {
		case "chrome":
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "edge":
			//driver=new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "firefox":
			//driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "safari":
			//driver=new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;	

		default:
			//System.out.println("Please pass the valid browser name: "+browsername);
			LogUtil.error("Pass the valid browwser name : "+browsername);
			throw new BrowserException("======Invalid Browser=====");
			
		}
		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	
	//This is used to initize the config properties
	//mvn clean install -DenvName
	public Properties initProp() {
		String envName = System.getProperty("env");
		FileInputStream ip = null;
		prop = new Properties();

		try {
			if (envName == null) {
				 //System.out.println("env is null, hence running the tests on QA env by default...");
				LogUtil.warn("env is null, hence running the tests on QA env by default...");
				ip = new FileInputStream("./src/test/resouces/config/qa.config.properties");
			} else {
				System.out.println("Running tests on env: " + envName);
				
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resouces/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resouces/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resouces/config/statge.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resouces/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resouces/config/config.properties");
					break;

				default:
					LogUtil.error("----invalid env name---" + envName);
					throw new FrameworkException("===INVALID ENV NAME==== : " + envName);
					
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}

}
