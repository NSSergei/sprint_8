package methods;


import main.SleepTrackerApp;

import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class Сhronotype {

    private String getSleepType(LocalDateTime start, LocalDateTime end) {
        LocalTime startTime = start.toLocalTime();
        LocalTime endTime = end.toLocalTime();

        if (startTime.isAfter(LocalTime.of(23, 0)) && endTime.isAfter(LocalTime.of(9,00))) {
            return "Сова";
        } else if (startTime.isBefore(LocalTime.of(22, 0)) && endTime.isBefore(LocalTime.of(7,00))){
            return "Жаворонок";
        } else {
            return "Голубь";
        }
    }


    public String peopleType(List<SleepTrackerApp> lst) {
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
