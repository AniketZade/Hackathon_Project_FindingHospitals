package PageObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import TestBase.BaseClass;

public class FindDoctorsPage extends BasePage{

	public FindDoctorsPage(WebDriver driver) {
		super(driver);
	}

	List<String> doctorNames = new ArrayList<String>();
	List<String> consultancyFees = new ArrayList<String>();
	List<String> patientStories = new ArrayList<String>();
	
	Map<String,String> doctorDetails = new LinkedHashMap<String,String>();
	

	@FindBy(xpath="(//div[@class='product-tab__title'])[1]")
	WebElement  findDoctors;
	
	@FindBy(xpath="//input[@placeholder='Search location']")
	WebElement searchCity;
	
	@FindBy(xpath="//input[@placeholder='Search doctors, clinics, hospitals, etc.']")
	WebElement selectSpeciality;
	
	@FindBy(xpath="//div[text()='Dentist']")
	WebElement clickOnDentist;
	
	@FindBy(xpath="//span[contains(text(),'Dentist')]")
	WebElement dentistPage;
	
	@FindBy(xpath="//span/span/span[text()='Patient Stories']")
	WebElement patientStr;
	
	@FindBy(xpath="//li[@tabindex='0']")
	List<WebElement> drpPatientStories;
	
	@FindBy(xpath="//span/span/span[text()='Experience']")
	WebElement exp;
	
	@FindBy(xpath="//li[@tabindex='0']")
	List<WebElement> expList;
	
	@FindBy(xpath="//span[@class='u-spacer--right-thin']")
	WebElement allFilter;
	
	@FindBy(xpath="(//div[@data-qa-id='Fees_radio'])")
	List<WebElement> feesList;
	
	@FindBy(xpath="(//div[@data-qa-id='Availability_radio'])")
	List<WebElement> availabilityList;
	
	@FindBy(xpath="//span[text()='Relevance']")
	WebElement  relevance;
	
	@FindBy(xpath="//li[@tabindex='0']")
	List<WebElement> relvanceList;
	
	@FindBy(xpath="//h2[@class='doctor-name']")
	List<WebElement> drNames;
	
	@FindBy(xpath="//div[@class='c-profile--qualification']")
	WebElement drDetails;
	
	
	
	
	
	
	public String getCurrentUrl() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}
	public boolean findDoctorbtn() {
		boolean btnDoctor = findDoctors.isDisplayed();
		return btnDoctor;
	}
	
	public void clickFindDoctors() {
		
		findDoctors.click();
	}
	
	public void searchCity() throws InterruptedException {
		searchCity.clear();
		searchCity.sendKeys(BaseClass.p.getProperty("entercity"));
		
	}
	
	public void searchSpeciality() throws InterruptedException {
		selectSpeciality.clear();
		selectSpeciality.sendKeys(BaseClass.p.getProperty("enterspeciality"));
		Thread.sleep(2000);
		clickOnDentist.click();
		Thread.sleep(1000);	
	}
	
	
	public String getdentistTxt() {
		String dentistPage1 = dentistPage.getText();
		
		return dentistPage1;
	}
	
	
	
	public void selectdrpDownPatientStories() throws InterruptedException {
		patientStr.click();
		Random patStr = new Random();
		int i= patStr.nextInt(drpPatientStories.size());
		drpPatientStories.get(i).click();	
	}
	
	public void selectdrpDownExperience() throws InterruptedException {
		exp.click();
		Random experience = new Random();
		int j= experience.nextInt(expList.size());
		expList.get(j).click();
		
	}
	
	public void selectFeesFromAllFilters() throws InterruptedException {
		allFilter.click();
		Random fees = new Random();
		int k= fees.nextInt(feesList.size());
		feesList.get(k).click();
	

	}
	
	public void selectAvailabilityFromAllFilters() throws InterruptedException {
		allFilter.click();

		Random availability = new Random();
		int m= availability.nextInt(availabilityList.size());
		availabilityList.get(m).click();


	}
	public void selectRelevance() {
		relevance.click();
		Random rel = new Random();
		int n= rel.nextInt(relvanceList.size());
		relvanceList.get(n).click();

	}
	
	public Map<String, String> extractDoctorsDetails() throws InterruptedException {

		for(int i=0;i<5;i++) {
			String drName = drNames.get(i).getText();


			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].click();", drNames.get(i));
			Set<String> tabs = driver.getWindowHandles();
			String parentTab = driver.getWindowHandle();
			for (String child : tabs) {	
				if(!parentTab.equals(child)) {
					driver.switchTo().window(child);
				}
			}
			String doctorDetail = drDetails.getText();
		
			doctorDetails.put(drName, doctorDetail);
			
			driver.close();
			driver.switchTo().window(parentTab);
			
			
		}
		return doctorDetails;
		
		
		
	}

	
}
