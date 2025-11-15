import java.util.*;

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
    static PriorityQueue<GroceryItem> lowStockQueue = new PriorityQueue<>(Comparator.comparingInt(i -> i.quantity));
    static HashSet<String> uniqueCategories = new HashSet<>();
    static HashMap<String, GroceryItem> itemMap = new HashMap<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        
         int choice;

        do {
            System.out.println("\n===== GROCERY INVENTORY TRACKER =====");
            System.out.println("[1] Add Item");
            System.out.println("[2] View Inventory");
            System.out.println("[3] Update Item");
            System.out.println("[4] Delete Item");
            System.out.println("[5] View Activity Logs (LinkedList)");
            System.out.println("[6] View Recently Added Items (Stack)");
            System.out.println("[7] View Low Stock Items (Priority Queue)");
            System.out.println("[8] View Categories (HashSet)");
            System.out.println("[9] Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: addItem(); break;
                case 2: viewInventory(); break;
                case 3: updateItem(); break;
                case 4: deleteItem(); break;
                case 5: viewLogs(); break;
                case 6: viewRecentlyAdded(); break;
                case 7: viewLowStock(); break;
                case 8: viewCategories(); break;
                case 9: System.out.println("Exiting system..."); break;
                default: System.out.println("Invalid choice!");
            }

        } while (choice != 9);
    }

    
    public static void addItem() {
        sc.nextLine(); 
        System.out.print("Enter item name: ");
        String name = sc.nextLine();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        GroceryItem item = new GroceryItem(name, qty, price);

        
        inventory.add(item);
        recentlyAdded.push(item);
        lowStockQueue.add(item);
        itemMap.put(name, item);

        // Category example: First letter grouping
        uniqueCategories.add(name.substring(0,1).toUpperCase());

        activityLogs.add("Added: " + name);

        System.out.println("Item added successfully!");
    }

   
    public static void viewInventory() {
        System.out.println("\n----- INVENTORY LIST -----");

        if (inventory.isEmpty()) {
            System.out.println("No items in inventory.");
            return;
        }

        for (GroceryItem item : inventory) {
            System.out.println(item);
        }
    }

    
    public static void updateItem() {
        sc.nextLine();
        System.out.print("Enter item name to update: ");
        String name = sc.nextLine();

        if (!itemMap.containsKey(name)) {
            System.out.println("Item not found!");
            return;
        }

        GroceryItem item = itemMap.get(name);

        System.out.print("New Quantity: ");
        item.quantity = sc.nextInt();

        System.out.print("New Price: ");
        item.price = sc.nextDouble();

        activityLogs.add("Updated: " + name);

        System.out.println("Item updated successfully!");
    }

    
    public static void deleteItem() {
        sc.nextLine();
        System.out.print("Enter item name to delete: ");
        String name = sc.nextLine();

        if (!itemMap.containsKey(name)) {
            System.out.println("Item not found!");
            return;
        }

        GroceryItem item = itemMap.get(name);

        inventory.remove(item);
        lowStockQueue.remove(item);
        recentlyAdded.remove(item);
        itemMap.remove(name);

        activityLogs.add("Deleted: " + name);

        System.out.println("Item deleted successfully!");
    }

    
    public static void viewLogs() {
        System.out.println("\nActivity Logs:");
        if (activityLogs.isEmpty()) {
            System.out.println("No logs yet.");
            return;
        }
        activityLogs.forEach(System.out::println);
    }

    
    public static void viewRecentlyAdded() {
        System.out.println("\nRecently Added Items (Stack Top → Bottom):");
        if (recentlyAdded.isEmpty()) {
            System.out.println("No recently added items.");
            return;
        }
        recentlyAdded.forEach(System.out::println);
    }

    
    public static void viewLowStock() {
        System.out.println("\nLow Stock Items (Lowest Qty First):");
        if (lowStockQueue.isEmpty()) {
            System.out.println("No items.");
            return;
        }
        lowStockQueue.forEach(System.out::println);
    }

   
    public static void viewCategories() {
        System.out.println("\nItem Categories (First Letter Grouping):");
        if (uniqueCategories.isEmpty()) {
            System.out.println("No categories.");
            return;
        }
        uniqueCategories.forEach(System.out::println);
    }
    
}
