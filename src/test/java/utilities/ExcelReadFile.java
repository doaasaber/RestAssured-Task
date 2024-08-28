package utilities;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class ExcelReadFile {
    private  XSSFWorkbook workbook;
    private  XSSFSheet sheet;
    private Map<String, Integer> columnsHeaders;


    public ExcelReadFile(String fileName) {
        String filePath = System.getProperty("user.dir") + "/inputs/" + fileName;
        try {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Test file not found" + e.getMessage());
            System.exit(0);
        }
    }

    public String getCellData(String sheetName, int row, String columnName){
        sheet = workbook.getSheet(sheetName);
        getAllHeaderColumnsValues();
        String data = null;
        try {

                data = sheet.getRow(row).getCell(columnsHeaders.get(columnName)).getStringCellValue();

        }

        catch (Exception e) {
            double data2 = sheet.getRow(row).getCell(columnsHeaders.get(columnName)).getNumericCellValue();
            int data3=(int)data2;
            data=Integer.toString(data3);
            System.out.println(e.getMessage());
        }
        data = (data == null) ? "" : data;
        return data;
    }

    public void getAllHeaderColumnsValues(){
        columnsHeaders = new HashMap<>();
        int totalNumOfColumns=sheet.getRow(0).getLastCellNum();
        for(int i=0;i<totalNumOfColumns;i++){
            columnsHeaders.put(sheet.getRow(0).getCell(i).getStringCellValue(), i);
        }


    }
}