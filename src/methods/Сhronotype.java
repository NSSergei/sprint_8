package methods;


import main.SleepAnalysisResult;
import main.SleepTrackerRecord;

import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Map;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Сhronotype implements Function<List<SleepTrackerRecord>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepTrackerRecord> lst){
        String chronoType = peopleType(lst);
        return new SleepAnalysisResult<>("Вы являетесь",chronoType);
    }

    private String getSleepType(LocalDateTime start, LocalDateTime end) {
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();

        if (startTime.isAfter(LocalTime.of(23, 0)) && endTime.isAfter(LocalTime.of(9,00))) {
            return "Совой";
        } else if (startTime.isBefore(LocalTime.of(22, 0)) && endTime.isBefore(LocalTime.of(7,00))){
            return "Жавороноком";
        } else {
            return "Голубем";
        }
    }


    public String peopleType(List<SleepTrackerRecord> lst) {
        Map<String, Integer> counts = lst.stream()
                .filter(elim -> elim.getSleepQuality() != SleepQuality.BAD)
                .map(elim -> getSleepType(elim.getStartSleepingSession(), elim.getEndSleepingSession()))
                .collect(Collectors.groupingBy(s -> s, Collectors.summingInt(s -> 1)));

        //System.out.println(counts);

        return counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
