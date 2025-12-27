package main;

import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static final String LOG_FILE_PATH = "log.txt";

    // Метод для добавления строки в лог-файл
    public static void log(String message) {
        System.out.println("Записываю в лог: " + message);
        try (FileWriter writer = new FileWriter(LOG_FILE_PATH, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}