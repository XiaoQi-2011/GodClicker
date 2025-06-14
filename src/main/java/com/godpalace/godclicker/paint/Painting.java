package com.godpalace.godclicker.paint;

import com.godpalace.gamegl.desktop.DesktopGraphics;
import com.godpalace.godclicker.Main;
import lombok.Getter;

import java.awt.*;

public class Painting {
    @Getter
    private boolean isPainting = false;
    private int x = 0, y = 10;
    private DesktopGraphics graphics;

    Thread thread = new Thread(() -> {
        while (true) {
            if (isPainting) {
                graphics.drawText("LeftClicker   ", x, y);
                graphics.drawText("RightClicker", x, y + 16);

                graphics.setColor(Main.leftClicker.isStarted() ? Color.GREEN : Color.RED);
                graphics.fillRect(x + 79, y, 16, 16);

                graphics.setColor(Main.rightClicker.isStarted() ? Color.GREEN : Color.RED);
                graphics.fillRect(x + 78, y + 16, 16, 16);
            } else {
                graphics.flush();
            }
        }
    });

    public Painting() {
        graphics = DesktopGraphics.getInstance();
        thread.start();
    }

    public void startPainting() {
        isPainting = true;
    }

    public void stopPainting() {
        isPainting = false;
    }
}
