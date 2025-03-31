package pixelcraft;

import java.awt.image.BufferedImage;

public class InvertColoursConverter extends Converter {
	
	public BufferedImage processImage(BufferedImage imager) {
		
		int height = imager.getHeight();
		int width = imager.getWidth();
		
		BufferedImage inverted = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		for (int y = 0; y<height; y++) {
			for (int x = 0; x<width; x++) {
				ARGB newer = new ARGB(imager.getRGB(x,y));
				int re = 255-newer.red;
				int bl = 255-newer.blue;
				int gr = 255-newer.green;
				
				inverted.setRGB(x, y, new ARGB(newer.alpha, re, gr, bl).toInt());
			}
		}
		
		return inverted;
	}

}
