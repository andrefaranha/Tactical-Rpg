package logic.entity;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteBuilder {

	public static Sprite build(String filepath, int frameWidth, int frameHight) {
		Image[][] frames;
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(filepath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int witdhInSprites = image.getWidth() / frameWidth;
		int heightInSprites = image.getHeight() / frameHight;

		frames = new BufferedImage[heightInSprites][witdhInSprites];

		for (int i = 0; i < heightInSprites; i++)
			for (int j = 0; j < witdhInSprites; j++)
				frames[i][j] = image.getSubimage(j * frameWidth, i * frameHight, frameWidth, frameHight);

		return new Sprite(frames);
	}
}
