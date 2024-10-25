package sameerakhtar.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class StandAloneTest {

//	@Test
	public static void main(String[] args) {
		String actualItem = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.id("userEmail")).sendKeys("akhtarsameer743@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sameerking01!");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".col-lg-4.col-lg-4.col-md-6")));
		List<WebElement> items = driver.findElements(By.cssSelector(".col-lg-4.col-lg-4.col-md-6"));
		WebElement prod = items.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(actualItem)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("#toast-container")));

		// ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();

		List<WebElement> expectedItems = driver.findElements(By.cssSelector(".infoWrap h3"));
		Boolean status = expectedItems.stream().anyMatch(s -> s.getText().equals(actualItem));
		Assert.assertTrue(status);

		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		driver.findElement(By.xpath("//span[text()=' India']")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		List<WebElement> orderIds = driver.findElements(By.cssSelector("tr.ng-star-inserted td label"));
		orderIds.stream().forEach(s -> System.out.println(s.getText()));

		driver.quit();
	}
}
