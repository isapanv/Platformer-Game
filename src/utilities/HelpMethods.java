package utilities;

import java.awt.geom.Rectangle2D;

import entities.Player;
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
		//making sure the player is inside visible area
		int maxWidth = lvlData[0].length * Game.TILES_SIZE;
		if (x < 0 || x >= maxWidth) {
			return true;
		}
		if (y < 0 || y >= Game.GAME_HEIGHT) {
			return true;
		}
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;
		return IsTileSolid((int)xIndex,(int)yIndex, lvlData);
	}
	public static boolean IsTileSolid(int tileX, int tileY, int[][] lvlData) {
		int value = lvlData[tileY][tileX];
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
		//check the pixel below bottom left and bottom right
		if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) {
			if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
				return false;
			}
		}
		return true;
	}
	public static boolean IsFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
		return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);	}
	
	
	public static boolean IsWalkable(int xStart, int xEnd, int y, int[][] lvlData) {
		for(int i = 0; i < xEnd - xStart; i++) {
			if(IsTileSolid(xStart + 1, y, lvlData)) {
				return false;
			}
			if (!IsTileSolid(xStart + i, y + 1, lvlData))
				return false;
		}
		return true;
	}
	
	public static boolean IsSightClear(int[][] lvlData,Rectangle2D.Float hitbox1, Rectangle2D.Float hitbox2, int tileY) {
		int tileX1 = (int) hitbox1.x/Game.TILES_SIZE;
		int tileX2 = (int) hitbox2.x/Game.TILES_SIZE;
		
		if(tileX1 > tileX2) {
			return IsWalkable(tileX2, tileX1, tileY, lvlData);
		}
		return IsWalkable(tileX1, tileX2, tileY, lvlData);
	}
}