package com.Library;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.Main.DriverScript;

public class GenericLibrary {
	
	       public static int CaseCount;
	       public static int  iScreenCounter=1;
	       public static int rowCount1;
	       public void testMain(Object[] args) throws IOException 
	       {
	                           
	              
	       }
	       /**
	       * To Return the Current Date and time stamp in 'MMM-dd-yyyy_h-mm-ss' format
	       * @param className
	       * @param Result
	       * @throws Exception
	       */
	       public static String getTimeStamp(){
	              Date d  = new Date();
	              SimpleDateFormat sdf = new SimpleDateFormat("MMM-dd-yyyy_h-mm-ss");
	              String date = sdf.format(d).toString();
	              return date;
	       }
	       
	       public static String createExtenReprtFolder(){
               File file = new File(FilePath.ExtentReport+DriverScript.ConstantTimeStamp);
               File file1 = new File(FilePath.ExtentReport+SetUpUtils.ConstantTimeStamp);
               if (!file1.exists()) {
                      if (file1.mkdirs()) {
                            
                      } else {
                            System.out.println("Failed to create Result Folder!");
                      }
               }
               return FilePath.ExtentReport+SetUpUtils.ConstantTimeStamp;
        }

	     
	      
	       
	       /**
	       * Create the ScreenShot Folder and returns the path
	       * @param className
	       * @param Result
	       * @throws Exception
	       */
	       public static String createScreenShotFolder() {
	              File file = new File(FilePath.SCREENSHOT+DriverScript.ConstantTimeStamp);
	              File file1 = new File(FilePath.SCREENSHOT+SetUpUtils.ConstantTimeStamp);
	              if (!file1.exists()) {
	                     if (file1.mkdir()) {
	                           //System.out.println("Screenshot_Directory is created!");
	                     } else {
	                           System.out.println("Failed to create Screenshot directory!");
	                     }
	              }
	              return FilePath.SCREENSHOT+SetUpUtils.ConstantTimeStamp;
	       }
	       
	      
	      	      	       /**
	       * To Read Test data from DataTable file
	       * @param className
	       * @param Result
	       * @throws Exception
	       */
	       public static String []  ReadTestdata(String filePath) throws Exception {
	              FileInputStream fileInputStream = new FileInputStream(filePath);
	              Workbook wb = WorkbookFactory.create(fileInputStream);
	              org.apache.poi.ss.usermodel.Sheet s =  wb.getSheet("Sheet1");
	              int rowCnt = ((org.apache.poi.ss.usermodel.Sheet) s).getLastRowNum();
	              org.apache.poi.ss.usermodel.Row row = null; 
	              row = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(1);
	              org.apache.poi.ss.usermodel.Cell cell;
	        int cols = ((org.apache.poi.ss.usermodel.Sheet) s).getRow(1).getPhysicalNumberOfCells();
	       java.util.List list =new ArrayList();
	              String [] values = new String [cols];
	              for(int c = 0; c < cols; c++) {
	           cell = ((org.apache.poi.ss.usermodel.Row) row).getCell((short)c);
	            if(cell != null) {
	                 cell = row.getCell(c);                                                              
	                values[c] = cell.getRichStringCellValue().toString();
	               }
	        }
	              
	              
	              return values;
	       }

