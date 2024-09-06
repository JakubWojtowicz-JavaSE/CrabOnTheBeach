package Entity;

import Main.Game;
import Main.GameStates;
import Utilz.Constants;
import Utilz.Data;
import Utilz.LoadSave;
import ui.shop.PinkstarSkin;
import ui.shop.SharkSkin;
import ui.shop.SkinToBuy;

import java.awt.*;
import java.awt.image.BufferedImage;

import static Utilz.Constants.CrabbyDetails.*;

public class Player extends Entity {

    private boolean isDead;
    public int score, bestScore, budget;
    private int skinIndex;

    private Direction imgDir; // in which direction should the graphic be drawn

    public Player(Game game) {
        super(game, Type.player,  Game.WINDOW_WIDTH/2-CRABBY_WIDTH/2, 10*Game.TILE_SIZE, CRABBY_WIDTH, CRABBY_HEIGHT, 2.5f, 4, IDLE);
        name = "Crabby";

        score = 0;
        bestScore = game.data.bestScore;
        budget = game.data.budget;
        skinIndex = game.data.skinNum;

        loadSkin(skinIndex);
    }

    private void loadSkin(int index) {
        if (index > -1) // -1 is Crabby, I wanted to be 0, but skins list has beggining from 0 (I don 't want to give Crabby to that list)
            changeSkin(game.ui.getSkin(index));
    }

    public void changeSkin(SkinToBuy skin) {
        name = skin.name;
        posData.setWidth(skin.width);
        posData.setHeight(skin.height);
        posData.setCollSpaces(skin.collLeftSpace, skin.collRightSpace, skin.collTopSpace, skin.collBottomSpace);
        imgs = skin.imgs;

        skinIndex = game.ui.skins.indexOf(skin);
    }

    protected void initCollSpaces() {
        collLeftSpace = (int) (17 * Game.SCALE);
        collRightSpace = (int) (15 * Game.SCALE);
        collTopSpace = (int) (5 * Game.SCALE);
        collBottomSpace = (int) (3 * Game.SCALE);
    }

    protected void loadImgs() {
        imgs = new BufferedImage[SPRITES_IN_ROW][SPRITES_IN_COL];

        for (int i = 0; i < imgs.length; i++) {
            for (int j = 0; j < imgs[0].length; j++) {
                imgs[i][j] = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITES).getSubimage(j*CRABBY_DEFAULT_WIDTH, i*CRABBY_DEFAULT_HEIGHT, CRABBY_DEFAULT_WIDTH, CRABBY_DEFAULT_HEIGHT);
            }
        }
    }

    public void update() {
        int x = posData.getColX();
        int width = posData.getColWidth();

        if (checkIsDead()) {
            if (dir == Direction.left)
                imgDir = Direction.right;
            else if (dir == Direction.right)
                imgDir = Direction.left;
        } else if (!game.collisionCh.checkEnemy(this)) {
            if (isAttacking) {
                state = ATTACK;
            } else {
                if (game.listeners.leftPressed) {
                    imgDir = Direction.left;
                    dir = Direction.left;
                    state = RUNNING;
                } else if (game.listeners.rightPressed) {
                    imgDir = Direction.right;
                    dir = Direction.right;
                    state = RUNNING;
                } else {
                    dir = Direction.idle;
                    state = IDLE;
                }

                switch (dir) {
                    case left -> {
                        x -= speed;
                        if (x < 0)
                            x = 0;
                    }
                    case right -> {
                        x += speed;
                        if (x > Game.WINDOW_WIDTH - width) {
                            x = Game.WINDOW_WIDTH - width;
                        }
                    }
                }
            }
            recovery();

            if (x != posData.getColX())
                posData.setColX(x);
        }
        updateAnim();
    }
    private int recoveryCounter;

    private void recovery() {
        if (speed < maxSpeed) {
            recoveryCounter++;
            if (recoveryCounter >= 900) {
                speed++;
                if (speed > maxSpeed)
                    speed = maxSpeed;
                recoveryCounter = 0;
            }
        }
    }

    public void giveDamage(int damage) {
        state = HIT;
        health -= damage;
        if (health <= 0) {
            animIndex = 0;
            isDead = true;
            chcekChanges();
        }
    }

    private boolean checkIsDead() {
        if (isDead) {
            state = DEAD;
            if (animIndex >= Constants.GetHowMSprInRow(name, state)-1) {
                game.changeGameState(GameStates.deathScreen);
            }
        }
        return isDead;
    }

    public boolean chcekChanges() {
        boolean toSave = false;
        if (score > game.data.bestScore) {
            game.data.bestScore = score;
            toSave = true;
        }
        if (budget != game.data.budget) {
            game.data.budget = budget;
            toSave = true;
        }
        return toSave;
    }

    public void increaseBudget(int i) {
        budget += i;
    }

    public void draw(Graphics g) {
        int x = posData.getXPos();
        int width = posData.getWidth();
        if (imgDir == Direction.right) {
            x += width;
            width = -width;
        }
        g.drawImage(imgs[state][animIndex], x, posData.getYPos(), width, posData.getHeight(), null);
        drawColliders(g);
    }

    public void reset(boolean defSkin) {
        score = 0;
        bestScore = game.data.bestScore;
        isDead = false;

        dir = Direction.idle;
        state = IDLE;
        health = 4;
        maxHealth = health;
        speed = 2.5f;
        maxSpeed = speed;
        posData.changeData(Game.WINDOW_WIDTH/2-Game.TILE_SIZE/2, 10*Game.TILE_SIZE, CRABBY_WIDTH, CRABBY_HEIGHT, collLeftSpace, collRightSpace, collTopSpace, collBottomSpace);
        loadImgs();
        animIndex = 0;
        animCounter = 0;
        isAttacking = false;

        if (!defSkin)
            loadSkin(skinIndex);
        else {
            skinIndex = -1; // crabby
            game.data.skinNum = skinIndex;
        }
    }
}
