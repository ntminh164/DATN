package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// WebElement userEmails = driver.findElement(By.id("userEmail"));
	// PageFactory

	@FindBy(xpath = "//a[contains(text(),'/Đăng ký')]")
	WebElement dangKy;

	@FindBy(xpath = "//a[contains(text(),'Đăng nhập')]")
	WebElement dangNhap;
	
	@FindBy(xpath = "//a[contains(text(),'Giỏ hàng')]")
	WebElement giohang;
	
	@FindBy(xpath = "//input[@id='product_search']")
	WebElement txtSearch;

	@FindBy(xpath = "//div[@class='product-info text-left']//h3[@class='name']//a[@href='detail.html']")
	private List<WebElement> listProduct;
	@FindBy(xpath = "body > div:nth-child(6) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > h3:nth-child(1) > a:nth-child(1)")
	WebElement prod;
	@FindBy(xpath = "//div[@class='owl-carousel home-owl-carousel custom-carousel owl-theme']//button[@id='33']//i[@class='fa fa-shopping-cart']")
	WebElement iconAddToCart;
	
	
	By addToCart = By.cssSelector(".fa fa-shopping-cart");
	
	public void goTo() {
		driver.get("http://127.0.0.1:8000/");
	}

	public RegisterPage goToRegisterPage() {
		dangKy.click();
		return new RegisterPage(driver);

	}

	public LoginPage goToLoginPage() {
		dangNhap.click();
		return new LoginPage(driver);

	}
	public CartPage goToCartPage() throws InterruptedException {
		giohang.click();
		return new CartPage(driver);

	}

	public void search(String productName) throws InterruptedException {
		txtSearch.sendKeys(productName, Keys.RETURN);

	}

	public Boolean VerifyProductDisplay(String productName) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,860)");
		Thread.sleep(2000);
		Boolean match = listProduct.stream().allMatch(product -> product.getText().contains(productName));
		return match;

	}
	public List<WebElement> getProductList() {
		return listProduct;
	}
	
	public WebElement getProductByName(String productName)
	{
		WebElement prod =	getProductList().stream().filter(product->
		product.getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	
	public void addProductToCart(String productName) throws InterruptedException
	{	
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,860)");
		WebElement prod = getProductByName(productName);
		Actions ref = new Actions (driver);
		ref.moveToElement(prod);
		prod.findElement(addToCart).click();
		
		


	}
	@FindBy(xpath = "(//select[@id='product_modal_color'])[1]")
	WebElement color;
	@FindBy(xpath = "(//select[@id='product_modal_size'])[1]")
	WebElement size;
	@FindBy(xpath = "(//button[contains(text(),'Thêm sản phẩm')])[1]")
	WebElement btnAdd;
	
	public void addProduct() throws InterruptedException
	{	
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,860)");
		Actions ref = new Actions (driver);
		ref.moveToElement(prod);
		iconAddToCart.click();


	}
	public void chooseColor() throws InterruptedException
	{	
		Select select = new Select(color);
		select.selectByValue("xanh");
		
	}
	public void chooseSize() throws InterruptedException
	{	
		Select select = new Select(size);
		select.selectByValue("M");
		btnAdd.click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
		;
	}	
	 
		


	
	}

