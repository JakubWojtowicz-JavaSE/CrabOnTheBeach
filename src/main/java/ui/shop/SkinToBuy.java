package ui.shop;

import Main.Game;
import Utilz.Constants;
import ui.UrmButton;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class SkinToBuy extends UrmButton {

    public String name;
    public int width, height, collLeftSpace, collRightSpace, collTopSpace, collBottomSpace;
    public BufferedImage imgs[][];

    protected boolean isUnlocked;
    protected int price, state;
    protected int animCounter, animIndex;

    public SkinToBuy(Game game, String name, int xPos, int yPos, int width, int height, int rowIndex) {
        super(game, xPos, yPos, width, height, rowIndex);
        this.name = name;
        setVar();
        initCollSpaces();
    }

    protected void initCollSpaces() {}

    private void setVar() {
        width = bounds.width;
        height = bounds.height;
    }

    protected void buttonFun() {
        if (isUnlocked) {
            setPlayerSkinToThis();
        } else if (game.player.budget >= price) {
            game.player.budget -= price;
            isUnlocked = true;
            setPlayerSkinToThis();
            game.data.unlockSkins.add(game.ui.skins.indexOf(this));
            System.out.println("Congratulations! You buy this skin!");
        } else {
            System.out.println("You don 't have enought money!");
        }
    }

    private void setPlayerSkinToThis() {
        game.data.skinNum = game.ui.skins.indexOf(this);
        game.player.changeSkin(this);
    }

    protected void loadImgs() {}

    public void update() {
        if (isMouseOver) {
            animCounter++;
            if (animCounter >= 10) {
                animIndex++;
                if (animIndex >= Constants.GetHowMSprInRow(name, state)) {
                    animIndex = 0;
                    state++;
                    if (state >= Constants.SkinsDetails.SPRITES_IN_ROW)
                        state = 0;
                }
                animCounter = 0;
            }
        } else {
            state = 0;
            animCounter = 0;
            animIndex = 0;
        }
    }

    public void unlockSkin() {
        isUnlocked = true;
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[state][animIndex], bounds.x, bounds.y, bounds.width, bounds.height, null);
        if (!isUnlocked) {
            g.setColor(new Color(0, 0, 0, 120));
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        } else if (game.data.skinNum == game.ui.skins.indexOf(this)) {
            Graphics2D g2 = (Graphics2D) g;
            g.setColor(new Color(255, 255, 255));
            g2.setStroke(new BasicStroke(2.3f*Game.SCALE));
            g.drawRoundRect(bounds.x-(int) (1*Game.SCALE), bounds.y-(int) (1*Game.SCALE), bounds.width+(int) (1*Game.SCALE), bounds.height+(int) (1*Game.SCALE), (int) (4*Game.SCALE), (int) (4*Game.SCALE));
        }

        game.ui.setFontSize(12*Game.SCALE);
        g.setColor(Color.WHITE);
        int toMinusX = -2 + ((String.valueOf(price).length()-1) * 3);

//        if (price >= 10 && price < 100)
//            toMinusX = 1;
//        else if (price >= 100 && price < 1000)
//            toMinusX = 4;

        g.drawString("Price: " + price, bounds.x - (int) (toMinusX*Game.SCALE), bounds.y + bounds.height + (int) (11*Game.SCALE));
    }
}
