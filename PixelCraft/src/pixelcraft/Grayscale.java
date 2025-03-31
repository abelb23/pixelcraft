package pixelcraft;

import java.awt.image.BufferedImage;

public class Grayscale extends Converter {

    @Override
    public BufferedImage processImage(BufferedImage originalImage) {
        if (originalImage == null) {
            throw new IllegalArgumentException("Error: Input image is null. Please check the file path.");
        }

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = originalImage.getRGB(x, y);
                int grayRgb = convertPixelToGray(rgb);
                grayImage.setRGB(x, y, grayRgb);
            }
        }

        return grayImage;
    }

    private int convertPixelToGray(int pixel) {
        ARGB argb = new ARGB(pixel);

        // Convert to grayscale by averaging RGB values
        int gray = (argb.red + argb.green + argb.blue) / 3;

        return new ARGB(argb.alpha, gray, gray, gray).toInt();
    }
}

