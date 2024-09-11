package goal;

public abstract class AbstractTask {
    protected String title;
    protected String description;
    protected static int count = 0;
    protected int id;
    protected Status status;

    AbstractTask(String title, String description){
        this.title = title;
        this.description = description;
        count += 1;
        id = count;
        this.status = Status.NEW;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }


    public Status getStatus() {
        return status;
    }
}
