package com.godpalace.godclicker.clicker;

import com.godpalace.godclicker.Mouse;

public interface Clicker {
    void setCps(int cps);
    void setBind(String bind);
    int getCps();
    String getBind();

    void start();
    void stop();

    Mouse getMouse();
}
