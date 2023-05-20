package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import testComponents.BaseTest;

@Listeners(testComponents.Listeners.class)
public class CheckoutTest extends BaseTest {
	@Test
	public void AddToCartSuccess() throws InterruptedException {

		landingPage.addProduct();
		landingPage.chooseColor();
		landingPage.chooseSize();
		CartPage cartPage= landingPage.goToCartPage();
		Thread.sleep(2000);
		Boolean match = cartPage.VerifyOrderDisplay("HOODIE  GRADIENT 3");
		Assert.assertFalse(match);
		
		
		

	}

	


}
