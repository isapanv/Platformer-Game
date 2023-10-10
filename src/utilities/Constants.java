package utilities;

public class Constants {
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	public static class PlayerConstants{
		public static final int IDLE = 0;
		public static final int WALK = 1;
		public static final int JUMP = 2;
		public static final int SPIN = 3;
		public static final int DEAD = 4;
		public static final int POWER_SHOT = 5;
		public static final int FAST_SHOT = 6;
		
		
		public static int getSpriteAmount(int player_action) {
			switch(player_action) {
			case IDLE:
				return 4;
			case WALK:
				return 8;
			case JUMP:
				return 8;
			case SPIN:
				return 10;
			case DEAD:
				return 7;
			case POWER_SHOT:
				return 5;
			case FAST_SHOT:
				return 6;
			default:
				return 1;
			}
		}
	}
	
	
	
}