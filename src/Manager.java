
import goal.*;

import java.util.HashMap;
import java.util.List;

public interface Manager {

    HashMap<Integer, Subtask> getAllSubtaskFromEpic(Epic epic);
    Task getTask(int id);

    void removeAllTasks();
    void removeTask(int id);

    void createTask(Task task);
    void createEpic(Epic Epic);

    void updateTask(Task task);
    void updateEpic(Epic epic);
}
