package easv_2nd_term_exam.util;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests the methods in the PictureUtility class.
 */
public class PictureUtilityTest {

    /**
     * Tests the imageToByteArray and byteArrayToImage methods in the PictureUtility class.
     * This is done by creating a 1x1 pixel image, converting it to a byte array, and then converting it back to an image.
     * The original and final images are then compared to ensure they are equal.
     */
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
}
