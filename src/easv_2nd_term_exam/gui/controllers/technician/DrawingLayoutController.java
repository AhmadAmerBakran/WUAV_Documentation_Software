package easv_2nd_term_exam.gui.controllers.technician;

import easv_2nd_term_exam.be.User;
import easv_2nd_term_exam.gui.controllers.ControllerManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;


public class DrawingLayoutController implements Initializable {

    @FXML
    private Pane canvasPane;

    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ImageView cameraIcon, speakerIcon, screenIcon, monitorIcon, amplifierIcon,
            televisionIcon, projectorIcon, cctvIcon, recieverIcon, microphoneIcon;
    private ImageView draggedIcon;

    @FXML
    private Canvas drawingCanvas;
    private Canvas tempCanvas;
    private GraphicsContext gc;
    private GraphicsContext tempGc;
    private User loggedUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loggedUser = ControllerManager.getInstance().getLoginViewController().getLoggedUser();
        ControllerManager.getInstance().setDrawingLayoutController(this);

        drawingCanvas = new Canvas();
        tempCanvas = new Canvas();
        canvasPane.getChildren().addAll(drawingCanvas, tempCanvas);

        gc = drawingCanvas.getGraphicsContext2D();
        tempGc = tempCanvas.getGraphicsContext2D();
        colorPicker.setValue(Color.BLACK);

        tempCanvas.setOnMousePressed(this::handleMousePressed);
        tempCanvas.setOnMouseDragged(this::handleMouseDragged);
        tempCanvas.setOnMouseReleased(this::handleMouseReleased);

        // Call initializeIcons() in the initialize() method
        initializeIcons();

        // Add listeners to update the canvas size when the pane size changes
        canvasPane.widthProperty().addListener((observable, oldValue, newValue) -> {
            drawingCanvas.setWidth(newValue.doubleValue());
            tempCanvas.setWidth(newValue.doubleValue());
        });

