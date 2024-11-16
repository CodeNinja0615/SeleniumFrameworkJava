package sameerakhtar.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import sameerakhtar.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// PageFactory
	By products = By.cssSelector(".col-lg-4.col-lg-4.col-md-6");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");

	@FindBy(css = ".col-lg-4.col-lg-4.col-md-6")
	private List<WebElement> items;

	@FindBy(css = ".ng-animating")
	private WebElement spinner;

	public List<WebElement> getProductList() {
		waitForElementToAppear(products);
		waitForElementToBeClickable(products);
		return items;
	}

	public WebElement getProductByName(String actualItem) {
		WebElement prod = items.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(actualItem)).findFirst()
				.orElse(null);
		return prod;
	}

	public void addProductToCart(String actualItem) {
		WebElement prod = getProductByName(actualItem);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(spinner);
	}

}
