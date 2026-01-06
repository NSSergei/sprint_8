package methods;

import main.SleepAnalysisResult;
import main.SleepTrackerApp;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;


public class MidleTimeForSession implements  Function<List<SleepTrackerApp>, SleepAnalysisResult>{
    @Override
    public SleepAnalysisResult apply(List<SleepTrackerApp> lst){
        long midleTime = midleTimeSession(lst);
        return new SleepAnalysisResult<>("Средняя длина сессии", midleTime);
    }

    //средняя продолжительность сессии (в минутах);
    public Long midleTimeSession(List<SleepTrackerApp> sessions){
        if(sessions == null || sessions.isEmpty()){
            return 0L;
        }
        long total = sessions.stream()
                .mapToLong(session -> Duration.between(session.getStartSleepingSession(),
                        session.getEndSleepingSession()).toMinutes())
                .sum();
        long midlTime = total / sessions.size();
        return midlTime;
    }
}
