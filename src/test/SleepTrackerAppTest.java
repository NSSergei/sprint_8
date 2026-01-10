package test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import main.SleepTrackerRecord;
import methods.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SleepTrackerAppTest {
    //метод вывода сессий сна
    @Test
    void testInfo() throws IOException {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

        List<SleepTrackerRecord> sessions = List.of(new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 15, 01)
                        ,LocalDateTime.of(2025, 12, 20, 15, 01), SleepQuality.BAD),
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 15, 01)
                        ,LocalDateTime.of(2025, 12, 20, 15, 01), SleepQuality.BAD));
        OutputInfo outputInfo = new OutputInfo();
        outputInfo.info(sessions);

        String expectedPart =
                LocalDateTime.of(2025, 12, 19, 15, 01).format(formatter) + "  " +
                        LocalDateTime.of(2025, 12, 20, 15, 01).format(formatter) + "  " +
                        "BAD";
    }
    //методы класса methods.Statistics
    //метод мин сессии
    @Test
    void testMinTimeSession(){
        List<SleepTrackerRecord> session = List.of(new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 15, 00)
                        ,LocalDateTime.of(2025, 12, 20, 15, 01), SleepQuality.BAD));
        MinTimeForSession statistics = new MinTimeForSession();
        Long values = statistics.minTimeSession(session).orElse(0L);
        // проверка вывода мин значения
        // statistics.minSession(statistics.minTimeSession(session));
        assertTrue(values == 120);

    }
    //метод макс сессии
    @Test
    void testMaxTimeSession(){
        List<SleepTrackerRecord> session = List.of(new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 20, 8, 00)
                        ,LocalDateTime.of(2025, 12, 20, 12, 00), SleepQuality.BAD));

        MaxTimeForSession maxTimeSession = new MaxTimeForSession();
        Long values = maxTimeSession .maxTimeSession(session).orElse(0L);
        // проверка вывода макс значения
        //statistics.maxSession(statistics.maxTimeSession(session));
        assertTrue(values == 240);
    }
    //метод среднего значений сессий
    @Test
    void testMidlTimeSession(){
        List<SleepTrackerRecord> session = List.of(new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 20, 10, 00)
                        ,LocalDateTime.of(2025, 12, 20, 12, 00), SleepQuality.BAD));

        MidleTimeForSession midlTimeForSession = new MidleTimeForSession();
        midlTimeForSession.midleTimeSession(session);
        assertTrue(midlTimeForSession.midleTimeSession(session) == 120);


    }

    //метод подсчета плохих циклов сна
    @Test
    void testBadSessionCountTwo(){
        List<SleepTrackerRecord> session = List.of(new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 20, 10, 00)
                        ,LocalDateTime.of(2025, 12, 20, 12, 00), SleepQuality.BAD));

        BadSession badSession = new BadSession();
        assertTrue(badSession.badSessionCount(session) == 2);
    }

    @Test
    void testBadSessionCountOne(){
        List<SleepTrackerRecord> session = List.of(new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 20, 10, 00)
                        ,LocalDateTime.of(2025, 12, 20, 12, 00), SleepQuality.UNDEFINED));

        BadSession badSession = new BadSession();
        assertTrue(badSession.badSessionCount(session) == 1);

    }

    //методы класса methods.NigthsCount
    //метод подсчета ночей
    @Test
    void testNigth(){
        List<SleepTrackerRecord> session = List.of(new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 10, 00)
                        ,LocalDateTime.of(2025, 12, 19, 12, 00), SleepQuality.BAD),
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 20, 10, 00)
                        ,LocalDateTime.of(2025, 12, 26, 12, 00), SleepQuality.UNDEFINED));

        NightsCount nigthsCount = new NightsCount();
        //вызов для проверки результата
        //System.out.println(nigthsCount.nigth(session));

        assertTrue(nigthsCount.countNights(session) == 7);

    }

    //методы класса methods.SleepQualityResult

    //метод проверки цикла
    // когда не было ни одной сессии сна, пересекающей интервал от 0:00 до 6:00.v (True)
    @Test
    void testOverlapsNightTrue(){
        List<SleepTrackerRecord> session = List.of(new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 22, 00)
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
        List<SleepTrackerRecord> session1 = List.of(new SleepTrackerRecord(LocalDateTime.of(2025, 12, 18, 17, 00)
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
        List<SleepTrackerRecord> session = List.of(new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 22, 00)
                ,LocalDateTime.of(2025, 12, 20, 6, 00), SleepQuality.UNDEFINED));
        SleepQualityResult sleepQualityResult = new SleepQualityResult();

        //System.out.println(sleepQualityResult.result(session).get(0).getSleepQuality());
        assertTrue(sleepQualityResult.result(session).get(0).getSleepQuality() == SleepQuality.NORMAL);
    }

    //методы класса methods.Сhronotype
    //метод определения хронотипа
    @Test
    void testSleepTypeLark (){
        List<SleepTrackerRecord> session = List.of(
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 19, 00)
                        ,LocalDateTime.of(2025, 12, 20, 4, 00), SleepQuality.UNDEFINED),
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 22, 23, 00)
                        ,LocalDateTime.of(2025, 12, 21, 6, 00), SleepQuality.UNDEFINED),
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 22, 10, 00)
                        ,LocalDateTime.of(2025, 12, 26, 5, 00), SleepQuality.UNDEFINED),
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 21, 10, 00)
                        ,LocalDateTime.of(2025, 12, 26, 4, 00), SleepQuality.UNDEFINED));

        Сhronotype chronotype = new Сhronotype();
        //System.out.println(chronotype.peopleType(session));
        assertTrue(chronotype.peopleType(session).equals("Жавороноком"));
    }
    @Test
    void testSleepTypeOwl (){
        List<SleepTrackerRecord> session = List.of(
                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 19, 23, 30),
                        LocalDateTime.of(2025, 12, 20, 9, 00),
                        SleepQuality.UNDEFINED),

                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 20, 23, 15),
                        LocalDateTime.of(2025, 12, 21, 9, 30),
                        SleepQuality.UNDEFINED),

                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 22, 23, 45),
                        LocalDateTime.of(2025, 12, 23, 9, 50),
                        SleepQuality.UNDEFINED),

                new SleepTrackerRecord(LocalDateTime.of(2025, 12, 21, 12, 0),
                        LocalDateTime.of(2025, 12, 26, 22, 0),
                        SleepQuality.UNDEFINED)
        );

        Сhronotype chronotype = new Сhronotype();
        //ystem.out.println(chronotype.peopleType(session));
        assertTrue(chronotype.peopleType(session).equals("Совой"));
    }
}
