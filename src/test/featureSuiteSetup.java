package test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
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
@Listeners((common.Screenshot.class))
public class FeatureSuiteSetup {
	public static final String selenium = null;
	String node = "webSite";
	String url = "url";	
	DashboardPage dashboard = new DashboardPage();
	NewProjectPage newProject;
	ReadFromExcelMap xlsFile;	
	String xmlFilePath = (System.getProperty("user.dir") + "\\src\\config\\config.xml");
	public ReadFromXML xmlFile = new ReadFromXML(xmlFilePath);
	String jatURL = xmlFile.read(node, url);
	LoginPage login = new LoginPage(jatURL);

	@BeforeSuite
	public void beforeSuite() throws BiffException, IOException {
		String excelFilepath = (System.getProperty("user.dir") + "\\src\\excelFiles\\dataSource.xls");
		xlsFile = new ReadFromExcelMap();
		String node = "credentials";
		String emailElement = "userEmail";
		String passwordElement = "password";
		login.loginJAT(xmlFile.read(node, emailElement),xmlFile.read(node, passwordElement));
		newProject = dashboard.clickNewProjectButton();
		String sheetName = "Projects";
		List<Map<String, String>> project = xlsFile.readFromExcel(excelFilepath, sheetName);	
		newProject.createNewProject(project.get(0).get("Title"));
	}
	
	@AfterSuite
	public void afterSuite() {	
		dashboard.deleteProject();	
		login.closeBrowser();
	}

}
