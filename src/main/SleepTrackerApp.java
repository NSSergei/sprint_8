package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;

import methods.*;


public class SleepTrackerApp {
    private LocalDateTime startSleepingSession;
    private LocalDateTime endSleepingSession;
    private  SleepQuality sleepQuality;
    private static final  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    static List<SleepTrackerApp> listSession = new ArrayList<>();

    public SleepTrackerApp(LocalDateTime startSleepingSession, LocalDateTime endSleepingSession,
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

    public static void main(String[] args) {
        LocalDateTime startSleepingSession = LocalDateTime.of(2025, 12, 17, 22, 00);
        LocalDateTime endSleepingSession = LocalDateTime.of(2025, 12, 18, 5, 00);

        LocalDateTime startSleepingSession1 = LocalDateTime.of(2025, 12, 18, 15, 00);
        LocalDateTime endSleepingSession1 = LocalDateTime.of(2025, 12, 18, 19, 00);

        LocalDateTime startSleepingSession2 = LocalDateTime.of(2025, 12, 19, 15, 01);
        LocalDateTime endSleepingSession2 = LocalDateTime.of(2025, 12, 19, 17, 01);

        LocalDateTime startSleepingSession3 = LocalDateTime.of(2025, 12, 28, 15, 01);
        LocalDateTime endSleepingSession3 = LocalDateTime.of(2025, 12, 29, 20, 01);

        //System.out.println(startSleepingSession.format(formatter));
        //System.out.println(endSleepingSession.format(formatter));

        SleepTrackerApp session1 = new SleepTrackerApp(startSleepingSession, endSleepingSession,
                SleepQuality.UNDEFINED);
        SleepTrackerApp session2 = new SleepTrackerApp(startSleepingSession1, endSleepingSession1,
                SleepQuality.UNDEFINED);
        SleepTrackerApp session3 = new SleepTrackerApp(startSleepingSession2, endSleepingSession2,
                SleepQuality.UNDEFINED);
        SleepTrackerApp session4 = new SleepTrackerApp(startSleepingSession3, endSleepingSession3,
                SleepQuality.UNDEFINED);

        listSession.add(session1);
        listSession.add(session2);
        listSession.add(session3);
        listSession.add(session4);

        //класс methods.NigthsCount
        NigthsCount nigthsCount = new NigthsCount();
        System.out.println(nigthsCount.nigth(listSession));
        System.out.println("----------------");

        //класс methods.SleepQualityResult
        SleepQualityResult sleepQualityResult = new SleepQualityResult();
        //метод оценки качества сна
        System.out.println(sleepQualityResult.result(listSession));
        System.out.println("________________");

        //класс methods.Сhronotype
        Сhronotype chronotype = new Сhronotype();
        System.out.println(chronotype.peopleType(sleepQualityResult.result(listSession)));
        System.out.println("________________");

        // класс methods.Statistics
        Statistics stats = new Statistics();
        stats.minSession(stats.minTimeSession(listSession));
        System.out.println("________________");
        stats.maxSession(stats.maxTimeSession(listSession));
        System.out.println("________________");
        System.out.println("Средняя сессия : " + stats.midlTimeSession(listSession));
        System.out.println("________________");
        System.out.println("Кол-во плохих сессий : " + stats.badSessionCount(listSession));
        System.out.println("________________");

        // класс methods.OutputInfo
        OutputInfo outputInfo = new OutputInfo();
        outputInfo.info(listSession);
    }
}