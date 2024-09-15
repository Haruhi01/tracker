package org.haruhi.tracker.manager;


import org.haruhi.tracker.database.*;
import org.haruhi.tracker.goal.AbstractTask;
import org.haruhi.tracker.goal.Epic;
import org.haruhi.tracker.goal.Subtask;
import org.haruhi.tracker.goal.Task;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TasksManager implements Manager {
    private TaskDBManager taskDBManager;

    public TasksManager(TaskDBManager manager){
        this.taskDBManager = manager;
    }


    @Override
    public HashMap<Integer, AbstractTask> getAll() {
        HashMap<Integer, AbstractTask> tasks = new HashMap<>();
        if(taskDBManager.get().isEmpty()) {
            List <AbstractTask> abstractTasks= taskDBManager.get();
            for (AbstractTask someTask : abstractTasks) {
                if (someTask instanceof Task) {
                    Task task = (Task) someTask;

                    tasks.put(task.getId(), task);
                }
            }
        }
        return tasks;
    }

    @Override
    public AbstractTask getBuyId(int id) {
        return taskDBManager.find(id);
    }

    @Override
    public void removeAll() {
        for(AbstractTask abstractTask : taskDBManager.get()){
            if(abstractTask instanceof Task){
                taskDBManager.delete(abstractTask.getId());
            }
        }
    }

    @Override
    public void removeBuyId(int id) {
        if (taskDBManager.delete(id)){
            System.out.println("Удаление таска прошло успешно");
        } else{
            System.out.println("Ошибка удаления таска");
        }
    }

    @Override
    public void add(String title, String description) {
        Task newTask = new Task(title, description);
        taskDBManager.add(newTask);
    }

    @Override
    public void update(AbstractTask abstractTask) {
        if(abstractTask instanceof Task){
            taskDBManager.update(abstractTask);
        }
    }


}
