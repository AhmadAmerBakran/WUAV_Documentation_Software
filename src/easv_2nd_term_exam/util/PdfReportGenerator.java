package easv_2nd_term_exam.util;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import easv_2nd_term_exam.be.Device;
import easv_2nd_term_exam.be.Picture;
import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static easv_2nd_term_exam.util.DialogUtility.showExceptionDialog;

public class PdfReportGenerator {


    private static ModelManagerLoader modelManagerLoader = ModelManagerLoader.getInstance();
    private static ModelManager modelManager = modelManagerLoader.getModelManager();



    public static void generatePdfReport(Report report, Stage primaryStage) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Report");
            fileChooser.setInitialFileName("report_" + report.getInstallationId() + ".pdf");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File outputFile = fileChooser.showSaveDialog(primaryStage);

            if (outputFile != null) {
                try (OutputStream outputStream = new FileOutputStream(outputFile)) {
                    PdfWriter writer = new PdfWriter(outputStream);
                    PdfDocument pdfDocument = new PdfDocument(writer);
                    Document document = new Document(pdfDocument, PageSize.A4);

                    ImageData logoImageData = ImageDataFactory.create(PdfReportGenerator.class.getResource("/easv_2nd_term_exam/gui/views/images_resource/Logo_WUAV.png").toString());
                    com.itextpdf.layout.element.Image logoImage = new com.itextpdf.layout.element.Image(logoImageData).scaleToFit(100, 100);

                    generatePage1(document, report, logoImage);

                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                    generatePage2(document, report, logoImage);

                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                    generateDevicesPage(document, report);

                    document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
                    generatePage3(document, report, logoImage);

                    document.close();
                } catch (IOException e) {
                    showExceptionDialog(e);
                }
            }
        } catch (Exception e) {
            showExceptionDialog(e);
        }
    }


    private static void generatePage1(Document document, Report report, com.itextpdf.layout.element.Image logoImage) {
        addPageHeader(document, report, logoImage);
        addPage1Content(document, report);
    }

    private static void generatePage2(Document document, Report report, com.itextpdf.layout.element.Image logoImage) throws Exception {
        List<Picture> pictures = modelManager.getPictureModel().getPicturesByInstallationId(report.getInstallationId());
        addPage2Content(document, report, pictures);
    }

    private static void generateDevicesPage(Document document, Report report) {
        Paragraph installedDevicesTitle = new Paragraph("Installed Devices:")
                .setFontSize(14)
                .setBold()
                .setTextAlignment(TextAlignment.LEFT);
        document.add(installedDevicesTitle);

        addDevicesTable(document, report);
    }



    private static void addPageHeader(Document document, Report report, com.itextpdf.layout.element.Image logoImage) {
        Table headerTable = new Table(2).useAllAvailableWidth();

        StringBuilder companyInfo = new StringBuilder();
        companyInfo.append("WUAV A/S\n");
        companyInfo.append("info@wuav.dk\n");
        companyInfo.append("Murervej 7,\n");
        companyInfo.append(" 6710 Esbjerg V\n");
        companyInfo.append("CVR: 26855667\n\n");

        Cell logoAndCompanyInfoCell = new Cell();
        logoAndCompanyInfoCell.add(logoImage);
        logoAndCompanyInfoCell.add(new Paragraph(companyInfo.toString()));
        logoAndCompanyInfoCell.setBorder(Border.NO_BORDER);

        headerTable.addCell(logoAndCompanyInfoCell);

        StringBuilder reportInfo = new StringBuilder();
        reportInfo.append("Report ID: ").append(report.getInstallationId()).append("\n");
        reportInfo.append("Created Date: ").append(report.getCreatedDate()).append("\n");
        reportInfo.append("Expiry Date: ").append(report.getExpiryDate()).append("\n");
        reportInfo.append("Customer ID: ").append(report.getCustomerId()).append("\n");
        reportInfo.append("Customer Name: ").append(report.getCustomerName()).append("\n");
        reportInfo.append("Customer Email: ").append(report.getCustomerEmail()).append("\n");
        reportInfo.append("Customer Address: ").append(report.getCustomerAddress());



        Cell reportInfoCell = new Cell()
                .add(new Paragraph(reportInfo.toString()).setTextAlignment(TextAlignment.RIGHT))
                .setBorder(Border.NO_BORDER);

        headerTable.addCell(reportInfoCell);

        document.add(headerTable);
    }





    private static void addPage2Content(Document document, Report report, List<Picture> pictures) {
        // This boolean will check if this is the first time adding pictures
        boolean firstTimeAddingPictures = true;

        for (int i = 0; i < pictures.size(); i += 2) {
            if (firstTimeAddingPictures) {
                firstTimeAddingPictures = false;
            } else {
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }

            Paragraph picturesTitle = new Paragraph("Some pictures of the installation")
                    .setFontSize(14)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(20);

            document.add(picturesTitle);

            byte[] imageData1 = pictures.get(i).getImageData();
            Image pdfImage1 = new Image(ImageDataFactory.create(imageData1))
                    .setWidth(UnitValue.createPercentValue(50))
                    .setAutoScale(true);

            document.add(pdfImage1);

            if (i + 1 < pictures.size()) {
                byte[] imageData2 = pictures.get(i + 1).getImageData();
                Image pdfImage2 = new Image(ImageDataFactory.create(imageData2))
                        .setWidth(UnitValue.createPercentValue(50))
                        .setAutoScale(true);

                document.add(pdfImage2);
            }
        }
    }



    private static void addPage1Content(Document document, Report report) {
        float middleOfPageYPosition = document.getPageEffectiveArea(PageSize.A4).getHeight() / 2 + 120;

        Paragraph installationType = new Paragraph("Installation Type: " + report.getInstallationType())
                .setFontSize(14)
                .setTextAlignment(TextAlignment.CENTER)
                .setFixedPosition(document.getLeftMargin(), middleOfPageYPosition, UnitValue.createPercentValue(100));

        Paragraph preparedBy = new Paragraph("Prepared by: " + report.getTechnicianName() + ", Technician at WUAV")
                .setFontSize(14)
                .setTextAlignment(TextAlignment.CENTER)
                .setFixedPosition(document.getLeftMargin(), middleOfPageYPosition - 20, UnitValue.createPercentValue(100));

        document.add(installationType);
        document.add(preparedBy);

        Paragraph customMessage = new Paragraph("We hope this report provides valuable insights into your installation. Thank you for trusting WUAV!")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER)
                .setFixedPosition(document.getLeftMargin(), middleOfPageYPosition - 60, UnitValue.createPercentValue(100));

        document.add(customMessage);

        float pageContentYPosition = middleOfPageYPosition - 100;

        Paragraph installationSummaryTitle = new Paragraph("Installation Summary")
                .setFontSize(16)
                .setBold()
                .setTextAlignment(TextAlignment.LEFT)
                .setFixedPosition(document.getLeftMargin(), pageContentYPosition, UnitValue.createPercentValue(100));

        document.add(installationSummaryTitle);

        String installationSummaryText = String.format("On %s, WUAV installed a new %s at %s's residence located at %s.",
                report.getCreatedDate(), report.getInstallationType(), report.getCustomerName(), report.getCustomerAddress());

        Paragraph installationSummary = new Paragraph(installationSummaryText)
                .setFontSize(12)
                .setTextAlignment(TextAlignment.LEFT)
                .setFixedPosition(document.getLeftMargin(), pageContentYPosition - 40, UnitValue.createPercentValue(100));

        document.add(installationSummary);

        float additionalInfoYPosition = pageContentYPosition - 120;
        Paragraph additionalInfoParagraph = new Paragraph()
                .add("Additional Info: ")
                .add(new Text(report.getDescription()).setBold())
                .setFontSize(12)
                .setFixedPosition(document.getLeftMargin(), additionalInfoYPosition, UnitValue.createPercentValue(100));
        document.add(additionalInfoParagraph);

        Paragraph deviceInfoParagraph = new Paragraph()
                .setFontSize(12)
                .setFixedPosition(document.getLeftMargin(), additionalInfoYPosition - 40, UnitValue.createPercentValue(100));

        document.add(deviceInfoParagraph);

    }

    private static void generatePage3(Document document, Report report, com.itextpdf.layout.element.Image logoImage) throws IOException {
        addPageHeader(document, report, logoImage);

        ImageData backgroundImageData = ImageDataFactory.create("C:\\Users\\ahmad\\IdeaProjects\\WUAV_Documentation_Software\\src\\easv_2nd_term_exam\\gui\\views\\images_resource\\background.jpg");
        com.itextpdf.layout.element.Image backgroundImage = new Image(backgroundImageData)
                .setWidth(UnitValue.createPercentValue(100))
                .setAutoScale(true)
                .setMarginTop(100);

        document.add(backgroundImage);

        Paragraph thankYouMessage = new Paragraph("Thank you for choosing WUAV for your installation needs. We appreciate your trust in our services.")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER);

        document.add(thankYouMessage);

        String supportText = "The installation project was completed successfully.\n" +
                "We recommend performing periodic maintenance to ensure optimal performance.\n" +
                "If you require any further assistance or support, feel free to contact WUAV at the provided contact information.\n" +
                "Support: info@wuav.dk / +45 7511 9191";

        Paragraph supportInfo = new Paragraph(supportText)
                .setFontSize(12)
                .setTextAlignment(TextAlignment.CENTER);

        document.add(supportInfo);

    }

    private static void addDevicesTable(Document document, Report report) {
        List<Device> devices = report.getDevices();

        if (devices != null && !devices.isEmpty()) {
            float[] columnWidths = {1, 1, 1};
            Table table = new Table(columnWidths);
            table.setWidth(UnitValue.createPercentValue(100));

            table.addHeaderCell(new Cell().add(new Paragraph("Device Name")).setBold());
            table.addHeaderCell(new Cell().add(new Paragraph("Username")).setBold());
            table.addHeaderCell(new Cell().add(new Paragraph("Password")).setBold());

            for (Device device : devices) {
                table.addCell(new Cell().add(new Paragraph(device.getName())));
                table.addCell(new Cell().add(new Paragraph(device.getUsername())));
                table.addCell(new Cell().add(new Paragraph(device.getPassword())));
            }

            document.add(table);
        }
    }


}
