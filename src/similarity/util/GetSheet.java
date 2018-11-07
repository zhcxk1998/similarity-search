package similarity.util;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author BB小天使
 * @author Yumen
 * @version 1.0
 */
public class GetSheet {
    public static String FILE_PATH = "src\\similarity\\file\\lz01.xls";

    // Get the xls sheet
    public Sheet getSheet() throws Exception {
        InputStream file = new FileInputStream(FILE_PATH);
        Workbook workbook = new HSSFWorkbook(file);
        return workbook.getSheetAt(0);
    }
}
