package testrunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"C:\\Users\\2303748\\eclipse-workspace\\Hackathon_Project_FindingHospitals\\FeatureFiles\\A_Practo.feature",
					"C:\\Users\\2303748\\eclipse-workspace\\Hackathon_Project_FindingHospitals\\FeatureFiles\\B_findDoctors.feature",
					"C:\\Users\\2303748\\eclipse-workspace\\Hackathon_Project_FindingHospitals\\FeatureFiles\\C_popularSurgeries.feature",
					"C:\\Users\\2303748\\eclipse-workspace\\Hackathon_Project_FindingHospitals\\FeatureFiles\\D_healthAndWellness.feature"},
					
		glue = "StepDefinations",
		plugin= {"pretty", "html:c_reports/myreport.html", 
				  "rerun:target/rerun.txt",
				  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		        },
		monochrome=true,
		dryRun=false,
		publish=true
		)
public class TestRunner {
	
}
