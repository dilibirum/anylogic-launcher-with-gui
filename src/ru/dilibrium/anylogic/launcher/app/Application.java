package ru.dilibrium.anylogic.launcher.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Objects;

/**
 * <b>Приложение (интерфейс) выбора и запуска нескольких экспериментов модели</b><br/><br/>
 *
 * @author ООО "Дилибриум"<br/>Техническая поддержка: <a href="mailto:support@dilibrium.ru">support@dilibrium.ru</a>
 * @version 0.0.1
 */
public class Application {

    private final int MAIN_FRAME_WIDTH = 1600;
    private final int MAIN_FRAME_HEIGHT = 900;
    private final String TITLE = Configs.load().get("model.title").toString();
    private final URL IMG_URL = Application.class.getResource("/app-icon.png");
    private final URL BACK_IMG_URL = Application.class.getResource("/back-img.png");

    public Application() {

        // Создаем окно приложения
        JFrame mainFrame = new JFrame(TITLE);
        if (IMG_URL != null) {
            mainFrame.setIconImage(new ImageIcon(IMG_URL).getImage());
        }
        mainFrame.setSize(MAIN_FRAME_WIDTH, MAIN_FRAME_HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);

        // Добавляем задний фон
        Icon imgIcon = null;
        if (BACK_IMG_URL != null) {
            imgIcon = new ImageIcon(BACK_IMG_URL);
            JLabel label = new JLabel(imgIcon, SwingConstants.CENTER);
            label.setSize(MAIN_FRAME_WIDTH,MAIN_FRAME_HEIGHT);
            mainFrame.add(label);
        }

        mainFrame.getContentPane()
                 .setBackground(
                         Color.decode(
                                 Objects.requireNonNull(
                                         Configs.get("colors.background")
                                 )
                         )
                 );

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JButton simulationBtn = new JButton(Configs.get("simulation.btnTitle"));
        simulationBtn.setSize(200, 50);

        // обработчик нажатия кнопки
        simulationBtn.addActionListener(e -> {

            new Thread(() -> {
                try {
                    Launcher.of(Experiment.SIMULATION).start();
                } catch (RuntimeException ex) {
                    System.out.println(
                            "WARNING: для запуска простого эксперимента, сначала завершите текущий эксперимент"
                    );
                }
            }).start();

        });

        /*
        JButton optimizationBtn = new JButton(Configs.get("optimization.btnTitle"));
        optimizationBtn.setSize(100, 50);

        // обработчик нажатия кнопки
        optimizationBtn.addActionListener(e -> {

           new Thread(() -> {
                try {
                    Launcher.of(Experiment.OPTIMIZATION).start();
                } catch (RuntimeException ex) {
                    System.out.println(
                            "WARNING: для запуска простого эксперимента, сначала завершите текущий эксперимент"
                    );
                }
            }).start();

        });
        */

        panel.add(simulationBtn);
        //panel.add(optimizationBtn);

        mainFrame.add(panel);

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
/*
    {
        try {
            Launcher.of(Experiment.SIMULATION).start();
        } catch (RuntimeException ex) {
            System.out.println(
                    "WARNING: для запуска простого эксперимента, сначала завершите текущий эксперимент"
            );
        }

        try {
            Launcher.of(Experiment.OPTIMIZATION).start();
        } catch (RuntimeException ex) {
            System.out.println(
                    "WARNING: для запуска оптимизационного эксперимента, сначала завершите текущий эксперимент"
            );
        }
    }*/
}
