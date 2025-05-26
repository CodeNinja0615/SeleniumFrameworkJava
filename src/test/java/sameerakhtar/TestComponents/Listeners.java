package sameerakhtar.TestComponents;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import sameerakhtar.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	WebDriver driver;
	ExtentReports extent = ExtentReporterNG.getReporterObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // --- Thread Safe

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //----Asssigning unique thread ID for test(replace every test object to extentTest.get())
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailure(result);
		extentTest.get().fail(result.getThrowable());

		//----Parallel (exclude method)
//		try {
//			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// ScreenShot, Attach it to the report
//		String path = null;
//		try {
//			path = getScreenshot(result.getMethod().getMethodName(), driver);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		
		//--Parallel (include method)
		try {
			// Use reflection to call getDriver() method instead of accessing driver field
			Method m = result.getInstance().getClass().getMethod("getDriver");
			driver = (WebDriver) m.invoke(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ScreenShot, Attach it to the report
		String path = null;
		if (driver != null) {
			try {
				path = getScreenshot(result.getMethod().getMethodName(), driver);
				if (path != null) {
					// Convert absolute path to relative path for ExtentReports
//					String baseDir = System.getProperty("user.dir"); //--for IntelliJ
//					String relPath = path.replace(baseDir + "/", "");
					extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("[Listener] WebDriver is null, screenshot not captured for: " + result.getMethod().getMethodName());
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extent.flush();
	}

}
