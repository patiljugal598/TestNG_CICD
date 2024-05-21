package com.testComponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mavenProjectE2EFramework.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentReports extent = ExtentReportNG.getExtentReport();
	ExtentTest test;
	
	ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {

		test = extent.createTest(result.getMethod().getMethodName());
		threadLocal.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test is passed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		threadLocal.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		try {
			 takeScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// keep the screenshot in the same folder where report is generating, else broken image will appear in the report
		test.addScreenCaptureFromPath(result.getMethod().getMethodName()+".png");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, "Test is skipped.");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
