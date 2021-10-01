package ru.dilibrium.anylogic.launcher.core;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * <b>Класс, создающий отдельный процесс</b><br/><br/>
 *
 * <br/>
 * @author ООО "Дилибриум"<br/>Техническая поддержка: <a href="mailto:support@dilibrium.ru">support@dilibrium.ru</a>
 * @version 0.0.1
 */
public final class JavaProcess {

    /**
     * Список всех созданных открытых процессов
     */
    private static List<Process> allOpenProcesses = new LinkedList<>();

    private JavaProcess() {}

    /**
     * Метод, создающий и запускающий новый процесс
     * @param klass класс, зарускающий новый процесс
     * @return код выполнения процесса
     * @throws IOException
     * @throws InterruptedException
     */
    public static int exec(Class klass) throws IOException, InterruptedException {
        String javaHome = System.getProperty("java.home");
        String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
        String classpath = System.getProperty("java.class.path");
        String className = klass.getName();
        List<String> command = new LinkedList<>();
        command.add(javaBin);
        command.add("-cp");
        command.add(classpath);
        command.add(className);

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.inheritIO().start();
        allOpenProcesses.add(process);
        process.waitFor();

        return process.exitValue();
    }

    /**
     * Метод возвращает список всех открытых процессов
     */
    public static List<Process> getAllOpenProcesses() throws NullPointerException{
        return JavaProcess.allOpenProcesses;
    }
}
