package Utilz;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

    public int skinNum = -1, bestScore, budget;
    public float volume;
    public ArrayList<Integer> unlockSkins = new ArrayList<>();
    public boolean isMusicOn, isSFXOn;
}
