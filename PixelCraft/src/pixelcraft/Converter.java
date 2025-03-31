package pixelcraft;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Converter {
	public BufferedImage processImage(BufferedImage image) {
		return image;
	}
	
	public void convert(String fileName, String newFileName) throws IOException {
		 {
			BufferedImage firstImage = ImageIO.read(new File(fileName));
			BufferedImage newerImage = processImage(firstImage);
			ImageIO.write(newerImage, "PNG", new File(newFileName));
		}
	}
}
