package com.promotionsapi.tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.promotionsapi.utils.Config;
 
public class TestBase
{
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
     
    @BeforeSuite
    public void setUp() throws Exception
    {
    	//Initialize the Config.java to read data from config.properties
    	Config.initConfigRead();
    	
    	
    	//Extent repot intialization
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/Report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
         
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("Host Name", "Santheep");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", "Santheep Remanan");
         
        htmlReporter.config().setDocumentTitle("AutomationTesting");
        htmlReporter.config().setReportName("Extent Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
    }
    
    @BeforeClass
    public void initializeReport() {
    	test = extent.createTest("Promotions API Test");
    }
    
    @AfterMethod
    public void getResult(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
        
        
    }
     
    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }
}