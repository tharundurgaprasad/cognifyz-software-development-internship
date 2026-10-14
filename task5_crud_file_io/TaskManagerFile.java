import java.io.*;
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

public class TaskManagerFile {
    static final String FILE_NAME = "tasks.txt";
    static ArrayList<Task> tasks = new ArrayList<>();
    static int nextId = 1;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadTasks();
        while (true) {
            System.out.println("\n===== TASK MANAGER (with File Storage) =====");
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
                case 5: saveTasks(); System.out.println("Tasks saved. Goodbye!"); return;
                default: System.out.println("Invalid choice. Try 1-5.");
            }
        }
    }

    static void loadTasks() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No saved tasks found. Starting fresh.");
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", 3);
                if (parts.length < 3) {
                    System.out.println("Skipping corrupted line: " + line);
                    continue;
                }
                try {
                    int id = Integer.parseInt(parts[0].trim());
                    tasks.add(new Task(id, parts[1], parts[2]));
                    if (id >= nextId) nextId = id + 1;
                } catch (NumberFormatException e) {
                    System.out.println("Skipping corrupted line: " + line);
                }
            }
            System.out.println(tasks.size() + " task(s) loaded from " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    static void saveTasks() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Task t : tasks) {
                pw.println(t.getId() + "," + t.getTitle().replace(",", ";") + "," + t.getStatus());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
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
        saveTasks();
        System.out.println("Task added and saved.");
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
        saveTasks();
        System.out.println("Task updated and saved.");
    }

    static void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        Task t = findTask(readInt());
        if (t == null) { System.out.println("Task not found."); return; }
        tasks.remove(t);
        saveTasks();
        System.out.println("Task deleted and saved.");
    }
}
