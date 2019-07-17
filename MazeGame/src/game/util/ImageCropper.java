package game.util;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

public class ImageCropper {
	public static final ImageCropper imageCropper = new ImageCropper();

	public ImageCropper() {
	}

	public BufferedImage cropper(String path, int startX, int startY, Dimension dimension) {
		Optional<BufferedImage> tempImage = readImage(new File(path));
		if (tempImage.isPresent())
			return tempImage.get().getSubimage(startX, startY, (int) dimension.getWidth(), (int) dimension.getHeight());
		else
			return null;
	}

	private Optional<BufferedImage> readImage(File file) {
		try {
			return Optional.of(ImageIO.read(file));
		} catch (IOException e) {
			System.out.println("Incorrect image file path");
		}
		return Optional.empty();
	}
}
