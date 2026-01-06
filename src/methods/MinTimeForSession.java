package methods;
import main.SleepAnalysisResult;
import main.SleepTrackerApp;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MinTimeForSession implements Function<List<SleepTrackerApp>, SleepAnalysisResult> {
    @Override
    public  SleepAnalysisResult apply(List<SleepTrackerApp> lst){
        long mimTime = minTimeSession(lst).orElse(0L);
        return new SleepAnalysisResult<>("Минимальная продолжительность сесии", mimTime);
    }
    //минимальная продолжительность сессии (в минутах);
    public Optional<Long> minTimeSession(List<SleepTrackerApp> sessions) {
        if (sessions == null || sessions.isEmpty()){
            return Optional.of(0L);
        }
        return sessions.stream()
                .map(session -> Duration.between(session.getStartSleepingSession(),
                        session.getEndSleepingSession()).toMinutes())
                .min(Long::compare);

    }
}
