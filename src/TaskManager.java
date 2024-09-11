import goal.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager implements Manager{
    private HashMap<Integer, Task> allTasks;
    private HashMap<Integer, Epic> allEpics;
    private HashMap<Integer, Subtask> allSubtask;



    public HashMap<Integer, Task> getAllTasks() {
        return allTasks;
    }

    @Override
    public Task getTask(int id) {
        return allTasks.get(id);
    }

    @Override
    public void removeAllTasks() {
        HashMap<Integer, Task> newMapOfTask = new HashMap<>();
        this.allTasks = newMapOfTask;
    }

    @Override
    public void removeTask(int id) {
        allTasks.remove(id);
    }


    @Override
    public HashMap<Integer, Subtask> getAllSubtaskFromEpic(Epic epic) {
        int id = epic.getId();
        HashMap <Integer, Subtask> subtaskFromEpic = new HashMap<>();
        for (Map.Entry<Integer, Subtask> pair : allSubtask.entrySet()){
            if (pair.getValue().getEpicId() == id) {
                subtaskFromEpic.put(pair.getKey(), pair.getValue());
            }
        }
        return subtaskFromEpic;
    }


    public Epic getEpic(int id) {
        return allEpics.get(id);
    }

    @Override
    public void updateEpic(Epic epic) {

    }


    @Override
    public void updateTask(Task task) {

    }

    @Override
    public void createEpic(Epic Epic) {

    }

    @Override
    public void createTask(Task task) {

    }
}
