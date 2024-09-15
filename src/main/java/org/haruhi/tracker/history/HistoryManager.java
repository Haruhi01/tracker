package org.haruhi.tracker.history;

import org.haruhi.tracker.goal.*;

import java.util.List;

public interface HistoryManager {
    void add(AbstractTask task);
    List<AbstractTask> getHistory();
}
