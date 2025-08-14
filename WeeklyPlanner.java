import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WeeklyPlanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Weekly Planner!");

        int numTasks = 0;
        while (numTasks < 3 || numTasks > 7) {
            System.out.print("How many tasks do you want to add this week? (3-7): ");
            if (scanner.hasNextInt()) {
                numTasks = scanner.nextInt();
                scanner.nextLine(); // clear buffer
            } else {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }

        String[] validDays = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
        ArrayList<Task> tasks = new ArrayList<>();

        for (int i = 0; i < numTasks; i++) {
            System.out.println("\nTask #" + (i + 1));

            // Get day
            String dayInput = "";
            boolean validDay = false;
            while (!validDay) {
                System.out.print("Day of the week: ");
                dayInput = scanner.nextLine().toLowerCase();
                for (String d : validDays) {
                    if (dayInput.equals(d)) {
                        validDay = true;
                        break;
                    }
                }
                if (!validDay) System.out.println("Invalid day. Try again.");
            }

            // Get task name
            System.out.print("Task name: ");
            String nameInput = scanner.nextLine().trim();

            // Get priority
            String priorityInput = "";
            boolean validPriority = false;
            while (!validPriority) {
                System.out.print("Priority (High, Medium, Low): ");
                priorityInput = scanner.nextLine().toLowerCase();
                if (priorityInput.equals("high") || priorityInput.equals("medium") || priorityInput.equals("low")) {
                    validPriority = true;
                } else {
                    System.out.println("Invalid priority. Try again.");
                }
            }

            tasks.add(new Task(nameInput, dayInput, priorityInput));
        }

        // Create Markdown file
        try {
            FileWriter writer = new FileWriter("WeeklyPlanner.md");
            writer.write("# Weekly Planner\n\n");

            for (String day : validDays) {
                boolean dayHasTasks = false;
                for (Task t : tasks) {
                    if (t.day.equals(day)) {
                        if (!dayHasTasks) {
                            writer.write("## " + capitalize(day) + "\n");
                            dayHasTasks = true;
                        }
                        writer.write("- " + t.name + " " + getIcon(t.name) + " _(Priority: " + capitalize(t.priority) + ")_\n");
                    }
                }
                if (dayHasTasks) writer.write("\n");
            }

            writer.close();
            System.out.println("\nFile 'WeeklyPlanner.md' created successfully!");
        } catch (IOException e) {
            System.out.println("Error creating the file.");
            e.printStackTrace();
        }

        scanner.close();
    }

    private static String capitalize(String word) {
        if (word == null || word.length() == 0) return word;
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    private static String getIcon(String taskName) {
        taskName = taskName.toLowerCase();
        if (taskName.contains("wash")) return "💧";
        if (taskName.contains("eat") || taskName.contains("cook")) return "🍽️";
        if (taskName.contains("dog")) return "🐶";
        if (taskName.contains("run") || taskName.contains("gym") || taskName.contains("exercise")) return "🏃";
        if (taskName.contains("study") || taskName.contains("read") || taskName.contains("java")) return "📚";
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
        return ""; 
    }
}