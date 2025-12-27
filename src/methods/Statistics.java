package methods;
import main.SleepTrackerApp;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Statistics {

    //минимальная продолжительность сессии (в минутах);
    public Optional<Long> minTimeSession(List<SleepTrackerApp> sessions) {
        return sessions.stream()
                .map(session -> Duration.between(session.getStartSleepingSession(),
                        session.getEndSleepingSession()).toMinutes())
                .min(Long::compare);

    }

    //сделать общий вывод
    public void minSession(Optional<Long> minTimeSession) {
        if (minTimeSession.isPresent()) {
            System.out.println("Минимальная продолжительность сессии: " + minTimeSession.orElse(0L) + " минут.");
        } else {
            System.out.println("Сессии не найдены.");
        }
    }

    // максимальная продолжительность сессии (в минутах);
    public Optional<Long> maxTimeSession(List<SleepTrackerApp> sessions){
        return sessions.stream()
                .map(session -> Duration.between(session.getStartSleepingSession(),
                        session.getEndSleepingSession()).toMinutes())
                .max(Long::compare);
    }

    //сделать общий вывод
    public void maxSession(Optional<Long> maxTimeSession) {
        if (maxTimeSession.isPresent()) {
            System.out.println("Максимальная продолжительность сессии: " + maxTimeSession.orElse(0L) + " минут.");
        } else {
            System.out.println("Сессии не найдены.");
        }
    }

    //средняя продолжительность сессии (в минутах);
    public Long midlTimeSession(List<SleepTrackerApp> sessions){
        if(sessions == null || sessions.isEmpty()){
            return 0L;
        }
        long total = sessions.stream()
                .mapToLong(session -> Duration.between(session.getStartSleepingSession(),
                        session.getEndSleepingSession()).toMinutes())
                .sum();
        long midlTime = total / sessions.size();
        return midlTime;
    }

    //количество сессий с плохим качеством сна.
    public long badSessionCount(List<SleepTrackerApp> sessions){
        long badSession = sessions.stream()
                .filter(el -> el.getSleepQuality() == SleepQuality.BAD)
                .count();
        return badSession;
    }
}
