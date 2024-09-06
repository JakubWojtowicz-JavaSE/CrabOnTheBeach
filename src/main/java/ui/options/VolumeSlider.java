package ui.options;

import Main.Game;
import Utilz.LoadSave;
import ui.UrmButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static Utilz.Constants.VolumeSliderDetails.*;

public class VolumeSlider extends UrmButton {

    private int sliderBgXPos, sliderBgYPos;
    private int minSliderX, maxSliderX;

    private float volume;

    public VolumeSlider(Game game, int sliderX, int sliderY) {
        super(game, sliderX, sliderY, VOLUME_SLIDER_WIDTH, VOLUME_SLIDER_HEIGHT, 0);
        this.sliderBgXPos = sliderX;
        this.sliderBgYPos = sliderY;

        setMinAndMaxXValue();
        if (bounds.x < minSliderX)
            bounds.x = minSliderX;
        else if (bounds.x > maxSliderX)
            bounds.x = maxSliderX;
        setVolume();
    }

    private void setMinAndMaxXValue() {
        minSliderX = sliderBgXPos + (int) (3*Game.SCALE);
        maxSliderX = sliderBgXPos + VOLUME_SLIDER_BG_WIDTH - VOLUME_SLIDER_WIDTH - (int) (3 * Game.SCALE);
    }

    private void setVolume() {
        volume = game.data.volume;
        bounds.x = (int) (minSliderX + (volume * (maxSliderX - minSliderX)));
    }

    public void updateVar() {
        setVolume();
    }

    protected void buttonFun() {}

    protected void loadImgs() {
        imgs = new BufferedImage[4];

        for (int i = 0; i < imgs.length - 1; i++) {
            imgs[i] = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_B_IMG).getSubimage(i*VOLUME_SLIDER_DEFAULT_WIDTH, 0, VOLUME_SLIDER_DEFAULT_WIDTH, VOLUME_SLIDER_DEFAULT_HEIGHT);
        }
        imgs[3] = LoadSave.GetSpriteAtlas(LoadSave.VOLUME_B_IMG).getSubimage(3*VOLUME_SLIDER_DEFAULT_WIDTH, 0, VOLUME_SLIDER_BG_DEFAULT_WIDTH, VOLUME_SLIDER_BG_DEFAULT_HEIGHT);
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[3], sliderBgXPos, sliderBgYPos, VOLUME_SLIDER_BG_WIDTH, VOLUME_SLIDER_BG_HEIGHT, null);

        g.drawImage(imgs[index], bounds.x, bounds.y, bounds.width, bounds.height, null);
    }

    public void mouseDragged(MouseEvent e) {
        if (isPressed) {
            int xBefore = bounds.x;
            bounds.x = e.getX();
            if (bounds.x < minSliderX) {
                bounds.x = minSliderX;
            } if (bounds.x > maxSliderX) {
                bounds.x = maxSliderX;
            }
            if (xBefore != bounds.x) {
                volume = (bounds.x - minSliderX) / (float) (maxSliderX - minSliderX);
                game.data.volume = volume;
                game.getAudioPlayer().setVolume(volume);
            }
        }
    }
}
