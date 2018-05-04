package com.All;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.Library.CommonFunctions;
import com.Library.Constants;

public class DummyTestcase  {
	 WebDriver drv;
	 SoftAssert sf=new SoftAssert();
	 String Path="D:\\Arun_Selenium\\AutoIT\\AutoITScripts\\FileUploadDoc2.exe";
	
	@Test
	public void Dummy() throws Exception
	{
		drv=CommonFunctions.LaunchApplication("FireFox");
		CommonFunctions.MultiWindowhandle(drv);
		String title=drv.getTitle();
		System.out.println(title);
		Thread.sleep(3000);
		CommonFunctions.Link(drv, "//div[text()='Login']");
		Thread.sleep(3000);
		CommonFunctions.EditBox(drv, "//input[@name='email']", Constants.UserName);
		CommonFunctions.EditBox(drv, "//input[@name='PASSWORD']", Constants.Pwd);
		CommonFunctions.Button(drv, "//button[text()='Login']");
		Thread.sleep(3000);
		String st=CommonFunctions.getText(drv, "//span[@id='nameDisplaySpan']");
		System.out.println(st);
		CommonFunctions.Link(drv, "//div[@class='leftNavBullet']/ul/li/a[1]");
		Thread.sleep(3000);
		CommonFunctions.ChecBox(drv, "//input[@name='cityCheckBox']");
		Thread.sleep(3000);
		CommonFunctions.RadioButton(drv, "//div/p/span/input[2]");
		Thread.sleep(3000);
		CommonFunctions.DropDownSelection(drv, "//select[@id='industryTypeId']", "Broadcasting");
		Thread.sleep(3000);
		CommonFunctions.Link(drv, "//div[@class='leftNavBullet']/ul/li/a[3]");
		Thread.sleep(3000);
		CommonFunctions.Auto_complete(drv, "//input[@name='designation']", "Mechanical", "//div[@id='sugDrp_designation']/ul/li/button","Mechanical Supervisor");
		Thread.sleep(3000);
		CommonFunctions.Link(drv, "//div[@class='leftNavBullet']/ul/li/a[4]");
		Thread.sleep(3000);
		CommonFunctions.Link(drv, "//a[@id='uploadLink']");
		Thread.sleep(3000);
		CommonFunctions.FileUpLoad(drv, "//input[@id='attachCV']", Path);
		Thread.sleep(3000);
		CommonFunctions.Link(drv, "//div[@id='uploadLayer']/div[2]/a/span");
		Thread.sleep(3000);
		CommonFunctions.ScrollUpOrDown(drv, -500);
		Thread.sleep(3000);
		CommonFunctions.MoveToEleAndClick(drv, "//div[text()='My Naukri']", "//a[text()='Log Out']");
		
		System.out.println("Ustull got supper scoldings");
		
		drv.quit();
	}

}
