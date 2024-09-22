package Utilz;

import Entity.Entity;
import Main.Game;

public class Constants {

    public static final String CREDITS =
                    "Code: xccevgames@gmail.com\n\n" +

                    "Assets(no copyright):\n" +
                    "   (money, shop and credits imgs are mine) \n" +
                            "           xccevgames@gmail.com\n" +
                    "   hipixelfrog@gmail.com\n" +
                    "   https://pixelfrog-assets.itch.io/\n\n" +

                    "Font(no copyright):\n" +
                    "   https://fontsgeek.com/00249-font\n\n" +

                    "Music(no copyright, while \n" +
                            "       don't redistribute as your own :) ):\n" +
                    "   jhaeka\n" +
                    "   Website: \n" +
                            "           https://joshuuu.itch.io/\n" +
                    "   Shononoki\n" +
                    "   Website: \n" +
                            "        https://shononoki.itch.io/rpg-music-pack-svl\n" +
                    "\n" +
                    "date: 28/08/2024";


    public static int GetHowMSprInRow(String name, int state) {
        switch (name) {
            case "Crabby" -> {
                switch (state) {
                    case Entity.IDLE -> {
                        return CrabbyDetails.SPRITES_IN_IDLE;
                    }
                    case Entity.RUNNING -> {
                        return CrabbyDetails.SPRITES_IN_RUNNING;
                    }
                    case Entity.ATTACK -> {
                        return CrabbyDetails.SPRITES_IN_ATTACK;
                    }
                    case Entity.HIT -> {
                        return CrabbyDetails.SPRITES_IN_HIT;
                    }
                    case Entity.DEAD -> {
                        return CrabbyDetails.SPRITES_IN_DEAD;
                    }
                }
            }
            case "Pinkstar" -> {
                switch (state) {
                    case Entity.IDLE -> {
                        return SkinsDetails.SPRITES_IN_IDLE;
                    }
                    case Entity.RUNNING -> {
                        return SkinsDetails.SPRITES_IN_RUNNING;
                    }
                    case Entity.ATTACK -> {
                        return SkinsDetails.SPRITES_IN_ATTACK;
                    }
                    case Entity.HIT -> {
                        return SkinsDetails.SPRITES_IN_HIT;
                    }
                    case Entity.DEAD -> {
                        return SkinsDetails.SPRITES_IN_DEAD;
                    }
                }
            }
            case "Shark" -> {
                switch (state) {
                    case Entity.IDLE -> {
                        return SkinsDetails.SPRITES_IN_IDLE;
                    }
                    case Entity.RUNNING -> {
                        return SkinsDetails.SPRITES_IN_RUNNING;
                    }
                    case Entity.ATTACK -> {
                        return SkinsDetails.SPRITES_IN_ATTACK;
                    }
                    case Entity.HIT -> {
                        return SkinsDetails.SPRITES_IN_HIT;
                    }
                    case Entity.DEAD -> {
                        return SkinsDetails.SPRITES_IN_DEAD;
                    }
                }
            }
            case "Ball" -> {return 1;}
            case "Potion" -> {return PotionDetails.SPRITES_IN_COL;}
            case "Money" -> {return 1;}
        }
        return 0;
    }

    public static class CrabbyDetails {
        public static final int CRABBY_DEFAULT_WIDTH = 72;
        public static final int CRABBY_DEFAULT_HEIGHT = 32;

        public static final int CRABBY_WIDTH = (int) (CRABBY_DEFAULT_WIDTH * Game.SCALE);
        public static final int CRABBY_HEIGHT = (int) (CRABBY_DEFAULT_HEIGHT * Game.SCALE);

        public static final int SPRITES_IN_ROW = 5;
        public static final int SPRITES_IN_COL = 9;

        public static final int SPRITES_IN_IDLE = 9;
        public static final int SPRITES_IN_RUNNING = 6;
        public static final int SPRITES_IN_ATTACK = 7;
        public static final int SPRITES_IN_HIT = 4;
        public static final int SPRITES_IN_DEAD = 5;
    }

    public static class SkinsDetails {
        public static final int SKIN_DEFAULT_WIDTH = 34;
        public static final int SKIN_DEFAULT_HEIGHT = 30;

        public static final int SKIN_WIDTH = (int) (SKIN_DEFAULT_WIDTH*1.066f * Game.SCALE);
        public static final int SKIN_HEIGHT = (int) (SKIN_DEFAULT_HEIGHT*1.066f * Game.SCALE);//crabby size

        public static final int SPRITES_IN_ROW = 5;
        public static final int SPRITES_IN_COL = 8;

