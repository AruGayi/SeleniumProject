package com.All;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xerces.parsers.IntegratedParserConfiguration;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class QSPDRIVER {
	
	@Test
	public void RunTestCase()
	{
      try{
    	 FileInputStream fis = new FileInputStream("./testdata/Controller.xlsx"); 
    	 XSSFWorkbook wb = new XSSFWorkbook(fis);
    	 XSSFSheet sht = wb.getSheet("Controller");
    	 int rwcnt = sht.getLastRowNum();
    	 for(int i=1;i<=rwcnt;i++)
    	 {
    		 String flag = sht.getRow(i).getCell(2).getStringCellValue();
    		 if(flag.equalsIgnoreCase("yes"))
    		 {
    		   String className = sht.getRow(i).getCell(0).getStringCellValue();
    		   String classPath = sht.getRow(i).getCell(1).getStringCellValue();
    		   //Reflection 
    		   //1. return entire class in runtime
    		   //based on class path
    		   Class<?> cls = Class.forName(classPath+"."+className);
    		   Constructor<?>c=cls.getConstructor();
    		   Object obj = c.newInstance(); 
    		   
    		   //Execution - start
    		   Reporter.log("Start Test Case - " + className );
    		   Method[] m=cls.getDeclaredMethods();
    		   
    		   //2.get the method of the runtime class 		   
    		   Method m1 = cls.getMethod("preCond",null);
    		   //execute or invoke Method
    		   m1.invoke(obj, null);
    		   
    		   //TEst
    		   Method m2 = cls.getMethod("testCaseMethod",null);
    		   //execute or invoke Method
    		   boolean outres = (boolean)m2.invoke(obj, null);
    		   if(outres==true)
    		   {
    			  sht.getRow(i).createCell(3).setCellValue("TC Passed");
    			  Reporter.log("Test Case - " + className + ": Passed");
    		   }else
    		   {
    			   sht.getRow(i).createCell(3).setCellValue("TC Failed");
    			   Reporter.log("Test Case - " + className + ": Failed"); 
    		   }
    		   
    		   //AFterMEthod
    		   Method m3 = cls.getMethod("postCond",null);
    		   //execute or invoke Method
    		   m3.invoke(obj, null);
    		   Reporter.log("End Test Case");
    		 }
    	 }//end for
    	 
    	 FileOutputStream fos = new FileOutputStream("./testdata/Controller.xlsx");
    	 wb.write(fos);
    	 fos.close();
    	 
      }catch(Exception exp3)
      {
    	exp3.printStackTrace();  
      }
	}

}

