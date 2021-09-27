package ru.dilibrium.anylogic.launcher.app;

/**
 * <b>Типы экспериментов в модели</b><br/><br/>
 *
 * <code>SIMULATION</code> — эксперимент с простой симуляцией
 * <code>OPTIMIZATION</code> — оптимизационный эксперимент
 * <br/>
 * @author ООО "Дилибриум"<br/>Техническая поддержка: <a href="mailto:support@dilibrium.ru">support@dilibrium.ru</a>
 * @version 0.0.1
 */
public enum Experiment {
    SIMULATION("Simulation"),
    OPTIMIZATION("Optimization")
    ;

    /**
     * Наименование эксперимента
     */
    String alias;

    Experiment(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return alias;
    }
}
