package utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
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
	public static final String COMPLETED_IMG = "completed_sprite.png";
	public static final String POTION_ATLAS = "potions_sprites.png";
	public static final String CONTAINER_ATLAS = "objects_sprites.png";
	public static final String TRAP_ATLAS = "trap_atlas.png";

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
	
	public static BufferedImage[] GetAllLevels() {
		URL url = LoadSave.class.getResource("/lvls");
		File file = null;
		try {
			file = new File(url.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File[] files = file.listFiles();
		File[] sorted = new File[files.length];
		for (int i = 0; i < sorted.length; ++i)
			for (int j = 0; j < files.length; ++j) {
				if (files[j].getName().equals((i+1)+".png"))
					sorted[i] = files[j];
			}
//		for (File f: files)
//			System.out.println("file: " + f.getName());
		BufferedImage[] imgs = new BufferedImage[sorted.length];
		for (int i = 0; i < imgs.length; ++i)
			try {
				imgs[i] = ImageIO.read(sorted[i]);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return imgs;
	}
}