	/*       *//**
	       * To Create HTML Report
	       * @param className
	       * @param Result
	       * @throws Exception
	       *//*
	       
	       public static void CreateHTMLReport(String SuiteDuration,String ConstantTimeStamp,String SuiteStart) throws Exception{
	        String cont=null; 
	        String cont1=null;
	        String cont2=null;
	        String cont3=null;
	        String cont4=null;
	        String cont5=null;
	        File fi1=null;
	        //if (CaseCount==0) {
	        
	        //cont="<HTML><HEAD><TITLE>Navigator Test Report</TITLE><META http-equiv=Content-Type content=text/html; charset=windows-1252><META content=MSHTML 6.00.2900.3268 name=GENERATOR></HEAD><H2 align=center><U><FONT color=#187030><B>Framework Consolidated Test Report</B></FONT> <U></H2><HR><TABLE width=100% height=37 border=0 align=center><TBODY><TR><TD height=19 <FONT color=black><B>Project Name :: </B></FONT><FONT color=#187030><B>Navigator</B></FONT><TD width=50% align=right><div align=left><FONT color=black><B>Date and Time of Execution : </B></FONT><FONT color=#187030><B>"+SuiteStart+"</B></FONT></div></TD></TR>";
	        cont="<HTML><HEAD><img src='D:\\FrameWork\\Logo\\ConnectiveRx.JPG' alt='logo' /><TITLE>Condor Test Report</TITLE><META http-equiv=Content-Type content=text/html; charset=windows-1252><META content=MSHTML 6.00.2900.3268 name=GENERATOR></HEAD><H2 align=center><U><FONT color=black><B>Automation Execution Report</U></B></FONT> <U></H2><HR><TABLE width=100% height=37 border=0 align=center><TBODY><TR><TD height=19 <FONT color=black><B>Project Name : </B></FONT><FONT color=#E87722><B>Condor</B></FONT><TD width=30% align=right><div align=left><FONT color=black><B>Date and Time of Execution : </B></FONT><FONT color=#E87722><B>"+SuiteStart+"</B></FONT></div></TD></TR></U>";
	         //fi1=new File(FilePath.RESULTS+DriverScript.ConstantTimeStamp+"\\Execution_Report.html");
	       // }
	         
	         //<style>body{background-color: #E87722}</style><body></body>
	        
	  FileOutputStream fos=new FileOutputStream(fi1);
	  fos.write(cont.getBytes());
	  
	  int passCount = 0;
	  int failCount = 0;
	  FileInputStream fileInputStream = new FileInputStream(FilePath.RESULTS+DriverScript.ConstantTimeStamp+"\\TestResult.xlsx");
	  //FileInputStream fileInputStream = new FileInputStream(MAIN.Logpath);
	        Workbook wb = WorkbookFactory.create(fileInputStream);
	        
	        org.apache.poi.ss.usermodel.Sheet s =  wb.getSheet("Sheet1");
	        int rowCnt = ((org.apache.poi.ss.usermodel.Sheet) s).getLastRowNum();
	        org.apache.poi.ss.usermodel.Row row = null; 
	        for(int j=1;j<=rowCnt;j++){
	               row = s.getRow(j);
	               org.apache.poi.ss.usermodel.Cell cell = row.getCell(2); 
	               String testCasename = cell.getRichStringCellValue().toString();
	               if(testCasename.equalsIgnoreCase("Pass")){
	                     passCount++;
	               }
	               if(testCasename.equalsIgnoreCase("fail")){
	                     failCount++;
	               }
	               
	        }
	        //#ffffff
	        cont="<TR bgColor=#ffffff><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR><TD><B><FONT></FONT></B></TD></TR></TBODY></TABLE><BR><BR><HR><TABLE width=80% align=center border=1><TBODY><TR bgColor=#E87722><TD align=middle><B><FONT color=#FFFDD0>Total Testcases </FONT></B></TD><TD align=middle><B><FONT color=#FFFDD0>No. of TC Passed </FONT></B></TD><TD align=middle><B><FONT color=#FFFDD0>No. of TC Failed </FONT></B></TD><TD align=middle><B><FONT color=#FFFDD0>Total Time Elapsed</FONT></B></TD></TR><TR bgColor=#ffffff>";
	  fos.write(cont.getBytes());
	        
	  cont="<TD align=middle><B>"+rowCnt+" </B></TD>";
	  fos.write(cont.getBytes());
	  cont="<TD align=middle><B>"+passCount+" </B></TD>";
	  fos.write(cont.getBytes());
	  cont="<TD align=middle><B><FONT color=#ff0066>"+failCount+"</FONT>";
	  fos.write(cont.getBytes());
	  cont="<TD align=middle><B>"+SuiteDuration+" </B></TD>";
	  fos.write(cont.getBytes());
	     
	  //<TR bgColor=#E87722><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD></TR><TD><B><FONT></FONT></B></TD></TR>
	  cont="</TBODY></TABLE><BR><BR><HR><H3 align=center><FONT color=black><U><B>Summary of Consolidated Test Report </U> </B></FONT></H3></TR><TR bgColor=#E87722>";
	  fos.write(cont.getBytes());
	  
	  
	     cont="<TABLE width=100% align=center border=1><TBODY><TR bgColor=#E87722><TD><FONT color=#FFFDD0><B>TestCase Name</B></FONT></TD><TD width=10%><FONT color=#FFFDD0><B>Execution Status</B></FONT></TD>";
	    fos.write(cont.getBytes());
	     
	 fileInputStream = new FileInputStream(FilePath.RESULTS+DriverScript.ConstantTimeStamp+"\\TestResult.xlsx");
	    // fileInputStream = new FileInputStream(MAIN.Logpath);
	     
	        wb = WorkbookFactory.create(fileInputStream);
	        //s =  wb.getSheet("Result");
	        s =  wb.getSheet("Sheet1");
	        rowCnt = ((org.apache.poi.ss.usermodel.Sheet) s).getLastRowNum();
	        row = null; 
	        //#FF0000 
	        for(int j=1;j<=rowCnt;j++){
	               row = s.getRow(j);
	               org.apache.poi.ss.usermodel.Cell cell = row.getCell(1); 
	               String testCasename = cell.getRichStringCellValue().toString();
	               cell = row.getCell(2); 
	               String Sts = cell.getRichStringCellValue().toString();
	               if (Sts.equalsIgnoreCase("Fail")){
	                     cont="<TR bgColor=#ffffff>";
	                     
	               }else{
	                     cont="<TR bgColor=#ffffff>";
	                     
	               }
	                     
	                                   
	               
	      fos.write(cont.getBytes());
	        cont="<TD>"+testCasename+"</TD>";
	        if (Sts.equalsIgnoreCase("Fail")){
	              cont1="<TD align=middle><B><FONT color=#FF0000 size=3>"+Sts.toUpperCase()+"</B></TD>";
	        }else{
	              cont1="<TD align=middle><B><FONT color=#006400 size=3>"+Sts.toUpperCase()+"</B></TD>";
	        }
	        fos.write(cont.getBytes());
	     fos.write(cont1.getBytes());
	        }
	        cont="</B></TD></TR></TBODY></TABLE><BR><BR><BR><BR><BR><BR><TABLE width=100% align=center bgColor=#E87722 border=0><TBODY><TR><TD align=middle><FONT face=Verdana, Arial color=#ffffff size=1></FONT></TD><TD align=right><FONT face=Verdana, Arial color=#ffffff size=1></FONT><footer> <img src='D:\\FrameWork\\Logo\\Galaxe.png' /> </footer></TD></TR></TBODY></TABLE></U></U></BODY></HTML>";
	        cont="</B></TD></TR></TBODY></TABLE><BR><BR><BR><BR><BR><BR><footer> </footer></BODY></HTML>";
	     fos.write(cont.getBytes());
	     fos.close();
	     CaseCount++;
	        
	 }

	  */    /* *//**
	       * takes the Screen Shot on failure and return the Screen shot file path
	       * @param className
	       * @param Result
	       * @throws Exception
	       *//*
	       public static String  snapShot(String fileName, String filePath) throws IOException, AWTException {
	        String osName = System.getProperty("os.name");
	        Robot rb = new Robot();
	        
	        String sTestCaseLogFolder = FilePath.SCREENSHOT_FOLDER;
	        
	         ArrayList<Integer> iTotalScreenCounter=new ArrayList<Integer>();
	        String commandName = "cmd.exe";
	        if (osName.equals("Windows 95")) {
	               commandName = "command.com";
	        }
	        String[] cmds = new String[9];

	        cmds[0] = commandName;
	        cmds[1] = "/C";
	        cmds[2] = FilePath.SCREENSHOT_EXE;
	        cmds[3] = "/f";
	        cmds[4] = fileName;
	        cmds[5] = "/d";
	        cmds[6] = filePath;
	        cmds[7] = "/q";
	        cmds[8] = "100,7,True";
	        Process substProcess = Runtime.getRuntime().exec(cmds, null, null);
	        
	        BufferedImage screenShot = rb.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
	        
	        
	        ImageIO.write(screenShot, "jpg", new File(DriverScript.ScreenShotPath+"\\"+"Error"+iScreenCounter+".jpg"));
	        //ImageIO.write(screenShot, "jpg", new File(sTestCaseLogFolder+"\\"+iScreenCounter+".jpg"));
	        iScreenCounter=iScreenCounter+1;
	        iTotalScreenCounter.add(iScreenCounter);
	        
	        return filePath+"/"+fileName;
	        
	        
	 }*/

	       
	       /**
	       * To convert seconds to hr:min:sec
	       * @param className
	       * @param Result
	       * @throws Exception
	       */
	       public static String getTime(long longVal){
	              //long longVal = biggy.longValue();
	           int hours = (int) longVal / 3600;
	           int remainder = (int) longVal - hours * 3600;
	           int mins = remainder / 60;
	           remainder = remainder - mins * 60;
	           int secs = remainder;
	        return hours+":"+mins+":"+secs;
	           

	    }
	       
	}




