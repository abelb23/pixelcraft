package pixelcraft;

import java.awt.image.BufferedImage;

/**
 * Applies a blur effect to an image by averaging the colors of neighboring pixels.
 * Uses a fixed blur size to determine the number of surrounding pixels included in the blur calculation.
 */
public class Blur extends Converter {
    private static int blurSize = 2;

    /**
     * Processes the input image and applies a blur effect.
     * 
     * single is the original image to be blurred.
     * returns A new BufferedImage with the blur effect applied.
     */
    public BufferedImage processImage(BufferedImage single) {
        int width = single.getWidth();
        int height = single.getHeight();
        
        BufferedImage blurredImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        
        for (int i = 0; i < height; i++) {
            for (int a = 0; a < width; a++) {
                blurredImage.setRGB(a, i, calculateBlurredPixel(single, a, i));
            }
        }
        
        return blurredImage;
    }

    /**
     * Computes the blurred pixel value by averaging the colors of neighboring pixels within the blur size.
     * 
     * single is the original image.
     * a is the x-coordinate of the pixel being processed.
     * i is the y-coordinate of the pixel being processed.
     * returns The ARGB value of the blurred pixel.
     */
    private int calculateBlurredPixel(BufferedImage single, int a, int i) {
        int alpha = 0, red = 0, green = 0, blue = 0;
        int counter = 0;
        
        for (int i2 = -blurSize; i2 <= blurSize; i2++) {
            for (int a2 = -blurSize; a2 <= blurSize; a2++) {
                int newX = a + a2;
                int newY = i + i2;
                
                if (newX >= 0 && newX < single.getWidth() && newY >= 0 && newY < single.getHeight()) {
                    ARGB pixel = new ARGB(single.getRGB(newX, newY));
                    alpha += pixel.alpha;
                    red += pixel.red;
                    green += pixel.green;
                    blue += pixel.blue;
                    counter++;
                }
            }
        }
        
        if (counter > 0) {
            return new ARGB(alpha / counter, red / counter, green / counter, blue / counter).toInt();
        } else {
            return single.getRGB(a, i);
        }
    }
}
