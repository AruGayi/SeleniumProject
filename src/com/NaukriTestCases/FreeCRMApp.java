package com.NaukriTestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.Library.CommonFunctions;



public class FreeCRMApp {
	WebDriver drv;
	@Test
	public void login() throws Exception {
		String st2 = "D:\\Arun_Selenium\\Drivers\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", st2);
        drv=new FirefoxDriver(); 
        drv.get("http://www.freecrm.com/index.html");
        Thread.sleep(3000);
        //CommonFunctions.EditBox(drv, "//input[@name='username']", "arugse@gmail.com");
        //CommonFunctions.EditBox1(drv, "xpath", "//input[@name='username']", "arugse@gmail.com");
        CommonFunctions.EditBox1(drv, "id", "//input[@name='username']", "arugse@gmail.com");
	}

}
