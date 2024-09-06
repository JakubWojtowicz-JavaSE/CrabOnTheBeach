package audio;

import Main.Game;

import javax.sound.sampled.*;
import java.net.URL;
import java.util.Random;

public class AudioPlayer {

    public static int MUSIC_01 = 0;

    public static int GAME_OVER = 0;

    private Game game;
    private Clip[] songs, effects;
    private int currentSongId;
    private float volume = 0.5f;
    private Random rand = new Random();

    public AudioPlayer(Game game) {
        this.game = game;
        volume = game.data.volume;

        loadSongs();
        loadEffects();
        playSong(MUSIC_01);
    }

    private void loadSongs() {
        String[] names = {"Beach_house"};
        songs = new Clip[names.length];
        for (int i = 0; i < songs.length; i++) {
            songs[i] = getCLip(names[i]);
        }
    }

    private void loadEffects() {
        String[] effectNames = {"game_over"};
        effects = new Clip[effectNames.length];
        for (int i = 0; i < effects.length; i++) {
            effects[i] = getCLip(effectNames[i]);
        }

        updateEffectsVolume();
    }

    private Clip getCLip(String name) {
        URL url = getClass().getResource("/audio/" + name + ".wav");
        AudioInputStream audio;

        try {
            audio = AudioSystem.getAudioInputStream(url);
            Clip c = AudioSystem.getClip();
            c.open(audio);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setVolume(float volume) {
        this.volume = volume;
        updateSongVolume();
        updateEffectsVolume();
    }

    public void stopSong() {
        if (songs[currentSongId].isActive())
            songs[currentSongId].stop();
    }

    public void setLevelSong(int lvlIndex) {
//        if ((lvlIndex & 2) == 0)
//            playSong(LEVEL_1);
//        else
//            playSong(LEVEL_2);
    }

    public void lvlCompleted() {
        stopSong();
//        playEffect(LVL_COMPLITED);
    }

    public void playAttackSound() {
        int start = 4;
        start += rand.nextInt(3);
        playEffect(start);
    }

    public void playEffect(int effect) {
        effects[effect].setMicrosecondPosition(0);
        effects[effect].start();
    }

    public void playSong(int song) {
        stopSong();

        currentSongId = song;
        updateSongVolume();
        songs[currentSongId].setMicrosecondPosition(0);
        songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void setSongMute(boolean isMute) {
        for (Clip c : songs) {
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(isMute);
        }
    }

    public void setEffectMute(boolean isMute) {
        for (Clip c : effects) {
            BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(isMute);
        }
    }

    private void updateSongVolume() {
        FloatControl gainControl = (FloatControl) songs[currentSongId].getControl(FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (range * volume) + gainControl.getMinimum();
        gainControl.setValue(gain);
    }

    private void updateEffectsVolume() {
        for (Clip c : effects) {
            FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }
}
