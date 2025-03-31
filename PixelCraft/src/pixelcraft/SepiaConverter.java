package pixelcraft;

import java.awt.image.BufferedImage;

public class SepiaConverter extends Converter {
	public BufferedImage processImage(BufferedImage oldImage) {
		int height = oldImage.getHeight();
		int width = oldImage.getWidth();
		
		BufferedImage sepiaImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);;
		
		makeImageSepia(oldImage, sepiaImage, 0, 0);
		
		return sepiaImage;
			
	}

	private void makeImageSepia(BufferedImage oldImage, BufferedImage sepiaImage, int x, int y) {
		
		if (x>= oldImage.getWidth()) {
			x=0;
			y++;
		}
		if (y >= oldImage.getHeight()) {
			return;
		}
		
		int colours = oldImage.getRGB(x, y);
		ARGB cool = new ARGB(oldImage.getRGB(x, y));
		
		int redUpdate = holdImage255((int)(0.393 * cool.red + 0.769 * cool.green + 0.189 * cool.blue));
		int greenUpdate = holdImage255((int)(0.349 * cool.red + 0.686 * cool.green + 0.168 * cool.blue));
		int blueUpdate = holdImage255((int)(0.272 * cool.red + 0.534 * cool.green + 0.131 * cool.blue));
		
		
		sepiaImage.setRGB(x,  y, new ARGB(cool.alpha, redUpdate, greenUpdate, blueUpdate).toInt());
		
		makeImageSepia(oldImage, sepiaImage, x+1, y);
	}
	
	private int holdImage255(int hold) {
		return Math.min(255, ((hold>=0)?hold : 0));
	}
	
	
}
