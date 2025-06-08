package com.seyedin.excelimport.util;

import com.seyedin.excelimport.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utility class for handling Excel file operations related to Customer entities.
 */
@Slf4j
public class ExcelHelper {

    /** The MIME type for XLSX Excel files */
    public static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    /** The default sheet name expected inside the Excel file */
    public static final String SHEET_NAME = "Sheet";

    /**
     * Checks if the uploaded file has the correct Excel MIME type.
     *
     * @param file the uploaded MultipartFile
     * @return true if the file is an Excel XLSX file, false otherwise
     */
    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    /**
     * Parses an InputStream of an Excel file and converts it into a list of Customer objects.
     * Assumes the Excel sheet has a header row that will be skipped.
     * Expects the following columns in order:
     * 0: idForm (numeric)
     * 1: firstName (string)
     * 2: lastName (string)
     * 3: email (string)
     * 4: age (numeric)
     * 5: department (string)
     *
     * @param is InputStream of the Excel file
     * @return List of Customer objects parsed from the Excel sheet
     * @throws RuntimeException if parsing fails for any reason
     */
    public static List<Customer> excelToCustomers(InputStream is) {
        try (Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheet(SHEET_NAME);
            Iterator<Row> rows = sheet.iterator();

            List<Customer> customers = new ArrayList<>();
            int rowNumber = 0;

            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // Skip the header row
                if (rowNumber++ == 0) continue;

                // Skip empty or null rows
                if (currentRow == null || currentRow.getCell(0) == null) continue;

                Customer customer = new Customer();

                // Map Excel cells to Customer fields
                customer.setIdForm((int) currentRow.getCell(0).getNumericCellValue());      // ID Form
                customer.setLastName(currentRow.getCell(1).getStringCellValue());          // Last Name
                customer.setFirstName(currentRow.getCell(2).getStringCellValue());         // First Name
                customer.setDepartment(currentRow.getCell(3).getStringCellValue());         // Department
                customer.setAge((int) currentRow.getCell(4).getNumericCellValue());         // Age
                customer.setEmail(currentRow.getCell(5).getStringCellValue());              // Email

                customers.add(customer);
            }

            return customers;

        } catch (Exception e) {
            log.error("Fail to parse Excel file: {}", e.getMessage());
            throw new RuntimeException("Fail to parse Excel file: " + e.getMessage());
        }
    }
}
