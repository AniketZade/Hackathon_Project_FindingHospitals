package PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PopularSurgeriesPage extends BasePage{

	public PopularSurgeriesPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//div[text()='Surgeries']")
	WebElement btnSurgery;
	
	@FindBy(xpath="//p[@class='mt-12px AilmentItem-module_itemText__XvCHL']")
	List<WebElement>  surgeryList;
	
	@FindBy(xpath="//h1[text()='Popular Surgeries']")
	WebElement popularSurgery;
	
	public void clickSurgery() {
		btnSurgery.click();
	}
	 public void scrollDown() {
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("arguments[0].scrollIntoView();",popularSurgery);
	 }
	
	public List<String> getAllSurgeries(){
		
		
		List<String> listSurgeries = new ArrayList<String>();
		
		for(WebElement surg:surgeryList) {
			String surgery = surg.getText();
			listSurgeries.add(surgery);
		}
		return listSurgeries;
		
	}
	public void scrollUp() {
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("window.scrollTo(0,0)", "");
	 }

}
