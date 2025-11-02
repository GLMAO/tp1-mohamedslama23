package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;
import java.awt.*;

public class HorlogeGUI extends JFrame implements TimerChangeListener {

    private final JLabel labelHeure = new JLabel("", SwingConstants.CENTER);
    private final TimerService timerService;

    public HorlogeGUI(TimerService timerService) {
        this.timerService = timerService;
        timerService.addTimeChangeListener(this);

        // Police et style
        labelHeure.setFont(new Font("Segoe UI", Font.BOLD, 60));
        labelHeure.setForeground(new Color(0, 200, 255)); // Bleu néon
        labelHeure.setBackground(new Color(10, 10, 30)); // Fond sombre
        labelHeure.setOpaque(true);
        labelHeure.setBorder(BorderFactory.createLineBorder(new Color(0, 100, 255), 3));

        // Fenêtre
        add(labelHeure);
        getContentPane().setBackground(new Color(5, 5, 25));
        setTitle("🕓 Horloge Néon Bleue");
        setSize(420, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            SwingUtilities.invokeLater(() -> {
                String heure = String.format("%02d:%02d:%02d",
                        timerService.getHeures(),
                        timerService.getMinutes(),
                        timerService.getSecondes());
                labelHeure.setText(heure);
            });
        }
    }
}

