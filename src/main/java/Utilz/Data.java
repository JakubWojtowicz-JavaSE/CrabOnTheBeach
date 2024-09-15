package Utilz;

import java.io.Serializable;
import java.util.ArrayList;

public class Data implements Serializable {

    public int skinNum = -1, bestTime, budget;
    public float volume = 0.7f;
    public ArrayList<Integer> unlockSkins = new ArrayList<>();
    public boolean firstOpen = true, isMusicOn = true, isSFXOn = true;
}
