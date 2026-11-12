import java.util.ArrayList;
import java.util.Scanner;

/**
 * Task Management System
 * A console program to add tasks, mark them as completed,
 * remove completed tasks, and display all tasks.
 */
public class Main {

    // Represents a single task (name + status)
    static class Task {
        private String taskName;      // private: hidden from other classes (encapsulation)
        private boolean isCompleted;  // false = incomplete, true = completed

        // Constructor: every new task starts as incomplete
        public Task(String taskName) {
            this.taskName = taskName;
            this.isCompleted = false;
        }

        // Getter: returns the task name
        public String getTaskName() {
            return taskName;
        }

        // Getter: returns the task status
        public boolean isCompleted() {
            return isCompleted;
        }

        // Marks the task as completed
        public void markAsCompleted() {
            this.isCompleted = true;
        }

        // Marks the task as incomplete
        public void markAsIncomplete() {
            this.isCompleted = false;
        }
    }

    // Holds the list of tasks and the operations on it
    static class TaskManager {
        // Dynamic list that stores all tasks
        private static ArrayList<Task> tasks = new ArrayList<>();

        // Reads a task name from the user and adds it to the list
        public static void addTask(Scanner input) {
            System.out.print("Enter the name of the task you want to add: ");
            String taskName = input.nextLine();
            Task newTask = new Task(taskName);
            tasks.add(newTask);
            System.out.println("Task added successfully!");
        }

        // Finds a task by name (ignoring upper/lower case) and marks it completed
        public static void markTaskAsCompleted(Scanner input) {
            System.out.print("Enter the name of the task to mark as completed: ");
            String taskName = input.nextLine();

            for (Task task : tasks) {
                if (task.getTaskName().equalsIgnoreCase(taskName)) {
                    task.markAsCompleted();
                    System.out.println("Task '" + taskName + "' marked as completed.");
                    return; // stop after finding the task
                }
            }
            // Reached only if no task matched
            System.out.println("Task '" + taskName + "' not found.");
        }

        // Deletes every task whose status is completed
        public static void removeCompletedTask() {
            tasks.removeIf(task -> task.isCompleted());
            System.out.println("Completed tasks removed.");
        }

        // Prints all tasks in a table with their status
        public static void displayTasks() {
            if (tasks.isEmpty()) {
                System.out.println("No tasks to display.");
                return;
            }

            System.out.println("=========================================");
            System.out.printf("| %-30s | %-12s |\n", "Task Name", "Status");
            System.out.println("=========================================");

            for (Task task : tasks) {
                // Ternary operator: condition ? valueIfTrue : valueIfFalse
                String status = task.isCompleted() ? "Completed" : "Incomplete";
                System.out.printf("| %-30s | %-12s |\n", task.getTaskName(), status);
            }
            System.out.println("=========================================");
        }
    }

    // Program entry point: shows the menu repeatedly until the user exits
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n=========================");
            System.out.println("Welcome to Task Manager!");
            System.out.println("1. Add a new task");
            System.out.println("2. Mark a task as completed");
            System.out.println("3. Remove completed tasks");
            System.out.println("4. Display all tasks");
            System.out.println("5. Exit");
            System.out.println("=========================");

            System.out.print("Please enter your choice (1-5): ");
            int choice = input.nextInt();
            input.nextLine(); // clears the leftover newline after nextInt()

            switch (choice) {
                case 1:
                    TaskManager.addTask(input);
                    break;
                case 2:
                    TaskManager.markTaskAsCompleted(input);
                    break;
                case 3:
                    TaskManager.removeCompletedTask();
                    break;
                case 4:
                    TaskManager.displayTasks();
                    break;
                case 5:
                    input.close();
                    return; // ends the program
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.");
            }
        }
    }
}
