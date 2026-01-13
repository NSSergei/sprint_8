package methods;

import main.SleepAnalysisResult;
import main.SleepTrackerRecord;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;


public class MidleTimeForSession implements  Function<List<SleepTrackerRecord>, SleepAnalysisResult>{
    @Override
    public SleepAnalysisResult apply(List<SleepTrackerRecord> lst){
        long midleTime = midleTimeSession(lst);
        return new SleepAnalysisResult<>("Средняя длина сессии", midleTime);
    }

    //средняя продолжительность сессии (в минутах);
    public Long midleTimeSession(List<SleepTrackerRecord> sessions){
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
