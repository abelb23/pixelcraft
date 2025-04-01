
import java.awt.image.BufferedImage;

/**
 * Inverts the colours of the input image.
 * Each pixel's RGB values are reversed by subtracting the current values from 255.
 */
public class InvertColoursConverter extends Converter {

    /**
     * Processes the given image and inverts its colours.
     * The RGB values of each pixel are inverted by subtracting them from 255.
     * 
     * imager The original image to be processed.
     */
    public BufferedImage processImage(BufferedImage imager) {
        int height = imager.getHeight();
        int width = imager.getWidth();
        
        // Create a new image with the same dimensions as the original
        BufferedImage inverted = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        // Iterate over each pixel in the image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get the ARGB values for the current pixel
                ARGB pixel = new ARGB(imager.getRGB(x, y));
                
                // Invert the RGB values by subtracting from 255
                int redInverted = 255 - pixel.red;
                int greenInverted = 255 - pixel.green;
                int blueInverted = 255 - pixel.blue;
                
                // Set the new pixel with the inverted colours
                inverted.setRGB(x, y, new ARGB(pixel.alpha, redInverted, greenInverted, blueInverted).toInt());
            }
        }
        
        // Return the new image with inverted colours
        return inverted;
    }
}
