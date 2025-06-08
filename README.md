# Excel to H2 Importer

A Spring Boot application that allows importing customer data from Excel (.xlsx) files into an in-memory H2 database.  
This project demonstrates how to:

- Upload and parse Excel files using Apache POI
- Validate data with Jakarta Bean Validation
- Prevent duplicates based on unique fields
- Log processing details with SLF4J
- Use Spring Data JPA with H2 database
- Build REST APIs with Spring Boot

## Features

- Upload `.xlsx` Excel files containing customer data  
- Automatic parsing and validation of Excel rows  
- Save valid, non-duplicate customers to H2 database  
- View all saved customers via REST endpoint  
- Detailed logging for saved and skipped records  
- In-memory database for easy setup and reset  

## Getting Started

### Prerequisites

- Java 21 or higher  
- Maven 3.6+  

### Build and Run

1. Clone the repository:

```bash
git clone https://github.com/your-username/excel-to-h2-importer.git
cd excel-to-h2-importer
```

2. Build the project:
- mvn clean install

3. Run the application:  
- mvn spring-boot:run

4. Access H2 Console for database inspection (optional):
- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:excelimportdb
- User: sa
- Password: (leave empty)

## Usage
- Use POST /api/customers/upload to upload an Excel file
- Use GET /api/customers to list all customers

## Excel File Format
- The Excel file must be .xlsx with a sheet named Sheet containing columns:  
- | ID Form | Last Name | First Name | Department | Age | Email |  
- The first row should be headers and will be skipped during import.

## License
This project is open source and available under the MIT License.
