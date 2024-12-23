package sameerakhtar.AbstractComponents;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

//	@FindBy(css = "button[routerlink*='cart']")
//	private WebElement cartHeader;
	@FindBy(xpath = "//button[contains(@routerlink, 'cart')]")
	private WebElement cartHeader;

	@FindBy(css = "button[routerlink*='myorders']")
	private WebElement orderHeader;

	
	public void copyToClipboard(String text) {
	    StringSelection stringSelection = new StringSelection(text); // Wrap the text
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard(); // Get the system clipboard
	    clipboard.setContents(stringSelection, null); // Set the clipboard content
	}
	
	public void pasteClipboardText() throws AWTException {
		Robot action = new Robot();
		action.keyPress(KeyEvent.CTRL_DOWN_MASK);
		action.keyPress(KeyEvent.VK_V);
		action.keyRelease(KeyEvent.VK_V);
		action.keyRelease(KeyEvent.CTRL_DOWN_MASK);
	}
	
	public void waitForElementToAppear(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(Element));
	}
	
	public void waitForElementToAppear(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
	}


	public void waitForElementToBeClickable(WebElement Element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(Element));
	}
	
	public void waitForElementToBeClickable(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}
	
	public void waitForElementToDisappear(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public void waitForElementToDisappear(By by) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public CartPage goToCartPage() {
		waitForElementToBeClickable(cartHeader);
		cartHeader.click();
		return new CartPage(driver);
	}

	public OrdersPage goToOrdersPage() {
		waitForElementToBeClickable(orderHeader);
		orderHeader.click();
		return new OrdersPage(driver);
	}
}
