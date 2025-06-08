package com.seyedin.excelimport.controller;

import com.seyedin.excelimport.service.CustomerService;
import com.seyedin.excelimport.util.ExcelHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService customerService;

    /**
     * Endpoint to upload Excel file and import customers into the database.
     *
     * @param file Excel file to be uploaded
     * @return ResponseEntity with success or error message
     */
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (!ExcelHelper.hasExcelFormat(file)) {
            message = "Please upload an Excel file (.xlsx)!";
            log.warn("Invalid file format: {}", file.getContentType());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        try {
            // Parse Excel and save to DB
            int savedCount = customerService.saveAll(ExcelHelper.excelToCustomers(file.getInputStream()));

            message = "Uploaded the file successfully! " + savedCount + " records saved.";
            log.info(message);
            return ResponseEntity.status(HttpStatus.OK).body(message);

        } catch (Exception e) {
            message = "Could not upload the file: " + e.getMessage();
            log.error(message, e);
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
