package ru.dilibrium.anylogic.launcher.app;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * <b>Свойства приложения</b><br/><br/>
 *
 * @author ООО "Дилибриум"<br/>Техническая поддержка: <a href="mailto:support@dilibrium.ru">support@dilibrium.ru</a>
 * @version 0.0.1
 */
public class Configs {

    private static final Properties properties = new Properties();
    private static final InputStream stream = Configs.class.getResourceAsStream("/app.properties");
    private static final InputStreamReader reader =
            stream != null
                    ? new InputStreamReader(stream, StandardCharsets.UTF_8)
                    : null;

    /**
     * Метод загружает файл свойств приложения
     * @return объект типа Properties
     * @see Properties
     */
    public static Properties load(){
        if (reader != null) {
            try {
                properties.load(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("WARNING: не удалось прочитать конфигурационный файл");
        }

        return properties;
    }

    /**
     * Метод возвращает свойство параметра конфигурации по ключу или <code>null</code>, если такой ключ не найден
     * @param key ключ (наименование) параметра
     */
    public static Object get(Object key) {
        if (properties.isEmpty()) return null;
        else return properties.get(key);
    }

    public static String get(String key) {
        if (properties.isEmpty()) return null;
        else return properties.getProperty(key);
    }

    /**
     * Метод возвращает свойство параметра конфигурации по ключу или значение по умолчанию, если такой ключ не найден
     * @param key ключ (наименование) параметра
     * @param defaultValue значение по умолчанию
     */
    public static Object get(Object key, Object defaultValue) {
        if (properties.isEmpty()) return null;
        else return properties.getOrDefault(key, defaultValue);
    }
}
