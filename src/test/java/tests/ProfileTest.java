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
import pageobjects.LoginPage;
import pageobjects.ProfilePage;
import testComponents.BaseTest;

@Listeners(testComponents.Listeners.class)
public class ProfileTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void UploadIncorrectTypeFile(HashMap<String, String> input) throws IOException, InterruptedException {
		LoginPage loginPage = landingPage.goToLoginPage();
		DashboardPage dashboardPage = loginPage.loginApplication(input.get("email"), input.get("password"));
		String confirmMessage = dashboardPage.getWellComeMsg();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Xin chào minh đã đến với shop của chúng tôi!"));
		ProfilePage profilePage= dashboardPage.goToProfilePage();
		String msg =profilePage.uploadFile("C:\\Selenium\\TermList.xlsx");
		Assert.assertTrue(msg.equalsIgnoreCase("Cập nhật thông tin thành công."));
		Thread.sleep(1000);
		

	}
	@Test(dataProvider = "getData")
	public void UploadCorrectTypeFile(HashMap<String, String> input) throws IOException, InterruptedException {
		LoginPage loginPage = landingPage.goToLoginPage();
		DashboardPage dashboardPage = loginPage.loginApplication(input.get("email"), input.get("password"));
		String confirmMessage = dashboardPage.getWellComeMsg();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Xin chào minh đã đến với shop của chúng tôi!"));
		ProfilePage profilePage= dashboardPage.goToProfilePage();
		profilePage.uploadFile("C:\\Selenium\\test.jpg");
		String msg =profilePage.uploadFile("C:\\Selenium\\test.jpg");
		Assert.assertTrue(msg.equalsIgnoreCase("Cập nhập thông tin thành công."));

	}
	
	@DataProvider
	public Object[][] getData(Method m) throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//data//LoginValidation.json");
		switch (m.getName()) {
		case "UploadIncorrectTypeFile": 
		    return new Object[][] {{ data.get(0) }};
		
		case "UploadCorrectTypeFile": 
	    return new Object[][] {{ data.get(0) }};
	}
	    return null;
	}}


