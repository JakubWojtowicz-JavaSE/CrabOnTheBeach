package ui.options;

import Main.Game;
import Utilz.LoadSave;
import ui.UrmButton;

import java.awt.image.BufferedImage;

import static Utilz.Constants.OptionsButtonsDetails.*;

public class NextMusicButton extends UrmButton {

    public NextMusicButton(Game game, int xPos, int yPos) {
        super(game, xPos, yPos, NAVIGATE_BUTTON_WIDTH, NAVIGATE_BUTTON_HEIGHT, 0);
    }

    protected void buttonFun() {
        game.getAudioPlayer().nextSong();
    }

    protected void loadImgs() {
        imgs = new BufferedImage[3];

        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = LoadSave.GetSpriteAtlas(LoadSave.NEXT_MUSIC_BUTTON).getSubimage(i * NAVIGATE_BUTTON_DEFAULT_WIDTH, rowIndex* NAVIGATE_BUTTON_DEFAULT_HEIGHT, NAVIGATE_BUTTON_DEFAULT_WIDTH, NAVIGATE_BUTTON_DEFAULT_HEIGHT);
        }
    }
}
