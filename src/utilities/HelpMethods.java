package utilities;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelpMethods {
	public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
		if (!IsSolid(x,y,lvlData))
			if (!IsSolid(x + width, y + height, lvlData))
				if (!IsSolid(x + width, y, lvlData))
					if (!IsSolid(x, y + height, lvlData))
						return true;
		return false;
	}
	public static boolean IsSolid(float x, float y, int[][] lvlData) {
		int maxWidth = lvlData[0].length * Game.TILES_SIZE;
		if (x < 0 || x >= maxWidth)
			return true;
		if (y < 0 || y >= Game.GAME_HEIGHT)
			return true;
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;
		int value = lvlData[(int)yIndex][(int)xIndex];
		if (value >= 48 || value < 0 || value != 11)
			return true;
		return false;
	}
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int)(hitbox.x / Game.TILES_SIZE);
		if (xSpeed > 0) {
			//right
			int TileXPos = currentTile * Game.TILES_SIZE;
			int XOffSet = (int)(Game.TILES_SIZE - hitbox.width);
			return TileXPos + XOffSet - 1;
		} else {
			//left
			return currentTile * Game.TILES_SIZE;
		}
	}
	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
		if (airSpeed > 0) {
			//down
			int TileYPos = currentTile * Game.TILES_SIZE;
			int YOffSet = (int)(Game.TILES_SIZE - hitbox.height);
			return TileYPos + YOffSet - 1;
		} else {
			//up
			return currentTile * Game.TILES_SIZE;
		}
	}
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		//check the pixel below bottomleft and bottomright
		if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) {
			if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
				return false;
			}
		}
		return true;
	}
}