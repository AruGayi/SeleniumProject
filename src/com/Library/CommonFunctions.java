package com.Library;

import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Main.DriverScript;
import com.google.common.base.Function;

public class CommonFunctions extends SetUpUtils {
	/*public static WebDriver drv;
	public static WebDriverWait wait;*/

	// ==============================Launching
	// Application=========================
	public static WebDriver LaunchApplication(String BrowsName) throws InterruptedException {

		switch (BrowsName) {

		case "ie":
			/*String st1 = "D:\\Arun_Selenium\\Drivers\\IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", st1);
		
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			drv = new InternetExplorerDriver(ieCapabilities);
			break;*/

		case "FireFox":
			String st2 = "D:\\Arun_Selenium\\Drivers\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", st2);
			drv = new FirefoxDriver();
			break;

		case "Chrome":
			String st = "D:\\Arun_Selenium\\Drivers\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", st);
			drv = new ChromeDriver();
			Thread.sleep(5000);
			break;

		default:
			System.out.println("Invalid Browser");
		}
		drv.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		drv.manage().window().maximize();
		drv.get(Constants.URL);
		Thread.sleep(5000);
		return drv;
	}

	// =========================Fluent Wait====================================

	public static void FluentWiat(WebDriver drv, final String Locator) {
		Wait<WebDriver> w = new FluentWait<WebDriver>(drv).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.MILLISECONDS).ignoring(NoSuchElementException.class);
		WebElement ele = w.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver drv) {
				return drv.findElement(By.xpath(Locator));
			}
		});
	}

	// ======================Link===================================

	public static String Link(WebDriver drv, String locator) throws Exception {
		int objcount = 0;
		String Err = null;
		WebElement elem = null;

		int i = 0;
		try {
			while (objcount == 0 && i != 3) {
				// FluentWiat(drv, locator);
				wiat(drv, locator);
				elem = drv.findElement(By.xpath(locator));
				if (elem == null) {

					FluentWiat(drv, locator);
				}
				i++;
			}
			if (elem != null) {
				if (elem.isDisplayed()) {
					elem.click();
					elem = null;

				} else {
					Err = "Link not Identified";
					return Err;
				}

			} else {
				Err = "Link not Identified";
				return Err;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Err = "Exception Occured";
			return Err;
		}
		return Err;

	}

	// ================================EditBox=========================

	public static String EditBox(WebDriver drv, String locator, String data) throws Exception {
		int objcount = 0;
		String Err = null;
		WebElement elem = null;

		int i = 0;
		try {
			while (objcount == 0 && i != 3) {
				// FluentWiat(drv, locator);
				wiat(drv, locator);
				elem = drv.findElement(By.xpath(locator));
				if (elem == null) {

					FluentWiat(drv, locator);
				}
				i++;
			}
			if (elem != null) {
				if (elem.isDisplayed()) {
					elem.sendKeys(data);
					;
					elem = null;

				} else {
					Err = "EditBox not Identified";
					return Err;
				}

			} else {
				Err = "EditBox not Identified";
				return Err;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Err = "Exception Occured";
			return Err;
		}
		return Err;

	}

	// ========================Button ==========================

	public static String Button(WebDriver drv, String locator) throws Exception {
		int objcount = 0;
		String Err = null;
		WebElement elem = null;

		int i = 0;
		try {
			while (objcount == 0 && i != 3) {
				// FluentWiat(drv, locator);
				wiat(drv, locator);
				elem = drv.findElement(By.xpath(locator));
				if (elem == null) {

					FluentWiat(drv, locator);
				}
				i++;
			}
			if (elem != null) {
				if (elem.isDisplayed()) {
					elem.click();
					elem = null;

				} else {
					Err = "Link not Identified";
					return Err;
				}

			} else {
				Err = "Link not Identified";
				return Err;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Err = "Exception Occured";
			return Err;
		}
		return Err;

	}

	// =======================Get Text================
	public static String getText(WebDriver drv, String locator) throws Exception {
		int objcount = 0;
		String Err = null;
		WebElement elem = null;
		String text = null;

		int i = 0;
		try {
			while (objcount == 0 && i != 3) {
				// FluentWiat(drv, locator);
				wiat(drv, locator);
				elem = drv.findElement(By.xpath(locator));
				if (elem == null) {

					FluentWiat(drv, locator);
				}
				i++;
			}
			if (elem != null) {
				if (elem.isDisplayed()) {
					text = elem.getText();
					elem = null;

				} else {
					Err = "Text not Available";
					return Err;
				}

			} else {
				Err = "Text not Available";
				return Err;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Err = "Exception Occured";
			return Err;
		}
		return text;

	}

	// =================Check Box ============================
	public static String ChecBox(WebDriver drv, String locator) throws Exception {
		int objcount = 0;
		String Err = null;
		WebElement elem = null;

		int i = 0;
		try {
			while (objcount == 0 && i != 3) {
				// FluentWiat(drv, locator);
				wiat(drv, locator);
				elem = drv.findElement(By.xpath(locator));
				if (elem == null) {

					FluentWiat(drv, locator);
				}
				i++;
			}
			if (elem != null) {
				if (elem.isDisplayed()) {
					if (elem.isSelected()) {
						System.out.println("Check Box is already selected ");
					} else {
						elem.click();

					}
					elem = null;
				} else {
					Err = "ChecBox not Identified";
					return Err;
				}

			} else {
				Err = "ChecBox not Identified";
				return Err;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Err = "Exception Occured";
			return Err;
		}
		return Err;

	}

	// =================Radio Button ============================
	public static String RadioButton(WebDriver drv, String locator) throws Exception {
		int objcount = 0;
		String Err = null;
		WebElement elem = null;

		int i = 0;
		try {
			while (objcount == 0 && i != 3) {
				// FluentWiat(drv, locator);
				wiat(drv, locator);
				elem = drv.findElement(By.xpath(locator));
				if (elem == null) {

					FluentWiat(drv, locator);
				}
				i++;
			}
			if (elem != null) {
				if (elem.isDisplayed()) {
					if (elem.isSelected()) {
						System.out.println("Check Box is already selected ");
					} else {
						elem.click();

					}
					elem = null;
				} else {
					Err = "Radio Button Identified";
					return Err;
				}

			} else {
				Err = "Radio Button Identified";
				return Err;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Err = "Exception Occured";
			return Err;
		}
		return Err;

	}

	// =================DropDown Selection ============================
	public static String DropDownSelection(WebDriver drv, String locator, String visibleText) throws Exception {
		int objcount = 0;
		String Err = null;
		WebElement elem = null;

		int i = 0;
		try {
			while (objcount == 0 && i != 3) {
				// FluentWiat(drv, locator);
				wiat(drv, locator);
				elem = drv.findElement(By.xpath(locator));
				if (elem == null) {

					FluentWiat(drv, locator);
				}
				i++;
			}
			if (elem != null) {
				if (elem.isDisplayed()) {

					Select sc = new Select(elem);
					List<WebElement> alloptions = sc.getOptions();
					Iterator<WebElement> it = alloptions.iterator();
					while (it.hasNext()) {
						String text = it.next().getText();
						System.out.println(text);
					}
					sc.selectByVisibleText(visibleText);
					elem = null;
					sc = null;
				} else {
					Err = "Drop Down not Identified";
					return Err;
				}

			} else {
				Err = "Drop Down Identified";
				return Err;
			}

		} catch (Exception e) {
			e.printStackTrace();
			Err = "Exception Occured";
			return Err;
		}
		return Err;

	}

	// =================Handling Multiple Window============================
	public static WebDriver MultiWindowhandle(WebDriver drv) throws Exception {
		String pare = drv.getWindowHandle();
		System.out.println(pare);
		Set<String> childwind = drv.getWindowHandles();
		int count = childwind.size();
		System.out.println(count);

		Iterator<String> it = childwind.iterator();
		while (it.hasNext()) {
			String child_windo = it.next();

			if (!pare.equalsIgnoreCase(child_windo)) {

				drv.switchTo().window(child_windo);

				drv.close();

				Thread.sleep(3000);
			}
			System.out.println("Hello");
		}
		drv.switchTo().window(pare);
		return drv;

	}
	
	// =================Scrolling Up or Down============================
		public static WebDriver ScrollUpOrDown(WebDriver drv, int endpoint) throws Exception {
			
			for (int i=0;i<10;i++)
			{
				// Positive value will scroll down 
				
				// Negative value will scroll Up 
				
				try {
					JavascriptExecutor jse = (JavascriptExecutor)drv;
					jse.executeScript("window.scrollBy(0,"+ endpoint + ")", "");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			Thread.sleep(2000);
			return drv;

		}
		
		// ========================Move to Element and Perform the Action on particular Element to be clicked ==========================

		public static String MoveToEleAndClick(WebDriver drv, String locator,String EleToBeClicked) throws Exception {
			int objcount = 0;
			String Err = null;
			WebElement elem = null;

			int i = 0;
			try {
				while (objcount == 0 && i != 3) {
					// FluentWiat(drv, locator);
					wiat(drv, locator);
					elem = drv.findElement(By.xpath(locator));
					if (elem == null) {

						FluentWiat(drv, locator);
					}
					i++;
				}
				if (elem != null) {
					if (elem.isDisplayed()) {
						try {
							Actions act = new Actions(drv);
							act.moveToElement(elem).perform();
							WebElement ElementClick=drv.findElement(By.xpath(EleToBeClicked));
							ElementClick.click();
							ElementClick=null;
							elem = null;
						} catch (Exception e) {
							
							e.printStackTrace();
						}

					} else {
						Err = "Element not Identified";
						return Err;
					}

				} else {
					Err = "Element not Identified";
					return Err;
				}

			} catch (Exception e) {
				e.printStackTrace();
				Err = "Exception Occured";
				return Err;
			}
			return Err;

		}
		
		// ========================File Upload Function ==========================
		
		public static String FileUpLoad(WebDriver drv, String locator,String Path) throws Exception {
			int objcount = 0;
			String Err = null;
			WebElement elem = null;

			int i = 0;
			try {
				while (objcount == 0 && i != 3) {
					// FluentWiat(drv, locator);
					wiat(drv, locator);
					elem = drv.findElement(By.xpath(locator));
					if (elem == null) {

						FluentWiat(drv, locator);
					}
					i++;
				}
				if (elem != null) {
					if (elem.isDisplayed()) {
						elem.click();
						try {
							Runtime.getRuntime().exec(Path);
							Thread.sleep(25000);
						} catch (Exception e) {
							e.printStackTrace();
						}
						elem = null;

					} else {
						Err = "Element not Identified";
						return Err;
					}

				} else {
					Err = "Unable to Upload File";
					return Err;
				}

			} catch (Exception e) {
				e.printStackTrace();
				Err = "Exception Occured";
				return Err;
			}
			return Err;

		}

		// ========================Auto-complete/ Customized drop down  ==========================
		
		public static String Auto_complete(WebDriver drv, String Firstlocator,String FirstText,String SecondLocator,String Expected) throws Exception {
			int objcount = 0;
			String Err = null;
			WebElement elem = null;
			int i = 0;
			try {
				while (objcount == 0 && i != 3) {
					// FluentWiat(drv, locator);
					wiat(drv, Firstlocator);
					elem = drv.findElement(By.xpath(Firstlocator));
					if (elem == null) {

						FluentWiat(drv, Firstlocator);
					}
					i++;
				}
				if (elem != null) {
					if (elem.isDisplayed()) {
						elem.sendKeys(FirstText);
						try {
							Thread.sleep(3000);
							List<WebElement> optionsToSelect  = drv.findElements(By.xpath(SecondLocator));
							
							int size=optionsToSelect.size();
							System.out.println(size);
							for(WebElement option : optionsToSelect){
								String mytext=option.getText();
							    System.out.println(mytext);
							    Thread.sleep(1000);
							    if(option.getText().equalsIgnoreCase(Expected)) {
							        System.out.println("Trying to select: "+Expected);
							        Thread.sleep(1000);
							        option.click();
							        break;
							    }
							}
							optionsToSelect=null;
						} catch (Exception e) {
							e.printStackTrace();
						}
						elem = null;

					} else {
						Err = "Unable to Select the Desired Text";
						return Err;
					}

				} else {
					Err = "Unable to find the element";
					return Err;
				}

			} catch (Exception e) {
				e.printStackTrace();
				Err = "Exception Occured";
				return Err;
			}
			return Err;

		}
//============================Reference Excel Function====================
		public static String[] getTestCaseId(String filePath)
	            throws EncryptedDocumentException, InvalidFormatException,
	            IOException {
	     FileInputStream fileInputStream = new FileInputStream(filePath);
	     Workbook wb = WorkbookFactory.create(fileInputStream);
	     org.apache.poi.ss.usermodel.Sheet s = wb.getSheet("DummyDriver.sheetName");
	     Row row = s.getRow(0);
	     int lastrow = s.getLastRowNum();// Integer.parseInt(s.getLastRowNum()//.toString());
	     String testId[]=new String[lastrow];
	     System.out.println(lastrow + " lastrow ");
	     String idValue = null;
	     int rowindex = -1;
	     for (int j = 1; j <= lastrow; j++) {
	            
	            String testCaseId= s.getRow(j).getCell(0).toString();
	            testId[j-1]=testCaseId;
	            
	
	     }
	     return testId;
	
	}
		
		public static String screenshot(WebDriver drv,String ScreName)
		{
			try {
				Calendar cal=Calendar.getInstance();
				SimpleDateFormat format=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
				TakesScreenshot ts=(TakesScreenshot)drv;
				File src=ts.getScreenshotAs(OutputType.FILE);
				String dest=ScreenShotPath+"/"+ScreName+"_"+format.format(cal.getTime())+".png";
				File destination=new File(dest);
				FileUtils.copyFile(src, destination);
				System.out.println("screen captured");
				return dest;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return e.getMessage();
			}
		}
		

public static WebDriver wiat(WebDriver drv,String Locator)
{
	wait=new WebDriverWait(drv, 20);
	wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(By.xpath(Locator)));
	return drv;
	
}


public static String EditBox1(WebDriver drv,String LocatorType, String locator, String data) throws Exception {
	int objcount = 0;
	String Err = null;
	WebElement elem = null;

	int i = 0;
	try {
		while (objcount == 0 && i != 3) {
			// FluentWiat(drv, locator);
			wiat(drv, locator);
			switch (LocatorType) {

			case "id":
				elem = drv.findElement(By.id(locator));
				break;
				
			case "xpath":
				elem = drv.findElement(By.xpath(locator));
				break;
				
			case "name":
				elem = drv.findElement(By.name(locator));
				break;
				
			case "class":
				elem = drv.findElement(By.className(locator));
				break;

			case "linkText":
				elem = drv.findElement(By.linkText(locator));
				break;
			default:
				System.out.println("Invalid Browser");
			}
			if (elem == null) {

				FluentWiat(drv, locator);
			}
			i++;
		}
		if (elem != null) {
			if (elem.isDisplayed()) {
				elem.sendKeys(data);
				;
				elem = null;

			} else {
				Err = "EditBox not Identified";
				return Err;
			}

		} else {
			Err = "EditBox not Identified";
			return Err;
		}

	} catch (Exception e) {
		e.printStackTrace();
		Err = "Exception Occured";
		return Err;
	}
	return Err;

}





}
