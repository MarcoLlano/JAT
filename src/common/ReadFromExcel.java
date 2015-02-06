package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author marco llano
 *
 */
public class ReadFromExcel {
	/**
	 * this method read all files in excel file then save it to memory for further use
	 * @param filePath
	 * @param fileName
	 * @param sheetName
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public Object[][] readExcel(String filePath,String fileName,String sheetName) throws IOException {
		File file = new File(filePath + "\\" + fileName);
		FileInputStream inputStream = new FileInputStream(file);
		Workbook dataProviderResource = null;
		String fileExtensionName = fileName.substring(fileName.indexOf("."));
		if(fileExtensionName.equals(".xlsx")){
			dataProviderResource = new XSSFWorkbook(inputStream);
		} else if (fileExtensionName.equals(".xls")) {
			dataProviderResource = new HSSFWorkbook(inputStream);
		}
		Sheet sheet = dataProviderResource.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		Object[][] dataArray = new Object[rowCount+1][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < rowCount + 1; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				dataArray[i][j] = row.getCell(j).getStringCellValue().toString();
			}
		}
		return dataArray;
	}
}
