import java.util.*;
import javax.swing.JOptionPane;

class GroceryItem {
    String name;
    int quantity;
    double price;

    GroceryItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String toString() {
        return name + " | Qty: " + quantity + " | Price: " + price;
    }
}

public class MINI_PROJECT_IN_DATASTRUCTURE {

    static ArrayList<GroceryItem> inventory = new ArrayList<>();
    static LinkedList<String> activityLogs = new LinkedList<>();
    static Stack<GroceryItem> recentlyAdded = new Stack<>();
    static PriorityQueue<GroceryItem> lowStockQueue =
            new PriorityQueue<>(Comparator.comparingInt(i -> i.quantity));
    static HashSet<String> uniqueCategories = new HashSet<>();
    static HashMap<String, GroceryItem> itemMap = new HashMap<>();

    public static void main(String[] args) {

        String menu = """
                ===== GROCERY INVENTORY TRACKER =====
                [1] Add Item
                [2] View Inventory
                [3] Update Item
                [4] Delete Item
                [5] View Activity Logs
                [6] View Recently Added Items
                [7] View Low Stock Items
                [8] View Categories
                [9] Exit
                """;

        int choice;

        do {
            String input = JOptionPane.showInputDialog(menu + "\nChoose an option:");
            if (input == null) break;

            try {
                choice = Integer.parseInt(input);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid input!");
                continue;
            }

            switch (choice) {
                case 1 -> addItem();
                case 2 -> viewInventory();
                case 3 -> updateItem();
                case 4 -> deleteItem();
                case 5 -> viewLogs();
                case 6 -> viewRecentlyAdded();
                case 7 -> viewLowStock();
                case 8 -> viewCategories();
                case 9 -> JOptionPane.showMessageDialog(null, "Exiting system...");
                default -> JOptionPane.showMessageDialog(null, "Invalid choice!");
            }

        } while (true);
    }

    public static void addItem() {
        String name = JOptionPane.showInputDialog("Enter item name:");
        if (name == null) return;

        int qty = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));

        GroceryItem item = new GroceryItem(name, qty, price);

        inventory.add(item);
        recentlyAdded.push(item);
        lowStockQueue.add(item);
        itemMap.put(name, item);
        uniqueCategories.add(name.substring(0,1).toUpperCase());
        activityLogs.add("Added: " + name);

        JOptionPane.showMessageDialog(null, "Item added successfully!");
    }

    public static void viewInventory() {
        if (inventory.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No items in inventory.");
            return;
        }

        StringBuilder sb = new StringBuilder("----- INVENTORY LIST -----\n");
        for (GroceryItem item : inventory) sb.append(item).append("\n");

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void updateItem() {
        String name = JOptionPane.showInputDialog("Enter item name to update:");
        if (name == null) return;

        if (!itemMap.containsKey(name)) {
            JOptionPane.showMessageDialog(null, "Item not found!");
            return;
        }

        GroceryItem item = itemMap.get(name);

        int newQty = Integer.parseInt(JOptionPane.showInputDialog("New Quantity:"));
        double newPrice = Double.parseDouble(JOptionPane.showInputDialog("New Price:"));

        item.quantity = newQty;
        item.price = newPrice;

        activityLogs.add("Updated: " + name);

        JOptionPane.showMessageDialog(null, "Item updated successfully!");
    }

    public static void deleteItem() {
        String name = JOptionPane.showInputDialog("Enter item name to delete:");
        if (name == null) return;

        if (!itemMap.containsKey(name)) {
            JOptionPane.showMessageDialog(null, "Item not found!");
            return;
        }

        GroceryItem item = itemMap.get(name);

        inventory.remove(item);
        lowStockQueue.remove(item);
        recentlyAdded.remove(item);
        itemMap.remove(name);

        activityLogs.add("Deleted: " + name);

        JOptionPane.showMessageDialog(null, "Item deleted successfully!");
    }

    public static void viewLogs() {
        if (activityLogs.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No logs yet.");
            return;
        }

        StringBuilder sb = new StringBuilder("Activity Logs:\n");
        activityLogs.forEach(log -> sb.append(log).append("\n"));

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void viewRecentlyAdded() {
        if (recentlyAdded.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No recently added items.");
            return;
        }

        StringBuilder sb = new StringBuilder("Recently Added Items:\n");
        recentlyAdded.forEach(i -> sb.append(i).append("\n"));

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void viewLowStock() {
        if (lowStockQueue.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No low stock items.");
            return;
        }

        StringBuilder sb = new StringBuilder("Low Stock Items:\n");
        lowStockQueue.forEach(i -> sb.append(i).append("\n"));

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public static void viewCategories() {
        if (uniqueCategories.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No categories.");
            return;
        }

        StringBuilder sb = new StringBuilder("Categories:\n");
        uniqueCategories.forEach(c -> sb.append(c).append("\n"));

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
