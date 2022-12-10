package com.sdetadda.extentreport;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.gherkin.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ExtentReportFilter {
	ExtentReports extent = new ExtentReports();
	ExtentTest test;
	@BeforeTest
	public void systemInfo()
	{
		//ExtentTest 
		test =extent.createTest("SystemInfo");
		test.assignAuthor("author");
		test.assignAuthor("author-1", "author-2");
		// usage
		extent.createTest("test").assignAuthor("Raghavendra").pass("Test details");
		extent.setSystemInfo("os", "Windows10");
	}
	@Test
	public void firstSampleTest() {

		ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkPassed.html");
		extent.attachReporter(spark);
		extent.createTest("MyFirstTest")
		.log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
		extent.flush();
	}
	@Test
	public void ReportOnlyContainsFailure()
	{
		// will only contain failures
		ExtentSparkReporter sparkFail = new ExtentSparkReporter("target/SparkFail.html")
				.filter()
				.statusFilter()
				.as(new Status[] { Status.FAIL })
				.apply();
		// will contain all tests
		ExtentSparkReporter sparkAll = new ExtentSparkReporter("target/SparkAll.html");
		extent.attachReporter(sparkFail, sparkAll);
		extent.createTest("FailTestDemo1")
		.log(Status.FAIL, "This is a logging event for MyFirstFailTestDemo");
		extent.createTest("FailTestDemo2")
		.log(Status.FAIL, "This is a logging event for MySecondFailTestDemo");
		extent.createTest("WARNINGTestDemo1")
		.log(Status.WARNING, "This is a logging event for WARNINGTestDemo1, and its first arning!");
		extent.createTest("WARNINGTestDemo2")
		.log(Status.WARNING, "This is a logging event for WARNINGTestDemo2, and its second warning!");
		extent.createTest("SKIPTestDemo1")
		.log(Status.SKIP, "This is a logging event for SKIP !");
		extent.createTest("INFOTestDemo1")
		.log(Status.INFO, "This is a logging event for INFO !");
		extent.createTest("PASSTestDemo1")
		.log(Status.PASS, "This is a logging event for PASS !");
	}
	
	@Test
	public void logging()
	{
		ExtentTest test = extent.createTest("MySecondTest");
		test.pass("Text details");
		test.log(Status.PASS, "Text details added by Raghavendra Mishra");
	}
	@Test
	public void exceptions()
	{
		Throwable t = new RuntimeException("A runtime exception");
		ExtentTest test = extent.createTest("MyExCeptionTest");
		test.fail(t);
		test.log(Status.FAIL, t);
	}
	@Test
	public void screenShots()
	{
		ExtentTest test = extent.createTest("ScreenShotTest");
		// reference image saved to disk
		test.fail(MediaEntityBuilder.createScreenCaptureFromPath("img.png").build());
		// base64
		test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
	}
	@Test()
	public void tagging()
	{
		ExtentTest test = extent.createTest("ScreenShotTest");
		test.assignCategory("tag");
		test.assignCategory("tag-1", "tag-2");
		// usage
		extent.createTest("Testcategory").assignCategory("tag-1").pass("details provided for category");
		extent.createTest("Testcategory").assignCategory("tag-2").fail("details provided for category");
		extent.createTest("Testcategory").assignCategory("tag-3").skip("details provided for category");
	}
	@Test
	public void assignDevice()
	{
		ExtentTest test = extent.createTest("Device Name");
		test.assignDevice("device-name");
		test.assignDevice("device-1", "device-2");
		// usage
		extent.createTest("Test").assignDevice("device-name").pass("details");
	}
	@AfterMethod
	public void teardown() {
		extent.flush();
	}
}
