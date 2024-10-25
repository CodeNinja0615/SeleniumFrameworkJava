package sameerakhtar.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sameerakhtar.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement userEmail = driver.findElement(By.id("userEmail"));
//	WebElement password = driver.findElement(By.id("userPassword"));
	// PageFactory

	@FindBy(id = "userEmail")
	private WebElement userEmail;

	@FindBy(id = "userPassword")
	private WebElement passwordEle;

	@FindBy(id = "login")
	private WebElement submitBtn;

	@FindBy(css = "[class*='flyInOut']")
	private WebElement errorMsg;

	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submitBtn.click();
		return new ProductCatalogue(driver);
	}

	public String getErrorMessgae() {
		waitForElementToAppear(errorMsg);
		return errorMsg.getText();
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
