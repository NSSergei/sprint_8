package methods;
import main.SleepTrackerApp;
import methods.SleepQualityResult;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.List;
import java.util.stream.Collectors;


public class SleepQualityResult {

    public boolean  overlapsNight(LocalDateTime start, LocalDateTime end){
        LocalDate startDate = start.toLocalDate();
        LocalDate endDate = end.toLocalDate();

        LocalDateTime nightStart = LocalDateTime.of(startDate, LocalTime.of(0,0));
        LocalDateTime nightEnd = LocalDateTime.of(endDate, LocalTime.of(6, 0));

        return start.isBefore(nightEnd) && end.isAfter(nightStart);
    }

    public List<SleepTrackerApp> result (List<SleepTrackerApp> lst){
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
