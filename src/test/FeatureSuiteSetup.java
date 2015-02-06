package test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import pages.DashboardPage;
import pages.LoginPage;
/**
 * 
 * @author marco llano
 *
 */
public class FeatureSuiteSetup {
	LoginPage login = new LoginPage();
	DashboardPage dashboard = new DashboardPage();

	@BeforeSuite
	public void beforeSuite() {
		String email = "Marco.Llano@fundacion-jala.org";
		String password = "Marco.Llano";
		String title="automationTest";		
		login.loginJAT(email,password);
		dashboard.clickNewProjectButton(title);
	}
	@AfterSuite
	public void afterSuite() {	
		dashboard.deleteProject();
		login.logoutJAT();		
	}

}
