package test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.DashboardPage;
import pages.LoginPage;
import pages.NewProjectPage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import jxl.read.biff.BiffException;
import common.ReadFromExcelMap;

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

	@BeforeSuite
	public void beforeSuite() throws BiffException, IOException {
		String email = "Marco.Llano@fundacion-jala.org";
		String password = "Marco.Llano";
		login.loginJAT(email,password);
		newProject = dashboard.clickNewProjectButton();
		
		String filepath = (System.getProperty("user.dir") + "/src/excelFiles/dataSource.xls");
		String sheetName = "Projects";		
		xlsFile = new ReadFromExcelMap();
		List<Map<String, String>> project = xlsFile.readFromExcel(filepath, sheetName);
	
		System.out.print(project.get(0).get("Title"));
		
		newProject.createNewProject(project.get(0).get("Title"));
	}
	@AfterSuite
	public void afterSuite() {	
		dashboard.deleteProject();
		login.logoutJAT();		
	}

}
