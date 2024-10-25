package sameerakhtar.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sameerakhtar.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//PageFactory
	
	@FindBy(css=".infoWrap h3")
	List<WebElement> expectedItems;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOutBtn;
	
	public boolean verifyProductAdded(String actualItem) {
		Boolean status = expectedItems.stream().anyMatch(s -> s.getText().equals(actualItem));
		return status;
	}
	
	public CheckoutPage goToCheckOut() {
		checkOutBtn.click();
		return new CheckoutPage(driver);
	}
	

}
