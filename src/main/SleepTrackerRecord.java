package main;

import methods.SleepQuality;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import methods.SleepQuality.*;


public class SleepTrackerRecord {
    private LocalDateTime startSleepingSession;
    private LocalDateTime endSleepingSession;
    private SleepQuality sleepQuality;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public SleepTrackerRecord(LocalDateTime startSleepingSession, LocalDateTime endSleepingSession,
                                    SleepQuality sleepQuality){
        this.startSleepingSession = startSleepingSession;
        this.endSleepingSession = endSleepingSession;
        this.sleepQuality = sleepQuality;
    }

    public LocalDateTime getEndSleepingSession(){
        return endSleepingSession;
    }

    public LocalDateTime getStartSleepingSession(){
        return startSleepingSession;
    }

    public Enum<SleepQuality> getSleepQuality(){
        return sleepQuality;
    }

    public void setSleepQuality(SleepQuality sleepQuality){
        this.sleepQuality = sleepQuality;
    }

    @Override
    public String toString() {
        return "Start: " + startSleepingSession.format(formatter) +
                ", End: " + endSleepingSession.format(formatter) +
                ", Quality: " + sleepQuality;
    }
}
