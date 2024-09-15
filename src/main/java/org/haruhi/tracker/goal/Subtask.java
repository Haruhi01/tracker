package org.haruhi.tracker.goal;

public class Subtask extends AbstractTask{
    private int epicId;

    public Subtask(String title, String description, Epic epic) {
        super(title, description);
        epicId = epic.getId();
    }
    public Subtask (int id, String title, String description, Status status, int epicId){
        super(id, title, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }


    public void setStatus(Status status){
        this.status = status;
    }
}
