package methods;

import main.SleepAnalysisResult;
import main.SleepTrackerApp;
import main.SleepTrackerAppInterface;
import static main.SleepTrackerApp.*;

import java.util.List;
import java.util.function.Function;


public class BadSession implements Function<List<SleepTrackerApp>, SleepAnalysisResult> {
    //количество сессий с плохим качеством сна.
    @Override
    public SleepAnalysisResult apply(List<SleepTrackerApp> list){
        long badCount = badSessionCount(list);
        return new SleepAnalysisResult<>("Количество плохих сессии ", badCount);
    }
    public long badSessionCount(List<SleepTrackerApp> sessions){
        if(sessions == null || sessions.isEmpty()){
            return 0L;
        }
        long badSession = sessions.stream()
                .filter(el -> el.getSleepQuality() == SleepQuality.BAD)
                .count();
        return badSession;
    }
}
