package methods;
import main.SleepAnalysisResult;
import main.SleepTrackerRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class SleepQualityResult implements Function<List<SleepTrackerRecord>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepTrackerRecord> lst){
        List<SleepTrackerRecord> statusList = result(lst);
        return new SleepAnalysisResult<>("Список сессий с оценкой качества сна", statusList);
    }

    public boolean  overlapsNight(LocalDateTime start, LocalDateTime end){
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();

        LocalDateTime nightStart = LocalDateTime.of(startDate, LocalTime.of(0,0));
        LocalDateTime nightEnd = LocalDateTime.of(endDate, LocalTime.of(6, 0));

        return start.isBefore(nightEnd) && end.isAfter(nightStart);
    }

    public List<SleepTrackerRecord> result (List<SleepTrackerRecord> lst) {
        return lst.stream()
                .map(session -> {
                    LocalDateTime start = session.getStartSleepingSession();
                    LocalDateTime end = session.getEndSleepingSession();
                    if(overlapsNight(start, end)){
                        session.setSleepQuality(SleepQuality.NORMAL);
                    }else {
                        session.setSleepQuality(SleepQuality.BAD);
                    }
                    return session;
                })
                .collect(Collectors.toList());
    }
}
