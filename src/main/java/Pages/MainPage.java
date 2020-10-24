package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import BaseClass.BaseUi;

public class MainPage extends BaseUi{
	
	
	@FindBy(xpath = "//li[@id='car-loan']") 
	public WebElement carLoanBtn;
	
	public CarLoanPage clickCarLoan() {
		carLoanBtn.click();
		
		return PageFactory.initElements(driver, CarLoanPage.class);
	}
}
