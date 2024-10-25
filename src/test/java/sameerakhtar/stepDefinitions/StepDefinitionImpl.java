package sameerakhtar.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sameerakhtar.TestComponents.BaseTest;
import sameerakhtar.pageobject.CartPage;
import sameerakhtar.pageobject.CheckoutPage;
import sameerakhtar.pageobject.ConfirmationPage;
import sameerakhtar.pageobject.LandingPage;
import sameerakhtar.pageobject.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String email, String password) {
		productCatalogue = landingPage.loginApplication(email, password);
	}

	@When("^I add the product (.+) in cart$")
	public void I_add_the_product_in_cart(String productName) {
		productCatalogue.getProductList();
		productCatalogue.getProductByName(productName);
		productCatalogue.addProductToCart(productName);
		cartPage = productCatalogue.goToCartPage();
	}

	@And("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		Boolean status = cartPage.verifyProductAdded(productName);
		Assert.assertTrue(status);
		CheckoutPage checkOut = cartPage.goToCheckOut();
		checkOut.selectCounty("India");
		confirmationPage = checkOut.submitOrder();
	}

	@Then("I verify the {string} message")
	public void I_verify_the_Incorrect_email_or_password_message(String string1) {
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessgae());
	}

	@Then("I verify the {string} message on comfirmation page")
	public void I_verify_the_success_message_on_comfirmation_page(String message) {
		String confirmMessage = confirmationPage.getMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(message));
	}

	@And("I quit the application")
	public void I_quit_the_application() {
		tearDown();
	}
}
