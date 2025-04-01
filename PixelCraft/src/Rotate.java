
import java.awt.image.BufferedImage;

public class Rotate extends Converter {

    
    public BufferedImage processImage(BufferedImage originalImage) {
        int originalWidth = originalImage.getWidth();
        int originalHeight = originalImage.getHeight();

        // For a 90-degree rotation, swap width and height
        BufferedImage rotatedImage = new BufferedImage(originalHeight, originalWidth, originalImage.getType());

        // Start recursive rotation
        rotateImage(originalImage, rotatedImage, 0, 0, originalWidth, originalHeight);

        return rotatedImage;
    }

    // Recursively rotates the image
    private void rotateImage(BufferedImage original, BufferedImage result, int x, int y, int width, int height) {
        // Base case: Stop when we reach the end of the image
        if (y >= height) return;

        if (x < width) {
            int rgb = original.getRGB(x, y);
            
            // Ensure indices are within bounds
            int newX = height - 1 - y;
            int newY = x;
            
            if (newX >= 0 && newX < result.getWidth() && newY >= 0 && newY < result.getHeight()) {
                result.setRGB(newX, newY, rgb);
            }

            // Move to the next pixel in the current row
            rotateImage(original, result, x + 1, y, width, height);
        } else {
            // Move to the next row
            rotateImage(original, result, 0, y + 1, width, height);
        }
    }
}
