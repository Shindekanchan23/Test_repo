package com.Utilities;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;

	@BeforeMethod
	public void OpenBrowser(String browser) {

		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("unsupported browser" + browser);
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

		try {
			Properties prop = new Properties();
			FileInputStream fs = new FileInputStream(
					"D://Personal//Automation_practice//Configuration//configuration.Properties");
			prop.load(fs);
			String client = prop.getProperty("Client");

			if (client.equalsIgnoreCase("SFO") || client.equalsIgnoreCase("InvictusClient")) {
				String invAppURL = prop.getProperty("InvictusBaseURL");
				driver.get(invAppURL);
				System.out.println(driver.getCurrentUrl() + "ABC");

			} else {
				String AppURL = prop.getProperty("BaseURL");
				driver.get(AppURL);
				System.out.println(driver.getCurrentUrl());	

			}

		} catch (Exception e) {
			System.out.println("exception while fetch url from config file " + e);

		}
	}

	@AfterMethod
	public void Closebrowser() {
		if (driver != null) {
			driver.close();

		}

	}
}
