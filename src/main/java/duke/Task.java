package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public abstract char getTaskType();

    public boolean isDone() {
        return this.isDone;
    }

    public abstract String getTime();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}