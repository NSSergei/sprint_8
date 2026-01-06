package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static main.SleepTrackerApp.*;

import methods.*;


public class SleepTrackerAppInterface {
    static List<SleepTrackerApp> listSession = new ArrayList<>();
    public static void main(String[] args) {
        Logger.log("Старт приложения");

        for (SleepTrackerApp session : listSession) {
            Logger.log("Добавленная сессия: " + session);
        }

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

        System.out.println("________________ SleepAnalysisResult, SleepQualityResult");
        //SleepQualityResult
        Logger.log("Определяем качество сна");
        SleepQualityResult sleepQualityResult = new SleepQualityResult();
        SleepAnalysisResult sleepAnalysisResult1 = sleepQualityResult.apply(listSession);
        Logger.log("Результат качество сна: " + sleepAnalysisResult1);


        System.out.println("________________ SleepAnalysisResult, nightsCount");
        Logger.log("Запуск анализа кол-ва ночей");
        NightsCount nightsCount = new NightsCount();
        //NightsCount
        SleepAnalysisResult sleepAnalysisResult2 = nightsCount.apply(listSession);
        Logger.log("Результат анализа ночей: " + sleepAnalysisResult2);
        //System.out.println(sleepAnalysisResult2);


        System.out.println("________________ SleepAnalysisResult, BadSession");
        Logger.log("Запуск анализа кол-ва плохих ночей");
        //BadSession
        BadSession badSession1 = new BadSession();
        SleepAnalysisResult sleepAnalysisResult3 = badSession1.apply(listSession);
        Logger.log("Результат анализа кол-ва плохих ночей: " + sleepAnalysisResult3);
        //System.out.println(sleepAnalysisResult3);


        System.out.println("________________ SleepAnalysisResult, MaxTimeForSession");
        Logger.log("Запуск анализа максимальной сессии сна");
        //MaxTimeForSession
        MaxTimeForSession maxTimeForSession = new MaxTimeForSession();
        SleepAnalysisResult sleepAnalysisResult4 = maxTimeForSession.apply(listSession);
        Logger.log("Результат анализа максимальной сессии сна: " + sleepAnalysisResult4);
        //System.out.println(sleepAnalysisResult4);


        System.out.println("________________ SleepAnalysisResult, MinTimeForSession");
        Logger.log("Запуск анализа минимальной сессии сна");
        //MinTimeForSession
        MinTimeForSession minTimeForSession = new MinTimeForSession();
        SleepAnalysisResult sleepAnalysisResult5 = minTimeForSession.apply(listSession);
        Logger.log("Результат анализа минимальной сессии сна: " + sleepAnalysisResult5);
        //System.out.println(sleepAnalysisResult5);


        System.out.println("________________ SleepAnalysisResult, MidleTimeForSession");
        Logger.log("Запуск анализа средней продолжительности  сна");
        //MidleTimeForSession
        MidleTimeForSession midleTimeForSession = new MidleTimeForSession();
        SleepAnalysisResult sleepAnalysisResult6 = midleTimeForSession.apply(listSession);
        Logger.log("Результат анализа средней продолжительности  сна: " + sleepAnalysisResult6);
        //System.out.println(sleepAnalysisResult6);


        System.out.println("________________ SleepAnalysisResult, OutputInfo");
        Logger.log("Вывод списка сессий");
        OutputInfo outputInfo = new OutputInfo();
        SleepAnalysisResult sleepAnalysisResult7 = outputInfo.apply(listSession);
        Logger.log("" + sleepAnalysisResult7);
        //System.out.println(sleepAnalysisResult7);


        System.out.println("________________ SleepAnalysisResult, Сhronotype");
        Logger.log("Определениe хронотипа");
        Сhronotype chronotype = new Сhronotype();
        SleepAnalysisResult sleepAnalysisResult8 = chronotype.apply(listSession);
        Logger.log("-" + sleepAnalysisResult8);
        //System.out.println(sleepAnalysisResult8);

    }
}