package com.PageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginEle {
	
   public LoginEle(WebDriver drv)
   {
	   PageFactory.initElements(drv, this);
   }
   
  //=======================All Elements==============
   
   @FindBy(xpath="//a[text()='Login']")
   private static WebElement Login;
   public static WebElement LoginLink()
   {
	   
	   return Login;
	 
   }
   //========================
   
   @FindBy(name="")
   private WebElement submitbtn;
   public WebElement sub()
   {
	   return submitbtn;
   }
   
}
