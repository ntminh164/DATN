package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class RegisterPage extends AbstractComponent {

	WebDriver driver;

	public RegisterPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='email']")
	WebElement email;
	@FindBy(xpath = "//input[@id='name']")
	WebElement name;
	@FindBy(xpath = "//input[@id='phone']")
	WebElement phone;
	@FindBy(xpath = "//input[@id='password']")
	WebElement password;
	@FindBy(xpath = "//input[@id='password_confirmation']")
	WebElement passwordCf;
	@FindBy(xpath = "//button[contains(text(),'Đăng ký')]")
	WebElement btnResgister;
	@FindBy(xpath = "//label[@id='email-error']")
	WebElement emailError;
	@FindBy(xpath = "//strong[normalize-space()='The email has already been taken.']")
	WebElement emailError2;
	
	@FindBy(xpath = "//label[@id='name-error']")
	WebElement nameError;
	@FindBy(xpath = "//label[@id='phone-error']")
	WebElement phoneError;
	@FindBy(xpath = "//label[@id='password-error']")
	WebElement passError;
	@FindBy(xpath = "//label[@id='password_confirmation-error']")
	WebElement passCfError;
	
	
	public DashboardPage registerApplication(String email, String name, String phone, String password, String passwordCf) {
		this.email.sendKeys(email);
		this.name.sendKeys(name);
		this.phone.sendKeys(phone);
		this.password.sendKeys(password);
		this.passwordCf.sendKeys(passwordCf);
		btnResgister.click();
		DashboardPage dashboardPage = new DashboardPage(driver);
		return dashboardPage;

	}
	
	public String getEmailError() {
		return emailError.getText();
	}
	public String getEmailError2() {
		return emailError2.getText();
	}
	public String getNameError() {
		return nameError.getText();
	}
	public String getPhoneError() {
		
		return phoneError.getText();
	}
	public String getPassError() {
		return passError.getText();
	}
	public String getPassCfError() {
		return passCfError.getText();
	}
	
	

}
