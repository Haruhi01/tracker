package goal;

import java.util.HashMap;
import java.util.Map;

public class Epic extends AbstractTask{
    private HashMap<Integer, Subtask> subtaskIds = new HashMap<>();

    Epic(String title, String description) {
        super(title, description);

    }

    public void addSubtask(String title, String description){
        Subtask newSubtask = new Subtask(title, description, this);
        subtaskIds.put(newSubtask.getId(), newSubtask);
    }

    public HashMap<Integer, Subtask> getSubtaskIds() {
        return subtaskIds;
    }



    @Override
    public Status getStatus() {
        int countDone = 0;
        int countInProcess = 0;
        for (Map.Entry<Integer, Subtask> subtask : subtaskIds.entrySet()){
            if (subtask.getValue().getStatus() == Status.DONE){
                countDone += 1;
            } else if (subtask.getValue().getStatus() == Status.IN_PROGRESS) {
                countInProcess += 1;
            }
        }
        if(countDone == subtaskIds.size()) return Status.DONE;
        else if (countDone == 0 && countInProcess == 0) return Status.NEW;
        return Status.IN_PROGRESS;

    }
}
