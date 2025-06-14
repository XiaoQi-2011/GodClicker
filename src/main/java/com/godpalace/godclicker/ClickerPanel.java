package com.godpalace.godclicker;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@Getter
public class ClickerPanel extends JPanel {

    private int cps;
    private String bind;
    private boolean isOn;
    private final Mouse mouse;

    private final JButton button;

    public ClickerPanel(String text, Mouse mouse) {
        this.mouse = mouse;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setSize(150, 80);

        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel l1 = new JLabel("开启:");
        button = new JButton("Off");
        button.setForeground(Color.RED);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(button, BorderLayout.CENTER);
        panel.add(l1, BorderLayout.WEST);
        add(panel);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel l3 = new JLabel("CPS:");
        JSpinner clickSpinner = new JSpinner(new SpinnerNumberModel(10, 1, 100, 1));
        clickSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(clickSpinner, BorderLayout.CENTER);
        panel.add(l3, BorderLayout.WEST);
        add(panel);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel l2 = new JLabel("绑定:");
        JTextField bindTextField = new JTextField();
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

        clickSpinner.addChangeListener(e -> {
            cps = (int) clickSpinner.getValue();
            Main.getClicker(mouse).setCps(cps);
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
