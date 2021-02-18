package com.bits.hr;

import static java.util.stream.Collectors.toMap;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadService2 {

    private final UploadUtil uploadUtil;

    public UploadService2(UploadUtil uploadUtil) {
        this.uploadUtil = uploadUtil;
    }

    public List<String> upload(MultipartFile file) throws Exception {

        Path tempDir = Files.createTempDirectory("");

        File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();

        file.transferTo(tempFile);

            //FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
            List<String> stringList=new ArrayList<String>();

            Workbook workbook = WorkbookFactory.create(tempFile); //new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {

                    Cell currentCell = cellIterator.next();
                    //getCellTypeEnum shown as deprecated for version 3.15
                    //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
                        stringList.add(currentCell.getStringCellValue().toString());
                        //System.out.print(currentCell.getStringCellValue() + "--");
                    }
                    else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                        stringList.add(Double.toString(currentCell.getNumericCellValue()));
                        //System.out.print(currentCell.getNumericCellValue() + "--");
                    }
                    else if (currentCell.getCellTypeEnum() == CellType.BOOLEAN) {
                        stringList.add(Boolean.toString(currentCell.getBooleanCellValue()));
                        //System.out.print(currentCell.getBooleanCellValue() + "--");
                    }
                    else if (currentCell.getCellTypeEnum() == CellType.FORMULA) {
                        stringList.add(currentCell.getCellFormula().toString());
                        //System.out.print(currentCell.getCellFormula() + "--");
                    }

                }
            }
            return stringList;
        }
}
