package ru.dilibrium.anylogic.launcher.app;

import ru.dilibrium.anylogic.ds54.ob.Optimization;
import ru.dilibrium.anylogic.ds54.ob.Simulation;
import ru.dilibrium.anylogic.launcher.core.Configs;
import ru.dilibrium.anylogic.launcher.core.JavaProcess;
import ru.dilibrium.anylogic.launcher.core.Launcher;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

/**
 * <b>Приложение (интерфейс) выбора и запуска нескольких экспериментов модели</b><br/><br/>
 *
 * @author ООО "Дилибриум"<br/>Техническая поддержка: <a href="mailto:support@dilibrium.ru">support@dilibrium.ru</a>
 * @version 0.0.2
 */
public class Application {

    private final int MAIN_FRAME_WIDTH = 1200;
    private final int MAIN_FRAME_HEIGHT = 780;
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
        mainFrame.setUndecorated(true);

        // Добавляем задний фон
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                Image img = null;
                try {
                    img = ImageIO.read(
                            new File(
                                    Objects.requireNonNull(BACK_IMG_URL).toURI()
                            )
                    );
                } catch (IOException | URISyntaxException e) {
                    mainFrame.getContentPane()
                            .setBackground(
                                    Color.decode(
                                            Objects.requireNonNull(
                                                    Configs.get("colors.background")
                                            )
                                    )
                            );
                    e.printStackTrace();
                }
                super.paintComponent(g);
                g.drawImage(img, 0, 0, mainFrame.getWidth(), mainFrame.getHeight(), null);
            }
        };

        panel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // кнопка запуска простого эксперимента
        JButton simulationBtn = new JButton(Configs.get("simulation.btnTitle"));
        simulationBtn.setSize(300, 100);
        panel.add(simulationBtn);

        // кнопка запуска оптимизационного эксперимента
        JButton optimizationBtn = new JButton(Configs.get("optimization.btnTitle"));
        optimizationBtn.setSize(300, 100);
        panel.add(optimizationBtn);

        // кнопка закрытия приложения
        JButton closeBtn = new JButton("Закрыть");
        closeBtn.setSize(300, 100);
        panel.add(closeBtn);

        mainFrame.add(panel);

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // обработчики нажатия кнопки
        optimizationBtn.addActionListener(e -> new Thread(() -> {
            try {
                Launcher.create(Optimization.class).start();
            } catch (IOException | InterruptedException exception) {
                exception.printStackTrace();
            }
        }).start());

        simulationBtn.addActionListener(e -> new Thread(() -> {
            try {
                Launcher.create(Simulation.class).start();
            } catch (IOException | InterruptedException exception) {
                exception.printStackTrace();
            }
        }).start());

        closeBtn.addActionListener(e -> {
            if (!JavaProcess.getAllOpenProcesses().isEmpty()) {
                System.out.println(JavaProcess.getAllOpenProcesses());
                for (Process process: JavaProcess.getAllOpenProcesses()) {
                    process.destroy();
                }
            }
            System.exit(0);
        });

    }
}
