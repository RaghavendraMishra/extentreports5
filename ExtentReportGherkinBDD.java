package com.sdetadda.extentreport;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
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
public class ExtentReportGherkinBDD {
	ExtentReports extent = new ExtentReports();
	ExtentTest test;
	ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkBDD.html");
	//ExtentSparkReporter sparkBDD = new ExtentSparkReporter("target/SparkBDD.html");
	
	@Test
	public void BDDGherkinTest()
	{	
		extent.attachReporter(spark);
		try {
			ExtentTest feature = extent.createTest(new GherkinKeyword("Feature"), "Refund item Test by Raghavendra");
			ExtentTest scenario = feature.createNode(new GherkinKeyword("Scenario"), "Jeff returns a faulty microwave");
			scenario.createNode(new GherkinKeyword("Given"), "Jeff has bought a microwave for $100").pass("pass");
			scenario.createNode(new GherkinKeyword("And"), "he has a receipt").pass("pass");
			scenario.createNode(new GherkinKeyword("When"), "he returns the microwave").pass("pass");
			scenario.createNode(new GherkinKeyword("Then"), "Jeff should be refunded $100").fail("fail");
		}
		catch (Exception e) {
			e.getMessage();	// TODO: handle exception
		}

	}
	@Test
	public void GherkinDialectDemo()
	{
		try {
			extent.setGherkinDialect("de");
			ExtentTest feature = extent.createTest(new GherkinKeyword("Funktionalität"), "Refund item VM");
			ExtentTest scenario = feature.createNode(new GherkinKeyword("Szenario"), "Jeff returns a faulty microwave");
			ExtentTest given = scenario.createNode(new GherkinKeyword("Angenommen"), "Jeff has bought a microwave for $100").skip("skip");
		}
		catch (Exception e) {
			e.getMessage();	// TODO: handle exception
		}
	}
	@Test
	public void exceptionMethod()
	{
		Throwable t = new RuntimeException("A runtime exception");
		ExtentTest test = extent.createTest("MyExCeptionTest");
		test.fail(t);
		test.log(Status.FAIL, t);
	}

	@AfterTest
	public void teardown() {
		extent.flush();
	}
}
