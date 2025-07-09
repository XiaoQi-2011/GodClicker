package com.godpalace.godclicker;

import com.godpalace.godclicker.util.Util;

import javax.swing.*;
import java.awt.*;

public class ClientUI extends JFrame {

    public static ClickerPanel leftPanel;
    public static ClickerPanel rightPanel;
    public ClientUI() {
        setTitle("GodClicker v1.0");
        setBackground(Color.WHITE);
        setSize(300, 250);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - getWidth() / 2,
                Toolkit.getDefaultToolkit().getScreenSize().height / 2 - getHeight() / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        leftPanel = new ClickerPanel("左键连点", Mouse.LEFT);
        rightPanel = new ClickerPanel("右键连点", Mouse.RIGHT);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        add(centerPanel, BorderLayout.CENTER);

        //MenuBar
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("文件");
        JMenuItem item = new JMenuItem("退出");
        item.addActionListener(e -> System.exit(0));
        menu.add(item);
        menuBar.add(menu);

        JMenu menu2 = new JMenu("设置");
        JMenuItem item2 = new JMenuItem("按下时长");
        item2.addActionListener(e -> {
            String value0 = String.valueOf(JOptionPane.showInputDialog("请输入按下时长(ms)", UiSetting.PressTime));
            int value = Util.SpiltCharToInt(value0);
            if (value <= 0) value = UiSetting.PressTime;
            UiSetting.PressTime = value;
            Main.leftClicker.setDelay(value);
            Main.rightClicker.setDelay(value);
        });
        menu2.add(item2);

        JMenuItem item3 = new JMenuItem("绘制状态[开]");
        item3.addActionListener(e -> {
            UiSetting.isPaintEnabled = !UiSetting.isPaintEnabled;
            if (UiSetting.isPaintEnabled) {
                Main.painting.startPainting();
            } else {
                Main.painting.stopPainting();
            }
            item3.setText(UiSetting.isPaintEnabled ? "绘制状态[开]" : "绘制状态[关]");
        });
        menu2.add(item3);
        menuBar.add(menu2);

        setJMenuBar(menuBar);
    }
}
