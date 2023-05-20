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
import pageobjects.RegisterPage;
import testComponents.BaseTest;

@Listeners(testComponents.Listeners.class)
public class RegisterTest extends BaseTest {

	@Test(dataProvider = "getData", priority = 20)
	public void SuccessRegister(HashMap<String, String> input) throws IOException, InterruptedException {

		RegisterPage registerPage = landingPage.goToRegisterPage();
		DashboardPage dashboardPage = registerPage.registerApplication(input.get("email"), input.get("name"),
				input.get("phone"), input.get("password"), input.get("passwordCf"));
		String confirmMessage = dashboardPage.getWellComeMsg();
 		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Xin chào minh đã đến với shop của chúng tôi!"));


	}

	

	@Test(dataProvider = "getData", priority = 21)
	public void EmailAlreadyExists(HashMap<String, String> input) throws IOException, InterruptedException {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();

		RegisterPage registerPage = landingPage.goToRegisterPage();
		registerPage.registerApplication(input.get("email"), input.get("name"), input.get("phone"), input.get("password"),
				input.get("passwordCf"));
		String confirmMessage = registerPage.getEmailError2();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("The email has already been taken."));

	}

	@Test(dataProvider = "getData", priority = 0)
	public void IgnoreEmail(HashMap<String, String> input) throws IOException, InterruptedException {

		RegisterPage registerPage = landingPage.goToRegisterPage();
		registerPage.registerApplication(input.get("email"), input.get("name"),
				input.get("phone"), input.get("password"), input.get("passwordCf"));
		String confirmMessage = registerPage.getEmailError();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Vui lòng nhập email của bạn"));
	}

	@Test(dataProvider = "getData", priority = 1)
	public void IgnoreName(HashMap<String, String> input) throws IOException, InterruptedException {

		RegisterPage registerPage = landingPage.goToRegisterPage();
		registerPage.registerApplication(input.get("email"), input.get("name"),
				input.get("phone"), input.get("password"), input.get("passwordCf"));
		String confirmMessage = registerPage.getNameError();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Vui lòng nhập tên của bạn"));
	}

	@Test(dataProvider = "getData", priority = 2)
	public void IgnorePhone(HashMap<String, String> input) throws IOException, InterruptedException {

		RegisterPage registerPage = landingPage.goToRegisterPage();
		registerPage.registerApplication(input.get("email"), input.get("name"),
				input.get("phone"), input.get("password"), input.get("passwordCf"));
		String confirmMessage = registerPage.getPhoneError();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Vui lòng nhập điện thoại của bạn"));
	}

	@Test(dataProvider = "getData", priority = 3)
	public void IgnorePassword(HashMap<String, String> input) throws IOException, InterruptedException {

		RegisterPage registerPage = landingPage.goToRegisterPage();
		registerPage.registerApplication(input.get("email"), input.get("name"),
				input.get("phone"), input.get("password"), input.get("passwordCf"));
		String confirmMessage = registerPage.getPassError();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Vui lòng điền mật khẩu của bạn"));
	}

	@Test(dataProvider = "getData", priority = 4)
	public void PasswordNotMatch(HashMap<String, String> input) throws IOException, InterruptedException {

		RegisterPage registerPage = landingPage.goToRegisterPage();
		registerPage.registerApplication(input.get("email"), input.get("name"),
				input.get("phone"), input.get("password"), input.get("passwordCf"));
		String confirmMessage = registerPage.getPassCfError();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Mật khẩu của bạn không khớp"));
	}

	@Test(dataProvider = "getData", priority = 5)
	public void PasswordLengthLessMinLength(HashMap<String, String> input) throws IOException, InterruptedException {

		RegisterPage registerPage = landingPage.goToRegisterPage();
		registerPage.registerApplication(input.get("email"), input.get("name"),
				input.get("phone"), input.get("password"), input.get("passwordCf"));
		String confirmMessage = registerPage.getPassError();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Mật khẩu yêu cầu tối thiểu 6 ký tự"));
	}

	@Test(dataProvider = "getData", priority = 6)
	public void PhoneNumberOtherNumeric(HashMap<String, String> input) throws IOException, InterruptedException {

		RegisterPage registerPage = landingPage.goToRegisterPage();
		registerPage.registerApplication(input.get("email"), input.get("name"),
				input.get("phone"), input.get("password"), input.get("passwordCf"));
		String confirmMessage = registerPage.getPhoneError();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Yêu cầu bắt buộc số từ 0-9"));
	}
	@Test(dataProvider = "getData", priority = 7)
	public void EmailIncorrectFormat(HashMap<String, String> input) throws IOException, InterruptedException {

		RegisterPage registerPage = landingPage.goToRegisterPage();
		registerPage.registerApplication(input.get("email"), input.get("name"),
				input.get("phone"), input.get("password"), input.get("passwordCf"));
		String confirmMessage = registerPage.getEmailError();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Email của bạn không đúng định dạng"));
	}



	@DataProvider
	public Object[][] getData(Method m) throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//data//RegisterValidation.json");
		switch (m.getName()) {
		case "SuccessRegister": 
		    return new Object[][] {{ data.get(0) }};
		case "EmailAlreadyExists": 
		    return new Object[][] {{ data.get(0) }};
		case "IgnoreEmail": 
		    return new Object[][] {{ data.get(1) }};
		case "IgnoreName": 
		    return new Object[][] {{ data.get(2) }};
		case "IgnorePhone": 
		    return new Object[][] {{ data.get(3) }};
		case "IgnorePassword": 
		    return new Object[][] {{ data.get(4) }};
		case "PasswordNotMatch": 
		    return new Object[][] {{ data.get(5) }};
		case "PasswordLengthLessMinLength": 
		    return new Object[][] {{ data.get(6) }};
		case "PhoneNumberOtherNumeric": 
		    return new Object[][] {{ data.get(7) }};
		case "EmailIncorrectFormat": 
		    return new Object[][] {{ data.get(8) }};
		}
	    return null;
	}
}
