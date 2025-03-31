package pixelcraft;

import java.awt.image.BufferedImage;

public class Blur extends Converter {
	private static int blurSize = 2;
	
	public BufferedImage processImage(BufferedImage single) {
		int width = single.getWidth();
		int height = single.getHeight();
		
		BufferedImage blurredv1 = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
		
		for (int i = 0; i<height; i++) {
			for (int a = 0; a<width; a++) {
				blurredv1.setRGB(a,  i,  calculateBlurredPixel(single, a, i));
			}
		}
		
		return blurredv1;
	}

	private int calculateBlurredPixel(BufferedImage single, int a, int i) {
		int alp = 0, red = 0, gre = 0, blu = 0;
		int counter = 0;
		
		for (int i2 = -blurSize; i2 <= blurSize; i2++) {
			for (int a2 = -blurSize; a2 <= blurSize; a2++) {
				int na = a + a2;
				int ni = i + i2;
				
				if (na >= 0 && na < single.getWidth() && ni >= 0 && ni < single.getHeight()) {
					ARGB order = new ARGB(single.getRGB(na,  ni));
					alp += order.alpha;
					red += order.red;
					gre += order.green;
					blu += order.blue;
					counter++;
				}
			}
		}
		
		if (counter > 0) {
		return new ARGB(alp/counter, red/counter, gre/counter, blu/counter).toInt();
		}
		
		else {
			return single.getRGB(a, i);
		}
		}
	}

