import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private int id;
    private String title;
    private String status;

    Task(int id, String title, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    int getId() { return id; }
    String getTitle() { return title; }
    String getStatus() { return status; }
    void setTitle(String title) { this.title = title; }
    void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + status;
    }
}

public class TaskManager {
    static ArrayList<Task> tasks = new ArrayList<>();
    static int nextId = 1;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== TASK MANAGER =====");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Update Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = readInt();
            switch (choice) {
                case 1: addTask(); break;
                case 2: viewTasks(); break;
                case 3: updateTask(); break;
                case 4: deleteTask(); break;
                case 5: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid choice. Try 1-5.");
            }
        }
    }

    static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    static Task findTask(int id) {
        for (Task t : tasks) if (t.getId() == id) return t;
        return null;
    }

    static void addTask() {
        System.out.print("Enter task title: ");
        String title = sc.nextLine().trim();
        if (title.isEmpty()) { System.out.println("Title cannot be empty."); return; }
        tasks.add(new Task(nextId++, title, "Pending"));
        System.out.println("Task added.");
    }

    static void viewTasks() {
        if (tasks.isEmpty()) { System.out.println("No tasks found."); return; }
        System.out.println("ID | Title | Status");
        for (Task t : tasks) System.out.println(t);
    }

    static void updateTask() {
        System.out.print("Enter task ID to update: ");
        Task t = findTask(readInt());
        if (t == null) { System.out.println("Task not found."); return; }
        System.out.print("New title (Enter to keep '" + t.getTitle() + "'): ");
        String title = sc.nextLine().trim();
        if (!title.isEmpty()) t.setTitle(title);
        System.out.print("New status (Pending/Done, Enter to keep '" + t.getStatus() + "'): ");
        String status = sc.nextLine().trim();
        if (!status.isEmpty()) t.setStatus(status);
        System.out.println("Task updated.");
    }

    static void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        Task t = findTask(readInt());
        if (t == null) { System.out.println("Task not found."); return; }
        tasks.remove(t);
        System.out.println("Task deleted.");
    }
}
