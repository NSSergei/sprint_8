package methods;

import main.SleepTrackerApp;

import java.time.LocalDateTime;
import java.util.List;
import java.time.Duration;

public class NigthsCount {
    //метод подсчета ночей
    public Long nigth(List<SleepTrackerApp> lst){
        //первая дата начало подсчета
        LocalDateTime firstNigth = lst.get(0).getStartSleepingSession();
        //последняя дата конец подсчета
        LocalDateTime lastNigth = lst.get(lst.size()-1).getEndSleepingSession();

        Duration duration = Duration.between(firstNigth,lastNigth);
        return duration.toDays();
    }
}

