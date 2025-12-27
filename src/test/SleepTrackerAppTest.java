package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import main.*;

import main.Logger;
import main.SleepTrackerApp;
import methods.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SleepTrackerAppTest {
    //метод вывода сессий сна с передачей в Лог
    @Test
    void testInfo() throws IOException {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        Files.deleteIfExists(Paths.get("log.txt"));

        List<SleepTrackerApp> sessions = List.of(new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 15, 01)
                        ,LocalDateTime.of(2025, 12, 20, 15, 01), SleepQuality.BAD),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 15, 01)
                        ,LocalDateTime.of(2025, 12, 20, 15, 01), SleepQuality.BAD));
        OutputInfo outputInfo = new OutputInfo();
        outputInfo.info(sessions);

        String expectedPart =
                LocalDateTime.of(2025, 12, 19, 15, 01).format(formatter) + "  " +
                        LocalDateTime.of(2025, 12, 20, 15, 01).format(formatter) + "  " +
                        "BAD";

        List<String> lines = Files.readAllLines(Paths.get("log.txt"));
        assertFalse(lines.isEmpty());
        assertTrue(lines.get(0).contains(expectedPart));


    }
    //методы класса methods.Statistics
    //метод мин сессии
    @Test
    void testMinTimeSession(){
        List<SleepTrackerApp> session = List.of(new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 15, 00)
                        ,LocalDateTime.of(2025, 12, 20, 15, 01), SleepQuality.BAD));
        Statistics statistics = new Statistics();
        Long values = statistics.minTimeSession(session).orElse(0L);
        // проверка вывода мин значения
        // statistics.minSession(statistics.minTimeSession(session));
        assertTrue(values == 120);

    }
    //метод макс сессии
    @Test
    void testMaxTimeSession(){
        List<SleepTrackerApp> session = List.of(new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 20, 8, 00)
                        ,LocalDateTime.of(2025, 12, 20, 12, 00), SleepQuality.BAD));

        Statistics statistics = new Statistics();
        Long values = statistics.maxTimeSession(session).orElse(0L);
        // проверка вывода макс значения
        //statistics.maxSession(statistics.maxTimeSession(session));
        assertTrue(values == 240);
    }
    //метод среднего значений сессий
    @Test
    void testMidlTimeSession(){
        List<SleepTrackerApp> session = List.of(new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 20, 10, 00)
                        ,LocalDateTime.of(2025, 12, 20, 12, 00), SleepQuality.BAD));

        Statistics statistics = new Statistics();
        statistics.midlTimeSession(session);
        assertTrue(statistics.midlTimeSession(session) == 120);


    }

    //метод подсчета плохих циклов сна
    @Test
    void testBadSessionCountTwo(){
        List<SleepTrackerApp> session = List.of(new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 20, 10, 00)
                        ,LocalDateTime.of(2025, 12, 20, 12, 00), SleepQuality.BAD));

        Statistics statistics = new Statistics();
        assertTrue(statistics.badSessionCount(session) == 2);
    }

    @Test
    void testBadSessionCountOne(){
        List<SleepTrackerApp> session = List.of(new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 20, 10, 00)
                        ,LocalDateTime.of(2025, 12, 20, 12, 00), SleepQuality.UNDEFINED));

        Statistics statistics = new Statistics();
        assertTrue(statistics.badSessionCount(session) == 1);

    }

    //методы класса methods.NigthsCount
    //метод подсчета ночей
    @Test
    void testNigth(){
        List<SleepTrackerApp> session = List.of(new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 20, 10, 00)
                        ,LocalDateTime.of(2025, 12, 26, 12, 00), SleepQuality.UNDEFINED));

        NigthsCount nigthsCount = new NigthsCount();
        //вызов для проверки результата
        //System.out.println(nigthsCount.nigth(session));

        assertTrue(nigthsCount.nigth(session) == 7);

    }

    //методы класса methods.SleepQualityResult

    //метод проверки цикла
    // когда не было ни одной сессии сна, пересекающей интервал от 0:00 до 6:00.v (True)
    @Test
    void testOverlapsNightTrue(){
        List<SleepTrackerApp> session = List.of(new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 22, 00)
                ,LocalDateTime.of(2025, 12, 20, 6, 00), SleepQuality.UNDEFINED));
        SleepQualityResult sleepQualityResult = new SleepQualityResult();

        /*System.out.println(session1.get(0).getStartSleepingSession() + " " +
                session1 .get(0).getEndSleepingSession());*/

        assertTrue(sleepQualityResult.overlapsNight(session.get(0).getStartSleepingSession(),
                session.get(0).getEndSleepingSession()));

    }

    //метод проверки цикла
    // когда не было ни одной сессии сна, пересекающей интервал от 0:00 до 6:00.v (False)
    @Test
    void testOverlapsNightFalse(){
        List<SleepTrackerApp> session1 = List.of(new SleepTrackerApp(LocalDateTime.of(2025, 12, 18, 17, 00)
                ,LocalDateTime.of(2025, 12, 18,23, 55,00), SleepQuality.UNDEFINED));

        SleepQualityResult sleepQualityResult = new SleepQualityResult();

        /*System.out.println(session1.get(0).getStartSleepingSession() + " " +
                session1 .get(0).getEndSleepingSession());*/

        assertFalse(sleepQualityResult.overlapsNight(session1.get(0).getStartSleepingSession(),
                session1.get(0).getEndSleepingSession()));

    }

    //метод присвоения качества сна в
    //зависимости от результата метода overlapsNight
    @Test
    void testResult(){
        List<SleepTrackerApp> session = List.of(new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 22, 00)
                ,LocalDateTime.of(2025, 12, 20, 6, 00), SleepQuality.UNDEFINED));
        SleepQualityResult sleepQualityResult = new SleepQualityResult();

        //System.out.println(sleepQualityResult.result(session).get(0).getSleepQuality());
        assertTrue(sleepQualityResult.result(session).get(0).getSleepQuality() == SleepQuality.NORMAL);
    }

    //методы класса methods.Сhronotype
    //метод определения хронотипа
    @Test
    void testSleepTypeLark (){
        List<SleepTrackerApp> session = List.of(
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 19, 00)
                        ,LocalDateTime.of(2025, 12, 20, 4, 00), SleepQuality.UNDEFINED),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 22, 23, 00)
                        ,LocalDateTime.of(2025, 12, 21, 6, 00), SleepQuality.UNDEFINED),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 22, 10, 00)
                        ,LocalDateTime.of(2025, 12, 26, 5, 00), SleepQuality.UNDEFINED),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 21, 10, 00)
                        ,LocalDateTime.of(2025, 12, 26, 4, 00), SleepQuality.UNDEFINED));

        Сhronotype chronotype = new Сhronotype();
        //System.out.println(chronotype.peopleType(session));
        assertTrue(chronotype.peopleType(session).equals("Жаворонок"));
    }
    @Test
    void testSleepTypeOwl (){
        List<SleepTrackerApp> session = List.of(
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 19, 23, 30),
                        LocalDateTime.of(2025, 12, 20, 9, 00),
                        SleepQuality.UNDEFINED),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 20, 23, 15),
                        LocalDateTime.of(2025, 12, 21, 9, 30),
                        SleepQuality.UNDEFINED),
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 22, 23, 45),
                        LocalDateTime.of(2025, 12, 23, 9, 50),
                        SleepQuality.UNDEFINED),
                // Можно оставить одну сессию дневного сна или другую, чтобы балансировать
                new SleepTrackerApp(LocalDateTime.of(2025, 12, 21, 12, 0),
                        LocalDateTime.of(2025, 12, 26, 22, 0),
                        SleepQuality.UNDEFINED)
        );

        Сhronotype chronotype = new Сhronotype();
        System.out.println(chronotype.peopleType(session));
        assertTrue(chronotype.peopleType(session).equals("Сова"));
    }


}
