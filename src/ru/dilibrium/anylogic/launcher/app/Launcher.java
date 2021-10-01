package ru.dilibrium.anylogic.launcher.app;

import ru.dilibrium.anylogic.launcher.core.JavaProcess;
import java.io.IOException;

/**
 * <b>Загрузчик экспериментов модели</b><br/><br/>
 *
 * <br/>
 * @author ООО "Дилибриум"<br/>Техническая поддержка: <a href="mailto:support@dilibrium.ru">support@dilibrium.ru</a>
 * @version 0.0.2
 */
public class Launcher {

    /**
     * Класс экспериента модели
     */
    private Class modelClass;

    private Launcher() {
    }

    /**
     * Инициализация нового загрузчика
     * @param modelClass тип эксперимента
     * @return созданный загрузчик
     */
    public static Launcher create(Class modelClass) {
        Launcher launcher = new Launcher();
        launcher.modelClass = modelClass;
        return launcher;
    }

    /**
     * Метод запуска эксперимента
     * @throws IOException
     * @throws InterruptedException
     */
    public void start() throws IOException, InterruptedException {
        System.out.println(modelClass.getSimpleName() + " started!");
        int finishCode = JavaProcess.exec(modelClass);
        System.out.println(modelClass.getSimpleName() + " finished with code " + finishCode);
    }
}
