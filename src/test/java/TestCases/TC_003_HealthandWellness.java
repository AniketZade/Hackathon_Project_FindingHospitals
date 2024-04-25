package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.FindDoctorsPage;
import PageObject.HealthandWellnessPage;
import TestBase.BaseClass;

public class TC_003_HealthandWellness extends BaseClass{
	
	public HealthandWellnessPage hw ;
	
	
	@Test(priority = 11,groups= {"sanity","master"})
	public void TC_011_verifywithInavlidPhoneNumber() {
		logger.info("************ TC_011_verifywithInavlidPhoneNumber Started *************");
		hw = new HealthandWellnessPage(driver);
		hw.clickOnCorporate();
		logger.info("Clicked on forCorporate button");
		hw.clickOnHealthAndWellness();
		logger.info("Clicked on Health and Wellness button ");

		Assert.assertEquals(hw.fillformWithInvalidNumber(),false,"The button is Enabled but the contact number is invalid");
		logger.info("The button is disabled when the contact number is invalid");
		logger.info("************ TC_011_verifywithInavlidPhoneNumber Successfully executed *************");
	}
	
	@Test(priority = 12,groups= {"master","sanity"})
	public void TC_012_verifywithInvalidEmailId() {
		logger.info("************ TC_012_verifywithInvalidEmailId Started *************");
		Assert.assertEquals(hw.fillformWithInvalidEmail(),false,"The button is Enabled but the email is invalid");
		logger.info("The button is disabled when the email Id is invalid");
		logger.info("************ TC_012_verifywithInvalidEmailId Successfully executed *************");
	}
	
	@Test(priority = 13,groups= {"master","sanity"})
	public void TC_013_verifywithValidInfo() {
		
		logger.info("************ TC_013_verifywithValidInfo Started *************");
		Assert.assertEquals(hw.fillformWithValidData(),true,"The information provided is not valid");
		logger.info("The button is enabled  when entered the valid information");
		logger.info("************ TC_013_verifywithValidInfo Successfully executed *************");
	}
	
	@Test(priority =14,groups= {"master","sanity"})
	public void TC_014_verifyThankYouTxt() throws InterruptedException {
		
		HealthandWellnessPage hw = new HealthandWellnessPage(driver);

		logger.info("************ TC_014_verifyThankYouTxt Started *************");
		Assert.assertEquals(hw.getDisplayedMsg().equalsIgnoreCase("THANK YOU"),true,"Displayed message is not valid");
		logger.info("Verified the ThankYou message displayed");
		logger.info("************ TC_014_verifyThankYouTxt Successfully executed *************");
		
	}
	
	
	
}
