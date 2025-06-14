package com.godpalace.godclicker;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Getter
public class ClickerPanel extends JPanel {

    private int maxCps;
    private int minCps;
    private String bind;
    private boolean isOn;
    private final Mouse mouse;

    private final JButton button;


    public ClickerPanel(String text, Mouse mouse) {
        this.mouse = mouse;

        maxCps = Main.getClicker(mouse).getMaxCps();
        minCps = Main.getClicker(mouse).getMinCps();
        bind = Main.getClicker(mouse).getBind();
        isOn = Main.getClicker(mouse).isStarted();

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setSize(150, 100);

        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel l1 = new JLabel("开启:");
        button = new JButton(isOn ? "On" : "Off");
        button.setForeground(isOn ? Color.GREEN : Color.RED);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button, BorderLayout.CENTER);
        panel.add(l1, BorderLayout.WEST);
        add(panel);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel l3 = new JLabel("最大CPS:");
        JSpinner clickSpinner1 = new JSpinner(new SpinnerNumberModel(maxCps, 1, 1000, 1));
        clickSpinner1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(clickSpinner1, BorderLayout.CENTER);
        panel.add(l3, BorderLayout.WEST);
        add(panel);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel l4 = new JLabel("最小CPS:");
        JSpinner clickSpinner2 = new JSpinner(new SpinnerNumberModel(minCps, 1, 1000, 1));
        clickSpinner2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(clickSpinner2, BorderLayout.CENTER);
        panel.add(l4, BorderLayout.WEST);
        add(panel);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel l2 = new JLabel("绑定:");
        JTextField bindTextField = new JTextField();
        bindTextField.setText(bind);
        bindTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(bindTextField, BorderLayout.CENTER);
        panel.add(l2, BorderLayout.WEST);
        add(panel);

        //add listener
        button.addActionListener(e -> {
            if (isOn) {
                setOn(false);
                Main.getClicker(mouse).stop();
            } else {
                setOn(true);
                Main.getClicker(mouse).start();
            }

        });

        clickSpinner1.addChangeListener(e -> {
            maxCps = (int) clickSpinner1.getValue();
            if (maxCps < minCps) {
                maxCps = minCps;
                clickSpinner1.setValue(minCps);
            }
            Main.getClicker(mouse).setMaxCps(maxCps);
        });

        clickSpinner2.addChangeListener(e -> {
            minCps = (int) clickSpinner2.getValue();
            if (minCps > maxCps) {
                minCps = maxCps;
                clickSpinner2.setValue(maxCps);
            }
            Main.getClicker(mouse).setMinCps(minCps);
        });

        bindTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                bind = KeyEvent.getKeyText(e.getKeyCode());
                bindTextField.setText(bind);
                Main.getClicker(mouse).setBind(bind);
            }
        });
    }

    public void setOn(boolean on) {
        if (!on) {
            button.setText("Off");
            button.setForeground(Color.RED);
        } else {
            button.setText("On");
            button.setForeground(Color.GREEN);
        }
        isOn = on;
    }
}
