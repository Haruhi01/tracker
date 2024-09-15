package history;

import goal.*;

import java.util.List;

public interface HistoryManager {
    void add(AbstractTask task);
    List<AbstractTask> getHistory();
}
