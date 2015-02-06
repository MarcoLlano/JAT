package common;

import org.testng.annotations.DataProvider;
import java.io.IOException;

public class DataProviderClass {
	/**
	 * update filepath to the path you will place the excl file, otherwise will not work
	 */
	private static String filePath = "C:\\Users\\Marco Llano\\workspace\\testJalaAgileTrackerProject\\src\\excelFiles";
	private static String fileName = "players.xlsx";
	private static ReadFromExcel objExcelFile;

	/**
	 * This method return data from the XLSX files to Create programs
	 * @return
	 * @throws IOException
	 */
	@DataProvider(name = "players")
	public static Object[][] programData() throws IOException {
		objExcelFile = new ReadFromExcel();
		return objExcelFile.readExcel(filePath,fileName,"Players");
	}
}
