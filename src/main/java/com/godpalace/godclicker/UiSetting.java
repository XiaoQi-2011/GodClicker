package com.godpalace.godclicker;

import com.godpalace.data.annotation.Data;
import com.godpalace.data.annotation.LocalDatabase;
import com.godpalace.data.database.FileDatabaseEngine;

@LocalDatabase(path = "config.gc")
public class UiSetting {
    @Data
    public static int PressTime = 2;
    @Data
    public static boolean isPaintEnabled = true;

    @Data
    public static int LeftMaxCps = 15;
    @Data
    public static int RightMaxCps = 15;
    @Data
    public static int LeftMinCps = 10;
    @Data
    public static int RightMinCps = 10;

    @Data
    public static String LeftClickBind = "";
    @Data
    public static String RightClickBind = "";

    public static void Init() {
        try {
            FileDatabaseEngine.init(UiSetting.class, null);
        } catch (Exception e) {
            System.err.println("Failed to initialize database engine.");
            throw new RuntimeException(e);
        }

        Main.leftClicker.setMaxCps(LeftMaxCps);
        Main.leftClicker.setMinCps(LeftMinCps);
        Main.leftClicker.setBind(LeftClickBind);
        Main.leftClicker.setDelay(PressTime);

        Main.rightClicker.setMaxCps(RightMaxCps);
        Main.rightClicker.setMinCps(RightMinCps);
        Main.rightClicker.setBind(RightClickBind);
        Main.rightClicker.setDelay(PressTime);

        if (isPaintEnabled) {
            Main.painting.startPainting();
        } else {
            Main.painting.stopPainting();
        }
    }
}
