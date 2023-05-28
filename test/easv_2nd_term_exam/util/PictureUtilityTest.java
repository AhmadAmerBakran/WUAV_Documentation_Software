package easv_2nd_term_exam.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PictureUtilityTest {

    private static final String TEST_IMAGE_PATH = "src/easv_2nd_term_exam/gui/views/images_resource/Logo_WUAV.png";


    @Test
    public void testImageToByteArrayAndBack() {
        // Create a 1x1 pixel image
        WritableImage originalImage = new WritableImage(1, 1);
        originalImage.getPixelWriter().setColor(0, 0, Color.RED);

        // Convert the image to a byte array
        byte[] imageBytes = PictureUtility.imageToByteArray(originalImage);

        // Verify the byte array is not null
        assertNotNull(imageBytes);

        // Convert the byte array back to an image
        Image finalImage = PictureUtility.byteArrayToImage(imageBytes);

        // Verify the final image is not null
        assertNotNull(finalImage);

        // Check that the original and final images are equal
        PixelReader originalReader = originalImage.getPixelReader();
        PixelReader finalReader = finalImage.getPixelReader();
        assertEquals(originalReader.getColor(0, 0), finalReader.getColor(0, 0));
    }

    @Test
    public void testByteArrayToImage() throws IOException {
        // Load the test image file into a byte array
        byte[] imageBytes = Files.readAllBytes(Paths.get(TEST_IMAGE_PATH));

        // Convert the byte array back to an image
        Image image = PictureUtility.byteArrayToImage(imageBytes);

        // Verify the image is not null
        assertNotNull(image);
    }
}
