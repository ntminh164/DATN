package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class DashboardPage extends AbstractComponent {
	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//body//div//h3[1]")
	WebElement wellComeMsg;
	@FindBy(xpath = "//a[contains(text(),'Cập nhật thông tin')]")
	WebElement btnProfile;
	@FindBy(xpath = "//img[@alt='logo']")
	WebElement btnHome;
	public String getWellComeMsg() {
		return wellComeMsg.getText();
	}
	
	public ProfilePage goToProfilePage()
	{
		btnProfile.click();
		return new ProfilePage(driver);
		
	}
	public LandingPage goToLandingPage()
	{
		btnHome.click();
		return new LandingPage(driver);
		
	}
}
