package ru.dilibrium.anylogic.launcher.app;

import com.anylogic.engine.gui.ExperimentHost;
import com.anylogic.engine.gui.IExperimentHost;
import ru.dilibrium.anylogic.ds54.ob.Optimization;
import ru.dilibrium.anylogic.ds54.ob.Simulation;

/**
 * <b>Загрузчик экспериментов модели</b><br/><br/>
 *
 * <br/>
 * @author ООО "Дилибриум"<br/>Техническая поддержка: <a href="mailto:support@dilibrium.ru">support@dilibrium.ru</a>
 * @version 0.0.1
 */
public class Launcher {

    /**
     * Простой эксперимент
     */
    private final Simulation simulation = new Simulation();

    /**
     * Оптимизационный эксперимент
     */
    private final Optimization optimization = new Optimization();

    /**
     * Наименование эусперимента, реализует выбор эксперимента для запуска
     */
    private Experiment experiment;

    private Launcher() {
    }

    /**
     * Инициализация нового загрузчика
     * @param experiment тип эксперимента
     * @return созданный загрузчик
     * @throws NullPointerException в случае, когда загрузчик создать не удалось
     */
    public static Launcher create(Experiment experiment) throws NullPointerException {
        Launcher launcher = new Launcher();
        launcher.experiment = experiment;
        return launcher;
    }

    /**
     * Метод запуска эксперимента
     * @throws RuntimeException в случае одновременного запуска нескольких экспериментов на одном сервере
     */
    public void start() throws RuntimeException {

        switch (this.experiment) {
            case SIMULATION:
            default:
                IExperimentHost simulationHost = new ExperimentHost(simulation);
                simulation.setup(simulationHost);
                simulationHost.launch();

                break;
            case OPTIMIZATION:
                IExperimentHost optimizationHost = new ExperimentHost(optimization);
                optimization.setup(optimizationHost);
                optimizationHost.launch();

                break;
        }

        System.out.println(this.experiment + " experiment started!");
    }
}
