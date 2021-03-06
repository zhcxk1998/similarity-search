package similarity.algorithm;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import similarity.Algorithm;
import similarity.util.GetSheet;

/**
 * @author BB小天使
 * @author Yumen
 * @version 1.0
 */
public class PearsonSimilarity implements Algorithm {
    private ArrayList<String> name = new ArrayList<String>();
    private ArrayList<Double> data = new ArrayList<Double>();

    /**
     * This method process the Pearson algorithm
     * @param info The string of display data
     * @param max_info The result of the most similar
     * @return a list of process infomation
     * @throws Exception
     */
    public String[] getInfo() throws Exception {
        // Get the xlsx
        GetSheet getSheet = new GetSheet();
        Sheet sheet = getSheet.getSheet();

        // Get the row and col of this sheet
        Row sheetRow = sheet.getRow(0);
        int rowNum = sheet.getLastRowNum();
        int colNum = sheetRow.getLastCellNum();

        String info="";
        double multi, sumA, sumB, A, B, sum;
        for (int col = 2; col < colNum; col++) {
            //Get date;
            info += String.format("%s\t  ", sheet.getRow(0).getCell(col).toString() + " ");
            name.add(sheet.getRow(0).getCell(col).toString());
        }
        info += "\n";
        double[] sorce_data = new double[19], sort_data = new double[19];
        int index = 0;
        double[] average = new double[colNum];
        for (int col = 1; col < colNum; col++) {
            sum = 0;
            for (int row = 1; row < rowNum; row++) {
                sum += sheet.getRow(row).getCell(col).getNumericCellValue();
            }
            // Because the first row is the structure name
            average[col] = sum / (rowNum - 1);
        }
        for (int col = 2; col < colNum; col++) {
            multi = sumA = sumB = 0;
            for (int row = 1; row < rowNum; row++) {
                A = sheet.getRow(row).getCell(1).getNumericCellValue();
                B = sheet.getRow(row).getCell(col).getNumericCellValue();
                multi += (A - average[1]) * (B - average[col]);
                sumA += Math.pow(A - average[1], 2);
                sumB += Math.pow(B - average[col], 2);
            }
            //Get date;
            info += String.format("%.2f%%\t  ", (multi / Math.sqrt(sumA * sumB)) * 100);
            data.add((multi / Math.sqrt(sumA * sumB)) * 100);
            sorce_data[index] = (multi / Math.sqrt(sumA * sumB)) * 100;
            sort_data[index++] = (multi / Math.sqrt(sumA * sumB)) * 100;
        }
        String[] result = new String[2];
        result[0] = info;
        info = "";
        Arrays.sort(sort_data);
        int max_index = 0;
        for (int i = 0; i < 19; i++) {
            if (sorce_data[i] == sort_data[18]) {
                max_index = i;
                break;
            }
        }
        String max_info = "最相似的是：" + name.get(max_index) +
                "\t" + String.format("%.2f%%", sorce_data[max_index]);
        result[1] = max_info;
        return result;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public ArrayList<Double> getData() {
        return data;
    }
}