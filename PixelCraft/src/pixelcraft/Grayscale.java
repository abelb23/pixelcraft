package pixelcraft;

import java.awt.image.BufferedImage;

/**
 * Converts an image to grayscale by averaging the red, green, and blue values of each pixel.
 * The grayscale value is used to update the red, green, and blue channels of each pixel,
 * creating a monochromatic version of the original image.
 */
public class Grayscale extends Converter {

    /**
     * Processes the given image and converts it to grayscale.
     * 
     * The method loops through each pixel in the image, calculates the average of the 
     * red, green, and blue channels, and sets the pixel's new RGB value to the grayscale value.
     * 
     * originalImage is the image to be processed.
     * Returns a new BufferedImage where each pixel is converted to grayscale.
     * 
     * throws IllegalArgumentException if the provided image is null.
     */
    public BufferedImage processImage(BufferedImage originalImage) {
        if (originalImage == null) {
            throw new IllegalArgumentException("Error: Input image is null. Please check the file path.");
        }

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Iterate through each pixel and convert it to grayscale
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);
                int grayRgb = convertPixelToGray(rgb);
                grayImage.setRGB(x, y, grayRgb);
            }
        }

        return grayImage;
    }

    /**
     * Converts a single pixel to grayscale by averaging its RGB values.
     * 
     * pixel is the integer RGB value of the pixel.
     * Returns the integer ARGB value of the pixel converted to grayscale.
     */
    private int convertPixelToGray(int pixel) {
        ARGB argb = new ARGB(pixel);

        // Convert to grayscale by averaging RGB values
        int gray = (argb.red + argb.green + argb.blue) / 3;

        // Return new ARGB value with grayscale color
        return new ARGB(argb.alpha, gray, gray, gray).toInt();
    }
}
