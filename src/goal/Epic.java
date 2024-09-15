package goal;

import java.util.HashMap;
import java.util.Map;

public class Epic extends AbstractTask{
    private HashMap<Integer, AbstractTask> subtaskIds = new HashMap<>();

    public Epic(String title, String description) {
        super(title, description);

    }

    public Epic(int id, String title, String description, Status status){
        super(id, title, description, status);
    }

    public void addSubtask(Subtask newSubtask){
        subtaskIds.put(newSubtask.getId(), newSubtask);
    }

    public HashMap<Integer, AbstractTask> getSubtaskIds() {
        return subtaskIds;
    }



    @Override
    public Status getStatus() {
        int countDone = 0;
        int countInProcess = 0;
        for (Map.Entry<Integer, AbstractTask> subtask : subtaskIds.entrySet()){
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
