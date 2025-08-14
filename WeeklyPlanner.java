
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WeeklyPlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Weekly Planner!");

        // Ask for number of tasks (3-7)
        int numTasks = 0;
        while (numTasks < 3 || numTasks > 7) {
            System.out.print("How many tasks do you want to add this week? (3-7): ");
            if (scanner.hasNextInt()) {
                numTasks = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
            } else {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear buffer
            }
        }

        // Arrays to store tasks
        String[][] taskNames = new String[numTasks][];   // Sub-tasks
        String[][] taskPriorities = new String[numTasks][]; // Priorities for sub-tasks
        String[] days = new String[numTasks];

        // Valid days in English
        String[] validDays = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};

        // Ask user for each task details
        for (int i = 0; i < numTasks; i++) {
            System.out.println("\nTask #" + (i + 1));

            // Ask for day and validate
            String dayInput = "";
            boolean validDay = false;
            while (!validDay) {
                System.out.print("Day of the week (Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday): ");
                dayInput = scanner.nextLine().toLowerCase();
                for (String day : validDays) {
                    if (dayInput.equals(day)) {
                        validDay = true;
                        break;
                    }
                }
                if (!validDay) {
                    System.out.println("Invalid day. Please enter a correct day name.");
                }
            }
            days[i] = dayInput;

            // Ask for task name(s) (may have multiple sub-tasks separated by commas)
            System.out.print("Task name(s) (separate multiple tasks with commas): ");
            String inputTasks = scanner.nextLine();
            String[] subTasks = inputTasks.split(",");

            // Array to store priorities for sub-tasks
            String[] subPriorities = new String[subTasks.length];

            // Ask priority for each sub-task
            for (int j = 0; j < subTasks.length; j++) {
                String sub = subTasks[j].trim();
                boolean validPriority = false;
                while (!validPriority) {
                    System.out.print("Priority for '" + sub + "' (High, Medium, Low): ");
                    String priorityInput = scanner.nextLine().toLowerCase();
                    if (priorityInput.equals("high") || priorityInput.equals("medium") || priorityInput.equals("low")) {
                        subPriorities[j] = priorityInput;
                        validPriority = true;
                    } else {
                        System.out.println("Invalid priority. Please enter High, Medium, or Low.");
                    }
                }
            }

            // Sort sub-tasks by priority (High -> Medium -> Low)
            for (int k = 0; k < subTasks.length - 1; k++) {
                for (int l = k + 1; l < subTasks.length; l++) {
                    if (comparePriority(subPriorities[k], subPriorities[l]) > 0) {
                        // Swap priorities
                        String tempP = subPriorities[k];
                        subPriorities[k] = subPriorities[l];
                        subPriorities[l] = tempP;
                        // Swap sub-tasks
                        String tempS = subTasks[k];
                        subTasks[k] = subTasks[l];
                        subTasks[l] = tempS;
                    }
                }
            }

            taskNames[i] = subTasks;
            taskPriorities[i] = subPriorities;
        }

        // Create Markdown file
        try {
            FileWriter writer = new FileWriter("WeeklyPlanner.md");
            writer.write("# Weekly Planner\n\n");

            // Order days and write tasks
            for (String day : validDays) {
                boolean dayHasTasks = false;
                for (int i = 0; i < numTasks; i++) {
                    if (days[i].equals(day)) {
                        if (!dayHasTasks) {
                            writer.write("## " + capitalize(day) + "\n"); // Day header
                            dayHasTasks = true;
                        }
                        for (int j = 0; j < taskNames[i].length; j++) {
                            String icon = getIcon(taskNames[i][j]); // Get icon based on keywords
                            writer.write("- " + taskNames[i][j].trim() + " " + icon + " _(Priority: " + capitalize(taskPriorities[i][j]) + ")_\n");
                        }
                    }
                }
                if (dayHasTasks) writer.write("\n"); // Extra line after tasks of the day
            }

            writer.close();
            System.out.println("\nFile 'WeeklyPlanner.md' created successfully, ordered by day of the week!");
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

        scanner.close();
    }

    // Capitalize first letter
    private static String capitalize(String word) {
        if (word == null || word.length() == 0) return word;
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    // Compare priorities High->Medium->Low
    private static int comparePriority(String p1, String p2) {
        return priorityValue(p1) - priorityValue(p2);
    }

    private static int priorityValue(String p) {
        switch(p.toLowerCase()) {
            case "high": return 1;
            case "medium": return 2;
            case "low": return 3;
            default: return 4;
        }
    }

    // Assign icons.
private static String getIcon(String taskName) {
    taskName = taskName.toLowerCase();

    if (taskName.contains("wash")) return "💧";
    if (taskName.contains("eat") || taskName.contains("cook")) return "🍽️";
    if (taskName.contains("dog")) return "🐶";
    if (taskName.contains("run") || taskName.contains("gym") || taskName.contains("exercise")) return "🏃";
    if (taskName.contains("study") || taskName.contains("read") || taskName.contains("book") || taskName.contains("java")) return "📚";
    if (taskName.contains("car") || taskName.contains("drive")) return "🚗";
    if (taskName.contains("shop") || taskName.contains("buy") || taskName.contains("shopping")) return "🛒";
    if (taskName.contains("clean")) return "🧹";
    if (taskName.contains("water plants") || taskName.contains("plant")) return "🪴";
    if (taskName.contains("water")) return "💦";
    if (taskName.contains("call") || taskName.contains("phone")) return "📞";
    if (taskName.contains("sleep") || taskName.contains("nap")) return "🛌";
    if (taskName.contains("music") || taskName.contains("listen")) return "🎵";
    if (taskName.contains("email")) return "📧";
    if (taskName.contains("meeting")) return "📅";
    if (taskName.contains("birthday")) return "🎂";
    if (taskName.contains("gift")) return "🎁";
    if (taskName.contains("movie")) return "🎬";
    if (taskName.contains("dance")) return "💃";
    if (taskName.contains("travel") || taskName.contains("flight")) return "✈️";
    if (taskName.contains("train")) return "🚆";
    if (taskName.contains("bus")) return "🚌";
    if (taskName.contains("walk")) return "🚶";
    if (taskName.contains("swim")) return "🏊";
    if (taskName.contains("doctor")) return "🩺";
    if (taskName.contains("hospital")) return "🏥";
    if (taskName.contains("coffee")) return "☕";
    if (taskName.contains("tea")) return "🍵";
    if (taskName.contains("breakfast")) return "🥞";
    if (taskName.contains("lunch")) return "🥗";
    if (taskName.contains("dinner")) return "🍽️";
    if (taskName.contains("party")) return "🎉";
    if (taskName.contains("homework")) return "📝";
    if (taskName.contains("exam") || taskName.contains("test")) return "🧪";
    if (taskName.contains("write")) return "✍️";
    if (taskName.contains("paint")) return "🎨";    
        return ""; // Default: no icon
    }
}