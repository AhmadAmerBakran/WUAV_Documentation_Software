package easv_2nd_term_exam.util;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import easv_2nd_term_exam.be.Picture;
import easv_2nd_term_exam.be.Report;
import easv_2nd_term_exam.gui.models.ModelManager;
import easv_2nd_term_exam.gui.models.ModelManagerLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.List;
import java.util.Optional;

public class AppUtility {


    private static ModelManagerLoader modelManagerLoader = ModelManagerLoader.getInstance();
    private static ModelManager modelManager = modelManagerLoader.getModelManager();


    public static void showExceptionDialog(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("An exception occurred:");
        alert.setContentText(ex.getMessage());

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }

    public static boolean showConfirmationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    public static void showInformationDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean showDeviceFieldsReminder() {
        String message = "Device Username and/or Password fields are empty. Do you want to continue without filling in these fields?";
        return showConfirmationDialog(message);
    }

    public static void generatePdfReport(Report report, Stage primaryStage) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Report");
            fileChooser.setInitialFileName("report_" + report.getInstallationId() + ".pdf");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File outputFile = fileChooser.showSaveDialog(primaryStage);

            if (outputFile != null) {
                OutputStream outputStream = new FileOutputStream(outputFile);
                PdfWriter writer = new PdfWriter(outputStream);
                PdfDocument pdfDocument = new PdfDocument(writer);
                Document document = new Document(pdfDocument, PageSize.A4);

                // Title
                Paragraph title = new Paragraph("Report: " + report.getInstallationId())
                        .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                        .setFontSize(18)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBold();
                document.add(title);

                // Add report details (e.g., customerName, customerEmail, customerAddress, etc.)
                document.add(new Paragraph("Customer Name: " + report.getCustomerName()));
                document.add(new Paragraph("Customer Email: " + report.getCustomerEmail()));
                document.add(new Paragraph("Customer Address: " + report.getCustomerAddress()));
                document.add(new AreaBreak());
                document.add(new Paragraph("Installation Description :" + report.getDescription()));

                // Add other report details
                // ...

                // Add images (e.g., diagramImage, uploadedImage)
                List<Picture> pictures = modelManager.getPictureModel().getPicturesByInstallationId(report.getInstallationId());
                for (Picture picture : pictures) {
                    byte[] imageData = picture.getImageData();
                    PdfImageXObject pdfImageXObject = new PdfImageXObject(ImageDataFactory.create(imageData));
                    float width = pdfDocument.getDefaultPageSize().getWidth() - document.getLeftMargin() - document.getRightMargin();
                    float height = (pdfImageXObject.getHeight() * width) / pdfImageXObject.getWidth();

                    com.itextpdf.layout.element.Image pdfImage = new com.itextpdf.layout.element.Image(
                            ImageDataFactory.create(picture.getImageData()))
                            .setWidth(width)
                            .setHeight(height)
                            .setAutoScale(true);
                    document.add(pdfImage);
                }

                // Close document
                document.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
