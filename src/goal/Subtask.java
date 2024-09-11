package goal;

public class Subtask extends Task{
    private int epicId;

    Subtask(String title, String description, Epic epic) {
        super(title, description);
        epicId = epic.getId();
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
