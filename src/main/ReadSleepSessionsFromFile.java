package main;

import methods.SleepQuality;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReadSleepSessionsFromFile {
    List<SleepTrackerRecord> listSession = new ArrayList<>();
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public List<SleepTrackerRecord> readSleepSessions(String fileName) throws IOException {
        if (!fileName.toLowerCase().endsWith(".txt")) {
            throw new IllegalArgumentException("Неверное расширение файла: ожидается .txt");
        }

        Path path = Path.of(fileName);
        if (!Files.exists(path)) {
            throw new IOException("Файл не найден: " + fileName);
        }
        FileReader fileReader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fileReader);

        while (br.ready()){
            String line = br.readLine();
            String[] lineToQuality = line.split(";");
            try {
                LocalDateTime start = LocalDateTime.parse(lineToQuality[0], formatter);
                LocalDateTime end = LocalDateTime.parse(lineToQuality[1], formatter);
                SleepQuality sq = SleepQuality.UNDEFINED;
                SleepTrackerRecord sleepTrackerRecord = new SleepTrackerRecord(start,end,sq);
                listSession.add(sleepTrackerRecord);
            } catch (Exception e){
                System.out.println("Ошибка при обработке строки: " + line);
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        br.close();
        return  listSession;
    }
}
