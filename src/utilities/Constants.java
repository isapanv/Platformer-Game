package utilities;

import main.Game;

public class Constants {
	public static class Environment{
		public static final int BIG_CLOUD_WITDH_DEF = 448;
		public static final int BIG_CLOUD_HEIGHT_DEF = 101;
		public static final int BIG_CLOUD_WITDH = (int)(BIG_CLOUD_WITDH_DEF * Game.SCALE);
		public static final int BIG_CLOUD_HEIGHT = (int)(BIG_CLOUD_HEIGHT_DEF * Game.SCALE);
		public static final int SMALL_CLOUD_WITDH_DEF = 74;
		public static final int SMALL_CLOUD_HEIGHT_DEF = 24;
		public static final int SMALL_CLOUD_WITDH = (int)(SMALL_CLOUD_WITDH_DEF * Game.SCALE);
		public static final int SMALL_CLOUD_HEIGHT = (int)(SMALL_CLOUD_HEIGHT_DEF * Game.SCALE);

	}
	
	
	public static class Directions{
		public static final int LEFT = 0;
		public static final int UP = 1;
		public static final int RIGHT = 2;
		public static final int DOWN = 3;
	}
	public static class  UI{
		public static class Buttons{
			public static final int B_WIDTH_DEF = 140;
			public static final int B_HEIGHT_DEF = 50;
			public static final int B_WIDTH = (int) (B_WIDTH_DEF * Game.SCALE);
			public static final int B_HEIGHT = (int) (B_HEIGHT_DEF * Game.SCALE);

		}
		public static class PauseButtons{
			public static final int SOUND_SIZE_DEF = 42;
			public static final int SOUND_SIZE = (int)(SOUND_SIZE_DEF * Game.SCALE);

		}
		public static class FUNC{
			public static final int FUNC_DEFAULT_SIZE = 56;
			public static final int FUNC_SIZE = (int) (FUNC_DEFAULT_SIZE * Game.SCALE);

		}
		
		public static class VolumeButtons {
			public static final int VOLUME_DEFAULT_WIDTH = 28;
			public static final int VOLUME_DEFAULT_HEIGHT = 44;
			public static final int SLIDER_DEFAULT_WIDTH = 215;

			public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH * Game.SCALE);
			public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT * Game.SCALE);
			public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH * Game.SCALE);
		}
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