        canvasPane.heightProperty().addListener((observable, oldValue, newValue) -> {
            drawingCanvas.setHeight(newValue.doubleValue());
            tempCanvas.setHeight(newValue.doubleValue());
        });

    }
    @FXML
    private void saveLayout(ActionEvent event) {
        WritableImage image = captureCanvasPaneSnapshot();
        ControllerManager.getInstance().getTechnicianDashboardController().addImage(image);
        ControllerManager.getInstance().getTechnicianDashboardController().displayImage(0);

        closeDrawingLayoutWindow();
    }

    private WritableImage captureCanvasPaneSnapshot() {
        return canvasPane.snapshot(new SnapshotParameters(), null);
    }



    private void closeDrawingLayoutWindow() {
        Stage stage = (Stage) canvasPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cancelDrawing(ActionEvent event) {
        closeDrawingLayoutWindow();
    }



    private enum Shape { LINE, RECTANGLE, CIRCLE, TRIANGLE, NONE }

    private Shape currentShape = Shape.NONE;
    private double startX, startY, endX, endY;
    private Stack<WritableImage> undoStack = new Stack<>();
    private List<ImageView> addedImageViews = new ArrayList<>();





    private void initializeIcons() {
        cameraIcon.setImage(new Image("/easv_2nd_term_exam/gui/views/images_resource/videoCam.png"));
        speakerIcon.setImage(new Image("/easv_2nd_term_exam/gui/views/images_resource/speaker.png"));
        screenIcon.setImage(new Image("/easv_2nd_term_exam/gui/views/images_resource/screen.png"));
        monitorIcon.setImage(new Image("/easv_2nd_term_exam/gui/views/images_resource/monitor.png"));
        amplifierIcon.setImage(new Image("/easv_2nd_term_exam/gui/views/images_resource/amplifier.png"));
        televisionIcon.setImage(new Image("/easv_2nd_term_exam/gui/views/images_resource/television.png"));
        projectorIcon.setImage(new Image("/easv_2nd_term_exam/gui/views/images_resource/projector.png"));
        cctvIcon.setImage(new Image("/easv_2nd_term_exam/gui/views/images_resource/cctv.png"));
        recieverIcon.setImage(new Image("/easv_2nd_term_exam/gui/views/images_resource/reciever.png"));
        microphoneIcon.setImage(new Image("/easv_2nd_term_exam/gui/views/images_resource/microphone.png"));
        setDragDetectionHandler(cameraIcon, "camera");
        setDragDetectionHandler(speakerIcon, "speaker");
        setDragDetectionHandler(screenIcon, "screen");
        setDragDetectionHandler(monitorIcon, "monitor");
        setDragDetectionHandler(amplifierIcon, "amplifier");
        setDragDetectionHandler(televisionIcon, "television");
        setDragDetectionHandler(projectorIcon, "projector");
        setDragDetectionHandler(cctvIcon, "cctv");
        setDragDetectionHandler(recieverIcon, "reciever");
        setDragDetectionHandler(microphoneIcon, "microphone");


        cameraIcon.setOnDragDetected(event -> {
            draggedIcon = cameraIcon;
            Dragboard db = cameraIcon.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString("camera");
            db.setContent(content);
            event.consume();
        });

        speakerIcon.setOnDragDetected(event -> {
            draggedIcon = speakerIcon;
            Dragboard db = speakerIcon.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString("speaker");
            db.setContent(content);
            event.consume();
        });

        screenIcon.setOnDragDetected(event -> {
            draggedIcon = screenIcon;
            Dragboard db = screenIcon.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString("screen");
            db.setContent(content);
            event.consume();
        });

        canvasPane.setOnDragOver(event -> {
            if (event.getGestureSource() != canvasPane &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
            event.consume();
        });

        canvasPane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString()) {
                ImageView iconCopy = new ImageView(draggedIcon.getImage());
                iconCopy.setX(event.getX() - iconCopy.getBoundsInLocal().getWidth() / 2);
                iconCopy.setY(event.getY() - iconCopy.getBoundsInLocal().getHeight() / 2);
                canvasPane.getChildren().add(iconCopy);
                addedImageViews.add(iconCopy);
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });

    }



    @FXML
    private void drawLine() {
        currentShape = Shape.LINE;
    }

    @FXML
    private void drawRectangle() {
        currentShape = Shape.RECTANGLE;
    }

    @FXML
    private void drawCircle() {
        currentShape = Shape.CIRCLE;
    }

    @FXML
    private void drawTriangle() {
        currentShape = Shape.TRIANGLE;
    }

    @FXML
    private void clearCanvas() {
        gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
        canvasPane.getChildren().removeIf(node -> node instanceof ImageView && node != cameraIcon && node != speakerIcon && node != screenIcon);
    }


    private void handleMousePressed(MouseEvent event) {
        WritableImage currentState = new WritableImage((int) drawingCanvas.getWidth(), (int) drawingCanvas.getHeight());
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);
        drawingCanvas.snapshot(params, currentState);
        undoStack.push(currentState);

        startX = event.getX();
        startY = event.getY();
    }


    private void handleMouseDragged(MouseEvent event) {
        endX = event.getX();
        endY = event.getY();

        tempGc.clearRect(0, 0, tempCanvas.getWidth(), tempCanvas.getHeight());
        drawShape(tempGc);
    }

    @FXML
    private void undoAction() {
        if (!undoStack.isEmpty()) {
            gc.clearRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());
            gc.drawImage(undoStack.pop(), 0, 0);

            if (!addedImageViews.isEmpty()) {
                ImageView lastAdded = addedImageViews.remove(addedImageViews.size() - 1);
                canvasPane.getChildren().remove(lastAdded);
            }
        }
    }



    private void handleMouseReleased(MouseEvent event) {
        endX = event.getX();
        endY = event.getY();

        drawShape(gc);
        tempGc.clearRect(0, 0, tempCanvas.getWidth(), tempCanvas.getHeight());
    }

    private void drawShape(GraphicsContext context) {
        context.setStroke(colorPicker.getValue());

        switch (currentShape) {
            case LINE:
                context.strokeLine(startX, startY, endX, endY);
                break;
            case RECTANGLE:
                context.strokeRect(Math.min(startX, endX), Math.min(startY, endY),
                        Math.abs(endX - startX), Math.abs(endY - startY));
                break;
            case CIRCLE:
                double radius = Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
                context.strokeOval(startX - radius, startY - radius, 2 * radius, 2 * radius);
                break;
            case TRIANGLE:
                context.strokePolygon(new double[]{startX, startX + (endX - startX) / 2, endX},
                        new double[]{endY, startY, endY}, 3);
                break;
            case NONE:
                break;
        }
    }

    private void setDragDetectionHandler(ImageView imageView, String imageKey) {
        imageView.setOnDragDetected(event -> {
            draggedIcon = imageView;
            Dragboard db = imageView.startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
            content.putString(imageKey);
            db.setContent(content);
            event.consume();
        });
    }
}
