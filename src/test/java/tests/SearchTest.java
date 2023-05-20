package tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import testComponents.BaseTest;

@Listeners(testComponents.Listeners.class)
public class SearchTest extends BaseTest {
	@Test(dataProvider = "getData")
	public void AbsoluteSearch(HashMap<String, String> input) throws IOException, InterruptedException {

		landingPage.search(input.get("productName"));
		Boolean match = landingPage.VerifyProductDisplay(input.get("productNameVerify"));
		Assert.assertTrue(match);

	}

	@Test(dataProvider = "getData")
	public void RelativeSearch(HashMap<String, String> input) throws IOException, InterruptedException {

		landingPage.search(input.get("productName"));
		Boolean match = landingPage.VerifyProductDisplay(input.get("productNameVerify"));
		Assert.assertTrue(match);

	}

	@Test(dataProvider = "getData")
	public void IgnoreCase(HashMap<String, String> input) throws IOException, InterruptedException {

		landingPage.search(input.get("productName"));
		Boolean match = landingPage.VerifyProductDisplay(input.get("productNameVerify"));
		Assert.assertTrue(match);

	}

	@Test(dataProvider = "getData")
	public void IgnoreAccentedLetter(HashMap<String, String> input) throws IOException, InterruptedException {

		landingPage.search(input.get("productName"));
		Boolean match = landingPage.VerifyProductDisplay(input.get("productNameVerify"));
		Assert.assertTrue(match);

	}

	@Test(dataProvider = "getData")
	public void SearchNoResult(HashMap<String, String> input) throws IOException, InterruptedException {

		landingPage.search(input.get("productName"));
		Boolean match = landingPage.VerifyProductDisplay(input.get("productNameVerify"));
		Assert.assertTrue(match);

	}

	@DataProvider
	public Object[][] getData(Method m) throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//data//Search.json");
		switch (m.getName()) {
		case "AbsoluteSearch":
			return new Object[][] { { data.get(0) } };
		case "RelativeSearch":
			return new Object[][] { { data.get(1) } };
		case "IgnoreCase":
			return new Object[][] { { data.get(2) } };
		case "IgnoreAccentedLetter":
			return new Object[][] { { data.get(3) } };
		case "SearchNoResult":
			return new Object[][] { { data.get(4) } };

		}

		return null;
	}

}
