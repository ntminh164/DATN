package tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageobjects.DashboardPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import testComponents.BaseTest;

@Listeners(testComponents.Listeners.class)
public class LoginTest extends BaseTest {

	@Test(dataProvider = "getData", priority = 6)
	public void SuccessLogin(HashMap<String, String> input) throws IOException, InterruptedException {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		LoginPage loginPage = landingPage.goToLoginPage();
		DashboardPage dashboardPage = loginPage.loginApplication(input.get("email"), input.get("password"));
		String confirmMessage = dashboardPage.getWellComeMsg();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Xin chào minh đã đến với shop của chúng tôi!"));
		driver.close();

	}
	@Test(dataProvider = "getData", priority = 5)
	public void EmailAutoTrimSpace(HashMap<String, String> input) throws IOException, InterruptedException {
		LoginPage loginPage = landingPage.goToLoginPage();
		DashboardPage dashboardPage = loginPage.loginApplication(input.get("email"), input.get("password"));
		String confirmMessage = dashboardPage.getWellComeMsg();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Xin chào minh đã đến với shop của chúng tôi!"));
		driver.close();

	}

	@Test(dataProvider = "getData", priority = 0)
	public void IncorrectPassword(HashMap<String, String> input) throws IOException, InterruptedException {

		LoginPage loginPage = landingPage.goToLoginPage();
		loginPage.loginApplication(input.get("email"), input.get("password"));
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.equalsIgnoreCase("http://127.0.0.1:8000/login"));

	}
	@Test(dataProvider = "getData", priority = 1)
	public void IncorrectEmail(HashMap<String, String> input) throws IOException, InterruptedException {

		LoginPage loginPage = landingPage.goToLoginPage();
		loginPage.loginApplication(input.get("email"), input.get("password"));
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.equalsIgnoreCase("http://127.0.0.1:8000/login"));

	}
	@Test(dataProvider = "getData", priority = 2)
	public void PasswordNotAutoStrimSpace(HashMap<String, String> input) throws IOException, InterruptedException {

		LoginPage loginPage = landingPage.goToLoginPage();
		loginPage.loginApplication(input.get("email"), input.get("password"));
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.equalsIgnoreCase("http://127.0.0.1:8000/login"));

	}

	@Test(dataProvider = "getData", priority = 3)
	public void IgnoreEmail(HashMap<String, String> input) throws IOException, InterruptedException {

		LoginPage loginPage = landingPage.goToLoginPage();
		loginPage.loginApplication(input.get("email"), input.get("password"));
		String confirmMessage = loginPage.getEmailError();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Vui lòng nhập email của bạn"));

	}

	@Test(dataProvider = "getData", priority = 4)
	public void IgnorePassword(HashMap<String, String> input) throws IOException, InterruptedException {

		LoginPage loginPage = landingPage.goToLoginPage();
		loginPage.loginApplication(input.get("email"), input.get("password"));
		String confirmMessage = loginPage.getPassError();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Vui lòng điền mật khẩu của bạn"));

	}
	@Test(dataProvider = "getData", priority = 4)
	public void AttackXSS(HashMap<String, String> input) throws IOException, InterruptedException {

		LoginPage loginPage = landingPage.goToLoginPage();
		loginPage.loginApplication(input.get("email"), input.get("password"));
		String url = driver.getCurrentUrl();
		Assert.assertTrue(url.equalsIgnoreCase("http://127.0.0.1:8000/login"));
	}
	@DataProvider
	public Object[][] getData(Method m) throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//data//LoginValidation.json");
		switch (m.getName()) {
		case "SuccessLogin": 
		    return new Object[][] {{ data.get(0) }};
		case "EmailAutoTrimSpace": 
		    return new Object[][] {{ data.get(5) }};
		case "IncorrectPassword": 
		    return new Object[][] {{ data.get(1) }};
		case "IncorrectEmail": 
		    return new Object[][] {{ data.get(2) }};
		case "PasswordNotAutoStrimSpace": 
		    return new Object[][] {{ data.get(6) }};
		case "IgnoreEmail": 
		    return new Object[][] {{ data.get(3) }};
		case "IgnorePassword": 
		    return new Object[][] {{ data.get(4) }};
		case "AttackXSS": 
		    return new Object[][] {{ data.get(7) }};
		}
	    return null;
	}}


