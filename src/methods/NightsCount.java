package methods;

import main.SleepAnalysisResult;
import main.SleepTrackerApp;

import java.util.function.Function;
import java.time.LocalDateTime;
import java.util.List;
import java.time.Duration;



public class NightsCount implements Function<List<SleepTrackerApp>, SleepAnalysisResult>{
    //метод подсчета ночей
    @Override
    public SleepAnalysisResult apply(List<SleepTrackerApp> list) {
        long nightsCount = countNights(list);
        return new SleepAnalysisResult<>("Количество ночей", nightsCount);
    }

    public Long countNights(List<SleepTrackerApp> lst){
        if (lst == null || lst.isEmpty()) {
            return 0L;
        }
        //первая дата начало подсчета
        LocalDateTime firstNight = lst.get(0).getStartSleepingSession();
        //последняя дата конец подсчета
        LocalDateTime lastNight = lst.get(lst.size()-1).getEndSleepingSession();

        Duration duration = Duration.between(firstNight,lastNight);
        return duration.toDays();
    }
}

