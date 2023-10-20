package utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {
	public static final String PLAYER_ATLAS = "cat.png";
	public static final String LEVEL_ATLAS = "level.png";
	//public static final String LEVEL_ONE_DATA = "levelone.png";
	public static final String LEVEL_ONE_DATA = "level_one_data_long.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU = "menu_background.png";
	public static final String PAUSE = "pause_menu.png";
	public static final String SOUND = "sound_button.png";
	public static final String FUNC = "urm_buttons.png";
	public static final String VOLUME= "volume_buttons.png";
	public static BufferedImage GetSpriteAtlas(String fileName) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	public static int[][] GetLevelData() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		int[][] lvlData = new int[img.getHeight()][img.getWidth()];
		for (int i = 0; i < img.getHeight(); ++i)
			for (int j = 0; j < img.getWidth(); ++j) {
				Color color = new Color(img.getRGB(j, i));
				int value = color.getRed();
				if (value >= 48)
					value = 0;
				lvlData[i][j] = value;
			}
		return lvlData;
	}
}