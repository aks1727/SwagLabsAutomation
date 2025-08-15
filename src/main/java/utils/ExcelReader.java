package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    // Read all rows
    private static List<Map<String, String>> readExcelData(String filePath, String sheetName) {
        return readExcelData(filePath, sheetName, -1); // -1 means "read all"
    }

    // Overloaded method: read specific row
    private static List<Map<String, String>> readExcelData(String filePath, String sheetName, int rowNum) {
        List<Map<String, String>> dataList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter(); // Keeps Excel formatting

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            Row headerRow = sheet.getRow(0);

            if (rowNum > 0) { // read only one row
                Row currentRow = sheet.getRow(rowNum);
                if (currentRow != null) {
                    Map<String, String> rowMap = new LinkedHashMap<>();
                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                        String key = formatter.formatCellValue(headerRow.getCell(j));
                        Cell cell = currentRow.getCell(j);
                        String value = formatter.formatCellValue(cell);
                        rowMap.put(key, value);
                    }
                    dataList.add(rowMap);
                }
            } else { // read all rows
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row currentRow = sheet.getRow(i);
                    Map<String, String> rowMap = new LinkedHashMap<>();
                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                        String key = formatter.formatCellValue(headerRow.getCell(j));
                        Cell cell = currentRow.getCell(j);
                        String value = formatter.formatCellValue(cell);
                        rowMap.put(key, value);
                    }
                    dataList.add(rowMap);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    // Get all login data
    public static List<Map<String, String>> getLoginData(String sheetName) {
    	String filePath = "src/test/resources/TestData/LoginData.xlsx";
        return readExcelData(filePath, sheetName);
    }

    // Get single row by rowNum (1-based index for Excel row, not list index)
    public static Map<String, String> getLoginData(String sheetName, int rowNum) {
        String filePath = "src/test/resources/TestData/LoginData.xlsx";
        List<Map<String, String>> singleRowList = readExcelData(filePath, sheetName, rowNum);
        return singleRowList.isEmpty() ? null : singleRowList.get(0);
    }
    
    public static List<Map<String, String>> getProductDetails(String sheetName, List<Integer> rows) {
        String filePath = "src/test/resources/TestData/ProductData.xlsx";
        List<Map<String, String>> selectedRows = new ArrayList<>();

        List<Map<String, String>> allData = readExcelData(filePath, sheetName);

        for (Integer rowNum : rows) {
            if (rowNum > 0 && rowNum <= allData.size()) {
                selectedRows.add(allData.get(rowNum - 1)); 
            }
        }

        return selectedRows;
    }

}
