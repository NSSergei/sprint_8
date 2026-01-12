package main;

import methods.*;

import java.io.FileWriter;
import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;


public class SleepTrackerApp{
    public static void main(String[] args)  throws IOException {
        FileWriter fw = new FileWriter("log.txt",false);

        if(args.length == 0){
            System.out.println("Пожалуйста, укажите путь к файлу логов как аргумент командной строки");
        }
        String logFilePath = args[0];

        if (!java.nio.file.Files.exists(java.nio.file.Path.of(logFilePath))) {
            System.out.println("Файл не найден: " + logFilePath);
            return;
        }

        ReadSleepSessionsFromFile readSleepSessionsFromFile = new ReadSleepSessionsFromFile();
        List<SleepTrackerRecord> listSession = new ArrayList<>(readSleepSessionsFromFile.readSleepSessions("sleep_log.txt"));

        Logger.log("Начало лога");





        System.out.println("________________ SleepAnalysisResult, SleepQualityResult");
        //SleepQualityResult
        SleepQualityResult sleepQualityResult = new SleepQualityResult();
        SleepAnalysisResult sleepAnalysisResult1 = sleepQualityResult.apply(listSession);
        Logger.log(" " + sleepAnalysisResult1);


        System.out.println("________________ SleepAnalysisResult, nightsCount");

        NightsCount nightsCount = new NightsCount();
        //NightsCount
        SleepAnalysisResult sleepAnalysisResult2 = nightsCount.apply(listSession);
        Logger.log("Общее" + sleepAnalysisResult2);

        System.out.println("________________ SleepAnalysisResult, BadSession");
        //BadSession
        BadSession badSession1 = new BadSession();
        SleepAnalysisResult sleepAnalysisResult3 = badSession1.apply(listSession);
        Logger.log(" " + sleepAnalysisResult3);


        System.out.println("________________ SleepAnalysisResult, MaxTimeForSession");
        //MaxTimeForSession
        MaxTimeForSession maxTimeForSession = new MaxTimeForSession();
        SleepAnalysisResult sleepAnalysisResult4 = maxTimeForSession.apply(listSession);
        Logger.log(" " + sleepAnalysisResult4);

        System.out.println("________________ SleepAnalysisResult, MinTimeForSession");
        //MinTimeForSession
        MinTimeForSession minTimeForSession = new MinTimeForSession();
        SleepAnalysisResult sleepAnalysisResult5 = minTimeForSession.apply(listSession);
        Logger.log(" " + sleepAnalysisResult5);

        System.out.println("________________ SleepAnalysisResult, MidleTimeForSession");
        //MidleTimeForSession
        MidleTimeForSession midleTimeForSession = new MidleTimeForSession();
        SleepAnalysisResult sleepAnalysisResult6 = midleTimeForSession.apply(listSession);
        Logger.log(" " + sleepAnalysisResult6);

        System.out.println("________________ SleepAnalysisResult, OutputInfo");
        OutputInfo outputInfo = new OutputInfo();
        SleepAnalysisResult sleepAnalysisResult7 = outputInfo.apply(listSession);
        Logger.log(" " + sleepAnalysisResult7);

        System.out.println("________________ SleepAnalysisResult, Сhronotype");
        Сhronotype chronotype = new Сhronotype();
        SleepAnalysisResult sleepAnalysisResult8 = chronotype.apply(listSession);
        Logger.log("Согласно хронотипу " + sleepAnalysisResult8);
    }
}