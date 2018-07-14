package gov.jiusan.star.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

/**
 * @author Marcus Lin
 */
public class ExcelUtil {

    private static final DataFormatter formatter = new DataFormatter();

    /**
     * 判断单元格的内容是否为 null 或 ""
     *
     * @param cell
     * @return
     */
    public static boolean isCellStringValueNullOrEmpty(Cell cell) {
        if (cell == null) {
            return true;
        }
        cell.setCellType(CellType.STRING);
        return "".equals(cell.getStringCellValue());
    }

    /**
     * 判断 row 的内容是否为 null 或 ""
     *
     * @param row
     * @return
     */
    public static boolean isRowNullOrEmpty(Row row) {
        if (row == null || row.getLastCellNum() <= 0) {
            return true;
        }
        boolean isEmpty = true;
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            if (!isCellStringValueNullOrEmpty(row.getCell(cellNum))) {
                isEmpty = false;
                break;
            }
        }
        return isEmpty;
    }

    /**
     * 以文本的方式取得 cell 中的数据（可避免从文本格式的 cell 中读数字时的报错问题）
     *
     * @param row
     * @param colIndex
     * @return
     */
    public static String extractStringData(Row row, int colIndex) {
        return formatter.formatCellValue(row.getCell(colIndex));
    }

}
