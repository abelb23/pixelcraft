
import java.awt.image.BufferedImage;

/**
 * Applies a sepia tone effect to an image using recursion.
 * Each pixel's RGB values are modified based on a standard sepia transformation formula.
 */
public class SepiaConverter extends Converter {
    
    /**
     * Processes the given image and applies a sepia tone effect.
     * 
     * oldImage The original image to be processed.
     * returns A new BufferedImage with the sepia effect applied.
     */
    public BufferedImage processImage(BufferedImage oldImage) {
        int height = oldImage.getHeight();
        int width = oldImage.getWidth();
        
        BufferedImage sepiaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        // Start recursion to apply sepia filter
        applySepiaFilter(oldImage, sepiaImage, 0, 0);
        
        return sepiaImage;
    }
    
    /**
     * Recursively processes each pixel in the image and applies the sepia effect.
     * 
     * oldImage is the original image.
     * sepiaImage is the new image with the sepia effect.
     *  x is the current x-coordinate of the pixel being processed.
     * y is current y-coordinate of the pixel being processed.
     */
    private void applySepiaFilter(BufferedImage oldImage, BufferedImage sepiaImage, int x, int y) {
        if (y >= oldImage.getHeight()) {
            return; // Base case: Stop when all pixels are processed.
        }
        
        if (x >= oldImage.getWidth()) {
            applySepiaFilter(oldImage, sepiaImage, 0, y + 1);
            return;
        }
        
        // Get ARGB values
        ARGB pixel = new ARGB(oldImage.getRGB(x, y));
        
        // Apply sepia formula
        int red = clamp255((int) (0.393 * pixel.red + 0.769 * pixel.green + 0.189 * pixel.blue));
        int green = clamp255((int) (0.349 * pixel.red + 0.686 * pixel.green + 0.168 * pixel.blue));
        int blue = clamp255((int) (0.272 * pixel.red + 0.534 * pixel.green + 0.131 * pixel.blue));
        
        // Set the new pixel colour
        sepiaImage.setRGB(x, y, new ARGB(pixel.alpha, red, green, blue).toInt());
        
        // recursion with the next pixel
        applySepiaFilter(oldImage, sepiaImage, x + 1, y);
    }
    
    /**
     * Ensures that the colour value remains within the valid range [0, 255].
     * 
     * value is the original colour value.
     * returns the adjusted value, ensuring it is within the valid range.
     */
    private int clamp255(int value) {
        return Math.min(255, Math.max(0, value));
    }
}
