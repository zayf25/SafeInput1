import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lab 11: ListMaker
 *
 * Menu:
 *  A – Add an item to the list (appends to end)
 *  D – Delete an item from the list (by number)
 *  I – Insert an item into the list (by position)
 *  P – Print the list
 *  Q – Quit (with Y/N confirmation)
 */
public class ListMaker {
    private static final String MENU = "\n[A]dd  [D]elete  [I]nsert  [P]rint  [Q]uit";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        boolean done = false;
        while (!done) {
            clearScreenLike();
            displayList(list);
            System.out.println(MENU);

            String choice = SafeInput.getRegExString(in, "\nEnter choice:", "[AaDdIiPpQq]").toUpperCase();

            switch (choice) {
                case "A":
                    doAdd(in, list);
                    break;
                case "D":
                    doDelete(in, list);
                    break;
                case "I":
                    doInsert(in, list);
                    break;
                case "P":
                    pause(in, "(Printed above) Press Enter to continue ");
                    break;
                case "Q":
                    if (SafeInput.getYNConfirm(in, "Are you sure you want to quit?")) {
                        done = true;
                    }
                    break;
            }
        }

        System.out.println("\nGoodbye!");
        in.close();
    }

    private static void doAdd(Scanner in, ArrayList<String> list) {
        String item = SafeInput.getNonZeroLenString(in, "Enter item to add:");
        list.add(item);
    }

    private static void doDelete(Scanner in, ArrayList<String> list) {
        if (list.isEmpty()) {
            pause(in, "List is empty. Press Enter to continue ");
            return;
        }

        int choice = SafeInput.getRangedInt(in,
                "Enter item number to delete (1-" + list.size() + "):", 1, list.size());

        String removed = list.remove(choice - 1);
        System.out.println("Removed: " + removed);
        pause(in, "Press Enter to continue...");
    }

    private static void doInsert(Scanner in, ArrayList<String> list) {
        int maxPos = list.size() + 1;
        int pos = SafeInput.getRangedInt(in,
                "Enter insert position (1-" + maxPos + "):", 1, maxPos);

        String item = SafeInput.getNonZeroLenString(in, "Enter item to insert:");
        list.add(pos - 1, item);
    }

    private static void displayList(ArrayList<String> list) {
        System.out.println("==================== Current List ====================");
        if (list.isEmpty()) {
            System.out.println("[EMPTY]");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("%2d) %s%n", i + 1, list.get(i));
            }
        }
        System.out.println("======================================================");
    }

    private static void pause(Scanner in, String prompt) {
        System.out.print(prompt);
        in.nextLine();
    }

    private static void clearScreenLike() {
        System.out.print("\n\n\n\n\n");
    }
}
