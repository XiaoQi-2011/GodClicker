package com.godpalace.godclicker;

import com.godpalace.godclicker.util.Util;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ClientUI extends JFrame {

    public static ClickerPanel leftPanel;
    public static ClickerPanel rightPanel;
    public ClientUI() {
        setTitle("GodClicker v1.0");
        setBackground(Color.WHITE);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        leftPanel = new ClickerPanel("左键连点", Mouse.LEFT);
        rightPanel = new ClickerPanel("右键连点", Mouse.RIGHT);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        //MenuBar
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("文件");
        JMenuItem item = new JMenuItem("退出");
        item.addActionListener(_ -> System.exit(0));
        menu.add(item);
        menuBar.add(menu);

        JMenu menu2 = new JMenu("设置");
        JMenuItem item2 = new JMenuItem("按下时长");
        item2.addActionListener(_ -> {
            String value0 = String.valueOf(JOptionPane.showInputDialog("请输入按下时长(ms)", UiSetting.PressTime));
            int value = Util.SpiltCharToInt(value0);
            if (value <= 0) value = UiSetting.PressTime;
            UiSetting.PressTime = value;
        });
        menu2.add(item2);
        menuBar.add(menu2);

        setJMenuBar(menuBar);
    }
}
