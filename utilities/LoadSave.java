package utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.Enemy1;
import main.Game;

import static utilities.Constants.EnemyConsts.ENEMY1;

public class LoadSave {
	/*public static final String PLAYER_ATLAS = "cat.png";
	public static final String LEVEL_ATLAS = "level.png";
	//public static final String LEVEL_ONE_DATA = "levelone.png";
	public static final String LEVEL_ONE_DATA = "level_one_data_long.png";
	public static final String MENU_BUTTONS = "button_atlas.png";
	public static final String MENU = "menu_background.png";
	public static final String PAUSE = "pause_menu.png";
	public static final String SOUND = "sound_button.png";
	public static final String FUNC = "urm_buttons.png";
	public static final String VOLUME= "volume_buttons.png";
	public static final String PLAY_BG = "play_bg.png";
	public static final String BIG_CLOUDS = "big_clouds.png";
	public static final String SMALL_CLOUDS = "small_clouds.png";
	public static final String ENEMY1_SPRITE = "enemy1.png";
	public static final String STATUS_BAR = "health_power_bar.png";
	*/
	public static final String PLAYER_ATLAS = "cat.png";
	public static final String LEVEL_ATLAS = "level.png";
	public static final String LEVEL_ONE_DATA = "level_one_data_long.png";
	public static final String MENU_BUTTONS = "menu_b.png";
	public static final String MENU = "menu.png";
	public static final String PAUSE = "pause.png";
	public static final String SOUND = "sound.png";
	public static final String FUNC = "urm.png";
	public static final String VOLUME= "sound_bar.png";
	public static final String PLAY_BG = "play_bg.png";
	public static final String BIG_CLOUDS = "big_clouds.png";
	public static final String SMALL_CLOUDS = "small_clouds.png";
	public static final String ENEMY1_SPRITE = "enemy1.png";
	public static final String STATUS_BAR = "health_power_bar.png";
	
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
	
	public static ArrayList<Enemy1> GetEnemies() {
		BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
		ArrayList<Enemy1> list = new ArrayList<>();
		for (int j = 0; j < img.getHeight(); j++)
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getGreen();
				if (value == 0) // 0 - ENEMY1
					list.add(new Enemy1(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
			}
		return list;

	}
}