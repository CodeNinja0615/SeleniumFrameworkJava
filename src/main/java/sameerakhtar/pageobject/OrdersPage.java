package sameerakhtar.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sameerakhtar.AbstractComponents.AbstractComponent;


public class OrdersPage extends AbstractComponent {

	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//tr /td[2]")
	List<WebElement> orderedItems;
	
	public boolean verifyOrder(String actualName) {
		return orderedItems.stream().anyMatch(order-> order.getText().equalsIgnoreCase(actualName));
	}
}
