package goal;

import java.util.Objects;

public abstract class AbstractTask {
    protected String title;
    protected String description;
    protected static int count = 0;
    protected final int id;
    protected Status status;

    AbstractTask(String title, String description){
        this.title = title;
        this.description = description;
        count += 1;
        id = count;
        this.status = Status.NEW;

    }

    AbstractTask(int id, String title, String description, Status status){
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
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

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AbstractTask that = (AbstractTask) object;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(description, that.description) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, id, status);
    }

    @Override
    public String toString() {
        String typeTask = this.getClass().getSimpleName();
        return typeTask + "\n"
                + this.id + "\n"
                + this.title + "\n"
                + this.description + "\n"
                + this.status;
    }
}
