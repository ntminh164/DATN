package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='email']")
	WebElement email;
	@FindBy(xpath = "//input[@id='password']")
	WebElement password;
	
	
	@FindBy(xpath = "//label[@id='email-error']")
	WebElement emailError;
	@FindBy(xpath = "//label[@id='password-error']")
	WebElement passError;
	@FindBy(xpath = "//button[contains(text(),'Đăng nhập')]")
	WebElement btnLogin;
	
	
	
	public DashboardPage loginApplication(String email, String password ) {
		this.email.sendKeys(email);
		this.password.sendKeys(password);
		btnLogin.click();
		DashboardPage dashboardPage = new DashboardPage(driver);
		return dashboardPage;

	}
	
	public String getEmailError() {
		return emailError.getText();
	}
	
	
	public String getPassError() {
		return passError.getText();
	}
	
	

}
