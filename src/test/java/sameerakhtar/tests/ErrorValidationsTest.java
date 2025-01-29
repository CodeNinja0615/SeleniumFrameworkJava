package sameerakhtar.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import sameerakhtar.TestComponents.BaseTest;
import sameerakhtar.TestComponents.Retry;
import sameerakhtar.pageobject.CartPage;
import sameerakhtar.pageobject.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {
		landingPage.loginApplication("akhtarsameer743@gmail.com", "Sameerking01");
		String error = landingPage.getErrorMessgae();
		Assert.assertEquals("Incorrect email or password.", error);
	}

	@Test(groups = {"ErrorHandling"})
	public void errorProductValidation() throws IOException {
		String actualItem = "IPHONE 13 PRO";
		ProductCatalogue productCatalogue = landingPage.loginApplication("sameerakhtar1513@gmail.com", "Sameerking01!");

		productCatalogue.getProductList();
		productCatalogue.getProductByName(actualItem);
		productCatalogue.addProductToCart(actualItem);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean status = cartPage.verifyProductAdded("ZARA COAT 33");
		Assert.assertFalse(status);
	}
}
