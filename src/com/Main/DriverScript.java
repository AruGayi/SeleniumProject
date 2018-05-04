package com.Main;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Date;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import com.Library.CommonFunctions;
import com.Library.FilePath;
import com.Library.GenericLibrary;


public class DriverScript {
	public static String ScreenShotPath;
	public static String ConstantTimeStamp;
	public static String sheetName;
	public static String className;
	public static String scriptName;
	public static int Counter = 0;
	public static String ExtentReportPath;

	/*public static void main(String[] args) throws Exception {
		DriverScript ms = new DriverScript();
		ConstantTimeStamp = GenericLibrary.getTimeStamp();
		ExtentReportPath = GenericLibrary.createExtenReprtFolder();
		
		ms.RunTestSuite();

	}*/

	@Test
	public void RunTestSuite(Method mtd) throws Exception {
		long SuiteStop = 0;
		long SuiteDuration = 0;
		String Duration = "";

		try {
			ConstantTimeStamp = GenericLibrary.getTimeStamp();
			
			ScreenShotPath = GenericLibrary.createScreenShotFolder();
			long SuiteStart = new Date().getTime();
			
			FileInputStream fileInputStream = new FileInputStream(FilePath.CONFIGURATION);
			Workbook wb = WorkbookFactory.create(fileInputStream);
			Sheet s = wb.getSheet("Modules");
			int rowCnt = s.getLastRowNum();
			for (int j = 1; j <= rowCnt; j++) {
				Counter = 0;
				org.apache.poi.ss.usermodel.Row row = s.getRow(j);
				org.apache.poi.ss.usermodel.Cell cell = row.getCell(1);
				String execStatus = cell.getRichStringCellValue().toString();
				if (execStatus.equalsIgnoreCase("yes")) {
					org.apache.poi.ss.usermodel.Cell cell1 = row.getCell(0);
					String scrptNm1 = cell1.getRichStringCellValue().toString();
					sheetName = scrptNm1;
					String filePath = FilePath.TestCaseAndTestData;
					String testId[] = CommonFunctions.getTestCaseId(filePath);
					for (String id : testId) {
						scriptName = id;
						System.out.println(scriptName + " " + scrptNm1);
						int indexNo = scriptName.lastIndexOf(".") + 1;
						String methodName = scriptName.substring(indexNo);
						Class<?> c;
						className = methodName;
						long startTime = new Date().getTime();
						c = Class.forName(scriptName); // ClassName
						java.lang.reflect.Constructor<?> cons = c.getConstructor();
						Object object = cons.newInstance();
						java.lang.reflect.Method method = object.getClass().getMethod(className);
						Boolean bool = (Boolean) method.invoke(object);
						long stopTime = new Date().getTime(); 
						boolean b = Boolean.valueOf(bool.toString());
						SuiteStop = new Date().getTime(); 
						SuiteDuration = (SuiteStop - SuiteStart) / 1000;
						Duration = GenericLibrary.getTime(SuiteDuration);
						Counter = Counter + 2;
						
					}

				}
			}
		

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

