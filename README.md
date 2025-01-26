# Invoice Report Generation Project

This project is a Spring-based application for generating dynamic reports, specifically invoices. The application utilizes the **DynamicReports** library to create PDF reports, and it allows for the use of different report formats such as PDF and Excel. The project implements the **Strategy Design Pattern** to provide flexibility in report generation strategies.

## Features

- **PDF Report Generation**: Generate invoices in PDF format using a dynamic report generation strategy.
- **Excel Report Generation (Planned)**: The system can be extended to support other report formats such as Excel by implementing new strategies.
- **Flexible Reporting Strategy**: Uses the Strategy design pattern to switch between different report generation strategies at runtime.
- **Customizable Report Layouts**: Reports are customized with dynamic data and styling (e.g., font styles, colors).
- **Font Support**: Custom fonts, such as symbols (`\u25A1` □ ), are supported in report designs.

## Components

### 1. **InvoiceController**

The `InvoiceController` is responsible for triggering the report generation process. It interacts with the service layer to retrieve the invoice data and delegates the report generation task to the appropriate strategy (e.g., PDF generation).

### Key Methods:
- **`generateInvoiceReport(String reportFormat)`**: Initiates the report generation process based on the specified report format (PDF, Excel, etc.).

### Example Usage:

```java
public static void main(String[] args) {
    // Initialize Spring application context
    org.springframework.context.ApplicationContext context =
            new org.springframework.context.annotation.AnnotationConfigApplicationContext("dz.protid.it");

    // Get the InvoiceController bean from the application context
    InvoiceController controller = context.getBean(InvoiceController.class);

    // Generate the report (you can specify "PDF" or "Excel")
    controller.generateInvoiceReport("PDF");
}
