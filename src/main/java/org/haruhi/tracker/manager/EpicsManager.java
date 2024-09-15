package org.haruhi.tracker.manager;

import org.haruhi.tracker.database.EpicDBManager;
import org.haruhi.tracker.database.TaskDBManager;
import org.haruhi.tracker.goal.AbstractTask;
import org.haruhi.tracker.goal.Epic;
import org.haruhi.tracker.goal.Subtask;
import org.haruhi.tracker.goal.Task;

import java.util.HashMap;
import java.util.List;

public class EpicsManager implements Manager{
    private EpicDBManager epicDBManager = EpicDBManager.getInstance();
    TaskDBManager taskDBManager = TaskDBManager.getInstance();


    public EpicsManager(){
    }

    public HashMap<Integer, AbstractTask> getSubtasksBuyEpic(Epic epic){
        HashMap<Integer, AbstractTask> returnSubtasks = new HashMap<>();
        if(taskDBManager.findBuyEpic(epic).isEmpty()){
            List<AbstractTask> subtasks = taskDBManager.findBuyEpic(epic);
            for(AbstractTask subtask : subtasks){
                returnSubtasks.put(subtask.getId(), subtask);
                return returnSubtasks;
            }
        } else {
            System.out.println("Subtasks not found");

        }
        return null;

    }

    public void addSubtask(Epic epic, String title, String description){
        Subtask newSubtask = new Subtask(title, description, epic);
        epic.addSubtask(newSubtask);
        taskDBManager.add(newSubtask);

    }




    @Override
    public HashMap<Integer, AbstractTask> getAll() {
        HashMap<Integer, AbstractTask> epics = new HashMap<>();
        if(epicDBManager.get().isEmpty()) {
            List<AbstractTask> abstractTasks= epicDBManager.get();
            for (AbstractTask someTask : abstractTasks) {
                epics.put(someTask.getId(), someTask);
            }
        }
        return epics;
    }

    @Override
    public AbstractTask getBuyId(int id) {
        return epicDBManager.find(id);
    }

    @Override
    public void removeAll() {
        if(epicDBManager.get().isEmpty()) {
            List<AbstractTask> epics = epicDBManager.get();
            for(AbstractTask epic : epics){
                epicDBManager.delete(epic.getId());
            }
        }
    }

    @Override
    public void removeBuyId(int id) {
        epicDBManager.delete(id);
    }

    @Override
    public void add(String title, String description) {
        Epic newEpic = new Epic(title, description);
        epicDBManager.add(newEpic);
    }

    @Override
    public void update(AbstractTask abstractTask) {
        epicDBManager.update(abstractTask);
    }

    public void updateSubtask(Epic epic){
        Subtask subtask = (Subtask) taskDBManager.findBuyEpic(epic);
        taskDBManager.update(subtask);
    }
}
