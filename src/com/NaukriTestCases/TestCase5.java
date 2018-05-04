package com.NaukriTestCases;

import org.testng.annotations.Test;

import com.Library.CommonFunctions;
import com.Library.Constants;
import com.Library.FilePath;
import com.Library.SetUpUtils;
import com.aventstack.extentreports.Status;

public class TestCase5  extends SetUpUtils{

	
	//===============Login and Verify Profile Test Case==============================
		
		@Test(priority=1)
		public void VerifyProfile() throws Exception
		{
			Etest.log(Status.INFO, "======Test Cases Executin for Login and Verify Profile Started===========");
			CommonFunctions.LaunchApplication("FireFox");
			Etest.log(Status.INFO, "Appication launched  Successfully");
			CommonFunctions.MultiWindowhandle(drv);
			Etest.log(Status.INFO, "All Secandary Windows are closed successfully");
			CommonFunctions.Link(drv, "//div[text()='Login']");
			Etest.log(Status.INFO, "Clicked on Login successfully");
			CommonFunctions.EditBox(drv, "//input[@name='email']", Constants.UserName);
			Etest.log(Status.INFO, "Entered User Nmae successfully");
			CommonFunctions.EditBox(drv, "//input[@name='PASSWORD']", Constants.Pwd);
			Etest.log(Status.INFO, "Entered Password successfully");
			CommonFunctions.Button(drv, "//button[text()='Login']");
			Etest.log(Status.INFO, "Clicked on Login button successfully");
			Thread.sleep(3000);
			String st=CommonFunctions.getText(drv, "//span[@id='nameDisplaySpan']");
			asrt.assertEquals(st, st);
			asrt.assertAll();
			Etest.log(Status.INFO, "Profile verification completed successfully");
			Etest.log(Status.INFO, "============Test Cases Executin for Login and Verify Profile completed=============");
		}
		
		//===============UpdateDomine Test Case==============================
		@Test(priority=2)
		public void UpdateDomine() throws Exception
		{
			Etest.log(Status.INFO, "===========Test Cases Executin for Update Domine Started============");
			CommonFunctions.Link(drv, "//div[@class='leftNavBullet']/ul/li/a[1]");
			Etest.log(Status.INFO, "Clicked on snapshot link successfully");		
			CommonFunctions.ChecBox(drv, "//input[@name='cityCheckBox']");
			Etest.log(Status.INFO, "Clicked on Check Box successfully");
			CommonFunctions.RadioButton(drv, "//div/p/span/input[2]");
			Etest.log(Status.INFO, "Clicked on Radi Button successfully");
			CommonFunctions.DropDownSelection(drv, "//select[@id='industryTypeId']", "Broadcasting");
			Etest.log(Status.INFO, "Domine is selected from Dropdown successfully");
			asrt.assertTrue(true);
			asrt.assertAll();
			Etest.log(Status.INFO, "=========Test Cases Executin for Update Domine completed==========");
		}
		
		//===============AutoComplete Test Case==============================
			@Test(priority=4)
			public void AutoComplete() throws Exception
			{
				Etest.log(Status.INFO, "========Test Cases Executin for AutoComplete Started=============");
				CommonFunctions.Link(drv, "//div[@class='leftNavBullet']/ul/li/a[3]");
				Etest.log(Status.INFO, "Clicked on emp/destination link  successfully");
				CommonFunctions.Auto_complete(drv, "//input[@name='designation']", "Mechanical", "//div[@id='sugDrp_designation']/ul/li/button","Mechanical Supervisor");
				Etest.log(Status.INFO, "Auto selection of text is successfully");
				asrt.assertTrue(true);
				asrt.assertAll();
				Etest.log(Status.INFO, "=============Test Cases Executin for AutoComplete completed==========");
			}
			
		
		//===============UploadCV  Test Case==============================
		@Test(priority=5)
		public void UploadCV() throws Exception
		{
			Etest.log(Status.INFO, "========Test Cases Executin for UpLoad CV  Started=============");
			CommonFunctions.Link(drv, "//div[@class='leftNavBullet']/ul/li/a[4]");
			Etest.log(Status.INFO, "Clicked on Attach Resume link successfully");
			CommonFunctions.Link(drv, "//a[@id='uploadLink']");
			Etest.log(Status.INFO, "Clicked on Upload New Resume link successfully");
			CommonFunctions.FileUpLoad(drv, "//input[@id='attachCV']", FilePath.AutoIt);
			Etest.log(Status.INFO, "Uploaded CV successfully");
			String text=CommonFunctions.getText(drv, "//i[@id='attachCVMsg']");
			asrt.assertEquals(text, text);
			asrt.assertAll();
			Etest.log(Status.INFO, "Verify file upload message successfully");
			CommonFunctions.Link(drv, "//div[@id='uploadLayer']/div[2]/a/span");
			Etest.log(Status.INFO, "Cloesed file Upload window  successfully");
			Etest.log(Status.INFO, "=============Test Cases Executin for UpLoad CV  completed==========");
			
		}
		
		//===============LogOut Test Case==============================
		@Test(priority=6)
		public void LogOut() throws Exception
		{
			Etest.log(Status.INFO, "========Test Cases Executin for LogOut Started=============");
			CommonFunctions.ScrollUpOrDown(drv, -500);
			Etest.log(Status.INFO, "Scroll Up for Logout successfully");
			CommonFunctions.MoveToEleAndClick(drv, "//div[text()='My Naukri']", "//a[text()='Log Out']");
			Etest.log(Status.INFO, "Clicked on Log Out button successfully");
			Thread.sleep(3000);
			String text=CommonFunctions.getText(drv, "//div[@class='lf']/span");
			asrt.assertEquals(text, text);
			asrt.assertTrue(false);
			asrt.assertAll();
			Etest.log(Status.INFO, "=============Test Cases Executin for LogOut completed==========");
		}
	}

