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

        Logger.log("Начало лога");
        List<SleepTrackerRecord> listSession = new ArrayList<>();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        FileReader fileReader = new FileReader("sleep_log.txt");
        BufferedReader br = new BufferedReader(fileReader);


        while (br.ready()){
            String line = br.readLine();
            String[] lineToQuality = line.split(";");
            try {
                LocalDateTime start = LocalDateTime.parse(lineToQuality[0], formatter);
                LocalDateTime end = LocalDateTime.parse(lineToQuality[1], formatter);
                SleepQuality sq = SleepQuality.UNDEFINED;
                SleepTrackerRecord sleepTrackerRecord = new SleepTrackerRecord(start,end,sq);
                listSession.add(sleepTrackerRecord);
            } catch (Exception e){
                System.out.println("Ошибка при обработке строки: " + line);
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        br.close();

        //System.out.println(listSession);


        System.out.println("________________ SleepAnalysisResult, SleepQualityResult");
        //SleepQualityResult
        SleepQualityResult sleepQualityResult = new SleepQualityResult();
        SleepAnalysisResult sleepAnalysisResult1 = sleepQualityResult.apply(listSession);
        //System.out.println(sleepAnalysisResult1);
        Logger.log(" " + sleepAnalysisResult1);


        System.out.println("________________ SleepAnalysisResult, nightsCount");

        NightsCount nightsCount = new NightsCount();
        //NightsCount
        SleepAnalysisResult sleepAnalysisResult2 = nightsCount.apply(listSession);
        //System.out.println(sleepAnalysisResult2);
        Logger.log("Общее" + sleepAnalysisResult2);

        System.out.println("________________ SleepAnalysisResult, BadSession");
        //BadSession
        BadSession badSession1 = new BadSession();
        SleepAnalysisResult sleepAnalysisResult3 = badSession1.apply(listSession);
        //System.out.println(sleepAnalysisResult3);
        Logger.log(" " + sleepAnalysisResult3);


        System.out.println("________________ SleepAnalysisResult, MaxTimeForSession");
        //MaxTimeForSession
        MaxTimeForSession maxTimeForSession = new MaxTimeForSession();
        SleepAnalysisResult sleepAnalysisResult4 = maxTimeForSession.apply(listSession);
        //System.out.println(sleepAnalysisResult4);
        Logger.log(" " + sleepAnalysisResult4);

        System.out.println("________________ SleepAnalysisResult, MinTimeForSession");
        //MinTimeForSession
        MinTimeForSession minTimeForSession = new MinTimeForSession();
        SleepAnalysisResult sleepAnalysisResult5 = minTimeForSession.apply(listSession);
        //System.out.println(sleepAnalysisResult5);
        Logger.log(" " + sleepAnalysisResult5);

        System.out.println("________________ SleepAnalysisResult, MidleTimeForSession");
        //MidleTimeForSession
        MidleTimeForSession midleTimeForSession = new MidleTimeForSession();
        SleepAnalysisResult sleepAnalysisResult6 = midleTimeForSession.apply(listSession);
        //System.out.println(sleepAnalysisResult6);
        Logger.log(" " + sleepAnalysisResult6);

        System.out.println("________________ SleepAnalysisResult, OutputInfo");
        OutputInfo outputInfo = new OutputInfo();
        SleepAnalysisResult sleepAnalysisResult7 = outputInfo.apply(listSession);
        //System.out.println(sleepAnalysisResult7);
        Logger.log(" " + sleepAnalysisResult7);

        System.out.println("________________ SleepAnalysisResult, Сhronotype");
        Сhronotype chronotype = new Сhronotype();
        SleepAnalysisResult sleepAnalysisResult8 = chronotype.apply(listSession);
        //System.out.println(sleepAnalysisResult8);
        Logger.log("Согласно хронотипу " + sleepAnalysisResult8);
    }
}