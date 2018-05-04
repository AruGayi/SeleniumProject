package com.Library;

import java.io.File;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class SetUpUtils {
	public static WebDriver drv;
	public static WebElement ele;
	
	
	public static ExtentReports reports;
	public static ExtentTest Etest;
	public static ExtentHtmlReporter Ehtmlreport;
	
	public static Wait<WebDriver> w;
	public static WebDriverWait wait;
	public static String ScrshtPath;
	public static SoftAssert asrt;
	public static String ScreenShotPath;
	public static String ConstantTimeStamp;
	public static String ExtentReports;
	@BeforeClass
	public  void setUp() throws InterruptedException
	{
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat format=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		asrt=new SoftAssert();
		
		
	}
	@AfterClass
	public  void teardown() throws InterruptedException
	{
		Thread.sleep(2000);
		drv.close();
		Thread.sleep(2000);
	}
	@BeforeSuite
	public  void extent()
	{
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat format=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		ConstantTimeStamp = GenericLibrary.getTimeStamp();
		ScreenShotPath = GenericLibrary.createScreenShotFolder();
		ExtentReports=GenericLibrary.createExtenReprtFolder();
		reports = new ExtentReports();
		Ehtmlreport=new ExtentHtmlReporter(ExtentReports+"/ExtentReports.html");
		Ehtmlreport.loadXMLConfig(FilePath.Extentconfig);
		reports.setSystemInfo("Environment", "QA");
		reports.attachReporter(Ehtmlreport);
	}
	@AfterSuite
	public  void UnregisterAll()
	{
		reports.flush();
		drv.quit();
		
	}
	
	@BeforeMethod
	public void registerthemethods(Method method)
	{
		String TestMethodName=method.getName();
		Etest=reports.createTest(TestMethodName);
		
	}
	@AfterMethod
	public void CaptureStatus(ITestResult results) throws Exception
	{
		
		
		if(results.getStatus()==ITestResult.SUCCESS)
		{
			Etest.log(Status.PASS, "The test case with name : "+results.getName()+"Passed");
		}
		else if (results.getStatus()==ITestResult.FAILURE)
		{
			Etest.log(Status.FAIL, "The test case with name  : "+results.getName()+"Failed");
			Etest.log(Status.FAIL, "Test failure with reson and SnapShot : "+ results.getThrowable());
			ScrshtPath = CommonFunctions.screenshot(drv, results.getName());
		    Etest.fail("Failed", MediaEntityBuilder.createScreenCaptureFromPath("ScrshtPath").build());
		    Etest.fail("details").addScreenCaptureFromPath("ScrshtPath");
		   	
		}
		else if (results.getStatus()==ITestResult.SKIP)
		{
			Etest.log(Status.SKIP, "The test case with name   : "+results.getName()+"is skiped due to dependent on prior test cases");
		}
		else{
			Etest.log(Status.INFO, "Somthing Went wrong couldnot execute the test Scripts");
		}
	}
}
