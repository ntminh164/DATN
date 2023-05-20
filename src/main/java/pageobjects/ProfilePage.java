package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ProfilePage extends AbstractComponent {
	WebDriver driver;

	public ProfilePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@id='profile_photo_path']")
	WebElement divFileUpload;
	@FindBy(xpath = "//button[contains(text(),'Cập nhật')]")
	WebElement btnUpdate;
	

	
	public String uploadFile(String filePath) throws InterruptedException  {

		divFileUpload.sendKeys(filePath);
		Thread.sleep(1000);
		btnUpdate.click();
		waitForElementToAppear(By.xpath("//div[@class='toast toast-success']"));
		return driver.findElement(By.xpath("//div[@class='toast toast-success']")).getText();
	}
	
	
}
