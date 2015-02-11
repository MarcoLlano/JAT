package test;

import org.junit.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import pages.MainBoardPage;
import common.DataProviderClass;

/**
 * Title: Register new applicant-JagdPanther allows create new applicants
 * @author marco llano
 *
 */
public class AddPlayerToProject {
	MainBoardPage mainBoard = new MainBoardPage();
	
	/**
	 * Verify that is able to add players to a project
	 * @param playerEmail
	 * @param playerRole
	 */
	@Test(dataProvider = "players",dataProviderClass = DataProviderClass.class)
	public void createApplicant(String playerEmail,String playerRole) {
		mainBoard.addPlayer(playerEmail, playerRole);
		Assert.assertTrue(mainBoard.getPlayerFromProject(playerEmail).contains(playerEmail));
	}

	@AfterMethod
	public void afterTest() {
		//mainBoard.clickDashboardButton();	//This code line is needed if is the las TC in Test Suite
	}
}
