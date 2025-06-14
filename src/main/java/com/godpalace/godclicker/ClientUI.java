package com.godpalace.godclicker;

import javax.swing.*;
import java.awt.*;

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
    }
}
