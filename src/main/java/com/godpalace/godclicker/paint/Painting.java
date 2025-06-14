package com.godpalace.godclicker.paint;

import lombok.Getter;

public class Painting {
    @Getter
    private boolean isPainting = false;
    private int x = 0, y = 10;
    private DesktopGraphics graphics;

    Thread thread = new Thread(() -> {
        while (true) {
            if (isPainting) {
                graphics.drawText("LeftClicker", x, y);
                graphics.drawText("RightClicker", x, y + graphics.getSize() * 2);

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
