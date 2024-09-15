package org.haruhi.tracker.history;

import org.haruhi.tracker.goal.AbstractTask;

public class History implements HistoryManager {
    private final static History instance = new History();
    private static LimitedLinkedList<AbstractTask> lastTasks = new LimitedLinkedList<>(5);

    private History(){}

    public static History getInstance() {
        return instance;
    }

    @Override
    public void add(AbstractTask task) {

        lastTasks.add(task);
   }


    @Override
    public LimitedLinkedList<AbstractTask> getHistory() {
        return lastTasks;
    }
}
