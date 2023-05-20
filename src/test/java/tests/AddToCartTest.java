package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageobjects.CartPage;
import pageobjects.CheckoutPage;
import pageobjects.DashboardPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import testComponents.BaseTest;

@Listeners(testComponents.Listeners.class)
public class AddToCartTest extends BaseTest {
	@Test
	public void AddToCartSuccess() throws InterruptedException, IOException {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		LoginPage loginPage = landingPage.goToLoginPage();
		DashboardPage dashboardPage = loginPage.loginApplication("ntminh164@gmail.com", "12345678");
		String confirmMessage = dashboardPage.getWellComeMsg();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Xin chào minh đã đến với shop của chúng tôi!"));
		dashboardPage.goToLandingPage();
		landingPage.addProduct();
		landingPage.chooseColor();
		landingPage.chooseSize();
		CartPage cartPage= landingPage.goToCartPage();
		Thread.sleep(2000);
		Boolean match = cartPage.VerifyOrderDisplay("HOODIE  GRADIENT 3");
		Assert.assertFalse(match);
		CheckoutPage  checkoutPage=cartPage.goToCheckout();
		checkoutPage.chooseThanhPho();
		checkoutPage.chooseQuanHuyen();
		
		checkoutPage.chooseXaPhuong();
		
		
		
	
		
		
		

	}

	


}
