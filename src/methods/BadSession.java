package methods;

import main.SleepAnalysisResult;
import main.SleepTrackerRecord;
import main.SleepTrackerApp;
import static main.SleepTrackerRecord.*;

import java.util.List;
import java.util.function.Function;


public class BadSession implements Function<List<SleepTrackerRecord>, SleepAnalysisResult> {
    //количество сессий с плохим качеством сна.
    @Override
    public SleepAnalysisResult apply(List<SleepTrackerRecord> list){
        long badCount = badSessionCount(list);
        return new SleepAnalysisResult<>("Количество плохих сессии ", badCount);
    }
    public long badSessionCount(List<SleepTrackerRecord> sessions){
        if(sessions == null || sessions.isEmpty()){
            return 0L;
        }
        long badSession = sessions.stream()
                .filter(el -> el.getSleepQuality() == SleepQuality.BAD)
                .count();
        return badSession;
    }
}
