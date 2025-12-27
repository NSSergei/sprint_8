package methods;

import main.Logger;
import main.SleepTrackerApp;

import java.time.format.DateTimeFormatter;
import java.util.List;


public class OutputInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    //*метод для вывода содержания
    public static void info (List<SleepTrackerApp> listSession){
        listSession.stream()
                .map(session -> session.getStartSleepingSession().format(formatter) + "  " +
                        session.getEndSleepingSession().format(formatter) + "  " +
                        session.getSleepQuality())
                .forEach(Logger::log);

    }
}
