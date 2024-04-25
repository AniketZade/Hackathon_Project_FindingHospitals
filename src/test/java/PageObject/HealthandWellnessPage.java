package PageObject;

import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestBase.BaseClass;

public class HealthandWellnessPage extends BasePage {

	public HealthandWellnessPage(WebDriver driver) {
		super(driver);
	}
	
	WebDriverWait wait1 = new WebDriverWait(driver,Duration.ofSeconds(60));
	
	
	@FindBy(xpath="//span[@class='nav-interact']")
	WebElement btnforCorporate;
	
	@FindBy(xpath="//a[text()='Health & Wellness Plans']")
	WebElement healthAndwellnes;
	
	@FindBy(id="name")
	WebElement enterName;
	
	@FindBy(id="organizationName")
	WebElement enterOrgName;
	
	@FindBy(id="contactNumber")
	WebElement contactNumber;
	
	@FindBy(id="officialEmailId")
	WebElement emailId;
	
	@FindBy(id="organizationSize")
	WebElement organizationSize;
	
	@FindBy(id="interestedIn")
	WebElement interestedIn;
	
	@FindBy(xpath="(//button[@type='submit'])[1]")
	WebElement btnSubmit;
	
	
	@FindBy(xpath="(//div[text()='THANK YOU'])[1]")
	WebElement txtThank;
	
	@FindBy(xpath="//button[text()='Verify']")
	WebElement  btnCaptcha;
	
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(60));
	
	public void clickOnCorporate() {
		btnforCorporate.click();
	}
	
	public void clickOnHealthAndWellness() {
		healthAndwellnes.click();
	}
	
	public boolean  fillformWithInvalidNumber() {
		
		enterName.sendKeys(RandomStringUtils.random(5,true,false));
		enterOrgName.sendKeys(RandomStringUtils.random(5,true,false));
		contactNumber.sendKeys("53729929rt");
		emailId.sendKeys(RandomStringUtils.random(5,true,false)+"@gmail.com");
		Select orgSize = new Select(organizationSize);
		orgSize.selectByValue("<=500");
		Select interested = new Select(interestedIn);
		interested.selectByValue(BaseClass.p.getProperty("takingademo"));
		
		return btnSubmit.isEnabled();
		
	}
	
	public boolean fillformWithInvalidEmail() {

		contactNumber.clear();
		
		contactNumber.sendKeys(BaseClass.p.getProperty("validContact"));

		emailId.clear();
		
		emailId.sendKeys(RandomStringUtils.random(5,true,false));
		
		return btnSubmit.isEnabled();

	}
	
	public boolean fillformWithValidData() {

		contactNumber.clear();
		contactNumber.sendKeys(BaseClass.p.getProperty("validContact"));
		emailId.clear();
		emailId.sendKeys(RandomStringUtils.random(5,true,false)+"@gmail.com");
		
		return btnSubmit.isEnabled();
	}
	public String  getDisplayedMsg() throws InterruptedException {
		btnSubmit.click();
//		try {
//			if(btnCaptcha.isDisplayed()) {
//				Thread.sleep(60000);
//			}
//		}
//		catch(Exception e) {
//			Thread.sleep(1000);
//		}
		wait1.until(ExpectedConditions.visibilityOf(txtThank));
		
	
		String  msg = txtThank.getText();
		return msg;
		
	}
	
}
