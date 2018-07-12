package gov.jiusan.star.batch;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class BatchImportService {

    public void processSeedData(File file) {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(file);
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(0);
    }

    enum SeedDataColumn {
        ORG_NAME(0),
        ORG_CODE(1),
        ORG_PARENT(2),
        IS_ROOT_ORG(3),
        ADMIN_USER_PWD(4);

        private int colIndex;

        SeedDataColumn(int colIndex) {
            this.colIndex = colIndex;
        }

        public int getColIndex() {
            return colIndex;
        }

        public void setColIndex(int colIndex) {
            this.colIndex = colIndex;
        }
    }

}
