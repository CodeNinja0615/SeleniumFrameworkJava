package sameerakhtar.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sameerakhtar.TestComponents.BaseTest;
import sameerakhtar.data.DataReader;
import sameerakhtar.pageobject.CartPage;
import sameerakhtar.pageobject.CheckoutPage;
import sameerakhtar.pageobject.ConfirmationPage;
import sameerakhtar.pageobject.OrdersPage;
import sameerakhtar.pageobject.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrderTest(HashMap<String, String> input) throws InterruptedException {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		productCatalogue.getProductList();
		productCatalogue.getProductByName(input.get("actualItem"));
		productCatalogue.addProductToCart(input.get("actualItem"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean status = cartPage.verifyProductAdded(input.get("actualItem"));
		Assert.assertTrue(status);
		CheckoutPage checkOut = cartPage.goToCheckOut();
		checkOut.selectCounty("India");
		ConfirmationPage confirmationPage = checkOut.submitOrder();

		String confirmMessage = confirmationPage.getMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrderTest" }, dataProvider = "getData", groups = { "Purchase" })
	public void orderHistoryTest(HashMap<String, String> input) {
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrdersPage ordersPage = productCatalogue.goToOrdersPage();
		Boolean orderStatus = ordersPage.verifyOrder(input.get("actualItem"));
		Assert.assertTrue(orderStatus);
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = DataReader.getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//sameerakhtar//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } }; //--Set of parameters
	}

//	@DataProvider
//	public Object[][] getData() {
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "akhtarsameer743@gmail.com");
//	map.put("passwrod", "Sameerking01!");
//	map.put("actualItem", "ZARA COAT 3");
//
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "sameerakhtar1513@gmail.com");
//	map1.put("passwrod", "Sameerking01!");
//	map1.put("actualItem", "ADIDAS ORIGINAL");
//		return new Object[][] {{"akhtarsameer743@gmail.com", "Sameerking01!", "ZARA COAT 3"},{"sameerakhtar1513@gmail.com","Sameerking01!", "ADIDAS ORIGINAL"}};
//	}
}
