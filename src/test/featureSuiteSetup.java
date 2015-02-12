package test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.DashboardPage;
import pages.LoginPage;
import pages.NewProjectPage;
import utils.ReadFromExcelMap;
import utils.ReadFromXML;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;

/**
 * 
 * @author Marco Llano
 *
 */
public class FeatureSuiteSetup {
	LoginPage login = new LoginPage();
	DashboardPage dashboard = new DashboardPage();
	NewProjectPage newProject;
	ReadFromExcelMap xlsFile;	
	String xmlFilePath = (System.getProperty("user.dir") + "\\src\\xmlFiles\\config.xml");
	public ReadFromXML xmlFile = new ReadFromXML(xmlFilePath);

	@BeforeSuite
	public void beforeSuite() throws BiffException, IOException {
		String excelFilepath = (System.getProperty("user.dir") + "\\src\\excelFiles\\dataSource.xls");
		xlsFile = new ReadFromExcelMap();
		String node = "credentials";
		String emailElement = "userEmail";
		String passwordElement = "password";
		String email = xmlFile.read(node, emailElement);
		String password = xmlFile.read(node, passwordElement);
		login.loginJAT(email,password);
		newProject = dashboard.clickNewProjectButton();
		String sheetName = "Projects";
		List<Map<String, String>> project = xlsFile.readFromExcel(excelFilepath, sheetName);
	
		System.out.print(project.get(0).get("Title"));
		
		newProject.createNewProject(project.get(0).get("Title"));
	}
	@AfterSuite
	public void afterSuite() {	
		dashboard.deleteProject();
		login.logoutJAT();		
	}

}
