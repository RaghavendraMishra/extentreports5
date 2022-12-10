package com.sdetadda.extentreport;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentConfigTest {
	ExtentReports extent;
	ExtentSparkReporter spark;
	ExtentTest test;
	ExtentSparkReporterConfig config;
  @BeforeTest
  public void startReport() {
	  spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/myreport.html");
      extent = new ExtentReports();
      extent.attachReporter(spark);
      spark.config().setTheme(Theme.DARK);
      spark.config().setDocumentTitle("SDET ADDA");
      spark.config().setReportName("Test report by Raghavendra Mishra");
      
  }
  @Test
  public void test1()
  {
	  extent.createTest("MyFirstTest").log(Status.PASS, "Pass");
	  extent.createTest("MySecondTestFail").log(Status.FAIL, "fail");
	 // test.log(Status.FAIL, MarkupHelper.createLabel("MySecondTestFail", ExtentColor.GREEN));
	  
  }
  @AfterTest
  public void endReport()
  {
	  extent.flush();
  }
}