        public static final int SPRITES_IN_IDLE = 8;
        public static final int SPRITES_IN_RUNNING = 6;
        public static final int SPRITES_IN_ATTACK = 8;
        public static final int SPRITES_IN_HIT = 4;
        public static final int SPRITES_IN_DEAD = 5;
    }

    public static class RockDetails {
        public static final int BALL_DEFAULT_WIDTH = 32;
        public static final int BALL_DEFAULT_HEIGHT = 32;

        public static final int BALL_WIDTH = (int) (BALL_DEFAULT_WIDTH * Game.SCALE);
        public static final int BALL_HEIGHT = (int) (BALL_DEFAULT_HEIGHT * Game.SCALE);


    }

    public static class PotionDetails {
        public static final int POTION_DEFAULT_WIDTH = 12;
        public static final int POTION_DEFAULT_HEIGHT = 16;

        public static final int POTION_WIDTH = (int) (POTION_DEFAULT_WIDTH * 2 * Game.SCALE);
        public static final int POTION_HEIGHT = (int) (POTION_DEFAULT_HEIGHT * 2 * Game.SCALE);

        public static final int SPRITES_IN_ROW = 1;
        public static final int SPRITES_IN_COL = 7;
    }

    public static class MenuButtonsDetails {
        public static final int BUTTON_DEFAULT_WIDTH = 140;
        public static final int BUTTON_DEFAULT_HEIGHT = 56;

        public static final int BUTTON_WIDTH = (int) (BUTTON_DEFAULT_WIDTH/1.2f*Game.SCALE);//116.66
        public static final int BUTTON_HEIGHT = (int) (BUTTON_DEFAULT_HEIGHT/1.2f*Game.SCALE);//46.66
    }

    public static class OptionsButtonsDetails {
        public static final int NAVIGATE_BUTTON_DEFAULT_WIDTH = 56;
        public static final int NAVIGATE_BUTTON_DEFAULT_HEIGHT = 56;

        public static final int SOUND_BUTTON_DEFAULT_WIDTH = 42;
        public static final int SOUND_BUTTON_DEFAULT_HEIGHT = 42;

        public static final int NAVIGATE_BUTTON_WIDTH = (int) (NAVIGATE_BUTTON_DEFAULT_WIDTH/1.5f*Game.SCALE);
        public static final int NAVIGATE_BUTTON_HEIGHT = (int) (NAVIGATE_BUTTON_DEFAULT_HEIGHT/1.5f*Game.SCALE);

        public static final int SOUND_BUTTON_WIDTH = (int) (NAVIGATE_BUTTON_DEFAULT_WIDTH/1.75f*Game.SCALE);
        public static final int SOUND_BUTTON_HEIGHT = (int) (NAVIGATE_BUTTON_DEFAULT_HEIGHT/1.75f*Game.SCALE);
    }

    public static class VolumeSliderDetails {
        public static final int VOLUME_SLIDER_DEFAULT_WIDTH = 28;
        public static final int VOLUME_SLIDER_DEFAULT_HEIGHT = 44;
        public static final int VOLUME_SLIDER_BG_DEFAULT_WIDTH = 215;
        public static final int VOLUME_SLIDER_BG_DEFAULT_HEIGHT = 44;

        public static final int VOLUME_SLIDER_WIDTH = (int) (VOLUME_SLIDER_DEFAULT_WIDTH/1.5f*Game.SCALE);
        public static final int VOLUME_SLIDER_HEIGHT = (int) (VOLUME_SLIDER_DEFAULT_HEIGHT/1.5f*Game.SCALE);
        public static final int VOLUME_SLIDER_BG_WIDTH = (int) (VOLUME_SLIDER_BG_DEFAULT_WIDTH/1.5f*Game.SCALE);
        public static final int VOLUME_SLIDER_BG_HEIGHT = (int) (VOLUME_SLIDER_BG_DEFAULT_HEIGHT/1.5f*Game.SCALE);
    }

    public static class DeathScreenDetails {
        public static final int DEATH_S_DEFAULT_WIDTH = 235;
        public static final int DEATH_S_DEFAULT_HEIGHT = 255;

        public static final int DEATH_S_WIDTH = (int) (DEATH_S_DEFAULT_WIDTH/1.2f*Game.SCALE);
        public static final int DEATH_S_HEIGHT = (int) (DEATH_S_DEFAULT_HEIGHT/1.2f*Game.SCALE);
    }

    public static class MoneyDetails {
        public static final int MONEY_DEFAULT_WIDTH = 11;
        public static final int MONEY_DEFAULT_HEIGHT = 11;

        public static final int MONEY_WIDTH = (int) (MONEY_DEFAULT_WIDTH*2.8f*Game.SCALE);
        public static final int MONEY_HEIGHT = (int) (MONEY_DEFAULT_HEIGHT*2.8f*Game.SCALE);
    }
}
