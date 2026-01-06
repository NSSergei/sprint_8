package methods;

import main.SleepAnalysisResult;
import main.SleepTrackerApp;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class OutputInfo  implements Function<List<SleepTrackerApp>, SleepAnalysisResult> {
        @Override
        public  SleepAnalysisResult apply(List<SleepTrackerApp> lst){
            List<String> printAll = info(lst);
            String allSessions = String.join("\n", printAll);
            return new SleepAnalysisResult<>("Вывод списка сессий", "\n" + List.of(allSessions));
        }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    //*метод для вывода содержания
    public List<String> info(List<SleepTrackerApp> listSession) {
        return listSession.stream()
                .map(session -> {
                    String infoString = session.getStartSleepingSession().format(formatter) + "  " +
                            session.getEndSleepingSession().format(formatter) + "  " +
                            session.getSleepQuality();
                    return infoString;
                })
                .collect(Collectors.toList());
    }
}
