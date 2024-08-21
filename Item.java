public abstract class Item {
    // Instance variables
    private String itemName;
    private double itemPrice;
    private static int numOfItems;
    
    // Default constructor
    Item() {}
    
    // Specific constructor, requring the item's name and price
    Item(String itemName, double itemPrice) {
        setItemName(itemName);
        setItemPrice(itemPrice);

        // Increments the number of items when created
        numOfItems++;
    }

    // Accessors
    public String getItemName() { return this.itemName; }
    public double getItemPrice() { return this.itemPrice; }
    public static int getNumOfItems() { return numOfItems; }

    // Mutators
    public void setItemName(String itemName) {
        if (itemName == null || itemName.isEmpty()) {
            throw new IllegalArgumentException("Item name must be provided.");
        }
        this.itemName = itemName;
    }

    public void setItemPrice(double itemPrice) {
        if (itemPrice < 0) {
            throw new IllegalArgumentException("Item price cannot be less than 0.");
        }
        this.itemPrice = itemPrice;
    }


    // Special Purpose Methods

    // Decrements the total number of items when an item is removed
    public static void decrementNumOfItems() {
        numOfItems--;
    }

    // Method that will be overridden by subclasses
    public String displayForCatalog() {
        return "";
    }

    // Displays the item's name and price, followed by other information specified in the subclasses
    public String toString() {
        return "Name: " + this.getItemName() + "\n" +
        "Price: $" + String.format("%.2f", this.getItemPrice());
    }
}
