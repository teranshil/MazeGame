package game.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class ScaleImage {

	public ScaleImage() {
	}

	public BufferedImage scaleImage(BufferedImage image, int scale) {
		BufferedImage after = new BufferedImage(image.getWidth() * scale, image.getHeight() * scale,
				BufferedImage.TYPE_INT_ARGB);
		AffineTransform at = new AffineTransform();
		at.scale(scale, scale);
		AffineTransformOp atp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = atp.filter(image, after);

		return after;

	}

}
