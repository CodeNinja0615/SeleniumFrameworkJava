package sameerakhtar.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sameerakhtar.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// PageFactory

	@FindBy(xpath = "//span[text()=' India']")
	private WebElement countryEle;

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	private WebElement country;

	@FindBy(css = ".action__submit")
	private WebElement confirm;

	By results = By.xpath("//span[text()=' India']");

	public void selectCounty(String countryName) {
		country.sendKeys(countryName);
		waitForElementToAppear(results);
		countryEle.click();
	}

	public ConfirmationPage submitOrder() {
		confirm.click();
		return new ConfirmationPage(driver);
	}

}
