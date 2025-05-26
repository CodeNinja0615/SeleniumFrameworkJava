package sameerakhtar.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import sameerakhtar.pageobject.LandingPage;

public class BaseTest {

//	public WebDriver driver;
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	public LandingPage landingPage;

	public WebDriver getDriver() {
		return driver.get();
	}

	public void initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//sameerakhtar//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") // --Ternary
																									// operators
				: prop.getProperty("browser");
//		prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("--headless");
			}
//			driver = new ChromeDriver(options);
			driver.set(new ChromeDriver(options));
		} else if (browserName.contains("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			if (browserName.contains("headless")) {
				options.addArguments("--headless");
			}
//			driver = new FirefoxDriver(options);
			driver.set(new FirefoxDriver(options));
		} else if (browserName.contains("edge")) {
			EdgeOptions options = new EdgeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("--headless");
			}
//			driver = new EdgeDriver(options);
			driver.set(new EdgeDriver(options));
		}
//		driver.manage().window().setSize(new Dimension(1920, 1080)); //----To run in full screen
		getDriver().manage().window().maximize();
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {// ----Goes to extent report
																							// in Listeners
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File filePath = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(src, filePath);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		initializeDriver();
		landingPage = new LandingPage(getDriver());
		landingPage.goTo();
		return landingPage; // ---Using this in stepDefinition in cucumber
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		getDriver().quit();
		driver.remove();
	}
}
