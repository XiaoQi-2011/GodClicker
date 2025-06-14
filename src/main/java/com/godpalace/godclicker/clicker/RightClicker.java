package com.godpalace.godclicker.clicker;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import com.github.kwhat.jnativehook.mouse.NativeMouseEvent;
import com.github.kwhat.jnativehook.mouse.NativeMouseListener;
import com.godpalace.godclicker.ClientUI;
import com.godpalace.godclicker.Mouse;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.InputEvent;

public class RightClicker implements Clicker{

    private int clicks = 0;
    private boolean isClicking = false;
    private boolean isStarted = false;
    private Robot robot;
    private final Mouse mouse = Mouse.RIGHT;

    @Setter
    @Getter
    private int cps = 10;

    @Setter
    @Getter
    private String bind = "None";

    @SuppressWarnings("all")
    private final Thread thread = new Thread(() -> {
        while (true) {
            if (isClicking) {
                robot.mousePress(InputEvent.BUTTON3_MASK);
                robot.mouseRelease(InputEvent.BUTTON3_MASK);
            }
            try {
                Thread.sleep(1000 / cps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    private final NativeMouseListener listener = new NativeMouseListener() {
        @Override
        public void nativeMousePressed(NativeMouseEvent e) {
            if (e.getButton() == 2) {
                clicks = 0;
                isClicking = true;
            }
        }

        @Override
        public void nativeMouseReleased(NativeMouseEvent e) {
            if (e.getButton() == 2) {
                clicks ++;
                if (clicks >= 2) {
                    isClicking = false;
                }
            }
        }
    };

    public RightClicker() throws AWTException {
        robot = new Robot();
        robot.setAutoDelay(10);
        thread.start();
        NativeKeyListener keyListener = new NativeKeyListener() {
            @Override
            public void nativeKeyPressed(NativeKeyEvent e) {
                if (NativeKeyEvent.getKeyText(e.getKeyCode()).equals(bind)) {
                    if (isStarted) {
                        ClientUI.rightPanel.setOn(false);
                        stop();
                    } else {
                        ClientUI.rightPanel.setOn(true);
                        start();
                    }
                }
            }
        };
        GlobalScreen.addNativeKeyListener(keyListener);
    }

    @Override
    public void start() {
        isStarted = true;
        GlobalScreen.addNativeMouseListener(listener);
    }

    @Override
    public void stop() {
        isStarted = false;
        GlobalScreen.removeNativeMouseListener(listener);
        isClicking = false;
    }

    @Override
    public Mouse getMouse() {
        return mouse;
    }
}
