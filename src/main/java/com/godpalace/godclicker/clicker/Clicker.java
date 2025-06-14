package com.godpalace.godclicker.clicker;

import com.godpalace.godclicker.Mouse;

public interface Clicker {
    void setMaxCps(int cps);
    void setMinCps(int cps);
    int getMaxCps();
    int getMinCps();

    void setBind(String bind);
    String getBind();

    void start();
    void stop();
    boolean isStarted();

    Mouse getMouse();

    void setDelay(int delay);
}
