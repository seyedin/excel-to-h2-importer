package com.seyedin.excelimport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExcelImporterApplication {

    private static final Logger log = LoggerFactory.getLogger(ExcelImporterApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ExcelImporterApplication.class, args);
        log.info("\n✅ Application started at: http://localhost:8080/h2-console \n👉 To connect: use JDBC URL → jdbc:h2:mem:excelimportdb\n👉 To view data: run SQL query → SELECT * FROM customers;\n");
    }
}
