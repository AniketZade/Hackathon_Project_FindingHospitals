package TestCases;

import java.io.IOException;
import java.util.List;

import org.testng.annotations.Test;

import PageObject.PopularSurgeriesPage;
import TestBase.BaseClass;
import Utilities.ExcelUtils;

public class TC_002_PopularSurgeries extends BaseClass{
	
public PopularSurgeriesPage ps;
	
	@Test(priority = 10,groups= {"regression","master"})
	public void  TC_010_displayAllSurgeries() throws IOException {
		logger.info("************ TC_010_displayAllSurgeries Started *************");
		ps = new PopularSurgeriesPage(driver);
		ps.clickSurgery();
		logger.info("Clicked on Surgery btn");
		ps.scrollDown();
		logger.info("Scrolldown till popular surgery list");
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
		logger.info("************ TC_010_displayAllSurgeries Successfully executed *************");
	}
}
