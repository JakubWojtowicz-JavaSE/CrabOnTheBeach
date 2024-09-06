package Main;

import Entity.Player;
import Utilz.Data;
import Utilz.LoadSave;
import audio.AudioPlayer;
import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Game extends JPanel implements Runnable {

    // Window sett
    public static final float SCALE = 1.5f;//1.5
    public static final int DEFAULT_TILE_SIZE = 32;
    public static final int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
    public static final int WINDOW_WIDTH = 10 * TILE_SIZE;
    public static final int WINDOW_HEIGHT = 12 * TILE_SIZE;

    // System
    private int FPS = 60;
    private int UPS = 100;
    private Thread thread;
    public Random random;
    public Data data;
    public EntitySpawner eSpawner;
    public CollisionChecker collisionCh;
    public Listeners listeners;
    private AudioPlayer audioPlayer;
    public UI ui;
    // Entities
    public Player player;

    // state
    private GameStates gameState;

    public Game() {
        initClasses();

        gameState = GameStates.menu;

        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(listeners);
        addMouseListener(listeners);
        addMouseMotionListener(listeners);
    }

    private void initClasses() {
        data = LoadSave.LoadData();

        eSpawner = new EntitySpawner(this);
        collisionCh = new CollisionChecker(this);
        listeners = new Listeners(this);
        audioPlayer = new AudioPlayer(this);
        ui = new UI(this);

        random = new Random();

        player = new Player(this);
    }

    public void reset() {
        eSpawner.setDefVar();
        player.reset(false);
    }

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    public void afterWinClose() {
        player.chcekChanges();
        LoadSave.SaveData(data);
    }

    public void changeGameState(GameStates newState) {
        gameState = newState;
        if (gameState == GameStates.options || gameState == GameStates.pause)
            ui.updateOptionsButtonsVar();
        else if (gameState == GameStates.deathScreen)   // in this case i can use 'else'
            audioPlayer.playEffect(AudioPlayer.GAME_OVER);
    }
    @Override
    public void run() {
        double timeBeetwenUpdate = 1000000000/UPS;
        double timeBeetwenRepaint = 1000000000/FPS;
        double deltaU = 0;
        double deltaR = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        int fps = 0;
        int ups = 0;

        while (thread != null) {
            currentTime = System.nanoTime();

            deltaU += (currentTime - lastTime) / timeBeetwenUpdate;
            deltaR += (currentTime - lastTime) / timeBeetwenRepaint;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
                ups++;
            }
            if (deltaR >= 1){
                repaint();
                deltaR--;
                fps++;
            }

            if (timer >= 1000000000) {
                System.out.println("UPS: " + ups + " FPS: " + fps);
                ups = 0;
                fps = 0;
                timer = 0;
            }
        }
    }

    private void update() {

        if (gameState == GameStates.playing) {
            player.update();
            eSpawner.update();
        }
        ui.update();
    }

    public void paintComponent(Graphics g) {
        ui.draw(g);
        g.dispose();
    }

    public GameStates getGameState() {
        return gameState;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}
