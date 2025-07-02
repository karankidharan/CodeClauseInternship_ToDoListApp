public class Task {
    String description;
    boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String toString() {
        return (isCompleted ? "[✓] " : "[ ] ") + description;
    }
}
