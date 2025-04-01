
import java.awt.image.BufferedImage;

public class MirrorImage extends Converter {
    
    // Creates a mirror image by flipping the image horizontally.

    public BufferedImage processImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        
        // Create a new image with the same dimensions and type
        BufferedImage mirroredImage = new BufferedImage(width, height, image.getType());
       
        // Process each pixel to create the mirror effect
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Get RGB value of the pixel from the original image
                int rgb = image.getRGB(x, y);
                
                // Calculate the mirrored x-coordinate (width - 1 - x)
                int mirroredX = width - 1 - x;
                
                // Set the pixel at the mirrored position
                mirroredImage.setRGB(mirroredX, y, rgb);
            }
        }
        
        return mirroredImage;
    }
}