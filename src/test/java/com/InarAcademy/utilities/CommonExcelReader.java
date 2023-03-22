package com.InarAcademy.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommonExcelReader {
    public Map<String,String> getDataFromExcel(String testCase,String workbookName,String sheetName) throws IOException {
        HashMap<String,String> excelData=new HashMap<>();
        excelData.put("Test Case :",testCase);
        FileInputStream inputStream=new FileInputStream("src/test/resources/TestData/"+ workbookName+".xlsx");
        Workbook workbook=new XSSFWorkbook(inputStream);
        Sheet sheet=workbook.getSheet(sheetName);
        DataFormatter dataFormatter=new DataFormatter();
        int rowIndexOfTestCase = 0;
        int columnIndexOfTestCase = 0 ;
        for(Row row:sheet){
            for (Cell cell:row){
              if(dataFormatter.formatCellValue(cell).equalsIgnoreCase("TestCase")){
                  rowIndexOfTestCase=row.getRowNum();
                  columnIndexOfTestCase=cell.getColumnIndex();
                  break;
                }
            }
        }
        for( int rowIndex=rowIndexOfTestCase+1; rowIndex<sheet.getLastRowNum();rowIndex++){
            Row currentRow=sheet.getRow(rowIndex);
            Cell testCaseCell =currentRow.getCell(columnIndexOfTestCase);
            if(testCaseCell!=null &&testCaseCell.getStringCellValue().equalsIgnoreCase(testCase)){
                for( int columnIndex=columnIndexOfTestCase+1;columnIndex<currentRow.getLastCellNum();columnIndex++){
                    Cell currentCell=currentRow.getCell(columnIndex);
                    Cell headerCell=sheet.getRow(rowIndexOfTestCase).getCell(columnIndex);
                    if(currentCell != null && headerCell !=null){
                        String key=dataFormatter.formatCellValue(headerCell);
                        String value=dataFormatter.formatCellValue(currentCell);
                        excelData.put(key,value);
                    }
                }
                break;
            }
        }
        inputStream.close();
        workbook.close();

        return excelData;
    }
}
