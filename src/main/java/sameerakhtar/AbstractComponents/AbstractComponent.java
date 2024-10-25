package sameerakhtar.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sameerakhtar.pageobject.CartPage;
import sameerakhtar.pageobject.OrdersPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(css = "button[routerlink*='cart']")
	private WebElement cartHeader;

	@FindBy(css = "button[routerlink*='myorders']")
	private WebElement orderHeader;

	public void waitForElementToAppear(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}

	public void waitForElementToAppear(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(Element));
	}

	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

	public CartPage goToCartPage() {
		cartHeader.click();
		return new CartPage(driver);
	}

	public OrdersPage goToOrdersPage() {
		orderHeader.click();
		return new OrdersPage(driver);
	}
}
