import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListApp {
    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    static final String RESET = "\u001B[0m";
    static final String CYAN = "\u001B[36m";
    static final String GREEN = "\u001B[32m";
    static final String RED = "\u001B[31m";
    static final String YELLOW = "\u001B[33m";
    static final String BLUE = "\u001B[34m";
    static final String BOLD = "\u001B[1m";

    public static void main(String[] args) {
        int choice = 0;

        printBanner("WELCOME TO THE TO-DO LIST APPLICATION 📝");

        do {
            showMenu();
            choice = getUserChoice();

            switch (choice) {
                case 1 -> viewTasks();
                case 2 -> addTask();
                case 3 -> editTask();
                case 4 -> deleteTask();
                case 5 -> markCompleted();
                case 6 -> {
                    System.out.println(GREEN + "\nExiting... Thank you for using the To-Do List App! 👋" + RESET);
                    break;
                }
                default -> System.out.println(RED + "Invalid choice. Please try again." + RESET);
            }
        } while (choice != 6);

        scanner.close();
    }

    static void showMenu() {
        System.out.println(CYAN + "\n╔══════════════════════════════╗");
        System.out.println("║      TO-DO LIST MENU         ║");
        System.out.println("╚══════════════════════════════╝" + RESET);
        System.out.println(YELLOW + "1. 📋 View Tasks");
        System.out.println("2. ➕ Add Task");
        System.out.println("3. ✏️  Edit Task");
        System.out.println("4. ❌ Delete Task");
        System.out.println("5. ✅ Mark Task as Completed");
        System.out.println("6. 🚪 Exit" + RESET);
        System.out.print(BLUE + "\nEnter your choice: " + RESET);
    }

    static int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.print(RED + "Please enter a valid number: " + RESET);
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    static void addTask() {
        System.out.print(BLUE + "Enter task description: " + RESET);
        String desc = scanner.nextLine();
        if (desc.trim().isEmpty()) {
            System.out.println(RED + "Task description cannot be empty." + RESET);
            return;
        }
        tasks.add(new Task(desc));
        System.out.println(GREEN + "✅ Task added successfully!" + RESET);
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println(RED + "No tasks available." + RESET);
        } else {
            System.out.println(YELLOW + "\n📋 Your Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(BOLD + (i + 1) + ". " + tasks.get(i) + RESET);
            }
        }
    }

    static void editTask() {
        if (tasks.isEmpty()) {
            System.out.println(RED + "No tasks to edit." + RESET);
            return;
        }
        viewTasks();
        System.out.print(BLUE + "Enter task number to edit: " + RESET);
        int num = getUserChoice();

        if (num >= 1 && num <= tasks.size()) {
            System.out.print(BLUE + "Enter new description: " + RESET);
            String newDesc = scanner.nextLine();
            if (newDesc.trim().isEmpty()) {
                System.out.println(RED + "Description cannot be empty." + RESET);
                return;
            }
            tasks.get(num - 1).description = newDesc;
            System.out.println(GREEN + "✏️ Task updated!" + RESET);
        } else {
            System.out.println(RED + "Invalid task number." + RESET);
        }
    }

    static void deleteTask() {
        if (tasks.isEmpty()) {
            System.out.println(RED + "No tasks to delete." + RESET);
            return;
        }
        viewTasks();
        System.out.print(BLUE + "Enter task number to delete: " + RESET);
        int num = getUserChoice();

        if (num >= 1 && num <= tasks.size()) {
            tasks.remove(num - 1);
            System.out.println(GREEN + "🗑️ Task deleted successfully!" + RESET);
        } else {
            System.out.println(RED + "Invalid task number." + RESET);
        }
    }

    static void markCompleted() {
        if (tasks.isEmpty()) {
            System.out.println(RED + "No tasks to mark." + RESET);
            return;
        }
        viewTasks();
        System.out.print(BLUE + "Enter task number to mark as completed: " + RESET);
        int num = getUserChoice();

        if (num >= 1 && num <= tasks.size()) {
            tasks.get(num - 1).isCompleted = true;
            System.out.println(GREEN + "🎉 Task marked as completed!" + RESET);
        } else {
            System.out.println(RED + "Invalid task number." + RESET);
        }
    }

    static void printBanner(String text) {
        String line = "═".repeat(text.length() + 4);
        System.out.println(CYAN + "\n╔" + line + "╗");
        System.out.println("║  " + text + "  ║");
        System.out.println("╚" + line + "╝" + RESET);
    }
}
