package com.godpalace.godclicker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.godpalace.godclicker.clicker.Clicker;
import com.godpalace.godclicker.clicker.LeftClicker;
import com.godpalace.godclicker.clicker.RightClicker;
import com.godpalace.godclicker.paint.Painting;

public class Main {
    public static ClientUI clientUI;
    public static LeftClicker leftClicker;
    public static RightClicker rightClicker;
    public static Painting painting;

    public static Clicker getClicker(Mouse mouse) {
        if (mouse == Mouse.LEFT) {
            return leftClicker;
        }
        else if (mouse == Mouse.RIGHT) {
            return rightClicker;
        }
        else {
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        GlobalScreen.registerNativeHook();

        leftClicker = new LeftClicker();
        rightClicker = new RightClicker();

        painting = new Painting();
        painting.startPainting();

        UiSetting.Init();

        clientUI = new ClientUI();
        clientUI.setVisible(true);
    }
}