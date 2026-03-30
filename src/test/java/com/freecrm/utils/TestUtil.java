package com.freecrm.utils;


import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class TestUtil {
    public static String TEST_DATA_FILE = System.getProperty("user.dir")+"\\src\\main\\java\\com\\freecrm\\testdata\\freeCrmTestData.xlsx";

    public static Object[][] getTestData(String sheetName) {
        System.out.println("TEST_DATA_FILE :" + TEST_DATA_FILE);
        Object[][] data = null;
        try {
            FileInputStream fisFile = new FileInputStream(TEST_DATA_FILE);
            Workbook book = WorkbookFactory.create(fisFile);
            Sheet sheet = book.getSheet(sheetName);

            int row = sheet.getLastRowNum();
            int col = sheet.getRow(0).getLastCellNum();

            data = new Object[row][col];

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;

    }
}

