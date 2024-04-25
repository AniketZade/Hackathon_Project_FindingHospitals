package TestCases;

import java.io.FileNotFoundException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.FindDoctorsPage;
import TestBase.BaseClass;
import Utilities.ExcelUtils;

public class TC_001_FindDoctors extends BaseClass {
	
	public FindDoctorsPage fd;
		
	@Test(priority=1,groups= {"sanity","master"})
	public void TC_001_verifyPractoPage() {
		logger.info("************ TC_001_verifyPractoPage Started *************");
		fd = new FindDoctorsPage(driver);
		String url = "https://www.practo.com/";
		Assert.assertEquals(url,fd.getCurrentUrl(),"The link is invalid");
		logger.info("The Practo link has been verified ");
		logger.info(" TC_001_verifyPractoPage Successfully executed ");
	}
	
	@Test(priority=2,groups= {"regression","master"})
	
	public void TC_002_verifyFindDoctorbtn() throws InterruptedException {
		logger.info("************ TC_002_verifyFindDoctorbtn Started *************");
		Assert.assertEquals(fd.findDoctorbtn(), true,"Find Doctor button is not displayed");
		logger.info(" Verifying Find Doctors button has been displayed ");
		Thread.sleep(2000);
		
		fd.clickFindDoctors();
		logger.info(" clicked on  Find Doctors button ");
		logger.info("************ TC_002_verifyFindDoctorbtn Successfully executed *************");
	}
	
	@Test(priority=3,groups= {"regression","master"})
	public void TC_003_searchCity() throws InterruptedException {
		
		logger.info("************ TC_003_dentistPage Started *************");
		fd.searchCity();
		logger.info("Entered the city in the search city field ");
		logger.info("************ TC_003_dentistPage Succesfully executed *************");	
	}
	@Test(priority = 4,groups= {"regression","master"})
	public void TC_004_searchSpecialityDentist() throws InterruptedException {
		logger.info("************ TC_004_searchSpecialityDentist Started *************");
		fd.searchSpeciality();
		logger.info(" Entered the speciality search speciality field");
		String txt = fd.getdentistTxt();
		logger.info(" text containing the dentist stored in  String txt");
		System.out.println(txt);
		Assert.assertEquals(txt.contains("Dentist"),true,"invalid page");
		logger.info(" Dentist page is valid");
		logger.info("************ TC_004_searchSpecialityDentist Successfully executed *************");
	}
	
	@Test(priority=5,groups= {"regression","master"})
	public void TC_005_selectPatientStory() throws InterruptedException {
		logger.info("************ TC_005_selectPatientStory Started *************");
	    fd.selectdrpDownPatientStories();
		Thread.sleep(1000);
		logger.info(" Number of patient story has been select from dropdown");
		logger.info("************ TC_005_selectPatientStory Successfully executed *************");
		
	}
	@Test(priority=6,groups= {"regression","master"})
	public void TC_006_selectExperience() throws InterruptedException {
		logger.info("************ TC_006_selectExperience Started *************");
		fd.selectdrpDownExperience();
		Thread.sleep(1000);
		logger.info(" Doctor experience has been select from dropdown");
		logger.info("************ TC_006_selectExperience Successfully executed *************");
	}
	@Test(priority=7,groups= {"regression","master"})
	public void TC_007_selectFeesandAvailability() throws InterruptedException {
		logger.info("************ TC_007_selectFeesandAvailability Started *************");
		fd.selectFeesFromAllFilters();
		Thread.sleep(1000);
		logger.info(" Fees has been select from dropdown");
		fd.selectAvailabilityFromAllFilters();
		Thread.sleep(1000);
		logger.info(" Availability has been select from dropdown");
		logger.info("************ TC_007_selectFeesandAvailability Successfully executed *************");
	}
	@Test(priority=8,groups= {"regression","master"})
	public void TC_008_selectRelevance() throws InterruptedException {
		logger.info("************ TC_008_selectRelevance Started *************");
		fd.selectRelevance();
		Thread.sleep(1000);
		logger.info(" Relevance has been select from dropdown");
		logger.info("************ TC_008_selectRelevance Successfully executed *************");
	}
	@Test(priority = 9,groups= {"regression","master"})
	public void TC_09_extractDoctorDetails() throws InterruptedException, FileNotFoundException {
		 FindDoctorsPage fd = new FindDoctorsPage(driver);

		
		logger.info("************ TC_09_extractDoctorDetails Started *************");
		Map<String,String> details = fd.extractDoctorsDetails();
		logger.info(" Doctor details has been stored in String details ");
		for(Map.Entry<String,String> entry:details.entrySet()) {
			System.out.println(entry.getKey()+": "+entry.getValue());
		}
		ExcelUtils.saveToExceldoctordetails(details);
		System.out.println("Write successfully");
		logger.info("Doctor's details has been displayed on console");
		logger.info("************ TC_09_extractDoctorDetails Successfully executed *************");
	}
}
