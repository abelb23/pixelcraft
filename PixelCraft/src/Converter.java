
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Converter {
    
    /**
     * Processes the image in a way that is specific to each converter.
     * Subclasses must implement this method to define their own image processing logic.
     *
     * image is the original image to be processed.
     * returns a new BufferedImage with the processing applied.
     */
    public abstract BufferedImage processImage(BufferedImage image);
    
    /**
     * Converts the input image and saves the processed image with a new filename.
     *
     * fileName is the input image filename.
     * newFileName is the filename for the processed image.
     * Throws IOException If an error occurs during reading or writing the image.
     */
    public void convert(String fileName, String newFileName) throws IOException {
        BufferedImage firstImage = ImageIO.read(new File(fileName));
        BufferedImage newerImage = processImage(firstImage);
        ImageIO.write(newerImage, "PNG", new File(newFileName));
    }
}
