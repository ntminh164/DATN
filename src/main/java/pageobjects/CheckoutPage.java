package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath = "//input[@id='post_code']")
	WebElement post_code;
	@FindBy(xpath = "//select[@id='matp']")
	WebElement thanhPho;
	@FindBy(xpath = "//select[@id='maqh']")
	WebElement quanHuyen;
	@FindBy(xpath = "//textarea[@id='notes']")
	WebElement note;
	@FindBy(xpath = "//select[@id='maxa']")
	WebElement xaPhuong;
	@FindBy(xpath = "//button[normalize-space()='Thanh toán']")
	WebElement btnThanhToan;
	
	public void chooseThanhPho() throws InterruptedException
	{	
		Select select = new Select(thanhPho);
		select.selectByValue("Thành phố Cần Thơ");
		
	}
	public void chooseQuanHuyen() throws InterruptedException
	{	
		Select select = new Select(quanHuyen);
		select.selectByValue("Huyện Cờ Đỏ");
		
	}	
	public void chooseXaPhuong() throws InterruptedException
	{	
		Select select = new Select(xaPhuong);
		select.selectByValue("Xã Thạch Phú");
		post_code.sendKeys("100000");
		note.sendKeys("Số 20, ngách 202");
		btnThanhToan.click();
		
	}
}
