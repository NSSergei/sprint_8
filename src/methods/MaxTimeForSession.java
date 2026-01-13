package methods;

import main.SleepAnalysisResult;
import main.SleepTrackerRecord;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class MaxTimeForSession implements Function<List<SleepTrackerRecord>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepTrackerRecord> list){
        long maxTime = maxTimeSession(list).orElse(0L);
        return new SleepAnalysisResult<>("Максимальная продолжительность сессии",maxTime);
    }
    // максимальная продолжительность сессии (в минутах);
    public Optional<Long> maxTimeSession(List<SleepTrackerRecord> sessions){
        if (sessions == null || sessions.isEmpty()){
            return Optional.of(0L);
        }
        return sessions.stream()
                .map(session -> Duration.between(session.getStartSleepingSession(),
                        session.getEndSleepingSession()).toMinutes())
                .max(Long::compare);
    }
}
