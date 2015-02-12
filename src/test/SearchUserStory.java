/**
 * 
 */
package test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.MainBoardPage;
import utils.ReadFromExcelMap;

/**
 * @author Marco Llano
 *
 */
public class SearchUserStory {
	MainBoardPage mainBoard = new MainBoardPage();
	SearchUserStory search;
	ReadFromExcelMap xlsFile;
	
	@Test
	public void verifySearchForUserStories() throws BiffException, IOException {
		String filepath = (System.getProperty("user.dir") + "/src/excelFiles/dataSource.xls");
		String sheetName = "UserStories";		
		xlsFile = new ReadFromExcelMap();
		List<Map<String, String>> listOfUserStories = xlsFile.readFromExcel(filepath, sheetName);
		
		//This loop will create a user story from each data of excel file 
		for(Map<String, String> list : listOfUserStories){
			mainBoard.createNewUserStory(list.get("Title"));
			//Verify if New User Story is found
			Assert.assertTrue(mainBoard.searchUserStory(list.get("Title")).contains(list.get("Title")));
		}
	}

	@AfterMethod
	public void afterMethod() {
		mainBoard.refreshPageMethod();
	}
}
