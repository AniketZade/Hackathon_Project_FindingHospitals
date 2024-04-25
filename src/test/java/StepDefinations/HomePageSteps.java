package StepDefinations;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import PageObject.FindDoctorsPage;
import PageObject.HealthandWellnessPage;
import PageObject.PopularSurgeriesPage;
import TestBase.BaseClass;
import Utilities.ExcelUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HomePageSteps extends BaseClass{
	
	public  static WebDriver driver;
	public static Logger logger;
	public Properties p;
	PopularSurgeriesPage ps;
	HealthandWellnessPage hw;
	FindDoctorsPage fd;
	public String url;
		
	@Given("User navigate to practo page")
	public void User_navigate_to_practo_page() throws IOException {
		logger = LogManager.getLogger(this.getClass());
		logger.info("Cucumber started");
		logger.info("********* Scenario: Practo page started");
		FileReader file = new FileReader(".//src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		logger.info("Config properties file loaded");
		driver =setupDriver("windows","chrome");	
	}
	
	@When("User get current Url from the Properties file")
	public void User_get_current_Url_from_the_Properties_file() {
		url = p.getProperty("appUrl");
		logger.info("Captured the current Url");
	}
	
	@Then("Verify the Url")
	public void Verify_the_Url() {
		fd = new FindDoctorsPage(driver);
		Assert.assertEquals(url,fd.getCurrentUrl(),"The link is invalid");
		logger.info("The Practo link has been verified");
		logger.info("********* Scenario: Practo page Finished ***********");
		
	}
	
	@Given("User is on practo page")
	public void User_is_on_practo_page() {
		logger.info("********* Scenario: Verify Find Doctors button started **********");
		System.out.println("User is on practo page");
	}
	
	@When("User check if the find doctors is displayed")
	public void User_check_if_the_find_doctors_is_displayed() {
		fd = new FindDoctorsPage(driver);
		Assert.assertEquals(fd.findDoctorbtn(), true,"Find Doctor button is not displayed");
		logger.info(" Verified Find Doctors button has been displayed ");
	}
	
	@Then("if displayed click on find doctors")
	public void if_displayed_click_on_find_doctors() {
		fd.clickFindDoctors();
		logger.info(" clicked on  Find Doctors button ");
		logger.info("********* Scenario: Verify Find Doctors button finished **********");
	}
	
	@Given("User navigate to find Doctors page")
	public void User_navigate_to_find_Doctors_page() throws InterruptedException {
		logger.info("********* Scenario: select city and speciality started **********");
		System.out.println("User is on find doctors page");
		
	}
	@When("User search for city")
	public void User_search_for_city() throws InterruptedException {
		fd = new FindDoctorsPage(driver);
		fd.searchCity();
		logger.info("searched the city");

		
	}
	
	@And("User search for speciality")
	public void User_search_for_speciality() throws InterruptedException {
		fd.searchSpeciality();
		logger.info("selecting speciality ");

		
	}
	
	@Then("User click on speciality")
	public void User_click_on_speciality() {
		String txt = fd.getdentistTxt();
		logger.info(" text containing the dentist stored in  String txt");
		Assert.assertEquals(txt.contains("Dentist"),true,"invalid page");
		logger.info(" Dentist page is valid");
		logger.info("********* Scenario: select city and speciality finished **********");

	}
	
	@Given("User select Patient Story from dropdown")
	public void user_select_patient_story_from_dropdown() throws InterruptedException {
		logger.info("********* Scenario: Filter doctors and extract doctors details started **********");
		fd= new FindDoctorsPage(driver);
		fd.selectdrpDownPatientStories();
		Thread.sleep(1000);
		logger.info(" Number of patient story has been select from dropdown");
	}

	@When("User select Experience from dropdown")
	public void user_select_experience_from_dropdown() throws InterruptedException {
		fd.selectdrpDownExperience();
		Thread.sleep(1000);
		logger.info(" Doctor experience has been select from dropdown");
	  
	}

	@When("User select Fees and Availability")
	public void user_select_fees_and_availability() throws InterruptedException {
		fd.selectFeesFromAllFilters();
		Thread.sleep(1000);
		logger.info(" Fees has been select from dropdown");
		fd.selectAvailabilityFromAllFilters();
		Thread.sleep(1000);
		logger.info(" Availability has been select from dropdown");
	
	}

	@Then("User select Relevance from the dropdown")
	public void user_select_relevance_from_the_dropdown() throws InterruptedException {
		fd.selectRelevance();
		Thread.sleep(1000);
		logger.info(" Relevance has been select from dropdown");
	}
	@And("User display the doctors details")
	public void User_display_the_doctors_details() throws InterruptedException, FileNotFoundException {
		Map<String,String> details = fd.extractDoctorsDetails();
		logger.info(" Doctor details has been stored in String details ");
		for(Map.Entry<String,String> entry:details.entrySet()) {
			System.out.println(entry.getKey()+": "+entry.getValue());
		}
		ExcelUtils.saveToExceldoctordetails(details);
		System.out.println("Write successfully");
		logger.info("Doctor's details has been displayed on console");
		logger.info("********* Scenario: Filter doctors and extract doctors details finished **********");
	}
	
	@Given("User naviagte through Surgeries")
	public void user_naviagte_through_surgeries() {
		logger.info("********* Scenario: Extract Surgeries finished **********");
		ps = new PopularSurgeriesPage(driver);
		ps.clickSurgery();
		logger.info("Clicked on Surgery btn");
	}

	@When("User scroll down till surgeries")
	public void user_scroll_down_till_surgeries() {
		ps.scrollDown();
		logger.info("Scrolldown till popular surgery list");
	}

	@Then("User fetch all the popular surgeries")
	public void user_fetch_all_the_popular_surgeries() throws IOException {
		List<String> surgeries = ps.getAllSurgeries();
		logger.info("Total surgeries stored in list");
		int i=1;
		for(String s:surgeries) {
			System.out.println((i)+"."+s);
			i++;
		}
		ExcelUtils.saveToExcelSurgeriesList("List of Surgeries",surgeries);
		logger.info("Total surgeries displayed on console");
		ps.scrollUp();
		logger.info("scrolling up till for corporate button");
		logger.info("********* Scenario: Extract Surgeries finished **********");
	}
	
	@Given("User click on For Corporate button")
	public void user_click_on_for_corporate_button() {
		logger.info("********* Scenario: verify Schedule a demo button navigation started **********");
		hw = new HealthandWellnessPage(driver);
		hw.clickOnCorporate();
		logger.info("Clicked on forCorporate button");
	}

	@When("click on Health and Wellness button")
	public void click_on_health_and_wellness_button() {
		hw.clickOnHealthAndWellness();
		logger.info("Clicked on Health and Wellness button ");
	}

	@When("User verify schedule button with Invalid Contact Number")
	public void user_verify_schedule_button_with_invalid_contact_number() {
		Assert.assertEquals(hw.fillformWithInvalidNumber(),false,"The button is Enabled but the contact number is invalid");
		logger.info("The button is disabled when the contact number is invalid");
	}

	@When("verify schedule button with with invalid Email Id")
	public void verify_schedule_button_with_with_invalid_email_id() {
		Assert.assertEquals(hw.fillformWithInvalidEmail(),false,"The button is Enabled but the email is invalid");
		logger.info("The button is disabled when the email Id is invalid");
	}

	@When("verify schedule button with with Valid Information")
	public void verify_schedule_button_with_with_valid_information() {
		Assert.assertEquals(hw.fillformWithValidData(),true,"The information provided is not valid");
		logger.info("The button is enabled  when entered the valid information");
	}

	@Then("verify Thankyou message is displayed or not")
	public void verify_thankyou_message_is_displayed_or_not() throws InterruptedException {
		Assert.assertEquals(hw.getDisplayedMsg().equalsIgnoreCase("THANK YOU"),true,"Displayed message is not valid");
		logger.info("Verified the ThankYou message displayed");
		
	}
	
	@And("User quiting the browser")
	public void User_quiting_the_browser() {
		driver.quit();
		logger.info("********* Scenario: verify Schedule a demo button navigation finished **********");
		
	}
}
