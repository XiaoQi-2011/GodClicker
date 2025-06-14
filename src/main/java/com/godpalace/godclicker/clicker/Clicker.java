package com.godpalace.godclicker.clicker;

import com.godpalace.godclicker.Mouse;

public interface Clicker {
    void setMaxCps(int cps);
    void setMinCps(int cps);
    void setBind(String bind);
    int getMaxCps();
    int getMinCps();
    String getBind();

    void start();
    void stop();

    Mouse getMouse();
}
