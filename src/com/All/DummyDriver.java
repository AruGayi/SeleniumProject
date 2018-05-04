package com.All;

import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.pacesys.reflect.Reflect;
import org.testng.TestNG;
import org.testng.annotations.Test;

import com.Library.CommonFunctions;
import com.Library.FilePath;
import com.Library.GenericLibrary;
public class DummyDriver extends ArunTestCases {
	public static String ScreenShotPath;
	public static String ConstantTimeStamp;
	public static String sheetName;
	public static String className;
	public static String scriptName;
	public static int Counter = 0;
	public static String ExtentReportPath;

	public static void main(String[] args) throws Exception {
		DummyDriver ms = new DummyDriver();
		ConstantTimeStamp = GenericLibrary.getTimeStamp();
		ExtentReportPath = GenericLibrary.createExtenReprtFolder();
		
		ms.RunTestSuite();

	}


	
	public void RunTestSuite() throws Exception {
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
						//System.out.println(scriptName + " " + scrptNm1);
						int indexNo = scriptName.lastIndexOf(".") + 1;
						String methodName = scriptName.substring(indexNo);
						Class<?> c;
						className = methodName;
						long startTime = new Date().getTime();
						c = Class.forName(scriptName); // ClassName
						
						java.lang.reflect.Constructor<?> cons = c.getConstructor();
						Object object = cons.newInstance();
						
					    Method[] method = object.getClass().getDeclaredMethods();
					    ArrayList<Method> methods = ((ArrayList<Method>)(Reflect.on(Class.forName(scriptName)).methods().annotatedWith(Test.class)));
					    
					    for (Method mtd:methods) {
				            Annotation[] annotations = mtd.getDeclaredAnnotations();
				            
				            for (Annotation annotation : annotations)
				            {
				            	
				                
									if (annotation instanceof Test ) {
										String name=((Test) annotation).testName();
										System.out.println(name);
										mtd.invoke(object, null);
									}
								
				            }
				        }
					    
					    /*Method[] methods = object.getClass().getMethods().;
					      for(Method mt : methods) {
					        if (mt.isAnnotationPresent(Test.class)) {
					            // Invoke method with appropriate arguments
					            Object obj = mt.invoke(object, null);
					        }
       					   
					      }*/
					    
					   
					    
					    
					  
						//System.out.println(bool);
						/*long stopTime = new Date().getTime(); 
						boolean b = Boolean.valueOf(bool.toString());
						SuiteStop = new Date().getTime(); 
						SuiteDuration = (SuiteStop - SuiteStart) / 1000;
						Duration = GenericLibrary.getTime(SuiteDuration);*/
						Counter = Counter + 2;
						
					}

				}
			}
		

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

